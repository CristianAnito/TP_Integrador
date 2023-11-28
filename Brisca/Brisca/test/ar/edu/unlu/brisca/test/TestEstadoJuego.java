package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.EstadoJuego;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEstadoJuego {
    EstadoJuego estadoJuego;
    Carta nuevaCartaTriunfo, nuevaCartaSalida;
    @BeforeEach
    void setUp() {
        estadoJuego = new EstadoJuego();
        Carta nuevaCartaTriunfo = new Carta(NumeroCarta.SIETE, Palo.ESPADA);
        Carta nuevaCartaSalida = new Carta(NumeroCarta.SIETE, Palo.ORO);
    }

    @Test
    void verificarQueSePuedeCambiarElPaloDeTriunfo() {
        estadoJuego.setCartaTriunfo(nuevaCartaTriunfo);
        assertEquals(nuevaCartaTriunfo, estadoJuego.getCartaTriunfo());
    }

    @Test
    void verificarQueSePuedeCambiarElPaloDeSalida() {
        estadoJuego.setCartaSalida(nuevaCartaSalida);
        assertEquals(nuevaCartaSalida, estadoJuego.getCartaSalida());
    }
}
