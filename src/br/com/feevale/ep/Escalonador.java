package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador do escalonador de processos Round Robin
 */
public class Escalonador {

    /** Lista de eventos de inicio de escalonamento */
    private final List<EscalonadorStartListener> observable;
    /** Objeto responsável pelo controle da fila de escalonamento */
    private Fila fila;
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
        fila = new Fila(1000, 10000, 240, 0.7);
        observable.forEach((obj) -> {
            obj.start(fila);
        });
        ct = new CriacaoProcessoThread(fila);
        ct.start();
        escalonador = new EscalonamentoThread(fila);
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
