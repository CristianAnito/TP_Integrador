package ar.edu.unlu.brisca.disenio.modelo;

public class EstadoJuego {
    Carta cartaTriunfo, cartaSalida;

    public EstadoJuego() {
        this.cartaTriunfo = null;
        this.cartaSalida = null;
    }

    public void setCartaTriunfo(Carta cartaTriunfo) {
        this.cartaTriunfo = cartaTriunfo;
    }
    public void setCartaSalida(Carta cartaSalida) {
        this.cartaSalida = cartaSalida;
    }
    public Carta getCartaTriunfo() {
        return cartaTriunfo;
    }

    public Carta getCartaSalida() {
        return cartaSalida;
    }

    /*
    Si la carta de triunfo es una carta de valor (as, tres, rey, caballo o sota), cualquier jugador, después de haber
    ganado una baza y antes de robar carta de la baceta, puede sustituirla por el siete del mismo palo. El siete o
    cualquier otra carta menor (seis, cinco, cuatro) puede sustituirse por el dos de triunfo. Este cambio no puede
    hacerse en el último turno de robo de cartas de la baceta.
     */

}
