package windows;

import structures.GameBoard;
import structures.Point;

import javax.swing.*;
import java.awt.*;

public class GameBoardWindow extends JPanel {

    private final GameBoard gameBoard;
    private boolean isSelected;
    private Point selectedPoint;
    private Color boardColor, pawnColor;
    private boolean isFilled;

    public GameBoardWindow(GameBoard gameBoard, Color boardColor, Color pawnColor) {
        this.gameBoard = gameBoard;
        setBounds(0, 100, 420, 420);
        this.isSelected = false;
        this.isFilled = false;
        this.boardColor = boardColor;
        this.pawnColor = pawnColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Point p : gameBoard.getSetOfPoints()) {

            g.setColor(boardColor);
            g.fillRect(p.getX(), p.getY(), 60, 60);

            if (p.includesBall()) {
                g.setColor(pawnColor);
                g.fillOval(p.getX(), p.getY(), 60, 60);
                if (isFilled) {
                    g.setColor(Color.green);
                    g.fillOval(p.getX() + 10, p.getY() + 10, 40, 40);
                }
            } else {
                g.setColor(Color.white);
                g.fillOval(p.getX(), p.getY(), 60, 60);
            }
        }

        if (isSelected) {
            g.setColor(Color.red);
            g.fillOval(selectedPoint.getX(), selectedPoint.getY(), 60, 60);

            g.setColor(pawnColor);
            g.fillOval(selectedPoint.getX() + 5, selectedPoint.getY() + 5, 50, 50);

            if (isFilled) {
                g.setColor(Color.green);
                g.fillOval(selectedPoint.getX() + 10, selectedPoint.getY() + 10, 40, 40);
            }
        }
    }

    public void selectPoint(int x, int y) {
        isSelected = true;
        selectedPoint = gameBoard.getPoint(x, y);
    }

    public void unselectPoint() {
        isSelected = false;
    }

    public void setBoardColor(Color boardColor) {
        this.boardColor = boardColor;
    }

    public void setPawnColor(Color pawnColor) {
        this.pawnColor = pawnColor;
    }

    public void fillPoints() {
        this.isFilled = true;
    }

    public void unFillPoints() {
        this.isFilled = false;
    }
}
