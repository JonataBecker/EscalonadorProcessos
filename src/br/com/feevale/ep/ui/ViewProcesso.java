package br.com.feevale.ep.ui;

import br.com.feevale.ep.Processo;
import br.com.feevale.ep.ProcessoEstado;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * View de processo
 */
public class ViewProcesso extends BorderPane {

    /** Dados do processo */
    private final Processo processo;
    
    public ViewProcesso(Processo processo) {
        this.processo = processo;
        setPrefSize(150, 100);
        setStyle("-fx-border-color: #CCC");
        initComponents();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: " + getProcessColor());
        setCenter(pane);
        ProgressBar bar = new ProgressBar();
        bar.setStyle("-fx-accent: #708090");
        bar.setProgress(Math.random());
        bar.setPrefWidth(150);
        setBottom(bar);
    }

    /**
     * Retorna a cor do processo
     *
     * @return String
     */
    private String getProcessColor() {
        ProcessoEstado processoEstado = ProcessoEstado.values()[(int) (Math.random() * 4)];
        switch (processoEstado) {
            case AGUARDANDO:
                return "#1CD781";
            case PROCESSANDO:
                return "#73C3EC";
            case IO:
                return "#F6AF5C";
            case FINALIZADO:
            default:
                return "#F0585D";
        }
    }

}
