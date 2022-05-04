import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    Tile(int size, Color color) {
        this.setPreferredSize(new Dimension(size, size));
        this.setBackground(color);
        this.setForeground(color);
        this.setFocusable(true);
        this.setVisible(true);
    }
}
