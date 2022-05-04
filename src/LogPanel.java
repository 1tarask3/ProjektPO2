import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.xml.soap.Text;
import java.awt.*;

public class LogPanel extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    LogPanel() {
        this.setFocusable(false);
        textArea = new JTextArea();
        textArea.setFocusable(false);
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(380, 500));
        textArea.setBackground(Color.white);
        textArea.setVisible(true);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setFocusable(false);
        scrollPane.setPreferredSize(new Dimension(380, 520));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setBorder(new TitledBorder(new EtchedBorder(), "Komunikaty"));
        this.setPreferredSize(new Dimension(400, 560));
        this.add(scrollPane);
        this.setBackground(Color.white);
        this.setFocusable(false);
        this.setVisible(true);
    }

    public void update(WorldManager worldManager) {
        textArea.setRows(worldManager.getWorld().logs.size());
        textArea.setText(worldManager.getWorld().getLogs());
    }
}
