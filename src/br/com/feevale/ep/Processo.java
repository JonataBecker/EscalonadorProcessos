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
    /** Indica se é I/O-bound */
    private final boolean ioBound;

    public Processo(int vida, boolean ioBound) {
        this.vida = vida;
        this.ioBound = ioBound;
        estado = ProcessoEstado.AGUARDANDO;
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

    @Override
    public String toString() {
        return "Processo{" + "vida=" + vida + ", vidaRestante=" + vidaRestante + ", estado=" + estado + ", ioBound=" + ioBound + '}';
    }


    
    
}
