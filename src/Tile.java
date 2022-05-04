import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel implements MouseListener {
    private Point position;
    private World world;
    private Color color;

    Tile(int size, Color color, Point position, World world) {
        this.addMouseListener(this);
        this.position = position;
        this.world = world;
        this.color = color;
        this.setPreferredSize(new Dimension(size, size));
        this.setBackground(color);
        this.setForeground(color);
        this.setFocusable(true);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.color == Color.black) {
            setBackground(new Color(30,30,30));
        }
        else {
            setBackground(this.color.brighter());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(color);
    }
}
