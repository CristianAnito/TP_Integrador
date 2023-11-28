package ar.edu.unlu.brisca.disenio.modelo;

import ar.edu.unlu.brisca.disenio.modelo.interfacee.GrupoCartas;

import java.util.List;

public class Jugador implements GrupoCartas {
    private Mano miMano;
    private Mazo miMazo;
    private int puntuacion;
    private String nombre;
    public Jugador(String nombre) {
        miMano = new Mano();
        miMazo = new Mazo();
        puntuacion = 0;
        this.nombre = nombre;
    }

    @Override
    public void agregarCarta(Carta carta)  {
        miMano.agregarCarta(carta);
    }

    @Override
    public void quitarCarta(Carta carta) {
        miMano.quitarCarta(carta);
    }

    @Override
    public List<Carta> obtenerCartas() {
        return miMano.getCartas();
    }

    public Mazo getMiMazo() {
        return miMazo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion += puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Mano getMiMano() {
        return miMano;
    }
}
