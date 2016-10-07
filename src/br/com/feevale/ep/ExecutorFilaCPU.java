package br.com.feevale.ep;

/**
 * Executor de fila CPU
 */
public class ExecutorFilaCPU implements ExecutorFila {

    /** Fila CPU */
    private final Fila filaCPU;
    /** Fila IO */
    private final Fila filaIO;

    public ExecutorFilaCPU(Fila filaCPU, Fila filaIO) {
        this.filaCPU = filaCPU;
        this.filaIO = filaIO;
    }

    @Override
    public void inicia() throws ProcessoInexistenteException {
        filaCPU.nextProcesso();
        filaCPU.getProcessoAtivo().inicia();
    }

    @Override
    public void finaliza() throws ProcessoInexistenteException {
        Processo processo = filaCPU.getProcessoAtivo();
        if (!processo.isCompleto()) {
            filaCPU.getProcessoAtivo().aguarda();
        }
    }

    @Override
    public void executa(int tempo) throws ProcessoInexistenteException, ProcessamentoInterrompidoException {
        filaCPU.executa(tempo);
        Processo processo = filaCPU.getProcessoAtivo();
        if (processo.isCompleto()) {
            processo.finaliza();
            filaCPU.remove(processo);
            throw new ProcessamentoInterrompidoException();
        }
    }

}
