package windows;

import structures.GameBoard;

import javax.swing.*;
import java.awt.*;

public class GamePhaseInformerWindow extends JLabel {

    GameBoard gameBoard;
    Timer timer;

    public GamePhaseInformerWindow(GameBoard gameBoard, Timer timer) {
        this.gameBoard = gameBoard;
        this.timer = timer;
        setUp();
    }

    private void setUp() {
        setText("There are " + gameBoard.getCountOfBalls() + " balls left");
        setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBounds(0, 520, 420, 100);
    }

    public void changeText() {
        if (gameBoard.getCountOfBalls() == 1) {
            timer.stop();
            setText("YOU WON!!!");
        } else if (!gameBoard.areAvailableMoves()) {
            timer.stop();
            setText("YOU LOST!!!");
        } else setText("There are " + gameBoard.getCountOfBalls() + " balls left");
        System.out.println(getText());
    }
}
