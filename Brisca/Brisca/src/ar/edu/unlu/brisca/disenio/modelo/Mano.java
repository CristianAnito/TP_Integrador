package ar.edu.unlu.brisca.disenio.modelo;

public class Mano extends ConjuntoCartas{
    public Mano() {
        super();
    }

    @Override
    public void agregarCarta(Carta carta)  {
        super.agregarCarta(carta);
    }

    public boolean isCompleta() {
        boolean miRespuesta = true;
        for (Carta carta : getCartas()){
            miRespuesta &= (carta != null);
        }
        return miRespuesta;
    }
}
