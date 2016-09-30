package br.com.feevale.ep.ui;

import br.com.feevale.ep.ProcessoEstado;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * View responsável pela criação da legenda de cores dos processos
 */
public class ViewLegendaCores extends GridPane {
    
    public ViewLegendaCores() {
        initComponents();
    }

    /**
     * Inicializa os componentes
     */
    private void initComponents() {
        ProcessoEstado pe[] = ProcessoEstado.values();
        int linha = 1;
        setPadding(new Insets(10));
        setVgap(5);
        setHgap(8);
        setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, 
                new CornerRadii(1), new BorderWidths(1), new Insets(25))));
        Label legenda = new Label("Legenda de cores:");
        legenda.setFont(Font.font(legenda.getFont().getFamily(), FontWeight.BOLD, legenda.getFont().getSize()));
        add(legenda, 0, 0, 2, 1);
        for (ProcessoEstado processoEstado : pe) {
            addRow(linha++, new Rectangle(20, 20, Color.web(processoEstado.getCor())), 
                    new Label(processoEstado.getStatus()));
        }
    }
    
}
