package mouse.events;

import actions.PointMover;
import actions.numerators.Phase;
import windows.MainWindow;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoardMover implements MouseListener {

    private final PointMover pointMover;
    private JPopupMenu popupMenu;
    private final MainWindow mainWindow;

    public GameBoardMover(PointMover pointMover, MainWindow mainWindow) {
        this.pointMover = pointMover;
        this.mainWindow = mainWindow;
        setUpPopupMenu();
    }

    private void setUpPopupMenu() {
        popupMenu = new JPopupMenu("Choose move");
        JMenuItem up = new JMenuItem("Go up");
        JMenuItem down = new JMenuItem("Go down");
        JMenuItem right = new JMenuItem("Go right");
        JMenuItem left = new JMenuItem("Go left");

        up.addActionListener(e -> PointMover.goUp(pointMover));

        down.addActionListener(e -> PointMover.goDown(pointMover));

        right.addActionListener(e -> PointMover.goRight(pointMover));

        left.addActionListener(e -> PointMover.goLeft(pointMover));

        popupMenu.add(up);
        popupMenu.add(down);
        popupMenu.add(right);
        popupMenu.add(left);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger() && pointMover.getX1() >= 0 &&
                pointMover.getY1() >= 0) {
            popupMenu.show(mainWindow, e.getX(), e.getY());
        } else {
            if (pointMover.getX1() >= 0 && pointMover.getY1() >= 0) {

                pointMover.setX2((e.getX() / 60) * 60);
                pointMover.setY2((e.getY() / 60) * 60);
                pointMover.setPhase(Phase.END);
            } else {
                pointMover.setX1((e.getX() / 60) * 60);
                pointMover.setY1((e.getY() / 60) * 60);
                pointMover.setPhase(Phase.BEGIN);
            }

            pointMover.move();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
