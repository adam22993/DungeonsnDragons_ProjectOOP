package UI.GUI_Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements MouseListener, ActionListener, MouseMotionListener {
    AudioPanel audioPanel;
    Label label, label2, label3, label4, label5, label6, label7, label8, label9, label10;
    public JButton button, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    LayeredPane panel;
    private int counter = 0;


    public Window() {
        super("Dungeons and Dragons OOP Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(System.getProperty("user.dir") + "/src/UI/Assets/Images/DNDICON.png").getImage());
        this.setSize(1280, 720);
        this.setVisible(true);
        this.setResizable(false);
        panel = new LayeredPane(0,0,1280,720);
        button = new JButton("Start Game");
        button.setBounds(0, 0, 150, 50);
        button.addActionListener(this);
        panel.add(button);
        button2 = new JButton("Pause Game");
        button2.setBounds(25, 0, 150, 50);
        button2.addActionListener(this);
        panel.add(button2);
        this.add(panel);

    }

    @Override
    public void mouseClicked(MouseEvent e) { // checks if mouse is clicked - after releasing press
        System.out.println("Mouse clicked" + counter);
        counter++;
        this.requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) { // checks if mouse is pressed - initial press
        System.out.println("Mouse pressed" + counter);
        counter++;
        this.requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) { // checks if mouse is released - after pressing is released
        System.out.println("Mouse released" + counter);
        counter++;
        this.requestFocus();
    }

    @Override
    public void mouseEntered(MouseEvent e) { // checks if mouse is entered - when mouse enters the window - not labels or panels
        System.out.println("Mouse entered" + counter);
        counter++;
        this.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) { // checks if mouse is exited - when mouse exits the window - not labels or panels
        System.out.println("Mouse exited" + counter);
        counter++;
        this.requestFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged" + counter);
        counter++;
        this.requestFocus();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (counter % 100 == 0)
            System.out.println("Mouse moved" + counter + " (x: " + e.getX() + ", y: " + e.getY() + ")");
        counter++;
        this.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println(button.getText());
        }
        if (e.getSource() == button2) {
            System.out.println(button2.getText());
        }
    }
}
