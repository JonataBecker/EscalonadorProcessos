package br.com.feevale.ep;

/**
 * Evento de adição de processos
 */
public interface FilaAddProcessListner {
    
    /**
     * Adição de processo
     * 
     * @param processo 
     */
    public void add(Processo processo);
    
}
