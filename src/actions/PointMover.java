package actions;

import actions.numerators.Phase;
import structures.GameBoard;
import structures.Point;
import windows.GameBoardWindow;
import windows.GamePhaseInformerWindow;

import static java.lang.Math.abs;

public class PointMover {

    private int x1, y1, x2, y2;
    private final GameBoard gameBoard;
    private final GameBoardWindow gameBoardWindow;
    private final GamePhaseInformerWindow gamePhaseInformerWindow;
    private Phase phase;

    public PointMover(GameBoard gameBoard, GameBoardWindow gameBoardWindow,
                      GamePhaseInformerWindow gamePhaseInformerWindow) {
        this.gameBoard = gameBoard;
        this.gameBoardWindow = gameBoardWindow;
        this.gamePhaseInformerWindow = gamePhaseInformerWindow;
        this.x1 = -1;
        this.y1 = -1;
        this.phase = Phase.BEGIN;
    }

    public void move() {
        if (phase == Phase.END && gameBoard.existPoint(x2, y2)) {
            Point p1 = gameBoard.getPoint(x1, y1);
            Point p2 = gameBoard.getPoint(x2, y2);

            if (!p2.includesBall()) {
                if (x2 - x1 == 120 && abs(y2 - y1) == 0) {
                    Point p3 = gameBoard.getPoint(x2 - 60, y2);
                    if(!p3.includesBall())
                        return;
                    Point.moveBall(p1, p2);
                    p3.removeBall();
                    gameBoard.reduceCountOfBalls();
                } else if (x2 - x1 == -120 && abs(y2 - y1) == 0) {
                    Point p3 = gameBoard.getPoint(x2 + 60, y2);
                    if(!p3.includesBall())
                        return;
                    Point.moveBall(p1, p2);
                    p3.removeBall();
                    gameBoard.reduceCountOfBalls();
                } else if (y2 - y1 == 120 && abs(x2 - x1) == 0) {
                    Point p3 = gameBoard.getPoint(x2, y2 - 60);
                    if(!p3.includesBall())
                        return;
                    Point.moveBall(p1, p2);
                    p3.removeBall();
                    gameBoard.reduceCountOfBalls();
                } else if (y2 - y1 == -120 && abs(x2 - x1) == 0) {
                    Point p3 = gameBoard.getPoint(x2, y2 + 60);
                    if(!p3.includesBall())
                        return;
                    Point.moveBall(p1, p2);
                    p3.removeBall();
                    gameBoard.reduceCountOfBalls();
                }
            }

            x1 = -1;
            y1 = -1;

            gameBoardWindow.unselectPoint();
            gameBoardWindow.repaint();
            gamePhaseInformerWindow.changeText();
            setPhase(Phase.BEGIN);

        } else {
            if (!gameBoard.existPoint(x1, y1) || !gameBoard.getPoint(x1, y1).includesBall()) {
                x1 = -1;
                y1 = -1;
                gameBoardWindow.unselectPoint();
            } else {
                gameBoardWindow.selectPoint(x1, y1);
                gameBoardWindow.repaint();
            }
        }
    }

    public static void goUp(PointMover pointMover) {
        if (pointMover.getX1() >= 0 && pointMover.getY1() >= 0) {
            int x2 = pointMover.getX1();
            int y2 = pointMover.getY1() - 120;

            pointMover.setX2(x2);
            pointMover.setY2(y2);
            pointMover.setPhase(Phase.END);
            pointMover.move();
        }
    }

    public static void goDown(PointMover pointMover) {
        if (pointMover.getX1() >= 0 && pointMover.getY1() >= 0) {
            int x2 = pointMover.getX1();
            int y2 = pointMover.getY1() + 120;

            pointMover.setX2(x2);
            pointMover.setY2(y2);
            pointMover.setPhase(Phase.END);
            pointMover.move();
        }
    }

    public static void goRight(PointMover pointMover) {
        if (pointMover.getX1() >= 0 && pointMover.getY1() >= 0) {
            int x2 = pointMover.getX1() + 120;
            int y2 = pointMover.getY1();

            pointMover.setX2(x2);
            pointMover.setY2(y2);
            pointMover.setPhase(Phase.END);
            pointMover.move();
        }
    }

    public static void goLeft(PointMover pointMover) {
        if (pointMover.getX1() >= 0 && pointMover.getY1() >= 0) {
            int x2 = pointMover.getX1() - 120;
            int y2 = pointMover.getY1();

            pointMover.setX2(x2);
            pointMover.setY2(y2);
            pointMover.setPhase(Phase.END);
            pointMover.move();
        }
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
