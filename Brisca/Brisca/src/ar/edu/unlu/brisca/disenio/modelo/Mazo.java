package ar.edu.unlu.brisca.disenio.modelo;

import java.util.List;

public class Mazo extends ConjuntoCartas{
    public Mazo() {
        super();
    }

    public void agregarCartas(Baza baza){
        for (Jugada jugada: baza.getJugadaList()){
            Carta carta = jugada.getCarta();
            agregarCarta(carta);
        }
    }

    public boolean contieneCarta(Carta carta) {
        List<Carta> cartas = getCartas();
        for (Carta carta1 : cartas){
            if (carta1 == carta)
                return true;
        }
        return false;
    }
}
