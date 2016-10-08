package br.com.feevale.ep.ui;

import br.com.feevale.ep.Escalonador;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Janela de processos
 */
public class ViewProcessos extends BorderPane {

    /** Controlador do escalonador de processo */
    private final Escalonador escalonador;
    /** View CPU */
    private ViewFila viewFilaCPU;
    /** View IO */
    private ViewFila viewFilaIO;

    public ViewProcessos(Escalonador escalonador) {
        this.escalonador = escalonador;
        initLayout();
        initListners();
    }

    /**
     * Inicializa layout da janela
     */
    private void initLayout() {
        setStyle("-fx-background-color:#FFF; -fx-border-color:#CCC; -fx-border-width:0 1 0 0;");
        setPadding(new Insets(5));
        viewFilaCPU = new ViewFila();
        viewFilaIO = new ViewFila();
        setCenter(buildView("Processos CPU", viewFilaCPU));
        setRight(buildView("Processos IO", viewFilaIO));
    }

    /**
     * Inicializa componentes
     */
    private void initListners() {
        escalonador.onStart((filaCPU, filaIO) -> {
            viewFilaCPU.setFila(filaCPU);
            viewFilaIO.setFila(filaIO);
        });
    }

    /**
     * Cria view de fila
     * 
     * @param titulo
     * @param view
     * @return BorderPane
     */
    private BorderPane buildView(String titulo, ViewFila view) {
        BorderPane pane = new BorderPane();
        pane.setPrefWidth(172);
        pane.setStyle("-fx-background-color:#FFF");
        pane.setPadding(new Insets(5));
        Label label = new Label(titulo);
        label.setFont(new Font("Arial", 18));
        label.setTextFill(Color.web("#666"));
        label.setPadding(new Insets(0, 0, 10, 0));
        pane.setTop(label);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(view);
        scroll.setFitToWidth(true);
        pane.setCenter(scroll);
        return pane;
    }

}
