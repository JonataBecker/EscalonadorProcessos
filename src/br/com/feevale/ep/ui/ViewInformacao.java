package br.com.feevale.ep.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * View responsável pelas informações
 */
public class ViewInformacao extends GridPane {

    /** Quantum */
    private TextField quantum;
    /** Tempo de vida máximo */
    private TextField tempoVida;
    /** Quantidade máxima de processos por minuto */
    private TextField quantidadeMaximaProcessos;
    /** Contador de linha */
    private int line;

    public ViewInformacao() {
        setPrefWidth(300);
        initComponents();
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
        quantum.setTooltip(new Tooltip("Intervalo de tempo para execução do processo"));
        addNode(labelQuantum);
        addNode(quantum);
        // Tempo de vida máximo
        Label labelTempoVida = new Label("Tempo de vida máximo:");
        tempoVida = new TextField();
        tempoVida.setTooltip(new Tooltip("Tempo de vida máximo do processo"));
        addNode(labelTempoVida);
        addNode(tempoVida);
        // Tempo de vida máximo
        Label labelQuantidadeMaximaProcessos = new Label("Quantidade máxima de processos:");
        quantidadeMaximaProcessos = new TextField();
        quantidadeMaximaProcessos.setTooltip(new Tooltip("Quantidade máxima de novos processos por minuto"));
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
