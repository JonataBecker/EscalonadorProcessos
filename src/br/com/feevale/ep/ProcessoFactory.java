package br.com.feevale.ep;

/**
 *
 * @author Marina
 */
public class ProcessoFactory {

    public static Processo cria() {
        return new Processo(2);
    }
    
}
