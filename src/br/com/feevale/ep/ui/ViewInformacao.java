package br.com.feevale.ep.ui;

import br.com.feevale.ep.Escalonador;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * View responsável pelas informações
 */
public class ViewInformacao extends GridPane {

    /** Controlador do escalonador de processo */
    private final Escalonador escalonador;
    /** Quantum */
    private TextField quantum;
    /** Tempo de vida máximo */
    private TextField tempoVida;
    /** Quantidade máxima de processos por minuto */
    private TextField quantidadeMaximaProcessos;
    /** Contador de linha */
    private int line;

    public ViewInformacao(Escalonador escalonador) {
        this.escalonador = escalonador;
        setPrefWidth(300);
        initComponents();
        escalonador.start();
    }

    /**
     * Inicializa componentes
     */
    private void initComponents() {
        setPadding(new Insets(25));
        ColumnConstraints comConstraints = new ColumnConstraints();
        comConstraints.setPercentWidth(100);
        getColumnConstraints().addAll(comConstraints);
        setVgap(5);
        // Quantum
        Label labelQuantum = new Label("Quantum:");
        quantum = new TextField();
        addNode(labelQuantum);
        addNode(quantum);
        // Tempo de vida máximo
        Label labelTempoVida = new Label("Tempo de vida máximo:");
        tempoVida = new TextField();
        addNode(labelTempoVida);
        addNode(tempoVida);
        // Tempo de vida máximo
        Label labelQuantidadeMaximaProcessos = new Label("Quantidade máxima de processos:");
        quantidadeMaximaProcessos = new TextField();
        addNode(labelQuantidadeMaximaProcessos);
        addNode(quantidadeMaximaProcessos);
    }

    /**
     * Adiciona componente na janela
     *
     * @param node
     */
    private void addNode(Node node) {
        addRow(line++, node);
    }

}
