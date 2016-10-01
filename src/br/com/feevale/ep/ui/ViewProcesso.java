package br.com.feevale.ep.ui;

import br.com.feevale.ep.Processo;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * View de processo
 */
public class ViewProcesso extends BorderPane {

    /** Dados do processo */
    private final Processo processo;
    /** Detalhe do processo */
    private Pane pane;
    /** Barra de progresso */
    private ProgressBar bar;

    public ViewProcesso(Processo processo) {
        this.processo = processo;
        setPrefSize(150, 100);
        setStyle("-fx-border-color: #CCC");
        initComponents();
        initListners();
        setCor();
        setBarra();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        pane = new Pane();
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
            setBarra();
            setCor();
        });
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
        bar.setProgress((double) processo.getTempoProcessamento() / processo.getVida());
    }

}
