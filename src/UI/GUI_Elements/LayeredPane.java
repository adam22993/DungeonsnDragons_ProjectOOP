package UI.GUI_Elements;

import javax.swing.*;

public class LayeredPane extends JLayeredPane {

    ButtonCreator buttonCreator = new ButtonCreator("yo");
    public LayeredPane(int x, int y, int width, int height) {
        super();
        setBounds(x, y, width, height);
        setOpaque(false);
    }

    public JPanel addStartMenuButtons(JButton[] buttons) {
        JPanel panel = new Panel();
        panel.setOpaque(false);
        panel.setSize(200,300);
        for (int i = 0; i < buttons.length; i++) {
            add(buttons[i], 0, i);
        }
        return panel;
    }

    public void setBackGroundImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        label.setVisible(true);
        add(label, 0, 0);
    }


//    public void addComponent(JComponent component, int layer, int position) {
//        add(component, layer, position);
//    }
//
//    public void addComponent(JComponent component, int layer) {
//        add(component, layer);
//    }
//
//    public void addComponent(JComponent component) {
//        add(component);
//    }
//
//    public void removeComponent(JComponent component) {
//        remove(component);
//    }
//
//    public void removeComponent(int index) {
//        remove(index);
//    }
//
//    public void removeAllComponents() {
//        removeAll();
//    }
//
//    public void setComponentPosition(JComponent component, int x, int y) {
//        component.setBounds(x, y, component.getWidth(), component.getHeight());
//    }
//
//    public void setComponentSize(JComponent component, int width, int height) {
//        component.setBounds(component.getX(), component.getY(), width, height);
//    }
//
//    public void setComponentBounds(JComponent component, int x, int y, int width, int height) {
//        component.setBounds(x, y, width, height);
//    }
//
//    public void setComponentLayer(JComponent component, int layer) {
//        setLayer(component, layer);
//    }
//
//    public void setComponentPositionAndSize(JComponent component, int x, int y, int width, int height) {
//        component.setBounds(x, y, width, height);
//    }
//
//    public void setComponentPositionAndSize(JComponent component, int x, int y) {
//        component.setBounds(x, y, component.getWidth(), component.getHeight());
//    }
//

}
