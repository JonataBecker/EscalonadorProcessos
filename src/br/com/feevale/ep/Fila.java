package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo controle de processos
 */
public class Fila {

    /** Eventos de adição de processos */
    private final List<ProcessoListner> observable;
    /** Lista de processos */
    private final List<Processo> processos;
    /** Unidade quantum */
    private final int quantum;
    /** Tempo de vida máximo */
    private final int tempoVida;
    /** Ocorrência do processo atual */
    private int ocorrenciaProcesso;
    /** Quantidade de processos novos por minuto */
    private int quantidadeProcessos;
    /** Probabilidade do processo ser I/O-bound */
    private double probabilidadeIO;

    public Fila(int quantum, int tempoVida,
            int quantidadeProcessos, double probabilidadeIO) {
        this.observable = new ArrayList<>();
        this.processos = new ArrayList<>();
        this.quantum = quantum;
        this.tempoVida = tempoVida;
        this.ocorrenciaProcesso = -1;
        this.quantidadeProcessos = quantidadeProcessos;
        this.probabilidadeIO = probabilidadeIO;
    }

    /**
     * Adiciona novo processo na lista
     *
     * @param processo
     */
    public void adicionaProcesso(Processo processo) {
        processos.add(processo);
        observable.forEach((obj) -> {
            obj.process(processo);
        });
    }

    /**
     * Retorna lista de processos da fila
     *
     * @return {@code List<Processo>}
     */
    public List<Processo> getProcessos() {
        return new ArrayList<>(processos);
    }

    /**
     * Retorna a unidade quantum
     * 
     * @return int
     */
    public int getQuantum() {
        return quantum;
    }
    
    /**
     * Retorna o tempo de vida
     *
     * @return int
     */
    public int getTempoVida() {
        return tempoVida;
    }

    /**
     * Retorna a quantidade de processos
     *
     * @return int
     */
    public int getQuantidadeProcessos() {
        return quantidadeProcessos;
    }

    /**
     * Retorna a probabilidade de IO
     *
     * @return double
     */
    public double getProbabilidadeIO() {
        return probabilidadeIO;
    }

    /**
     * Adiciona evento de adição de processo
     *
     * @param observer
     */
    public void onAddProcess(ProcessoListner observer) {
        observable.add(observer);
    }

    /**
     * Retorna próximo processo da fila
     *
     * @return Processo
     */
    public Processo getNextProcesso() {
        if (processos.isEmpty()) {
            return null;
        }
        ocorrenciaProcesso++;
        if (ocorrenciaProcesso == processos.size()) {
            ocorrenciaProcesso = 0;
        }
        return processos.get(ocorrenciaProcesso);
    }

}
