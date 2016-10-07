package br.com.feevale.ep.ui;

import br.com.feevale.ep.Fila;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Janela de fila de processos
 */
public class ViewFila extends FlowPane {
    
    /**
     * Inicializa componentes
     * 
     * @param fila
     */
    public void setFila(Fila fila) {
        getChildren().clear();
        fila.onAddProcess((processo) -> {
            Platform.runLater(() -> {
                Pane pane = new BorderPane(new ViewProcesso(fila, processo));
                pane.setPadding(new Insets(5));
                getChildren().add(fila.getProcessos().indexOf(processo), pane);
            });
        });
    }

}
