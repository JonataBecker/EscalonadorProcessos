package br.com.feevale.ep;

/**
 * Executor fila IO
 */
public class ExecutorFilaIO implements ExecutorFila {

    /** Fila CPU */
    private final Fila filaCPU;
    /** Fila IO */
    private final Fila filaIO;

    public ExecutorFilaIO(Fila filaCPU, Fila filaIO) {
        this.filaCPU = filaCPU;
        this.filaIO = filaIO;
    }

    @Override
    public void inicia() throws ProcessoInexistenteException {
    }

    @Override
    public void finaliza() throws ProcessoInexistenteException {
        Processo processo = filaIO.getProcessoAtivo();
        if (processo.isIO()) {
            filaIO.nextProcesso();
        }
    }

    @Override
    public void executa(int tempo) throws ProcessoInexistenteException, ProcessamentoInterrompidoException {
        filaIO.executa(tempo);
        Processo processo = filaIO.getProcessoAtivo();
        if (processo.isIOCompleto()) {
            filaIO.nextProcesso();
            filaIO.remove(processo);
            processo.aguarda();
            filaCPU.adiciona(processo);
            throw new ProcessamentoInterrompidoException();
        }
    }

}
