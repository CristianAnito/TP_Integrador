package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.Jugada;
import ar.edu.unlu.brisca.disenio.modelo.Jugador;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestJugada {
    Jugada miJugada;
    Jugador jugador, jugador1, jugador2;
    Carta carta, carta1, carta2;
    Jugada jugada1, jugada2;

    @BeforeEach
    void setUp() {
        jugador = new Jugador("Carlos");
        carta = new Carta(NumeroCarta.TRES, Palo.BASTO, ValorCarta.DIEZ);
        miJugada = new Jugada(jugador, carta);
        jugador1 = new Jugador("Jugador1");
        jugador2 = new Jugador("Jugador2");
        carta1 = new Carta(NumeroCarta.AS, Palo.ORO, ValorCarta.ONCE);
        carta2 = new Carta(NumeroCarta.REY, Palo.COPA, ValorCarta.TRES);

        jugada1 = new Jugada(jugador1, carta1);
        jugada2 = new Jugada(jugador2, carta2);
    }

    @Test
    void verificarQueElJugadorEsCarlos() {
        assertEquals(jugador, miJugada.getJugador());
    }

    @Test
    void verificarQueLaCartaJugadaEsEl3DeBasto() {
        assertEquals(carta, miJugada.getCarta());
    }

    @Test
    void verificarQueLasJugadasSePuedenComparar() {
        assertNotEquals(jugada1, jugada2);
    }
}
