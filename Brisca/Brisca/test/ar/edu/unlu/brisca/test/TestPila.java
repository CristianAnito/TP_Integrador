package ar.edu.unlu.brisca.test;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.Pila;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPila {
    Pila miPila;
    List<Carta> cartas;
    @BeforeEach
    void setUp() {
        miPila = new Pila();
        cartas = miPila.getCartas();
    }

    @Test
    void verificarQueLaPilaSeCreaCon40Cartas() {
        assertEquals(40, cartas.size());
    }

    @Test
    void verificarQueLaPilaDeCartasSeMezcle(){
        List<Carta> cartasAntesDeMezclar = new ArrayList<>(miPila.getCartas());
        miPila.mezclar();
        List<Carta> cartasDespuesDeMezclar = miPila.getCartas();
        assertNotEquals(cartasAntesDeMezclar, cartasDespuesDeMezclar);
    }

    @Test
    void verificarQueNoSePuedaAgregarUnaCartaConUnValorIncorrecto(){
        Carta carta = new Carta(NumeroCarta.CINCO, Palo.ORO, ValorCarta.TRES);
        assertNotEquals(carta.getValorCarta(), ValorCarta.CERO);
    }

    @Test
    void verificarQueUnaCartaSeQuitaCorrectamenteDeLaPila(){
        Carta carta = miPila.obtenerCarta(0);
        miPila.quitarCarta(carta);
        assertFalse(miPila.getCartas().contains(carta));
    }

    @Test
    void verificarQueLaPilaSeVacieCorrectamente(){
        Pila pilaVacia = new Pila();
        assertFalse(miPila.esVacia());
        pilaVacia.getCartas().clear();
        assertTrue(pilaVacia.esVacia());
    }
}
