package ar.edu.unlu.brisca.disenio.modelo;

public class Jugada {
    private Jugador jugador;
    private Carta carta;
    public Jugada(Jugador jugador, Carta carta) {
        this.carta = carta;
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Carta getCarta() {
        return carta;
    }
}
