package windows;

import actions.PointMover;
import mouse.events.GameBoardMover;
import structures.GameBoard;
import structures.numerators.TypeOfBoard;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final Color boardColor, pawnColor;
    private final TypeOfBoard typeOfBoard;
    private final int x,y;

    public MainWindow(Color boardColor, Color pawnColor, TypeOfBoard typeOfBoard) {
        this.typeOfBoard = typeOfBoard;
        this.boardColor = boardColor;
        this.pawnColor = pawnColor;
        this.x = 432;
        this.y = 620;
        setUpWindow();
    }

    MainWindow(Color boardColor, Color pawnColor, TypeOfBoard typeOfBoard,int x,int y){
        this.typeOfBoard = typeOfBoard;
        this.boardColor = boardColor;
        this.pawnColor = pawnColor;
        this.x = x;
        this.y = y;
        setUpWindow();
    }

    public void setUpWindow() {
        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setBounds(0, 0, 420, 620);

        GameBoard gameBoard = new GameBoard(typeOfBoard);
        GamePhaseInformerWindow gamePhaseInformerWindow = new GamePhaseInformerWindow(gameBoard);
        GameBoardWindow gameBoardWindow = new GameBoardWindow(gameBoard, boardColor, pawnColor);
        PointMover pointMover = new PointMover(gameBoard,
                gameBoardWindow, gamePhaseInformerWindow);
        GameMenuWindow gameMenuWindow = new GameMenuWindow(this, gameBoardWindow,
                pointMover, typeOfBoard);
        GameBoardMover gameBoardMover = new GameBoardMover(pointMover, this);

        gameBoardWindow.addMouseListener(gameBoardMover);

        setJMenuBar(gameMenuWindow);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(gamePhaseInformerWindow, BorderLayout.SOUTH);
        contentPane.add(gameBoardWindow, BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(this);
    }
}
