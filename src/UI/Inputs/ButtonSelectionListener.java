package UI.Inputs;

public class ButtonSelectionListener implements Patterns.ButtonSelectionListener{
    @Override
    public int onButtonSelected(int buttonIndex) {
        System.out.println("Button " + buttonIndex + " was selected");
        return buttonIndex;
    }
}
