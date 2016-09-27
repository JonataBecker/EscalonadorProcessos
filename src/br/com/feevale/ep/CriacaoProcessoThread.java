package br.com.feevale.ep;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marina
 */
public class CriacaoProcessoThread extends Thread {
    
    private final Fila fila;

    public CriacaoProcessoThread(Fila fila) {
        this.fila = fila;
    }
    
    
    @Override
    public void run() {
        while (true) {
            fila.adicionaProcesso(ProcessoFactory.cria());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CriacaoProcessoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
}
