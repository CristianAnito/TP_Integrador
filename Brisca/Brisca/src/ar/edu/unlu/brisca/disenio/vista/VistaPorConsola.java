package ar.edu.unlu.brisca.disenio.vista;

import ar.edu.unlu.brisca.disenio.controlador.Controlador;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.Equipo;
import ar.edu.unlu.brisca.disenio.modelo.Jugador;
import ar.edu.unlu.brisca.disenio.modelo.Mano;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class VistaPorConsola implements VistaPorInterfaz{
    private Scanner entrada;
    private Controlador controlador;
    public VistaPorConsola() {
        this.entrada = new Scanner(System.in);
    }
    public int mostrarMenu(){
        int opcion = -1;
        while (opcion < 0 || opcion > 4){
            System.out.println("------------------------------------------------------");
            System.out.println("--                      BRISCA                      --");
            System.out.println("------------------------------------------------------");
            System.out.println();
            System.out.println("------------------------------------------------------");
            System.out.println("--              Menu de Configuracion               --");
            System.out.println("------------------------------------------------------");
            System.out.println("--                     Opciones                     --");
            System.out.println("------------------------------------------------------");
            System.out.println("--1. Establecer la cantidad de rondas a jugar       --");
            System.out.println("--2. Agregar Equipo (1 a 3 integrantes)             --");
            System.out.println("--3. Mostrar lista de jugadores                     --");
            System.out.println("--4. Comenzar partida                               --");
            System.out.println("--5. Top 3                                          --");
            System.out.println("------------------------------------------------------");
            System.out.println("--0. Salir del juego                                --");
            System.out.println("------------------------------------------------------");
            opcion = entrada.nextInt();
        }
        return opcion;
    }
    @Override
    public void comenzarJuego() {
        int opcion = 1;
        while (opcion != 0){
            opcion = mostrarMenu();
            switch (opcion){
                case 1:
                    controlador.asignarRondasMaximas(consultarRondasMaximas());
                    System.out.println("--Rondas maximas establecidas");
                    esperarEnter();
                    break;
                case 2:
                    controlador.agregarEquipo(new Equipo(controlador.armarEquipo()));
                    esperarEnter();
                    break;
                case 3:
                    mostrarJugadores(controlador.getListaEquipos());
                    esperarEnter();
                    break;
                case 4:
                    if (!controlador.getMisEquipos().isEmpty() && controlador.getMisEquipos().size() > 1)
                        controlador.jugar();
                    else System.out.println("Debe agregar mas de un equipo al menos...");
                    esperarEnter();
                    break;
                case 5:
                    tablaDeJugadoresGanadores();
                    esperarEnter();
                    break;
            }
        }
    }
    public void mostrarListaJugadores(List<Equipo> misEquipos) {
        for (Equipo equipo : misEquipos){
            for (Jugador jugador : equipo.getEquipo()){
                mostrarInformacionJugador(jugador);
            }
        }
    }

    public void mostrarInformacionJugador(Jugador jugador) {
        System.out.println("--Cartas del jugador " + jugador.getNombre());
        System.out.println(mostrarCartas(jugador.getMiMano()));
        System.out.println("------------------------------------------------------");
    }
    public String mostrarCartas(Mano miMano){
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (Carta cartas : miMano.getCartas()){
            s.append("\n").append(i).append(". Numero: ").append(cartas.getNumero().getValorNumero()).append(" Palo: ").
                    append(cartas.getPalo());
            i++;
        }
        return s.toString();
    }

    public int solicitarSeleccionDeCarta() {
        int opcion = -1;
        while (opcion < 1 || opcion > 3) {
            System.out.println("-- Escoje una carta...     ");
            opcion = entrada.nextInt();
        }
        return opcion;
    }

    private int consultarRondasMaximas(){
        int puntos = -1;
        while (puntos < 0 || puntos > 40){
            System.out.println("------------------------------------------------------");
            System.out.println("--             Establecer Rondas Maximas            --");
            System.out.println("------------------------------------------------------");
            System.out.print("-- Rondas Maximas: ");
            puntos = entrada.nextInt();
        }
        return puntos;
    }
    private void mostrarJugadores(List<Equipo> misEquipos) {
        System.out.println("------------------------------------------------------");
        System.out.println("--                Lista de jugadores                --");
        System.out.println("------------------------------------------------------");
        for (Equipo equipos : misEquipos){
            for (Jugador jugador : equipos.getEquipo()){
                System.out.printf("Nombre: %s \t Puntaje: %d \n",jugador.getNombre(), jugador.getPuntuacion());
            }
        }
    }
    public int solicitarCantidadJugadores(){
        int cantJugadores = -1;
        while (cantJugadores < 1 || cantJugadores > 3){
            System.out.println("-----------------------------------------------------------");
            System.out.println("--               Agregando equipo (jugador)              --");
            System.out.println("-----------------------------------------------------------");
            System.out.println("--Ingrese la cantidad de jugadores para el equipo (1 a 3): ");
            // Leer la entrada como String
            String input = entrada.next();

            try {
                // Intentar convertir la entrada a un entero
                cantJugadores = Integer.parseInt(input);

                // Verificar que esté en el rango deseado
                if (cantJugadores < 1 || cantJugadores > 3) {
                    System.out.println("--Ingrese un número válido en el rango especificado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("--Ingrese un número válido.");
            }
        }

        return cantJugadores;
    }

    public String agregarJugador() {
        Scanner sc = new Scanner(System.in);
        String nombre = "";
        while (nombre.isEmpty()){
            System.out.println("-----------------------------------------------------------");
            System.out.println("--Agregando jugador...                                   --");
            System.out.println("--Nombre:");
            nombre = sc.nextLine();
        }
        return nombre;
    }
    public void limpiarPantalla() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }
    public void esperarEnter(){
        System.out.println("Presione ENTER para continuar...");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();
        limpiarPantalla();
    }
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    public void imprimirCartel(String s) {
        System.out.println();
        System.out.println(s);
    }

    private void presentarResultadosFinales(Equipo equipoGanador, List<Equipo> misEquipos) {
        controlador.setGanadoresList(equipoGanador);

        List<Jugador> todosLosJugadores = new ArrayList<>();

        for (Equipo equipo : misEquipos) {
            todosLosJugadores.addAll(equipo.getEquipo());
        }

        //Ordeno los jugadores por la puntuacion
        todosLosJugadores.sort((jugador1, jugador2) -> Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion()));

        System.out.println("------------------------------------------------------");
        System.out.println("--             Ganador de la Partida                --");
        System.out.println("------------------------------------------------------");
        for (Jugador jugador : equipoGanador.getEquipo()){
            System.out.println("--" + jugador.getNombre());
        }
        System.out.println("------------------------------------------------------");
        System.out.println("--Resultados Finales:                               --");
        System.out.println("------------------------------------------------------");

        for (Jugador jugador : todosLosJugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntuacion() + " puntos");
        }
    }

    private void tablaDeJugadoresGanadores(){
        List<Equipo> ganadoresList = controlador.getGanadoresList();
        // Utilizando un Comparator
        Comparator<Equipo> comparador = Comparator.comparingInt(Equipo::getPuntuacion).reversed();

        // Ordenar la lista de equipos
        ganadoresList.sort(comparador);

        for (int i = 0; i < 3; i++){
            for (Equipo equipo : ganadoresList){
                System.out.println("Equipo de: ");
                for (Jugador jugador : equipo.getEquipo()){
                    System.out.println(jugador.getNombre());
                }
                System.out.println("Puntuacion: " + equipo.getPuntuacion());
            }
        }
    }

    public void cerrarJuego() {
        Scanner sc = new Scanner(System.in);
        presentarResultadosFinales(controlador.getEquipoGanador(), controlador.getEquiposList());
        System.out.println("------------------------------------------------------");
        System.out.println("--¿Desea jugar otra partida? (Sí/No)                --");
        System.out.println("------------------------------------------------------");
        String respuesta = sc.nextLine().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("sí")) {
            // Iniciar una nueva partida
            comenzarJuego();
        } else {
            // Mostrar un mensaje de despedida
            System.out.println("------------------------------------------------------");
            System.out.println("--      ¡Gracias por jugar! Hasta la próxima.       --");
            System.out.println("------------------------------------------------------");

            // Cerrar el programa
            System.exit(0);
        }
    }
}
