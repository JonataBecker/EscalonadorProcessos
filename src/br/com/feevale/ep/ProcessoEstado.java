package br.com.feevale.ep;

/**
 * Estados de processos
 */
public enum ProcessoEstado {

    AGUARDANDO("#1CD781", "Aguardando"),
    PROCESSANDO("#73C3EC", "Processando"),
    FINALIZADO("#F0585D", "Finalizado"),
    IO("#F6AF5C", "Em I/O");
    /** Cor definida para o estado do processo */
    private final String cor;
    /** Nome do status do processo */
    private final String status;
    
    private ProcessoEstado(String cor, String status) {
        this.cor = cor;
        this.status = status;
    }

    /**
     * Retorna a cor definida para o estado do processo
     * @return cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * Retorna o nome do status do processo
     * @return status
     */
    public String getStatus() {
        return status;
    }
    
}
