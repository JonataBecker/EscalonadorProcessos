package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo controle de processos
 */
public class Fila {

    /** Eventos de adição de processos */
    private final List<ProcessoListener> observable;
    /** Eventos de remoção de processos */
    private final List<ProcessoListener> observableRemove;
    /** Lista de processos */
    private final List<Processo> processos;
    /** Identificador do processo atual */
    private Processo processoAtivo;

    public Fila() {
        this.observable = new ArrayList<>();
        this.observableRemove = new ArrayList<>();
        this.processos = new ArrayList<>();
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
     * Retorna processo ativo
     *
     * @return Processo
     * @throws ProcessoInexistenteException Processo inexistente
     */
    public Processo getProcessoAtivo() throws ProcessoInexistenteException {
        if (processoAtivo == null) {
            throw new ProcessoInexistenteException();
        }
        return processoAtivo;
    }

    /**
     * Adiciona novo processo na lista
     *
     * @param processo
     */
    public void adiciona(Processo processo) {
        if (processoAtivo == null) {
            processos.add(processo);
            processoAtivo = processo;
        } else {
            processos.add(indexOf(processoAtivo), processo);
        }
        observable.forEach((obj) -> {
            obj.process(processo);
        });
        getProcessos().forEach((obj) -> {
            obj.fireStatusChange();
        });
    }

    /**
     * Remove processo da lista
     *
     * @param processo
     */
    public void remove(Processo processo) {
        processos.remove(processo);
        observableRemove.forEach((obj) -> {
            obj.process(processo);
        });
    }

    /**
     * Adiciona evento de adição de processo
     *
     * @param observer
     */
    public void onAddProcess(ProcessoListener observer) {
        observable.add(observer);
    }

    /**
     * Adiciona evento de remoção de processo
     *
     * @param observer
     */
    public void onRemoveProcess(ProcessoListener observer) {
        observableRemove.add(observer);
    }

    /**
     * Retorna a ocorrência do processo na fila
     *
     * @param processo
     * @return int
     */
    public int indexOf(Processo processo) {
        return processos.indexOf(processo);
    }

    /**
     * Retorna próximo processo da fila
     *
     * @throws ProcessoInexistenteException Processo inexistente
     */
    public void nextProcesso() throws ProcessoInexistenteException {
        if (processos.isEmpty()) {
            throw new ProcessoInexistenteException();
        }
        if (processoAtivo == null) {
            processoAtivo = processos.get(0);
            return;
        }
        int ocorrenciaProcesso = indexOf(processoAtivo) + 1;
        if (ocorrenciaProcesso == processos.size()) {
            ocorrenciaProcesso = 0;
        }
        Processo process = processos.get(ocorrenciaProcesso);
        processoAtivo = process.equals(processoAtivo) && !processoAtivo.isProcessamento() ? null : process;
    }

    /**
     * Executa processo
     *
     * @param tempo
     * @throws ProcessoInexistenteException Processo inexistente
     */
    public void executa(int tempo) throws ProcessoInexistenteException {
        if (processoAtivo == null) {
            nextProcesso();
            if (processoAtivo == null) {
                throw new ProcessoInexistenteException();
            }
        }
        processoAtivo.executa(tempo);
    }

}
