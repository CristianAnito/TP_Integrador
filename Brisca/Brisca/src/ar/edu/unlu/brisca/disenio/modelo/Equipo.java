package ar.edu.unlu.brisca.disenio.modelo;

import java.util.List;

public class Equipo {
    private List<Jugador> equipo;
    private int puntuacion;

    public Equipo(List<Jugador> jugadores) {
        equipo = jugadores;
        puntuacion = 0;
    }

    public List<Jugador> getEquipo() {
        return equipo;
    }

    public void agregarJugador(Jugador jugador) {
        equipo.add(jugador);
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion += puntuacion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}
