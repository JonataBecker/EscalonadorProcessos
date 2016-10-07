package br.com.feevale.ep.ui;

import br.com.feevale.ep.Escalonador;
import br.com.feevale.ep.utils.NumericField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * View responsável pelas informações
 */
public class ViewInformacao extends GridPane {

    /**
     * Controlador do escalonador de processo
     */
    private final Escalonador escalonador;
    /**
     * Quantum
     */
    private TextField quantum;
    /**
     * Tempo de vida máximo
     */
    private TextField tempoVida;
    /**
     * Quantidade máxima de processos por minuto
     */
    private TextField quantidadeMaximaProcessos;
    /**
     * Contador de linha
     */
    private int line;

    public ViewInformacao(Escalonador escalonador) {
        this.escalonador = escalonador;
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
        quantum = new NumericField();
        quantum.setTooltip(new Tooltip("Intervalo de tempo para execução do processo"));
        addNode(labelQuantum);
        addNode(quantum);
        // Tempo de vida máximo
        Label labelTempoVida = new Label("Tempo de vida máximo:");
        tempoVida = new NumericField();
        tempoVida.setTooltip(new Tooltip("Tempo de vida máximo do processo"));
        addNode(labelTempoVida);
        addNode(tempoVida);
        // Tempo de vida máximo
        Label labelQuantidadeMaximaProcessos = new Label("Quantidade máxima de processos:");
        quantidadeMaximaProcessos = new NumericField();
        quantidadeMaximaProcessos.setTooltip(new Tooltip("Quantidade máxima de novos processos por minuto"));
        addNode(labelQuantidadeMaximaProcessos);
        addNode(quantidadeMaximaProcessos);
        // Probabilidade I/O
        Label labelProbabilidadeIO = new Label("Probabilidade I/O:");
        Slider probabilidade = new Slider(0, 1, 0.5);
        probabilidade.setShowTickLabels(true);
        probabilidade.setShowTickMarks(true);
        probabilidade.setMajorTickUnit(0.1);
        probabilidade.setMinorTickCount(1);
        probabilidade.setBlockIncrement(0.05);
        probabilidade.setSnapToTicks(true);
        probabilidade.setTooltip(new Tooltip("Probabilidade de um novo processo ser I/O bound"));
        addNode(labelProbabilidadeIO);
        addNode(probabilidade);
        // Botão para iniciar ou parar a simulação
        Button botaoSimulacao = new Button("Iniciar");
        botaoSimulacao.setMinWidth(250);
        botaoSimulacao.setOnAction((ActionEvent e) -> {
            acaoBotaoSimulacao(botaoSimulacao);
        });
        addNode(botaoSimulacao);
    }

    /**
     * Adiciona componente na janela
     *
     * @param node
     */
    private void addNode(Node node) {
        addRow(line++, node);
    }

    /**
     * Trata ação de clique no botão de simulação
     * @param botao 
     */
    private void acaoBotaoSimulacao(Button botao) {
        if (botao.getText().equals("Iniciar")) {
            botao.setText("Parar");
            escalonador.start();
        } else {
            botao.setText("Iniciar");
            escalonador.interrupt();
        }
    }

}
