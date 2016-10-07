package br.com.feevale.ep.utils;

import javafx.scene.control.TextField;

/**
 * Classe para criação de campos numéricos
 */
public class NumericField extends TextField {
    
    public NumericField() {
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]*")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        if (replacement.matches("[0-9]*")) {
            super.replaceSelection(replacement); 
        }
    }
    
    public int getValue() {
        return Integer.valueOf(getText());
    }
    
}
