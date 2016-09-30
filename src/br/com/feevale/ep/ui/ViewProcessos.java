package br.com.feevale.ep.ui;

import br.com.feevale.ep.Escalonador;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Janela de processos
 */
public class ViewProcessos extends FlowPane {

    /** Controlador do escalonador de processo */
    private final Escalonador escalonador;

    public ViewProcessos(Escalonador escalonador) {
        this.escalonador = escalonador;
        setStyle("-fx-background-color:#FFF; -fx-border-color: #CCC");
        setPadding(new Insets(5));
        initListners();
    }

    /**
     * Inicializa componentes
     */
    private void initListners() {
        escalonador.onStart((fila) -> {
            fila.onAddProcess((processo) -> {
                Platform.runLater(() -> {
                    Pane pane = new BorderPane(new ViewProcesso(processo));
                    pane.setPadding(new Insets(5));
                    getChildren().add(pane);
                });
            });
        });
    }

}
