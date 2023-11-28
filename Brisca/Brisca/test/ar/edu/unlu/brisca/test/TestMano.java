package ar.edu.unlu.brisca.test;

import ar.edu.unlu.brisca.disenio.modelo.Carta;
import ar.edu.unlu.brisca.disenio.modelo.Mano;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMano {
    Mano miMano;
    Carta carta1, carta2, carta3, carta4;

    @BeforeEach
    void setUp() {
        miMano = new Mano();
        carta1 = new Carta(NumeroCarta.AS, Palo.ORO, ValorCarta.ONCE);
        carta2 = new Carta(NumeroCarta.REY, Palo.COPA, ValorCarta.TRES);
        carta3 = new Carta(NumeroCarta.CABALLO, Palo.ESPADA, ValorCarta.TRES);
        carta4 = new Carta(NumeroCarta.SEIS, Palo.ORO, ValorCarta.CERO);
    }

    @Test
    void verificarQueUnaCartaSeAgregaAMiManoCorrectamente() throws Exception {
        miMano.agregarCarta(carta1);
        assertEquals(1, miMano.getCartas().size());
    }

    @Test
    void verificarQueUnaBazaSeAgregueCorrectamenteAlMazo(){
        miMano.quitarCarta(carta1);
        assertEquals(0, miMano.getCartas().size());
    }

    @Test
    void verificarQueAlCrearUnMazoSeCreeVacio(){
        Mano mano = new Mano();
        assertTrue(mano.esVacia());
        assertEquals(0, mano.getCartas().size());
    }

    @Test
    void verificarQueSePuedeObtenerUnaCartaPorSuPosicion() throws Exception {
        Mano mano = new Mano();
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);

        assertEquals(carta1, mano.obtenerCarta(0));
        assertEquals(carta2, mano.obtenerCarta(1));
    }

    @Test
    void verificarQueNoSePuedeAgregarMasDeTresCartas () throws Exception {
        miMano.agregarCarta(carta2);
        miMano.agregarCarta(carta3);
        miMano.agregarCarta(carta4);
        assertThrows(Exception.class, () -> miMano.agregarCarta(carta1));
    }

    @Test
    void TresValoresDevuelveCartasCompletas() throws Exception {
        assertTrue(miMano.isCompleta());
    }
}
