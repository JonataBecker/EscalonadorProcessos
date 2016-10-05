package br.com.feevale.ep;

/**
 * Classe responsável pelo escalonamento de processos
 */
public class EscalonamentoThread extends Thread {

    /**
     * Tempo de processamento
     */
    private static final int TEMPO_PROCESSAMENTO = 100;
    /**
     * Fila de processos
     */
    private final Fila fila;

    public EscalonamentoThread(Fila fila) {
        this.fila = fila;
        setDaemon(true);
    }

    /**
     * Retorna próximo processo
     *
     * @return Processo
     * @throws InterruptedException
     */
    private Processo getNext() throws InterruptedException {
        while (true) {
            Processo processo = fila.getNextProcesso();
            if (processo != null) {
                return processo;
            }
            Thread.sleep(TEMPO_PROCESSAMENTO);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                int tempo = 0;
                Processo processo = getNext();
                if (!processo.isCompleto()) {
                    processo.inicia();
                    while (true) {
                        Thread.sleep(TEMPO_PROCESSAMENTO);
                        tempo += TEMPO_PROCESSAMENTO;
                        processo.executa(TEMPO_PROCESSAMENTO);
                        // Se processo esta completo
                        if (processo.isCompleto()) {
                            processo.finaliza();
                            break;
                        }
                        // Se finalizou quantum
                        if (tempo >= fila.getQuantum()) {
                            processo.aguarda();
                            break;
                        }
                    }
                }
            }
        } catch (InterruptedException ex) {
        }
    }

}
