package pl.po.projekt;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameGUI extends JFrame implements KeyListener {
    private final WorldManager worldManager;
    private final InfoPanel infoPanel;
    private final LogPanel logPanel;
    private final JPanel gamePanel;
    private final static int GAME_PANEL_SIDE_SIDE = 600;
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private Direction humansDirection = null;
    private boolean gameInProgress = false;


    GameGUI(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.darkGray);
        this.setBackground(Color.pink);
        this.setLayout(new BorderLayout());
        this.setTitle("Projekt Java - symulacja świata");
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout());
        gamePanel.setPreferredSize(new Dimension(GAME_PANEL_SIDE_SIDE, GAME_PANEL_SIDE_SIDE));
        gamePanel.setBackground(Color.darkGray);
        gamePanel.setFocusable(true);
        gamePanel.setVisible(true);
        gamePanel.addKeyListener(this);

        logPanel = new LogPanel();
        logPanel.setFocusable(true);
        logPanel.addKeyListener(this);
        infoPanel = new InfoPanel(worldManager, this);
        infoPanel.setFocusable(true);
        infoPanel.addKeyListener(this);
        this.add(logPanel, BorderLayout.EAST);
        this.add(infoPanel, BorderLayout.SOUTH);
        this.add(gamePanel);
        this.pack();
        this.addKeyListener(this);
    }

    public void newGame(int x, int y) {
        gamePanel.setVisible(false);
        gamePanel.removeAll();
        worldManager.GenerateNewWorld(x, y);
        draw();
        gameInProgress = true;
        infoPanel.setVisibleButtonsWhenGameInProgress();
        gamePanel.setVisible(true);
    }

    public void draw() {
        if (worldManager != null && worldManager.getWorld() != null) {
            gamePanel.setVisible(false);
            gamePanel.removeAll();
            this.remove(gamePanel);
            int tileSize = GAME_PANEL_SIDE_SIDE / worldManager.biggerDimension();
            gamePanel.setLayout(new GridLayout(worldManager.getWorld().getX(), worldManager.getWorld().getY()));
            World world = worldManager.getWorld();
            for (int i = 0; i < world.getY(); i++) {
                for (int j = 0; j < world.getX(); j++) {
                    if (world.getOrganismFromBoard(new Point(j, i)) != null) {
                        gamePanel.add(new Tile(this, tileSize, world.getOrganismFromBoard(new Point(j, i)).getColor(), new Point(j, i), worldManager));
                    }
                    else {
                        gamePanel.add(new Tile(this, tileSize, Color.black, new Point(j, i), worldManager));
                    }
                }
            }
        }
        else gamePanel.setBackground(Color.black);
        logPanel.update(worldManager);
        gamePanel.setVisible(true);
        this.add(gamePanel);
        this.pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (infoPanel.humansDirection != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP: case KeyEvent.VK_W:
                    humansDirection = Direction.UP;
                    infoPanel.humansDirection.setText("GÓRA");
                    break;
                case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                    humansDirection = Direction.DOWN;
                    infoPanel.humansDirection.setText("DÓŁ");
                    break;
                case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                    humansDirection = Direction.RIGHT;
                    infoPanel.humansDirection.setText("PRAWO");
                    break;
                case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                    humansDirection = Direction.LEFT;
                    infoPanel.humansDirection.setText("LEWO");
                    break;
                default:
                    humansDirection = null;
                    infoPanel.humansDirection.setText("BRAK");
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setHumansDirection(Direction direction) {
        humansDirection = direction;
    }

    public Direction getHumansDirection() {
        return humansDirection;
    }
}
