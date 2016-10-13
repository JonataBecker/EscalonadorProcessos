package br.com.feevale.ep;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela representação de processos
 */
public class Processo {

    /** Tempo máximo de IO */
    public static final int TEMPO_MAX_IO = 3000;

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
    /** Vida total IO */
    private int vidaIO;
    /** Tempo de processamento IO */
    private int tempoProcessamentoIO;
    /** Estado do processo */
    private ProcessoEstado estado;
    /** Indica se o processo permite IO */
    private final boolean permiteIO;
    
    public Processo(int pdi, int vida, boolean permiteIO) {
        this.observableStatus = new ArrayList<>();
        this.observableClose = new ArrayList<>();
        this.pdi = pdi;
        this.vida = vida;
        this.permiteIO = permiteIO;
        this.estado = ProcessoEstado.AGUARDANDO;
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
     * Retorna verdadeiro se processo permite IO
     * 
     * @return boolean
     */
    public boolean isPermiteIO() {
        return permiteIO;
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
     * Retorna tempo IO
     *
     * @return int
     */
    public int getVidaIO() {
        return vidaIO;
    }

    /**
     * Retorna o tempo de processamento IO
     *
     * @return int
     */
    public int getTempoProcessamentoIO() {
        return tempoProcessamentoIO;
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
        if (isProcessamento()) {
            this.tempoProcessamento += tempo;
        } else if (isIO()) {
            this.tempoProcessamentoIO += tempo;
        }
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
     * Retorna verdadeiro se processo IO esta completo
     *
     * @return boolean
     */
    public boolean isIOCompleto() {
        return tempoProcessamentoIO >= vidaIO;
    }

    /**
     * Retorna verdadeiro se processo esta em processamento
     *
     * @return boolean
     */
    public boolean isProcessamento() {
        return estado.equals(ProcessoEstado.PROCESSANDO);
    }

    /**
     * Retorna verdadeiro se processo esta em IO
     *
     * @return boolean
     */
    public boolean isIO() {
        return estado.equals(ProcessoEstado.IO);
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
     * Executa encerramento do processo
     * 
     * @param vidaIO
     */
    public void setIO(int vidaIO) {
        this.vidaIO = vidaIO;
        setEstado(ProcessoEstado.IO);
    }

    /**
     * Dispara eventos de troca de status
     */
    public void fireStatusChange() {
        new ArrayList<>(observableStatus).forEach((obj) -> {
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
        return "Processo{" + "vida=" + vida + ", vidaRestante=" + tempoProcessamento + ", estado=" + estado + '}';
    }

}
