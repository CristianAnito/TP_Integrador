package ar.edu.unlu.brisca.disenio.controlador;

import ar.edu.unlu.brisca.disenio.modelo.Equipo;
import ar.edu.unlu.brisca.disenio.modelo.Juego;
import ar.edu.unlu.brisca.disenio.modelo.Jugador;
import ar.edu.unlu.brisca.disenio.modelo.Mesa;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Evento;
import ar.edu.unlu.brisca.disenio.observer.Observable;
import ar.edu.unlu.brisca.disenio.observer.Observador;
import ar.edu.unlu.brisca.disenio.vista.VistaPorConsola;

import java.util.ArrayList;
import java.util.List;

public class Controlador implements Observador {
    private Juego modelo;
    private VistaPorConsola vista;

    //Al controlador le paso el modelo y la vista con quienes estara siendo de intermediario
    public Controlador(Juego modelo, VistaPorConsola vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.agregarObservador(this);
    }

    @Override
    public void actualizar(Evento evento, Observable observado) {
        switch (evento) {
            case EQUIPO_AGREGADO:
                this.vista.imprimirCartel("-Equipo agregado!-");
                break;
            case GANADOR_BAZA:
                this.vista.imprimirCartel("------------------------------------------------------\n" +
                        "--               Ganador de la Ronda                --\n" +
                        "------------------------------------------------------\n" +
                        "-- " + modelo.getGanadorBaza() + " --\n" +
                        "------------------------------------------------------");
                this.vista.esperarEnter();
                break;
            case CARTA_SALIDA_CAMBIADA:
                this.vista.imprimirCartel("--Palo de salida: " + modelo.getMiMesa().getEstadoJuego().getCartaSalida().getPalo());
                break;
            case REPARTIR_CARTAS:
                this.vista.imprimirCartel("------------------------------------------------------\n" +
                        "--              Repartiendo Cartas...               --\n" +
                        "------------------------------------------------------");
                break;
            case CARTA_TRIUNFO_CAMBIADA:
                this.vista.imprimirCartel("--Carta de triunfo: \n" +
                        "Numero: " + modelo.getMiMesa().getEstadoJuego().getCartaTriunfo().getNumero() +
                        "  Palo: " + modelo.getMiMesa().getEstadoJuego().getCartaTriunfo().getPalo());
                this.vista.esperarEnter();
                break;
            case INFORMACION_JUGADOR:
                this.vista.mostrarListaJugadores(modelo.getMisEquipos());
                break;
            case INICIAR_TURNO_JUGADOR:
                this.vista.imprimirCartel("------------------------------------------------------\n" +
                        "--                 Jugando Carta                    --\n" +
                        "------------------------------------------------------\n");
                this.vista.imprimirCartel("--Carta de triunfo: \n" +
                        "Numero: " + modelo.getMiMesa().getEstadoJuego().getCartaTriunfo().getNumero() +
                        "  Palo: " + modelo.getMiMesa().getEstadoJuego().getCartaTriunfo().getPalo() + "\n" +
                        "------------------------------------------------------");
                this.realizarTurnoDeJugador(modelo.getJugadorActual());
                break;
            case CONTINUAR_O_NO:
                this.vista.cerrarJuego();
                break;
            default:
                break;
        }
    }

    private void realizarTurnoDeJugador(Jugador jugadorActual) {
        this.vista.mostrarInformacionJugador(jugadorActual);
        int opcion = this.vista.solicitarSeleccionDeCarta();
        modelo.setOpcion(opcion);
    }

    public void jugar() {
        modelo.jugar();
    }
    public void asignarRondasMaximas(int maxRondas) {
        modelo.setMaxRondas(maxRondas);
    }

    public List<Equipo> getListaEquipos() {
        return modelo.obtenerEquipos();
    }
    public void agregarEquipo(Equipo equipo){
        modelo.agregarEquipoALaLista(equipo);
    }
    public List<Equipo> getMisEquipos() {
        return modelo.getMisEquipos();
    }
    public List<Jugador> armarEquipo() {
        int cantJugadores = vista.solicitarCantidadJugadores();

        // Lógica de validación en el Controlador
        while ((modelo.isJugadoresXequipo() && modelo.getJugadoresAgregados() != cantJugadores) || modelo.getJugadoresTotal() + cantJugadores > 6) {
            System.out.println("-- Error en la cantidad de jugadores. Verifique las restricciones.");
            cantJugadores = vista.solicitarCantidadJugadores();
        }

        // Lógica para actualizar el modelo
        modelo.setJugadoresTotal(cantJugadores);
        modelo.setJugadoresXequipo(true);
        modelo.setJugadoresAgregados(cantJugadores);

        // Lógica para agregar jugadores al equipo en el Controlador
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < cantJugadores; i++) {
            Jugador jugador = new Jugador(vista.agregarJugador());
            jugadores.add(jugador);
        }

        return jugadores;
    }

    public int getJugadoresTotal() {
        return modelo.getJugadoresTotal();
    }

    public Equipo getEquipoGanador() {
        return modelo.determinarGanadorDelJuego();
    }

    public void setGanadoresList(Equipo equipoGanador) {
        modelo.setGanadoresList(equipoGanador);
    }

    public List<Equipo> getEquiposList() {
        return modelo.getMisEquipos();
    }
    public Mesa getMesa(){
        return modelo.getMiMesa();
    }

    public List<Equipo> getGanadoresList() {
        return modelo.getGanadoresList();
    }
}
