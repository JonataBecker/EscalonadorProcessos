package br.com.feevale.ep.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        stage.setTitle("Escalonador de processos - Round Robin");
        stage.setMaximized(true);
        stage.setScene(new Scene(pane));
        stage.getIcons().add(new Image(Escalonador.class.getResourceAsStream("/br/com/feevale/ep/res/icone.png")));
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
