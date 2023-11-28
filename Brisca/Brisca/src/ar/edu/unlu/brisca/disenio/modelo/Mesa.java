package ar.edu.unlu.brisca.disenio.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private List<Baza> bazas;
    private EstadoJuego estadoJuego;
    public Mesa(){
        bazas = new ArrayList<Baza>();
        estadoJuego = new EstadoJuego();
        this.inicializar();
    }
    public void inicializar() {
        bazas.clear();
        estadoJuego.setCartaTriunfo(null);
        estadoJuego.setCartaSalida(null);
    }

    public int getBazas() {
        return bazas.size();
    }

    public void agregarBaza(Baza miBaza) {
        if (miBaza.getJugadaList().size() > 1)
            bazas.add(miBaza);
    }

    //gana la baza quien juega la carta de triunfo más alta sin importar el palo de salida.
    public Jugador getGanadorBaza(Baza miBaza) {
        Carta mejorCarta = null;
        Jugador jugadorGanador = null;
        Carta paloTriunfo = estadoJuego.getCartaTriunfo();
        Carta paloSalida = estadoJuego.getCartaSalida();

        //recorro la lista de jugadas
        for (Jugada jugada : miBaza.getJugadaList()) {
            //actualiza la mejor carta, si no hay ninguna, se le asigna la primera carta jugada y su jugador
            if (mejorCarta == null || esMejorCarta(jugada.getCarta(), mejorCarta, paloTriunfo, paloSalida)) {
                mejorCarta = jugada.getCarta();
                jugadorGanador = jugada.getJugador();
            }
        }

        return jugadorGanador;
    }

    //compara la mejor carta hasta el momento con la siguiente que se jugo, para determinar si se debe actualizar
    //quien es la mejor carta y su jugador
    private boolean esMejorCarta(Carta carta1, Carta carta2, Carta paloTriunfo, Carta paloSalida) {
        //verifica si ambas cartas son de triunfo
        if (isCartaTriunfo(carta1, paloTriunfo) && isCartaTriunfo(carta2, paloTriunfo)) {
            if (carta1.getValorCarta() > carta2.getValorCarta()) {
                return true;
                //si las cartas tienen el mismo valor quiere decir que valen cero, asi que se compara con respecto al numero de la carta
            } else if (carta1.getValorCarta() == carta2.getValorCarta()) {
                return carta1.getNumero().getValorNumero() > carta2.getNumero().getValorNumero();
            } else return false;

        } else if (isCartaTriunfo(carta1, paloTriunfo) && !isCartaTriunfo(carta2, paloTriunfo)) {
            return true;
            //si ninguna carta es del palo de triunfo, comparo con el palo de salida
        } else if (!isCartaTriunfo(carta1, paloTriunfo) && !isCartaTriunfo(carta2, paloTriunfo)) {
            return comparaCartasConPaloSalida(carta1, carta2, paloSalida);
        }
        return false;
    }

    private boolean comparaCartasConPaloSalida(Carta carta1, Carta carta2, Carta paloSalida) {
        if (isCartaSalida(carta1, paloSalida) && isCartaSalida(carta2, paloSalida)) {
            if (carta1.getValorCarta() > carta2.getValorCarta()) {
                return true;
                //si las cartas tienen el mismo valor quiere decir que valen cero, asi que se compara con respecto al numero de la carta
            } else if (carta1.getValorCarta() == carta2.getValorCarta()) {
                return carta1.getNumero().getValorNumero() > carta2.getNumero().getValorNumero();
            } else return false;
        } else if (isCartaSalida(carta1, paloSalida) && !isCartaTriunfo(carta2, paloSalida)) {
            return true;
            //si ninguna carta es del palo de salida, comparo con respecto al valor numerico
        } else if (!isCartaTriunfo(carta1, paloSalida) && !isCartaTriunfo(carta2, paloSalida)) {
            return carta1.getNumero().getValorNumero() > carta2.getNumero().getValorNumero();
        }
        return false;
    }

    private boolean isCartaTriunfo(Carta carta, Carta paloTriunfo) {
        return carta.getPalo() == paloTriunfo.getPalo();
    }

    private boolean isCartaSalida(Carta carta, Carta paloSalida) {
        return carta.getPalo() == paloSalida.getPalo();
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }
}
