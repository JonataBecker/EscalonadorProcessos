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
        double rand = Math.random() * 100;
        if (rand < 33) {
            return "#F5F5F5";
        } else if (rand < 66) {
            return "#DCEDC8";
        }
        return "#F0F4C3";
    }

}
