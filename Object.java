import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Object extends JPanel {
    private JLabel label;
    private boolean isSelected;

    public Object() {
        label = new JLabel("Object Name");
        setLayout(null);
        setBackground(Color.WHITE);
        setOpaque(true);
        setVisible(true);
        add(label);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                isSelected = true;

            }
        });
    }
}
