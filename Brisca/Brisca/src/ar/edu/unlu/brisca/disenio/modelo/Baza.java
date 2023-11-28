package ar.edu.unlu.brisca.disenio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Baza{
    private List<Jugada> jugadaList;
    public Baza() {
        jugadaList = new ArrayList<>();
    }

    public void agregarJugada(Jugada jugada) {
        jugadaList.add(jugada);
    }

    public int getNumeroJugadas() {
        return jugadaList.size();
    }

    public boolean contieneJugada(Jugada jugada) {
        return jugadaList.contains(jugada);
    }

    public Jugada getJugada(int posicion) throws Exception {
        if (posicion > 0 && posicion <= jugadaList.size())
            return jugadaList.get(posicion - 1);
        else throw new Exception("Posicion fuera de rango...");
    }

    public double limpiarBaza() {
        jugadaList.clear();
        return 0;
    }

    public List<Jugada> getJugadaList() {
        return jugadaList;
    }
}
