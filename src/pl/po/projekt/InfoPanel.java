package pl.po.projekt;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InfoPanel extends JPanel {
    private final static int INFO_PANEL_WIDTH = 600;
    private final static int INFO_PANEL_HEIGHT = 200;
    public JButton newGameButton = new JButton("Nowa gra");
    public JButton newTourButton = new JButton("Nowa tura");
    public JButton readGameButton = new JButton("Wczytaj grę");
    public JButton saveGameButton = new JButton("Zapisz grę");
    public JTextField newGameXLabel = new JTextField("Szerokość planszy");
    public JTextField newGameYLabel = new JTextField("Wysokość planszy");
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
            }
            catch (Exception ex) {
                System.out.println("Wprowadź liczby całkowite z przedziału 10-150");
            }
        });

        newTourButton.addActionListener(e -> {
            worldManager.doTour(GUI.getHumansDirection());
            GUI.draw();
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
        this.add(newGameXLabel);
        this.add(newGameYLabel);
        this.add(newGameButton);
        this.add(readGameButton);
        this.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(false);
        this.setVisible(true);
    }

    public void setVisibleButtonsWhenGameInProgress() {
        this.add(saveGameButton);
        this.add(newTourButton);
        this.add(humansDirectionLabel);
        this.add(humansDirection);
    }
}
