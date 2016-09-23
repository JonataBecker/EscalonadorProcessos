package br.com.feevale.ep;

/**
 * Classe responsável pela representação de processos
 */
public class Processo {

    /** Vida total */
    private final int vida;
    /** Vida restante */
    private int vidaRestante;
    /** Estado do processo */
    private ProcessoEstado estado;

    public Processo(int vida) {
        this.vida = vida;
    }

    /**
     * Retorna a vida total do processo
     *
     * @return int
     */
    public int getVida() {
        return vida;
    }

    /**
     * Retorna a vida restante do processo
     *
     * @return int
     */
    public int getVidaRestante() {
        return vidaRestante;
    }

    /**
     * Retorna o estado do processo
     *
     * @return ProcessoEstado
     */
    public ProcessoEstado getEstado() {
        return estado;
    }
}
