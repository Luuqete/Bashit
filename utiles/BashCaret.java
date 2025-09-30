package utiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class BashCaret extends DefaultCaret {

    @Override
    protected synchronized void damage(Rectangle r) {
        if (r == null)
            return;
        // Dibuja todo el ancho del caracter
        x = r.x;
        y = r.y;
        width = getComponent().getFontMetrics(getComponent().getFont()).charWidth('W');
        height = r.height;
        repaint();
    }

    public void paint(Graphics g) {
        JTextComponent comp = getComponent();
        if(!isVisible()) return;

        if (comp == null)
            return;

        try {
            Shape shape = comp.modelToView2D(getDot());
            if (shape == null)
                return;

            Graphics2D g2 = (Graphics2D) g.create();
            Rectangle2D r = shape.getBounds2D();
            g2.setColor(comp.getCaretColor());
            int charWidth = comp.getFontMetrics(comp.getFont()).charWidth('M');
            g2.fillRect((int) r.getX(), (int) r.getY(), charWidth, (int) r.getHeight());

            int pos = getDot();
            Document doc = comp.getDocument();
            if (pos < doc.getLength()) {
                String ch = doc.getText(pos, 1);

                g2.setColor(comp.getBackground()); // ahora sí se va a ver
                g2.setFont(comp.getFont());

                int baseline = (int) r.getY() + comp.getFontMetrics(comp.getFont()).getAscent();

                g2.drawString(ch, (int) r.getX(), baseline);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}