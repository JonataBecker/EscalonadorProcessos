package br.com.feevale.ep;

/**
 * Executor de fila CPU
 */
public class ExecutorFilaCPU implements ExecutorFila {

    /** Fila CPU */
    private final Fila filaCPU;
    /** Fila IO */
    private final Fila filaIO;
    /** Unidade quantum */
    private final int quantum;
    /** Probabilidade IO */
    private final double probabilidadeIO;

    public ExecutorFilaCPU(Fila filaCPU, Fila filaIO, int quantum, double probabilidadeIO) {
        this.filaCPU = filaCPU;
        this.filaIO = filaIO;
        this.quantum = quantum;
        this.probabilidadeIO = probabilidadeIO;
    }

    @Override
    public void inicia() throws ProcessoInexistenteException {
        filaCPU.getProcessoAtivo().inicia();
    }

    @Override
    public void finaliza() throws ProcessoInexistenteException {
        Processo processo = filaCPU.getProcessoAtivo();
        if (processo.isProcessamento()) {
            filaCPU.getProcessoAtivo().aguarda();
            filaCPU.nextProcesso();
        }
    }

    @Override
    public void executa(int tempo) throws ProcessoInexistenteException, ProcessamentoInterrompidoException {
        filaCPU.executa(tempo);
        Processo processo = filaCPU.getProcessoAtivo();
        if (processo.isCompleto()) {
            processo.finaliza();
            filaCPU.nextProcesso();
            filaCPU.remove(processo);
            throw new ProcessamentoInterrompidoException();
        }
        // Se deve se passado processo para IO
        if (isDefineIO(processo)) {
            processo.setIO((int) (Math.random() * Processo.TEMPO_MAX_IO + 1));
            filaCPU.nextProcesso();
            filaCPU.remove(processo);
            filaIO.adiciona(processo);
            throw new ProcessamentoInterrompidoException();
        }
    }
    
    /**
     * Retorna verdadeiro se deve adicionar o processo como IO
     * 
     * @param processo
     * @return boolean
     */
    private boolean isDefineIO(Processo processo) {
        double ciclos = (double) processo.getVida() / quantum;
        return (probabilidadeIO / ciclos) >= Math.random();
    }

}
