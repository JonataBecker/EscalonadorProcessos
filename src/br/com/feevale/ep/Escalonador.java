package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador do escalonador de processos Round Robin
 */
public class Escalonador {

    /** Lista de eventos de inicio de escalonamento */
    private final List<EscalonadorStartListner> observable;
    /** Objeto responsável pelo controle da fila de escalonamento */
    private Fila fila;

    public Escalonador() {
        this.observable = new ArrayList<>();
    }

    /**
     * Inicia escalonamento
     */
    public void start() {
        fila = new Fila(1, 10, 100, 240, 0.7);
        observable.forEach((obj) -> {
            obj.start(fila);
        });
        Thread ct = new CriacaoProcessoThread(fila);
        ct.start();
    }

    /**
     * Adiciona evento de incio de escalonamento
     *
     * @param observer
     */
    public void onStart(EscalonadorStartListner observer) {
        observable.add(observer);
    }

}
