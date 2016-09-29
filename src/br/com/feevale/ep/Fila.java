package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo controle de processos
 */
public class Fila {

    /** Eventos de adição de processos */
    private final List<FilaAddProcessListner> observable;
    /** Lista de processos */
    private final List<Processo> processos;
    /** Ocorrência do processo atual */
    private final int ocorrenciaProcesso;
    /** Unidade quantum */
    private final int quantum;
    /** Tempo de vida máximo */
    private final int tempoVida;
    /** Quantidade de processos novos por minuto */
    private int quantidadeProcessos;
    /** Probabilidade do processo ser I/O-bound */
    private double probabilidadeIO;
    
    public Fila(int ocorrenciaProcesso, int quantum, int tempoVida, 
            int quantidadeProcessos, double probabilidadeIO) {
        this.observable = new ArrayList<>();
        this.processos = new ArrayList<>();
        this.ocorrenciaProcesso = ocorrenciaProcesso;
        this.quantum = quantum;
        this.tempoVida = tempoVida;
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
            obj.add(processo);
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
    public void onAddProcess(FilaAddProcessListner observer) {
        observable.add(observer);
    }

}
