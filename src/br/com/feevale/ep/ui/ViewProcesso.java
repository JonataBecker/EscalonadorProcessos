package br.com.feevale.ep.ui;

import br.com.feevale.ep.Fila;
import br.com.feevale.ep.Processo;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

/**
 * View de processo
 */
public class ViewProcesso extends BorderPane {

    /** Fila de processos */
    private final Fila fila;
    /** Dados do processo */
    private final Processo processo;
    /** Detalhe do processo */
    private BorderPane pane;
    /** Sequência */
    private Label sequencia;
    /** Pdi */
    private Label pdi;
    /** Barra de progresso */
    private ProgressBar bar;

    public ViewProcesso(Fila fila, Processo processo) {
        this.fila = fila;
        this.processo = processo;
        setPrefSize(150, 100);
        setStyle("-fx-border-color: #CCC");
        initComponents();
        initListners();
        atualiza();
        setPdi();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        pane = new BorderPane();
        sequencia = new Label();
        pane.setTop(sequencia);
        pdi = new Label();
        pane.setCenter(pdi);
        setCenter(pane);
        bar = new ProgressBar();
        bar.setStyle("-fx-accent: #708090");
        bar.setPrefWidth(150);
        setBottom(bar);
    }
    
    /**
     * Inicializa eventos
     */
    private void initListners() {
        processo.onChangeStatus((obj) -> {
            Platform.runLater(() -> {
                atualiza();
            });
        });
    }

    /**
     * Atualiza informações do processo
     */
    private void atualiza() {
        setSequencia();
        setBarra();
        setCor();
    }

    /**
     * Define a sequência do processo
     */
    private void setSequencia() {
        sequencia.setText(String.valueOf(fila.getProcessos().indexOf(processo)));
    }

    /**
     * Define o pdi do processo
     */
    private void setPdi() {
        pdi.setText("PDI: " + String.valueOf(processo.getPdi()));
    }

    /**
     * Define a cor do processo
     */
    private void setCor() {
        pane.setStyle("-fx-background-color: " + processo.getEstado().getCor());
    }

    /**
     * Define a cor do processo
     */
    private void setBarra() {
        double value;
        if (processo.isIO()) {
            value = (double) processo.getTempoProcessamentoIO() / processo.getVidaIO();
        } else {
            value = (double) processo.getTempoProcessamento() / processo.getVida();
        }
        bar.setProgress(value);
    }

}
