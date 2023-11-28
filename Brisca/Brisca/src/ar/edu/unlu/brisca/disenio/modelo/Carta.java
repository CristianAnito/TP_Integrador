package ar.edu.unlu.brisca.disenio.modelo;

import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;

import java.util.Objects;

public class Carta {
    private NumeroCarta numero;
    private Palo palo;
    private ValorCarta valorCarta;

    public Carta(NumeroCarta numero, Palo palo, ValorCarta valorCarta) {
        this.numero = numero;
        this.palo = palo;
        this.valorCarta = valorCarta;
    }

    public Carta(NumeroCarta numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
        this.valorCarta = ValorCarta.CERO;
    }

    public NumeroCarta getNumero() {
        return numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getValorCarta() {
        return valorCarta.getValor();
    }

    public boolean esCartaValor() {
        return !Objects.equals(valorCarta.getValor(), ValorCarta.CERO.getValor());
    }
}
