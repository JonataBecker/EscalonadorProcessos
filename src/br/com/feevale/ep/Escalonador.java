package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador do escalonador de processos Round Robin
 */
public class Escalonador {

    /** Lista de eventos de inicio de escalonamento */
    private final List<EscalonadorStartListener> observable;
    /** Objeto responsável pelo controle da fila de escalonamento de CPU */
    private Fila filaCPU;
    /** Objeto responsável pelo controle da fila de escalonamento de IO */
    private Fila filaIO;
    /** Objeto responsável pela criação dos processos na fila */
    private Thread ct;
    /** Objeto responsável pelo escalonamento dos processos */
    private Thread escalonador;
    
    public Escalonador() {
        this.observable = new ArrayList<>();
    }

    /**
     * Inicia escalonamento
     */
    public void start() {
        filaCPU = new Fila();
        filaIO = new Fila();
        observable.forEach((obj) -> {
            obj.start(filaCPU);
        });
        ct = new CriacaoProcessoThread(filaCPU, 10000, 3);
        ct.start();
        escalonador = new EscalonamentoThread(new ExecutorFilaCPU(filaCPU, filaIO), 1000);
        escalonador.start();
    }

    /**
     * Adiciona evento de início de escalonamento
     *
     * @param observer
     */
    public void onStart(EscalonadorStartListener observer) {
        observable.add(observer);
    }

    /**
     * Interrompe o processamento das threads do escalonador
     */
    public void interrupt() {
        ct.interrupt();
        escalonador.interrupt();
    }
}
