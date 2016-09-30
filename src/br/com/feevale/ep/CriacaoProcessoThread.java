package br.com.feevale.ep;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread de criação de processos
 */
public class CriacaoProcessoThread extends Thread {
    
    /** Fila para criação de processos */
    private final Fila fila;

    public CriacaoProcessoThread(Fila fila) {
        this.fila = fila;
        setDaemon(true);
    }
    
    @Override
    public void run() {
        while (true) {
            fila.adicionaProcesso(ProcessoFactory.cria(fila));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CriacaoProcessoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
}
