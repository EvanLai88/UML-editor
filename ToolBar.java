import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.AbstractAction;

public class ToolBar extends JToolBar {
    private Canvas canvas;
    public UMLeditor uml;
    public JButton selectBtn, assoBtn, genBtn, comBtn, classBtn, useCaseBtn;
    public JButton mode = null;
    public ArrayList<JButton> BtnList;
    public Color unSet = Color.LIGHT_GRAY;
    public Color set = Color.BLACK;

    public ToolBar(Canvas c) {
        canvas = c;
        selectBtn = new JButton(new ImageIcon("img/select.png"));
        assoBtn = new JButton(new ImageIcon("img/associate.png"));
        genBtn = new JButton(new ImageIcon("img/general.png"));
        comBtn = new JButton(new ImageIcon("img/composite.png"));
        classBtn = new JButton(new ImageIcon("img/class.png"));
        useCaseBtn = new JButton(new ImageIcon("img/usecase.png"));
        
        
        BtnList = new ArrayList<JButton>();
        BtnList.add(selectBtn);
        BtnList.add(assoBtn);
        BtnList.add(genBtn);
        BtnList.add(comBtn);
        BtnList.add(classBtn);
        BtnList.add(useCaseBtn);
        
        for(JButton b: BtnList) {
            btnInit(b);
            b.addActionListener(new AbstractAction() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JButton jb: BtnList) {
                        jb.setBackground(unSet);
                    }
                    b.setBackground(set);
                    mode = b;
                    canvas.setMode(mode.getName());
                    // System.out.println(mode.getName());
                }
                
            });
        }

        selectBtn.setName("select");
        assoBtn.setName("associate");
        genBtn.setName("general");
        comBtn.setName("composite");
        classBtn.setName("class");
        useCaseBtn.setName("use Case");
        
        setOrientation(VERTICAL);
        add(selectBtn);
        add(assoBtn);
        add(genBtn);
        add(comBtn);
        add(classBtn);
        add(useCaseBtn);
        
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setFloatable(false);

    }

    private void btnInit(JButton btn) {
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
    }
}
