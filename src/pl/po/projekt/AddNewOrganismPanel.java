package pl.po.projekt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddNewOrganismPanel extends JFrame {
    private final static OrganismsArrayList organismsList = new OrganismsArrayList();

    public AddNewOrganismPanel(GameGUI gui, Tile tile, WorldManager worldManager) {
        this.setTitle("Dodaj nowy organizm");
        this.setPreferredSize(new Dimension(100, 600));
        JPanel panel = new JPanel();
        ArrayList<ChooseOrganismButton> buttons = new ArrayList<>();
        for (Class<? extends Organism> aClass : organismsList) {
            try {
                Organism organism = aClass.newInstance();
                buttons.add(new ChooseOrganismButton(gui, this, organism, tile, worldManager));
            } catch (Exception ex) {

            }
        }
        for (ChooseOrganismButton button : buttons) {
            panel.add(button);
        }
        this.add(panel);
        pack();
        setVisible(true);
    }
}
