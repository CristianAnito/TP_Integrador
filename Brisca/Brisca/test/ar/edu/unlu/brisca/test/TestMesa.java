package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.*;
import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestMesa {
    Mesa miMesa;
    Baza miBaza;
    Jugada jugada1, jugada2;
    Jugador jugador1, jugador2;
    Carta carta1, carta2, cartaTriunfo;
    EstadoJuego estadoJuego;

    @BeforeEach
    void setUp() {
        miBaza = new Baza();
        jugador1 = new Jugador("Jugador1");
        jugador2 = new Jugador("Jugador2");
        carta1 = new Carta(NumeroCarta.AS, Palo.ORO, ValorCarta.ONCE);
        carta2 = new Carta(NumeroCarta.REY, Palo.COPA, ValorCarta.TRES);
        cartaTriunfo = new Carta(NumeroCarta.TRES, Palo.ORO, ValorCarta.DIEZ);

        jugada1 = new Jugada(jugador1, carta1);
        jugada2 = new Jugada(jugador2, carta2);
        miMesa = new Mesa();
        estadoJuego = new EstadoJuego();
    }

    @Test
    void alInicializarLaMesaDevuelveQueNoHayNingunaBaza(){
        miMesa.inicializar();
        assertEquals(0, miMesa.getBazas());
    }

    @Test
    void alInicializarLaMesaDevuelveQueNoHayNingunaBazaPaso2(){
        Baza miBaza = new Baza();
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        miMesa.agregarBaza(miBaza);
        miMesa.inicializar();
        assertEquals(0, miMesa.getBazas());
    }

    @Test
    void cargarNuevaBazaDevuelveBazaUno(){
        Baza miBaza = new Baza();
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        miMesa.agregarBaza(miBaza);
        assertEquals(1, miMesa.getBazas());
    }

    @Test
    void cargarNuevaBazaConSoloUnaJugadaNoEstaPermitido(){
        Baza miBaza = new Baza();
        miBaza.agregarJugada(jugada2);
        miMesa.agregarBaza(miBaza);
        assertEquals(0, miMesa.getBazas());
    }

    //Cuando se agrega una baza, se deberia calcular quien es el ganador y se le puede consultar a la Mesa.
    @Test
    void cargarNuevaBazaGeneraQueCartaEsLaGanadora(){
        estadoJuego.setCartaTriunfo(cartaTriunfo);
        Baza miBaza = new Baza();
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        miMesa.agregarBaza(miBaza);
        assertEquals(jugador1, miMesa.getGanadorBaza(miBaza));
        assertNotEquals(null, jugador1);
    }

    @Test
    void cargarNuevaBazaConCartasDiferentesAPaloTriunfo(){
        Carta carta11 = new Carta(NumeroCarta.SEIS, Palo.COPA, ValorCarta.CERO);
        Carta carta22 = new Carta(NumeroCarta.REY, Palo.COPA, ValorCarta.TRES);

        jugada1 = new Jugada(jugador1, carta11);
        jugada2 = new Jugada(jugador2, carta22);

        estadoJuego.setCartaTriunfo(cartaTriunfo);
        estadoJuego.setCartaSalida(carta11);
        Baza miBaza = new Baza();
        miBaza.agregarJugada(jugada1);
        miBaza.agregarJugada(jugada2);
        miMesa.agregarBaza(miBaza);
        assertEquals(jugador2, miMesa.getGanadorBaza(miBaza));
        assertNotEquals(null, jugador2);
    }

    @Test
    void PreguntarPorGanadorSinBazasDevuelveNull(){
        assertEquals(null, miMesa.getGanadorBaza(miBaza));
    }

}
