package UI.GUI_Elements;

import Controller.Messages.SystemMessagesController;
import Controller.Messages.MessageCallback;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Vector;

public class AudioPanel {
    JPanel audioPanel;
    boolean isMuted;
    boolean isPlaying;
    float volume;

    Vector<Clip> clips;

    SystemMessagesController messagesController;

    public AudioPanel(MessageCallback messagesController) {
        audioPanel = new Panel();
        audioPanel.setLayout(new BoxLayout(audioPanel, BoxLayout.Y_AXIS));
        audioPanel.add(new Label("Audio"));
        audioPanel.add(new JButton("Mute"));
        audioPanel.add(new JButton("Volume Up"));
        audioPanel.add(new JButton("Volume Down"));
        audioPanel.add(new JButton("Next"));
        audioPanel.add(new JButton("Previous"));
        audioPanel.add(new JButton("Play/Pause"));

        isMuted = false;
        isPlaying = false;
        volume = 0.5f;
    }
}
