package UI;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.CountDownLatch;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import textAreaInputHandler.TextAreaInputHander;

import javax.swing.JScrollPane;

public class UI extends JFrame {

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private int fontSize;
    private TextAreaInputHander textAreaInputHandler;
    private CountDownLatch latch;

    public UI(textProcessor.TextProcessor textProcessor, Object inputLock) {

        fontSize = 15;
        latch = new CountDownLatch(1);
        textAreaInputHandler = new TextAreaInputHander(textProcessor, inputLock);
        

        if (SwingUtilities.isEventDispatchThread()) {
            setUpFrame();
            setUpComponents(textProcessor);
            add(scrollPane);
        } else {
            SwingUtilities.invokeLater(() -> {
                setUpFrame();
                setUpComponents(textProcessor);
                add(scrollPane);
            });
        }

    }

    private void setUpComponents(textProcessor.TextProcessor textProcessor) {
        textArea = new JTextArea();
        textArea.setBackground(new Color(10, 10, 10));
        textArea.setForeground(new Color(250, 250, 250));
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.getDocument().addDocumentListener(textAreaInputHandler);

        utiles.BashCaret caret = new utiles.BashCaret();

        caret.setBlinkRate(500); // Parpadeo
        textArea.setCaret(caret);
        textArea.setCaretColor(new Color(250, 250, 250, 200));

        textArea.getInputMap(JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke("ENTER"),
                "enterPressed");
        textArea.getActionMap().put("enterPressed", textAreaInputHandler);

        Action originalBackSpace = textArea.getActionMap()
                .get(textArea.getInputMap().get(KeyStroke.getKeyStroke("BACK_SPACE")));

        textArea.getInputMap(JComponent.WHEN_FOCUSED).put(javax.swing.KeyStroke.getKeyStroke("BACK_SPACE"),
                "backSpacePressed");
        textArea.getActionMap().put("backSpacePressed",
                new textAreaInputHandler.BackSpaceHandler(originalBackSpace, textProcessor));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(new Color(10, 10, 10));
        scrollPane.setSize(getSize());
    }

    private void setUpFrame() {
        setTitle("Bash*t");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void enableInput() {
        SwingUtilities.invokeLater(() -> {
            textArea.setEditable(true);
        });
        textAreaInputHandler.setWaitingForInput(true);
        textAreaInputHandler.started();
    }

    public void disableInput() {
        SwingUtilities.invokeLater(() -> {
            textArea.setEditable(false);
        });
        textAreaInputHandler.setWaitingForInput(false);
    }

    public String getInput() {
        final String[] input = { "" };
        SwingUtilities.invokeLater(() -> {
            input[0] = textArea.getText();
            latch.countDown(); 
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        latch = new CountDownLatch(1);
        return input[0];
    }

    public void setText(String text) {
        SwingUtilities.invokeLater(() -> {
            textArea.setText(text);
        });
    }
}