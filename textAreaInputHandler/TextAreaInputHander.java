package textAreaInputHandler;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import javax.swing.AbstractAction;
import javax.swing.JTextArea;

import textProcessor.TextProcessor;

public class TextAreaInputHander extends AbstractAction implements DocumentListener {

    private TextProcessor textProcessor;
    private boolean isWaitingForInput;
    private Object inputLock;
    private boolean started;

    public TextAreaInputHander(TextProcessor textProcessor, Object inputLock) {
        this.textProcessor = textProcessor;
        this.isWaitingForInput = false;
        this.inputLock = inputLock;
        this.started = false;
    }

   

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        if (started) {
            Document doc = e.getDocument();
            try {
                String text = doc.getText(0, doc.getLength());
                if ((text.length() > 0) && (!textProcessor.isOnLimit(text))) {
                    SwingUtilities.invokeLater(() -> {
                        try {
                            doc.remove(0, doc.getLength());
                            doc.insertString(0, textProcessor.getCurrentText(), null);
                            if(text.length() > textProcessor.getCurrentText().length()){
                                String textoRestante = text.substring(textProcessor.getCurrentText().length()-1);
                                System.out.println("textoRestante: " + textoRestante);
                                doc.insertString(doc.getLength(), textoRestante, null);

                            }

                           
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                    });
                }
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isWaitingForInput) {

            SwingUtilities.invokeLater(() -> {
                try {
                    JTextArea textArea = (JTextArea) e.getSource();
                    Document doc = textArea.getDocument();
                    doc.insertString(doc.getLength(), "\n", null);
                    textArea.setCaretPosition(doc.getLength());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            synchronized (inputLock) {
                inputLock.notify();
                isWaitingForInput = false;
            }
        }
    }

    public void setWaitingForInput(boolean waitingForInput) {
        isWaitingForInput = waitingForInput;
    }

    public void started() {
        this.started = true;
    }

}
