package ar.edu.unlu.brisca.disenio.modelo.interfacee;

import ar.edu.unlu.brisca.disenio.modelo.Carta;

import java.util.List;

public interface GrupoCartas {
    void agregarCarta(Carta carta) throws Exception;
    void quitarCarta(Carta carta);
    List<Carta> obtenerCartas();
}
