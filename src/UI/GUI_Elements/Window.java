package UI.GUI_Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame implements MouseListener, ActionListener {
    AudioPanel audioPanel;
    Label label;
    JButton button, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    LayeredPane panel;


    public Window() {
        super("Dungeons and Dragons OOP Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(System.getProperty("user.dir") + "/src/UI/Assets/Images/DNDICON.png").getImage());
        this.setSize(1280, 720);
        this.setVisible(true);
        this.setResizable(false);
        panel = new LayeredPane(0,0,1280,720);
        this.add(panel);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Button Pressed");
        }
    }
}
