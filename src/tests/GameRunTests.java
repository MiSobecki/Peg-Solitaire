package tests;

import structures.numerators.TypeOfBoard;
import windows.MainWindow;

import java.awt.*;

public class GameRunTests {

    public static void runGame() {
        new MainWindow(Color.black, Color.blue,
                TypeOfBoard.BRITISH);
    }
}
