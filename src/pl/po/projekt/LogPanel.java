package pl.po.projekt;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import java.awt.Color;

public class LogPanel extends JPanel {
    private final JTextArea textArea;

    LogPanel() {
        this.setFocusable(false);
        textArea = new JTextArea();
        textArea.setFocusable(false);
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(380, 500));
        textArea.setBackground(Color.white);
        textArea.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
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
