package br.com.feevale.ep;

/**
 * Classe responsável pelo escalonamento de processos
 */
public class EscalonamentoThread extends Thread {

    /** Tempo de processamento */
    private static final int TEMPO_PROCESSAMENTO = 100;
    /** Fila de processos */
    private final ExecutorFila executor;
    /** Quantum */
    private final int quantum;

    public EscalonamentoThread(ExecutorFila executor, int quantum) {
        this.executor = executor;
        this.quantum = quantum;
        setDaemon(true);
    }

    /**
     * Executa escalonamento
     * 
     * @throws InterruptedException Processamento interrompido
     * @throws ProcessoInexistenteException Processo inexistênte
     */
    private void exec() throws InterruptedException, ProcessoInexistenteException {
        int tempo = 0;
        executor.inicia();
        try {
            while (true) {
                Thread.sleep(TEMPO_PROCESSAMENTO);
                tempo += TEMPO_PROCESSAMENTO;
                executor.executa(TEMPO_PROCESSAMENTO);
                // Se finalizou quantum
                if (tempo >= quantum) {
                    executor.finaliza();
                    break;
                }
            }
        } catch (ProcessamentoInterrompidoException e) {
            executor.finaliza();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    exec();
                } catch (ProcessoInexistenteException e) {
                    Thread.sleep(50);
                }
            }
        } catch (InterruptedException ex) {
        }
    }

}
