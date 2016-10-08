package br.com.feevale.ep.ui;

import br.com.feevale.ep.Fila;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Janela de fila de processos
 */
public class ViewFila extends FlowPane {

    /** Lista de processos */
    private final Map<Integer, Pane> processos;

    public ViewFila() {
        this.processos = new HashMap<>();
    }
    
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
                processos.put(processo.getPdi(), pane);
            });
        });
        fila.onRemoveProcess((processo) -> {
            if (!processo.isCompleto()) {
                Platform.runLater(() -> {
                    getChildren().remove(processos.get(processo.getPdi()));
                });
            }
        });
    }

}
