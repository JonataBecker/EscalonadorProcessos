package br.com.feevale.ep;

/**
 * Evento de adição de processos
 */
public interface ProcessoListner {
    
    /**
     * Adição de processo
     * 
     * @param processo 
     */
    public void process(Processo processo);
    
}
