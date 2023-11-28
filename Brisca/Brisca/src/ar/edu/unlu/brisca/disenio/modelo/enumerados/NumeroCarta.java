package ar.edu.unlu.brisca.disenio.modelo.enumerados;

public enum NumeroCarta {
    AS(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6), SIETE(7), SOTA(10), CABALLO(11), REY(12);
    private final Integer valor;
    NumeroCarta(Integer valor){
        this.valor = valor;
    }

    public Integer getValorNumero() {
        return valor;
    }
}
