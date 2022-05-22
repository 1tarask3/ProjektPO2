package pl.po.projekt;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InfoPanel extends JPanel {
    private final static int INFO_PANEL_WIDTH = 600;
    private final static int INFO_PANEL_HEIGHT = 50;
    public JButton newGameButton = new JButton("Nowa gra");
    public JButton newTourButton = new JButton("Nowa tura");
    public JButton readGameButton = new JButton("Wczytaj grę");
    public JButton saveGameButton = new JButton("Zapisz grę");
    public JButton superabilityButton = new JButton("Aktywuj superumiejetność");
    public JTextField newGameXLabel = new JTextField("Szerokość");
    public JTextField newGameYLabel = new JTextField("Wysokość");
    public JLabel abilityActivated = new JLabel("UMIEJĘTNOSĆ AKTYWOWANA");
    public JLabel humansDirectionLabel = new JLabel("Następny ruch gracza: ");
    public JLabel humansDirection = new JLabel();
    private final WorldManager worldManager;

    public InfoPanel(WorldManager worldManager, GameGUI GUI) {
        this.setFocusable(false);
        this.worldManager = worldManager;

        this.newGameButton.addActionListener(e -> {
            try {
                int x = Integer.parseInt(newGameXLabel.getText());
                int y = Integer.parseInt(newGameYLabel.getText());
                if (x > 150) x = 150;
                if (y > 150) y = 150;
                if (x < 10) x = 10;
                if (y < 10) y = 10;
                GUI.newGame(x, y);
                GUI.draw();

                setVisibleButtonsWhenGameInProgress();
            }
            catch (Exception ex) {
                System.out.println("Wprowadź liczby całkowite z przedziału 10-150");
            }
        });

        newTourButton.addActionListener(e -> {
            worldManager.doTour(GUI.getHumansDirection());
            GUI.draw();
            setVisibleButtonsWhenGameInProgress();
        });
        saveGameButton.addActionListener(e -> {
            try {
                File file = new File("zapis.txt");
                file.createNewFile();
                worldManager.saveGame(file);
            }
            catch (Exception ex) {
                System.out.println("Nie udało się zapisać gry" + "\n" + ex);
            }
        });
        readGameButton.addActionListener(e -> {
            File file = new File("zapis.txt");
            worldManager.readGame(file);
            GUI.draw();
            setVisibleButtonsWhenGameInProgress();
        });
        superabilityButton.addActionListener(e -> {
            worldManager.activateSuperability();
            superabilityButton.setVisible(false);
            abilityActivated.setVisible(true);
        });
        newGameButton.setFocusable(false);
        readGameButton.setFocusable(false);
        humansDirection.setFocusable(false);
        humansDirection.setForeground(Color.white);
        humansDirectionLabel.setFocusable(false);
        humansDirectionLabel.setForeground(Color.white);
        abilityActivated.setFocusable(false);
        abilityActivated.setForeground(Color.pink);
        newTourButton.setFocusable(false);
        saveGameButton.setFocusable(false);
        superabilityButton.setFocusable(false);
        this.add(newGameXLabel);
        this.add(newGameYLabel);
        this.add(newGameButton);
        this.add(readGameButton);
        saveGameButton.setVisible(false);
        this.add(saveGameButton);
        newTourButton.setVisible(false);
        this.add(newTourButton);
        humansDirectionLabel.setVisible(false);
        this.add(humansDirectionLabel);
        humansDirection.setVisible(false);
        this.add(humansDirection);
        superabilityButton.setVisible(false);
        this.add(superabilityButton);
        abilityActivated.setVisible(false);
        this.add(abilityActivated);
        this.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(false);
        this.setVisible(true);
    }

    public void setVisibleButtonsWhenGameInProgress() {
        newTourButton.setVisible(true);
        saveGameButton.setVisible(true);

        if (worldManager.isHumanAlive()) {
            humansDirection.setVisible(true);
            humansDirectionLabel.setVisible(true);
            int cooldown = worldManager.getWorld().getHuman().getAbilityCooldown();
            if (cooldown >= 10) {
                superabilityButton.setVisible(true);
            }
            else if (cooldown >= 5){
                abilityActivated.setVisible(false);
            }
        }
        else {
            humansDirection.setVisible(false);
            humansDirectionLabel.setVisible(false);
            superabilityButton.setVisible(false);
        }
    }
}
