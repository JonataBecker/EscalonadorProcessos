package br.com.feevale.ep;

/**
 * Executor de filas de processamento
 */
public interface ExecutorFila {

    /**
     * Inicia execução
     *
     * @throws ProcessoInexistenteException Processo inexistênte
     */
    public void inicia() throws ProcessoInexistenteException;

    /**
     * Finaliza execução
     *
     * @throws ProcessoInexistenteException Processo inexistênte
     */
    public void finaliza() throws ProcessoInexistenteException;

    /**
     * Executa processo
     *
     * @param tempo
     * @throws ProcessoInexistenteException Processo inexistênte
     * @throws ProcessamentoInterrompidoException Processamento interrompido
     */
    public void executa(int tempo) throws ProcessoInexistenteException, ProcessamentoInterrompidoException;

}
