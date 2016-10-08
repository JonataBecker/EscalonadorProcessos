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
    /** Objeto responsável pelo escalonamento dos processos IO */
    private Thread escalonadorIO;
    
    public Escalonador() {
        this.observable = new ArrayList<>();
    }

    /**
     * Inicia escalonamento
     * 
     * @param quantum
     * @param tempoVida
     * @param maxProcessos
     * @param probabilidadeIO
     */
    public void start(int quantum, int tempoVida, int maxProcessos, double probabilidadeIO) {
        filaCPU = new Fila();
        filaIO = new Fila();
        observable.forEach((obj) -> {
            obj.start(filaCPU, filaIO);
        });
        ct = new CriacaoProcessoThread(filaCPU, tempoVida, maxProcessos);
        ct.start();
        escalonador = new EscalonamentoThread(new ExecutorFilaCPU(filaCPU, filaIO, quantum, probabilidadeIO), quantum);
        escalonador.start();
        escalonadorIO = new EscalonamentoThread(new ExecutorFilaIO(filaCPU, filaIO), quantum);
        escalonadorIO.start();
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
        escalonadorIO.interrupt();
    }
}
