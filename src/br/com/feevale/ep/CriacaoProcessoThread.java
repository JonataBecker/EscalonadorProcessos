package br.com.feevale.ep;

/**
 * Thread de criação de processos
 */
public class CriacaoProcessoThread extends Thread {

    /** Fila para criação de processos */
    private final Fila fila;
    /** Tempo de vida máximo */
    private final int tempoVida;
    /** Probabilidade IO */
    private final int probabilidadeIO;

    public CriacaoProcessoThread(Fila fila, int tempoVida, int probabilidadeIO) {
        this.fila = fila;
        this.tempoVida = tempoVida;
        this.probabilidadeIO = probabilidadeIO;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                fila.adiciona(ProcessoFactory.cria(tempoVida, probabilidadeIO));
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {
        }
    }

}
