package br.com.feevale.ep;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marina
 */
public class TesteMarina {

    public static void main(String[] args) {
        Fila fila = new Fila();

        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("Tamanho da fila: " + fila.getProcessos().size());
                for (Processo processo : fila.getProcessos()) {
                    System.out.println(processo);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TesteMarina.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        Thread ct = new CriacaoProcessoThread(fila, 10, 10, 0.5);

        t.start();
        ct.start();

    }
}
