package windows;

import javax.swing.*;
import java.awt.*;

public class GameTimer extends JLabel {
    private int time;

    GameTimer() {
        time = 0;
        updateTime();
        setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBounds(0, 520, 420, 100);
    }

    public void updateTime() {
        setText(String.format("%d%d : %d%d", time / 60 / 10, time / 60 % 10, time % 60 / 10, time % 60 % 10));
        time++;
    }
}
