package ar.edu.unlu.brisca.disenio.modelo.enumerados;

public enum ValorCarta {
    ONCE(11), DIEZ(10), CUATRO(4), TRES(3), DOS(2), CERO(0);

    private final Integer valor;
    ValorCarta(Integer valor) {
        this.valor = valor;
    }
    public Integer getValor() {
        return valor;
    }
}
