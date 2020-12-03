package structures;

import structures.numerators.TypeOfBoard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private final List<Point> setOfPoints;

    private int countOfBalls;

    public GameBoard(TypeOfBoard typeOfBoard) {
        setOfPoints = new ArrayList<>();
        if (typeOfBoard == TypeOfBoard.BRITISH) fillBritishBoard();
        else fillEuropeanBoard();
    }

    private void fillBritishBoard() {

        // fill 3 middle lines
        for (int x = 2; x <= 4; x++) {
            for (int y = 0; y <= 6; y++) {
                setOfPoints.add(new Point(x * 60, y * 60));
                countOfBalls++;
            }
        }

        // fill 2 left lines
        for (int x = 0; x <= 1; x++) {
            for (int y = 2; y <= 4; y++) {
                setOfPoints.add(new Point(x * 60, y * 60));
                countOfBalls++;
            }
        }

        // fill 2 right lines
        for (int x = 5; x <= 6; x++) {
            for (int y = 2; y <= 4; y++) {
                setOfPoints.add(new Point(x * 60, y * 60));
                countOfBalls++;
            }
        }

        // setting middle point to don't have ball
        Point middlePoint = getPoint(3 * 60, 3 * 60);
        middlePoint.removeBall();
        countOfBalls--;
    }

    private void fillEuropeanBoard() {
        fillBritishBoard();

        // fill 4 points that didn't occur in british type of board
        setOfPoints.add(new Point(60, 60));
        countOfBalls++;
        setOfPoints.add(new Point(300, 60));
        countOfBalls++;
        setOfPoints.add(new Point(60, 300));
        countOfBalls++;
        setOfPoints.add(new Point(300, 300));
        countOfBalls++;
    }

    public Point getPoint(int x, int y) throws IllegalArgumentException {
        for (Point p : setOfPoints) {
            if (p.getX() == x && p.getY() == y) return p;
        }
        throw new IllegalArgumentException("Such point doesn't exist");
    }

    public boolean existPoint(int x, int y) {
        for (Point p : setOfPoints) {
            if (p.getX() == x && p.getY() == y) return true;
        }
        return false;
    }

    public ArrayList<Point> getSetOfPoints() {
        return (ArrayList<Point>) setOfPoints;
    }

    public void reduceCountOfBalls() {
        countOfBalls--;
    }

    public int getCountOfBalls() {
        return countOfBalls;
    }

    public boolean areAvailableMoves() {
        int x2, y2;

        for (Point p : setOfPoints) {
            if (p.includesBall()) {
                x2 = p.getX();
                y2 = p.getY();

                if (checkMove(x2 + 120, y2) && !checkMove(x2 + 60, y2)) return true;
                if (checkMove(x2 - 120, y2) && !checkMove(x2 - 60, y2)) return true;
                if (checkMove(x2, y2 + 120) && !checkMove(x2, y2 + 60)) return true;
                if (checkMove(x2, y2 - 120) && !checkMove(x2, y2 - 60)) return true;
            }
        }

        return false;
    }

    private boolean checkMove(int x2, int y2) {
        if (existPoint(x2, y2)) {
            Point temp = getPoint(x2, y2);
            return !temp.includesBall();
        }
        return false;
    }

}
