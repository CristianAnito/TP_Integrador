package ar.edu.unlu.brisca.disenio.observer;

import ar.edu.unlu.brisca.disenio.modelo.enumerados.Evento;

public interface Observable {
    public void notificar(Evento evento);
    public void agregarObservador(Observador observador);
}
