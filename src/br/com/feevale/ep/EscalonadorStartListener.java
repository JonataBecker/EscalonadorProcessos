package br.com.feevale.ep;

/**
 * Evento de inicio de escalonamento
 */
public interface EscalonadorStartListener {
    
    /**
     * Inicio do escalonamento
     * 
     * @param filaCPU  
     * @param filaIO  
     */
    public void start(Fila filaCPU, Fila filaIO);
}
