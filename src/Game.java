import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Game {
    public static void main(String[] args) {
        new GameGUI(new WorldManager());
    }
}
