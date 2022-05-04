import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InfoPanel extends JPanel {
    private final static int INFO_PANEL_WIDTH = 600;
    private final static int INFO_PANEL_HEIGHT = 200;
    public JButton newGameButton;
    public JButton newTourButton;
    public JButton readGameButton;
    public JTextField newGameXLabel;
    public JTextField newGameYLabel;
    public JLabel humansDirectionLabel;
    public JLabel humansDirection;
    private WorldManager worldManager;
    private GameGUI GUI;

    InfoPanel(WorldManager worldManager, GameGUI GUI) {
        this.setFocusable(false);
        this.worldManager = worldManager;
        this.GUI = GUI;

        this.newGameXLabel = new JTextField("Szerokość planszy");
        this.newGameYLabel = new JTextField("Wysokość planszy");
        this.newGameButton = new JButton("Nowa gra");
        newGameButton.setFocusable(false);
        this.newTourButton = new JButton("Nowa tura");
        newTourButton.setFocusable(false);
        this.readGameButton = new JButton("Wczytaj grę");
        readGameButton.setFocusable(false);
        this.humansDirectionLabel = new JLabel("Następny ruch gracza: ");
        humansDirectionLabel.setFocusable(false);
        this.humansDirection = new JLabel("BRAK");
        humansDirection.setFocusable(false);

        this.newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                }
            }
        });

        newTourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worldManager.doTour(GUI.getHumansDirection());
                GUI.draw();
            }
        });

        this.add(newGameXLabel);
        this.add(newGameYLabel);
        this.add(newGameButton);
        this.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(false);
        this.setVisible(true);
    }


}
