package pl.po.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JPanel implements MouseListener {
    private final Point position;
    private final WorldManager worldManager;
    private final Color color;
    private final GameGUI gui;

    Tile(GameGUI gui, int size, Color color, Point position, WorldManager worldManager) {
        this.addMouseListener(this);
        this.position = position;
        this.worldManager = worldManager;
        this.color = color;
        this.gui = gui;
        this.setPreferredSize(new Dimension(size, size));
        this.setBackground(color);
        this.setForeground(color);
        this.setFocusable(true);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (worldManager.getWorld().getOrganismFromBoard(position) == null) new AddNewOrganismFrame(gui, this, worldManager);
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

    public Point getPosition() {
        return position;
    }
}
