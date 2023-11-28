package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.Jugada;
import ar.edu.unlu.brisca.disenio.modelo.Jugador;
import ar.edu.unlu.brisca.disenio.modelo.Baza;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBaza {
    Baza miBaza;
    Jugada jugada1, jugada2;
    Jugador jugador1, jugador2;
    Carta carta1, carta2;

    @BeforeEach
    void setUp() {
        miBaza = new Baza();
        jugador1 = new Jugador("Jugador1");
        jugador2 = new Jugador("Jugador2");
        carta1 = new Carta(NumeroCarta.AS, Palo.ORO, ValorCarta.ONCE);
        carta2 = new Carta(NumeroCarta.REY, Palo.COPA, ValorCarta.TRES);

        jugada1 = new Jugada(jugador1, carta1);
        jugada2 = new Jugada(jugador2, carta2);
    }

    @Test
    void verificarQueLasJugadasSeAgreganCorrectamenteALaBaza() {
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        assertEquals(2, miBaza.getNumeroJugadas());
    }

    @Test
    void verificarQueUnaJugadaEstaEnLaListaDeJugadas() {
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        assertTrue(miBaza.contieneJugada(jugada2));
    }

    @Test
    void verificarQueUnaSePuedeAccederAUnaJugada() throws Exception {
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        assertEquals(jugada2, miBaza.getJugada(2));
    }

    @Test
    void asegurarQueLaBazaSeLimpiaLuegoDeTransferirLasCartasAlGanador(){
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        assertEquals(0, miBaza.limpiarBaza());
    }
}
