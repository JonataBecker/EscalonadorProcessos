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
     * @return Processo criado
     */
    public static synchronized Processo cria(int tempoVida) {
        int vida = (int) (Math.random() * tempoVida + 1);
        return new Processo(lastPdi++, vida);
    }
    
}
