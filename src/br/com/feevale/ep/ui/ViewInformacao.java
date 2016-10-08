package br.com.feevale.ep.ui;

import br.com.feevale.ep.Escalonador;
import br.com.feevale.ep.utils.NumericField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    /** Controlador do escalonador de processo */
    private final Escalonador escalonador;
    /** Quantum */
    private NumericField quantum;
    /** Tempo de vida máximo */
    private NumericField tempoVida;
    /** Quantidade máxima de processos por minuto */
    private NumericField quantidadeMaximaProcessos;
    /** Probabilidade de IO */
    private Slider probabilidade;
    /** Contador de linha */
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
        quantum.setText("1000");
        quantum.setTooltip(new Tooltip("Intervalo de tempo para execução do processo"));
        addNode(labelQuantum);
        addNode(quantum);
        // Tempo de vida máximo
        Label labelTempoVida = new Label("Tempo de vida máximo:");
        tempoVida = new NumericField();
        tempoVida.setText("10000");
        tempoVida.setTooltip(new Tooltip("Tempo de vida máximo do processo"));
        addNode(labelTempoVida);
        addNode(tempoVida);
        // Tempo de vida máximo
        Label labelQuantidadeMaximaProcessos = new Label("Quantidade máxima de processos:");
        quantidadeMaximaProcessos = new NumericField();
        quantidadeMaximaProcessos.setText("30");
        quantidadeMaximaProcessos.setTooltip(new Tooltip("Quantidade máxima de novos processos por minuto"));
        addNode(labelQuantidadeMaximaProcessos);
        addNode(quantidadeMaximaProcessos);
        // Probabilidade I/O
        Label labelProbabilidadeIO = new Label("Probabilidade I/O:");
        probabilidade = new Slider(0, 1, 0.5);
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
            try {
                valida();
                botao.setText("Parar");
                int vQuantum = quantum.getValue();
                int vTempoVida = tempoVida.getValue();
                int vMaxProcessos = quantidadeMaximaProcessos.getValue();
                double vProbabilidadeIO = probabilidade.getValue();
                escalonador.start(vQuantum, vTempoVida, vMaxProcessos, vProbabilidadeIO);
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            botao.setText("Iniciar");
            escalonador.interrupt();
        }
    }
    
    /**
     * Executa validação dos campos
     * 
     * @throws Exception Erro na validação
     */
    private void valida() throws Exception {
        valida(quantum);
        valida(tempoVida);
        valida(quantidadeMaximaProcessos);
    }
    
    /**
     * Executa validação de um campo
     * 
     * @param field
     * @throws Exception Erro na validação
     */
    private void valida(TextField field) throws Exception {
        if (field.getText().isEmpty()) {
            field.requestFocus();
            throw new Exception("Campo '" + field.getTooltip().getText() + "' é obrigatorio!");
        }
    }
    

}
