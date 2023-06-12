package UI.GUI_Elements;

import javax.swing.*;

public class ButtonCreator extends JButton {
    public ButtonCreator(String text) {
        super();
    }

    public ButtonCreator[] createWelcomeScreenButtons() {
        ButtonCreator[] buttons = new ButtonCreator[2];
        buttons[0] = new ButtonCreator("New Game");
        buttons[1] = new ButtonCreator("Exit");
        return buttons;
    }
}
