package br.com.feevale.ep;

/**
 * Classe responsável pela criação do processo
 */
public class ProcessoFactory {
    
    private static int lastPdi = 1000;
    
    /**
     * Cria processo conforme parâmetros
     * 
     * @param tempoVida 
     * @param probabilidadeIO 
     * @return Processo criado
     */
    public static synchronized Processo cria(int tempoVida, int probabilidadeIO) {
        int vida = (int) (Math.random() * tempoVida + 1);
        boolean ioBound = Math.random() < probabilidadeIO;
        return new Processo(lastPdi++, vida, ioBound);
    }
    
}
