package br.com.feevale.ep.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Janela de processos
 */
public class ViewProcessos extends FlowPane {

    public ViewProcessos() {
        setStyle("-fx-background-color:#FFF; -fx-border-color: #CCC");
        setPadding(new Insets(5));
        initComponents();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        for (int i = 0; i < 20; i++) {
            Pane pane = new BorderPane(new ViewProcesso());
            pane.setPadding(new Insets(5));
            getChildren().add(pane);
        }
    }

}
