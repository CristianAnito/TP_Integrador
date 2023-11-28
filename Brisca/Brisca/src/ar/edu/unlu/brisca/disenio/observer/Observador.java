package ar.edu.unlu.brisca.disenio.observer;

import ar.edu.unlu.brisca.disenio.modelo.enumerados.Evento;

public interface Observador {
    public void actualizar(Evento evento, Observable observado);
}
