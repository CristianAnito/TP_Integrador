package ar.edu.unlu.brisca.juego;

import ar.edu.unlu.brisca.disenio.controlador.Controlador;
import ar.edu.unlu.brisca.disenio.modelo.Juego;
import ar.edu.unlu.brisca.disenio.vista.VistaPorConsola;

public class Brisca {

    public static void main(String[] args) {
        Juego modelo = new Juego();
        VistaPorConsola vista = new VistaPorConsola();
        Controlador controlador = new Controlador(modelo, vista);
        //comienza el juego
        vista.comenzarJuego();
    }
}
