package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela representação de processos
 */
public class Processo {

    /** Eventos do processo */
    private final List<ProcessoListener> observableStatus;
    /** Eventos de encerramento do processo */
    private final List<ProcessoListener> observableClose;
    /** PDI */
    private final int pdi;
    /** Vida total */
    private final int vida;
    /** Tempo de processamento */
    private int tempoProcessamento;
    /** Estado do processo */
    private ProcessoEstado estado;
    /** Indica se é I/O-bound */
    private final boolean ioBound;

    public Processo(int pdi, int vida, boolean ioBound) {
        this.observableStatus = new ArrayList<>();
        this.observableClose = new ArrayList<>();
        this.pdi = pdi;
        this.vida = vida;
        this.ioBound = ioBound;
        estado = ProcessoEstado.AGUARDANDO;
    }

    /**
     * Retorna o pdi do processo
     * 
     * @return int
     */
    public int getPdi() {
        return pdi;
    }
    
    /**
     * Retorna a vida total do processo
     *
     * @return int
     */
    public int getVida() {
        return vida;
    }

    /**
     * Retorna o tempo de processamento
     *
     * @return int
     */
    public int getTempoProcessamento() {
        return tempoProcessamento;
    }

    /**
     * Retorna o estado do processo
     *
     * @return ProcessoEstado
     */
    public ProcessoEstado getEstado() {
        return estado;
    }

    /**
     * Define estado do processo
     * 
     * @param estado 
     */
    private void setEstado(ProcessoEstado estado) {
        this.estado = estado;
        fireStatusChange();
    }
    
    /**
     * Inicia processamento
     */
    public void inicia() {
        setEstado(ProcessoEstado.PROCESSANDO);
    }

    /**
     * Aguarda fila
     */
    public void aguarda() {
        setEstado(ProcessoEstado.AGUARDANDO);
    }

    /**
     * Executa processamento
     *
     * @param tempo
     */
    public void executa(int tempo) {
        this.tempoProcessamento += tempo;
        fireStatusChange();
    }

    /**
     * Retorna verdadeiro se processo esta completo
     *
     * @return boolean
     */
    public boolean isCompleto() {
        return tempoProcessamento >= vida;
    }

    /**
     * Executa encerramento do processo
     */
    public void finaliza() {
        setEstado(ProcessoEstado.FINALIZADO);
        observableClose.forEach((obj) -> {
            obj.process(this);
        });
    }

    /**
     * Dispara eventos de troca de status
     */
    public void fireStatusChange() {
        observableStatus.forEach((obj) -> {
            obj.process(this);
        });
    }

    /**
     * Adiciona evento de troca de status
     *
     * @param observer
     */
    public void onChangeStatus(ProcessoListener observer) {
        this.observableStatus.add(observer);
    }

    /**
     * Adiciona evento de encerramento do processo
     *
     * @param observer
     */
    public void onClose(ProcessoListener observer) {
        this.observableClose.add(observer);
    }

    @Override
    public String toString() {
        return "Processo{" + "vida=" + vida + ", vidaRestante=" + tempoProcessamento + ", estado=" + estado + ", ioBound=" + ioBound + '}';
    }

}
