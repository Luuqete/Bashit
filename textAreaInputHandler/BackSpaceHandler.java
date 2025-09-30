package textAreaInputHandler;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import textProcessor.TextProcessor;

public class BackSpaceHandler extends AbstractAction {

    Action originalBackSpace;
    TextProcessor textProcessor;

    public BackSpaceHandler(Action originalBackSpace, TextProcessor textProcessor) {
        this.originalBackSpace = originalBackSpace;
        this.textProcessor = textProcessor;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
        originalBackSpace.actionPerformed(e);
        JTextArea textArea = (JTextArea) e.getSource();
        Document doc = textArea.getDocument();
        if (doc.getLength() == 0) {
            try {
                String text = doc.getText(0, doc.getLength());
                doc.insertString(0, textProcessor.getCurrentText(), null);
            } catch (BadLocationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }
}
