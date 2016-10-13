package br.com.feevale.ep;

/**
 * Thread de criação de processos
 */
public class CriacaoProcessoThread extends Thread {

    /** Fila para criação de processos */
    private final Fila fila;
    /** Tempo de vida máximo */
    private final int tempoVida;
    /** Quantidade máxima de processos por minuto */
    private final int maxProcessos;
    /** Probabilidade de IO */
    private final double probabilidadeIO;
    
    public CriacaoProcessoThread(Fila fila, int tempoVida, int maxProcessos, double probabilidadeIO) {
        this.fila = fila;
        this.tempoVida = tempoVida;
        this.maxProcessos = maxProcessos;
        this.probabilidadeIO = probabilidadeIO;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            int tempo = 60 * 1000 / maxProcessos;
            while (true) {
                fila.adiciona(ProcessoFactory.cria(tempoVida, probabilidadeIO));
                Thread.sleep(tempo);
            }
        } catch (InterruptedException ex) {
        }
    }

}
