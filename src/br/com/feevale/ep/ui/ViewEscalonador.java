package br.com.feevale.ep.ui;

import br.com.feevale.ep.Escalonador;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe responsável pela interface principal
 */
public class ViewEscalonador extends Application {

    private final Escalonador escalonador;
    private BorderPane pane;
    private BorderPane paneInfo;

    public ViewEscalonador() {
        this.escalonador = new Escalonador();
    }

    @Override
    public void start(Stage stage) throws Exception {
        initComponents();
        stage.setTitle("Escalonador de processos - Round Robin");
        stage.setMaximized(true);
        stage.setScene(new Scene(pane));
        stage.getIcons().add(new Image(ViewEscalonador.class.getResourceAsStream("/br/com/feevale/ep/res/icone.png")));
        stage.show();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        pane = new BorderPane();
        pane.setCenter(buildProcessPane());
        pane.setRight(buildPanelLateral());
    }

    public static void run(String[] args) {
        launch(args);
    }

    /**
     * Criar painel de processos
     * 
     * @return Node
     */
    private Node buildProcessPane() {
        return new ViewProcessos(escalonador);
    }
    
    /**
     * Cria painel lateral com informações do escalonador
     * 
     * @return paneInfo
     */
    private Node buildPanelLateral() {
        paneInfo = new BorderPane();
        paneInfo.setCenter(new ViewInformacao(escalonador));
        paneInfo.setBottom(new ViewLegendaCores());
        return paneInfo;
    }

}
