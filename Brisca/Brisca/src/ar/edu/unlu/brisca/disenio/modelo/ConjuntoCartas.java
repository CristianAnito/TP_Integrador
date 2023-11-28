package ar.edu.unlu.brisca.disenio.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class ConjuntoCartas {
    private List<Carta> cartaList;
    public ConjuntoCartas(){
        cartaList = new ArrayList<>();
    }
    public void agregarCarta(Carta carta) {
        cartaList.add(carta);
    }
    public void quitarCarta(Carta carta){
        cartaList.remove(carta);
    }
    public Carta obtenerCarta(Integer posicion){
        return cartaList.get(posicion);
    }
    public List<Carta> getCartas(){
        return cartaList;
    }
    public boolean esVacia() {
        return getCartas().isEmpty();
    }
}
