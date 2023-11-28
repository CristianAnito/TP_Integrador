package ar.edu.unlu.brisca.disenio.modelo;

import ar.edu.unlu.brisca.disenio.controlador.Controlador;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Evento;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.observer.Observable;
import ar.edu.unlu.brisca.disenio.observer.Observador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Juego implements Observable {
    private List<Equipo> misEquipos;
    private List<Equipo> ganadoresList;
    private Jugador jugadorGanadorRonda;
    private Pila miPila;
    private Mesa miMesa;
    private Baza miBaza;
    private int maxRondas;
    private int jugadoresAgregados;
    private int jugadoresTotal;
    private boolean jugadoresXequipo;
    private boolean esPrimerRonda = true;
    private ArrayList<Observador> observadores;
    private Jugador jugadorActual;
    private int opcion;

    public void jugar()  {
        // Inicio de rondas
        while (!miPila.esVacia() && maxRondas != 0) {
            // Iniciar una nueva ronda
            iniciarNuevaRonda();

            // Realizar turnos de jugadores
            realizarTurnosDeJugadores();

            // Calcular y asignar ganador de la baza
            asignarGanadorDeBaza();

            // Fin de la ronda
            miBaza.getJugadaList().clear();
            esPrimerRonda = false;
            maxRondas--;
        }
        //cerrarJuego();
        this.notificar(Evento.CONTINUAR_O_NO);
    }

    public Equipo determinarGanadorDelJuego() {
        asignarPuntuacionAEquipo();

        Equipo ganador = null;
        int puntuacionMaxima = 0;

        for (Equipo equipo : misEquipos) {
            int puntuacionEquipo = equipo.getPuntuacion();

            if (puntuacionEquipo > puntuacionMaxima) {
                puntuacionMaxima = puntuacionEquipo;
                ganador = equipo;
            }
        }

        return ganador;
    }
    private void asignarPuntuacionAEquipo() {
        asignarPuntuacionAJugador();

        //para cada Equipo, le asigno su puntuacion
        for (Equipo equipo : misEquipos){
            for (Jugador jugador : equipo.getEquipo()){
                equipo.setPuntuacion(jugador.getPuntuacion());
            }
        }
    }
    private void asignarPuntuacionAJugador() {

        //para cada jugador, le asigno su puntuacion
        for (Equipo equipo : misEquipos){
            for (Jugador jugador : equipo.getEquipo()){
                for (Carta carta : jugador.getMiMazo().getCartas()){
                    jugador.setPuntuacion(carta.getValorCarta());
                }
            }
        }
    }

    public void asignarGanadorDeBaza() {
        miMesa.agregarBaza(miBaza);
        jugadorGanadorRonda = miMesa.getGanadorBaza(miBaza);
        jugadorGanadorRonda.getMiMazo().agregarCartas(miBaza);
        this.notificar(Evento.GANADOR_BAZA);
    }

    private void realizarTurnosDeJugadores() {
        int contadorTurno = 1;
        //Jugar las cartas (no estoy jugando a partir del que gano la baza)
        for (Equipo equipo : misEquipos){
            for (Jugador jugador : equipo.getEquipo()){
                jugadorActual = jugador;
                this.notificar(Evento.INICIAR_TURNO_JUGADOR);
                //obtengo la carta jugada
                Carta cartaJugada = jugador.obtenerCartas().get(opcion - 1);
                if (contadorTurno == 1){
                    miMesa.getEstadoJuego().setCartaSalida(cartaJugada);
                    this.notificar(Evento.CARTA_SALIDA_CAMBIADA);
                }

                jugador.quitarCarta(cartaJugada);
                Jugada jugada = new Jugada(jugador, cartaJugada);
                miBaza.agregarJugada(jugada);
                contadorTurno++;
            }
        }
    }

    private void  iniciarNuevaRonda()  {
        this.notificar(Evento.REPARTIR_CARTAS);

        //Si no es la primera ronda, verifico si reemplazo la carta de triunfo por un 7 o 2 del mismo palo (depende de la situacion).
        if (!esPrimerRonda){
            Carta cartaSustituir = new Carta(NumeroCarta.SIETE, miMesa.getEstadoJuego().getCartaTriunfo().getPalo());
            Carta cartaSustituirDos = new Carta(NumeroCarta.DOS, miMesa.getEstadoJuego().getCartaTriunfo().getPalo());

            //Si la carta de triunfo es una de valor, puede ser sustituida por un 7 del mismo palo
            if (miMesa.getEstadoJuego().getCartaTriunfo().esCartaValor()
                    && jugadorGanadorRonda.obtenerCartas().contains(cartaSustituir))
                sustituirCarta(jugadorGanadorRonda, miMesa.getEstadoJuego().getCartaTriunfo(), cartaSustituir);

                //Si la carta triunfo es una sin valor, puede ser sustituida por el 2 del mismo palo
            else if (!miMesa.getEstadoJuego().getCartaTriunfo().esCartaValor()
                    && jugadorGanadorRonda.obtenerCartas().contains(cartaSustituirDos))
                sustituirCarta(jugadorGanadorRonda, miMesa.getEstadoJuego().getCartaTriunfo(), cartaSustituirDos);
        }

        //Repartir las cartas (no estoy repartiendo a partir del que gano la baza)
        for (Equipo equipo : misEquipos){
            for (Jugador jugador : equipo.getEquipo()){
                //Si es la primera ronda reparto tres cartas a cada jugador
                if (esPrimerRonda){
                    for (int i = 0; i < 3; i++){
                        jugador.agregarCarta(miPila.getCartas().remove(i));
                    }

                    //Sino, reparto una carta a cada jugador
                }else jugador.agregarCarta(miPila.getCartas().remove(0));
            }
        }

        //establecer palo de triunfo
        if (esPrimerRonda)
            miMesa.getEstadoJuego().setCartaTriunfo(miPila.getCartas().remove(0));

        this.notificar(Evento.INFORMACION_JUGADOR);
        this.notificar(Evento.CARTA_TRIUNFO_CAMBIADA);
    }

    private void sustituirCarta(Jugador jugadorGanadorBaza, Carta cartaTriunfo, Carta cartaSustituir)  {
        jugadorGanadorBaza.quitarCarta(cartaSustituir);
        jugadorGanadorBaza.agregarCarta(cartaTriunfo);
        miMesa.getEstadoJuego().setCartaTriunfo(cartaSustituir);
    }

    public void limpiarJuego(){
        misEquipos.clear();
        miPila = new Pila();
        miMesa.inicializar();
        maxRondas = 0;
        jugadoresAgregados = 0;
        jugadoresTotal = 0;
        jugadoresXequipo = false;
    }

    public Juego() {
        jugadorGanadorRonda = null;
        misEquipos = new ArrayList<>();
        ganadoresList = new ArrayList<>();
        miBaza = new Baza();
        miPila = new Pila();
        miMesa = new Mesa();
        maxRondas = miPila.getCartas().size();
        jugadoresAgregados = 0;
        jugadoresTotal = 0;
        jugadoresXequipo = false;
        this.observadores = new ArrayList<>();
    }

    public void setMaxRondas(int maxRondas) {
        this.maxRondas = maxRondas;
    }

    // metodos para Observer

    @Override
    public void notificar(Evento evento) {
        for (Observador observador : this.observadores) {
            observador.actualizar(evento, this);
        }
    }

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }

    //Getteres y Setteres
    public List<Equipo> obtenerEquipos() {
        return misEquipos;
    }
    public void agregarEquipoALaLista(Equipo equipo){
        misEquipos.add(equipo);
        this.notificar(Evento.EQUIPO_AGREGADO);
    }

    public int getJugadoresAgregados() {
        return jugadoresAgregados;
    }

    public int getJugadoresTotal() {
        return jugadoresTotal;
    }

    public boolean isJugadoresXequipo() {
        return jugadoresXequipo;
    }

    public void setJugadoresAgregados(int jugadoresAgregados) {
        this.jugadoresAgregados = jugadoresAgregados;
    }

    public void setJugadoresTotal(int jugadoresTotal) {
        this.jugadoresTotal += jugadoresTotal;
    }

    public void setJugadoresXequipo(boolean jugadoresXequipo) {
        this.jugadoresXequipo = jugadoresXequipo;
    }

    public List<Equipo> getMisEquipos() {
        return misEquipos;
    }
    public String getGanadorBaza(){
        return jugadorGanadorRonda.getNombre();
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Mesa getMiMesa() {
        return miMesa;
    }

    public void setGanadoresList(Equipo equipoGanador) {
        ganadoresList.add(equipoGanador);
    }

    public List<Equipo> getGanadoresList() {
        return ganadoresList;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}
