package br.com.feevale.ep;

/**
 *
 * @author Marina
 */
public class ProcessoFactory {
    /**
     * Cria processo conforme parâmetros
     * @param fila
     * @return Processo criado
     */
    public static Processo cria(Fila fila) {
        int vida = (int) (Math.random() * fila.getTempoVida() + 1);
        boolean ioBound = Math.random() < fila.getProbabilidadeIO();
        return new Processo(vida, ioBound);
    }
    
}
