package br.com.feevale.ep;

/**
 * Classe responsável pela criação do processo
 */
public class ProcessoFactory {
    
    private static int lastPdi = 1000;
    
    /**
     * Cria processo conforme parâmetros
     * @param fila
     * @return Processo criado
     */
    public static synchronized Processo cria(Fila fila) {
        int vida = (int) (Math.random() * fila.getTempoVida() + 1);
        boolean ioBound = Math.random() < fila.getProbabilidadeIO();
        return new Processo(lastPdi++, vida, ioBound);
    }
    
}
