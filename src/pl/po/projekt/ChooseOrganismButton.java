package pl.po.projekt;

import javax.swing.*;
import java.awt.*;

public class ChooseOrganismButton extends JButton {
    public Organism organism;
    public ChooseOrganismButton(GameGUI gui, JFrame frame, Organism organism, Tile tile, WorldManager worldManager) {
        this.organism = organism;
        this.setBackground(organism.getColor());
        this.setText(organism.getName());
        this.setPreferredSize(new Dimension(100, 50));
        this.setVisible(true);

        this.addActionListener(e -> {
            try {
                organism.getClass().getDeclaredConstructor(Point.class, World.class).newInstance(tile.getPosition(), worldManager.getWorld());
                frame.setVisible(false);
                frame.dispose();
                gui.draw();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public Organism getOrganism() {
        return organism;
    }
}
