package br.com.feevale.ep;

/**
 * Evento de inicio de escalonamento
 */
public interface EscalonadorStartListner {
    
    /**
     * Inicio do escalonamento
     * 
     * @param fila 
     */
    public void start(Fila fila);
}
