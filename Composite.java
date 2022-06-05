import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Composite extends Object {
    private ArrayList<Object> panelList;

    public Composite(String name, int x, int y, int width, int height) {
        super(name, width+20, height+20);
        isComposite = true;
        setLocation(x-10, y-10);
        setOpaque(true);
        panelList = new ArrayList<Object>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawString(Name, 0, 12);
        g.drawRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void unselectAll() {
        for(Object obj: panelList) {
            obj.setSelect(false);
            obj.unselectAll();
        }
    }

    public void addPanel(Object obj) {
        add(obj);
        panelList.add(obj);
    }

    public void removePanel(Object obj) {
        remove(obj);
        panelList.remove(obj);
        obj.setSelect(false);
    }

    public ArrayList<Object> getpanelList() {
        return panelList;
    }
}
