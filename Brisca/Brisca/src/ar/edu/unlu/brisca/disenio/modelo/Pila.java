package ar.edu.unlu.brisca.disenio.modelo;

import ar.edu.unlu.brisca.disenio.modelo.enumerados.NumeroCarta;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.Palo;
import ar.edu.unlu.brisca.disenio.modelo.enumerados.ValorCarta;

import java.util.Collections;

public class Pila extends ConjuntoCartas{
    public Pila() {
        super();
        inicializarPila();
        mezclar();
    }

    public void mezclar() {
        Collections.shuffle(this.getCartas());
    }

    public void inicializarPila() {
        //recorre el enumerado de Palo
        for (Palo palo : Palo.values()){
            //recorre el enumerado de Numero para las cartas
            for (NumeroCarta numero : NumeroCarta.values()){
                //Obtengo el valor de cada carta, segun las reglas de la Brisca
                ValorCarta valorCarta = obtenerValorCarta(numero);
                //Creo una nueva instancia de Carta para cada carta y se agrga a la lista de cartas, que se hereda de ConjuntoCartas
                agregarCarta(new Carta(numero, palo, valorCarta));
            }
        }
    }

    public ValorCarta obtenerValorCarta(NumeroCarta numero) {
        if (numero == NumeroCarta.AS)
            return ValorCarta.ONCE;
        else if (numero == NumeroCarta.TRES) {
            return ValorCarta.DIEZ;
        } else if (numero == NumeroCarta.REY) {
            return ValorCarta.CUATRO;
        } else if (numero == NumeroCarta.CABALLO) {
            return ValorCarta.TRES;
        }else if (numero == NumeroCarta.SOTA)
            return ValorCarta.DOS;
        else return ValorCarta.CERO;
    }

    @Override
    public void agregarCarta(Carta carta) {
        NumeroCarta numero = carta.getNumero();
        ValorCarta valorCarta = obtenerValorCarta(numero);
        try{
            if (valorCarta.getValor() == carta.getValorCarta())
                super.agregarCarta(carta);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
