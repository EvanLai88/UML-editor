import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.print.attribute.standard.PrinterLocation;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
    private Canvas canvas;
    private Composite composite;
    private JMenu fileMenu, editMenu;
    private JMenuItem changeName, group, ungroup;
    private int groupCount = 0;

    public MenuBar(Canvas instance) {
        canvas = instance;
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        changeName = new JMenuItem("change object name");
        group = new JMenuItem("Group");
        ungroup = new JMenuItem("UnGroup");

        changeName.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canvas.updateSelectedPanel().isEmpty()){
                    JOptionPane.showMessageDialog(getParent(), "No Object selected!!");
                    return;
                }
                String name = JOptionPane.showInputDialog(getParent(), "Change name", null);
                // System.out.println(name);
                for(Object obj: canvas.updateSelectedPanel()) {
                    obj.Name = name;
                    canvas.revalidate();
                    canvas.repaint();
                }
            }
        });

        group.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canvas.updateSelectedPanel().isEmpty()){
                    // JOptionPane.showMessageDialog(getParent(), "No Object selected!!");
                    return;
                }
                int x=3000, y=3000, x0=0, y0=0, width=0, height=0;
                for(Object obj: canvas.updateSelectedPanel()){
                    if(obj.getX() < x) {
                        x = obj.getX();
                    }
                    if(obj.getY() < y) {
                        y = obj.getY();
                    }
                    if(obj.getX()+obj.getWidth() > x0) {
                        x0=obj.getX()+obj.getWidth();
                    }
                    if(obj.getY()+obj.getHeight() > y0) {
                        y0=obj.getY()+obj.getHeight();
                    }
                }
                width = x0 - x;
                height = y0 - y;
                composite = new Composite(canvas, "Group"+String.valueOf(groupCount++), x, y, width, height);
                canvas.addPanel(composite);
                
                for(Object obj: canvas.updateSelectedPanel()) {
                    canvas.removePanel(obj);
                    obj.setLocation(obj.getX()-x+10, obj.getY()-y+10);
                    composite.addPanel(obj);
                    obj.setOrigin(composite);
                }
                canvas.revalidate();
                canvas.repaint();
                canvas.unselectAll();
            }
        });

        ungroup.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canvas.updateSelectedPanel().isEmpty()){
                    // JOptionPane.showMessageDialog(getParent(), "No Object selected!!");
                    return;
                }
                int x, y, width, height;
                ArrayList<Object> tmpList = new ArrayList<Object>();
                for(Object obj: canvas.updateSelectedPanel()){
                    if( obj instanceof Composite) {
                        tmpList.clear();
                        tmpList.addAll(((Composite)obj).getpanelList());
                        for(Object object: tmpList) {
                            ((Composite)obj).removePanel(object);
                            object.setOrigin(null);
                            object.setLocation(object.getX()+obj.getX(), object.getY()+obj.getY());
                            canvas.addPanel(object);
                        }
                        canvas.removePanel(obj);
                    }
                }
                canvas.revalidate();
                canvas.repaint();
                canvas.unselectAll();
            }
        });

        editMenu.add(changeName);
        editMenu.add(group);
        editMenu.add(ungroup);

        add(fileMenu);
        add(editMenu);
    }
}
