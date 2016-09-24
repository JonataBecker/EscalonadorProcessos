package br.com.feevale.ep.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe responsável pela interface principal
 */
public class Escalonador extends Application {

    private BorderPane pane;
    
    @Override
    public void start(Stage stage) throws Exception {
        initComponents();
        stage.setTitle("Escacolador de processos - Round Robin");
        stage.setMaximized(true);
        stage.setScene(new Scene(pane));
        stage.show();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        pane = new BorderPane();
        pane.setMinSize(960, 800);
        pane.setCenter(new ViewProcessos());
        pane.setRight(new ViewInformacao());
    }
    
    public static void run(String[] args) {
        launch(args);
    }
    
}
