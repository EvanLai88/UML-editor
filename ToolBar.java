import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;


public class ToolBar implements MouseInputListener {
    private static JToolBar toolbar;
    private static ToolbarBtn selectBtn, assoBtn, genBtn, comBtn, classBtn, useCaseBtn;

    public ToolBar() {
        ImageIcon selIcon = new ImageIcon("img/select.png");
        ImageIcon assIcon = new ImageIcon("img/associate.png");
        ImageIcon genIcon = new ImageIcon("img/general.png");
        ImageIcon comIcon = new ImageIcon("img/composite.png");
        ImageIcon claIcon = new ImageIcon("img/class.png");
        ImageIcon useIcon = new ImageIcon("img/usecase.png");
        
        toolbar = new JToolBar(null, JToolBar.VERTICAL);

        selectBtn = new ToolbarBtn("select", selIcon);
        assoBtn = new ToolbarBtn("associate", assIcon);
        genBtn = new ToolbarBtn("general", genIcon);
        comBtn = new ToolbarBtn("composite", comIcon);
        classBtn = new ToolbarBtn("class", claIcon);
        useCaseBtn = new ToolbarBtn("use case", useIcon);

        toolbar.add(selectBtn);
        toolbar.add(assoBtn);
        toolbar.add(genBtn);
        toolbar.add(comBtn);
        toolbar.add(classBtn);
        toolbar.add(useCaseBtn);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        toolbar.setBorder(blackline);
        toolbar.setFloatable(false);

    }

    public static JToolBar getInstance() {
        return toolbar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
