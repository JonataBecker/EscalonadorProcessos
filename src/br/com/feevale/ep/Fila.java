package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável pelo controle de processos
 */
public class Fila {

    /** Lista de processos */
    private final List<Processo> processos;
    /** Ocorrência do processo atual */
    private final int ocorrenciaProcesso;
    /** Unidade quantum */
    private final int quantum;
    /** Tempo de vida máximo */
    private final int tempoVida;

    public Fila(int ocorrenciaProcesso, int quantum, int tempoVida) {
        this.processos = new ArrayList<>();
        this.ocorrenciaProcesso = ocorrenciaProcesso;
        this.quantum = quantum;
        this.tempoVida = tempoVida;
    }

    /**
     * Adiciona novo processo na lista
     */
    public void adicionaProcesso() {
        processos.add(new Processo(10));
    }

    /**
     * Retorna lista de processos da fila
     *
     * @return {@code List<Processo>}
     */
    public List<Processo> getProcessos() {
        return Collections.unmodifiableList(processos);
    }

}
