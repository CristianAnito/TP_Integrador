package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.Baza;
import ar.edu.unlu.brisca.disenio.modelo.Jugada;
import ar.edu.unlu.brisca.disenio.modelo.Jugador;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.Mazo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMazo {
    Mazo miMazo;
    Jugador jugador1, jugador2;
    Carta carta1, carta2, carta3, carta4;
    Jugada jugada1, jugada2;
    Baza miBaza;

    @BeforeEach
    void setUp() {
        miMazo = new Mazo();
        jugador1 = new Jugador("Jugador1");
        jugador2 = new Jugador("Jugador2");
        carta1 = new Carta(NumeroCarta.AS, Palo.ORO, ValorCarta.ONCE);
        carta2 = new Carta(NumeroCarta.REY, Palo.COPA, ValorCarta.TRES);

        jugada1 = new Jugada(jugador1, carta1);
        jugada2 = new Jugada(jugador2, carta2);
        miBaza = new Baza();
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
    }

    @Test
    void verificarQueUnaCartaEstaEnMiMazo() throws Exception {
        miMazo.agregarCartas(miBaza);
        assertTrue(miMazo.contieneCarta(carta1));
    }

    @Test
    void verificarQueUnaBazaSeAgregueCorrectamenteAlMazo() throws Exception {
        miMazo.agregarCartas(miBaza);
        assertEquals(2, miMazo.getCartas().size());
    }

    @Test
    void verificarQueAlCrearUnMazoSeCreeVacio(){
        Mazo mazo = new Mazo();
        assertTrue(mazo.esVacia());
        assertEquals(0, mazo.getCartas().size());
    }
}
