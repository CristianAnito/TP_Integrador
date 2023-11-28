package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCarta {
    Carta carta;

    @BeforeEach
    void setUp() {
        carta = new Carta(NumeroCarta.SEIS, Palo.ORO);
    }

    @Test
    void verificarQueLaCartaSeCreeCorrectamente() {
        assertEquals(NumeroCarta.SEIS, carta.getNumero());
        assertEquals(Palo.ORO, carta.getPalo());
        assertEquals(0, carta.getValorCarta());
    }

}
