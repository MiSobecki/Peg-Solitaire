package windows;

import actions.PointMover;
import structures.numerators.TypeOfBoard;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GameMenuWindow extends JMenuBar {

    private JMenu game, moves, settings, help;
    private final MainWindow mainWindow;
    private final GameBoardWindow gameBoardWindow;
    private final PointMover pointMover;

    private Color boardColor = Color.black, pawnColor = Color.blue;
    private TypeOfBoard typeOfBoard;

    public GameMenuWindow(MainWindow mainWindow, GameBoardWindow gameBoardWindow,
                          PointMover pointMover, TypeOfBoard typeOfBoard) {
        this.mainWindow = mainWindow;
        this.gameBoardWindow = gameBoardWindow;
        this.pointMover = pointMover;
        this.typeOfBoard = typeOfBoard;
        setBounds(0, 0, 420, 100);
        setUp();
    }

    private void setUp() {
        this.moves = new JMenu("Moves");
        this.settings = new JMenu("Settings");
        this.game = new JMenu("Game");
        this.help = new JMenu("Help");

        add(game);
        add(moves);
        add(settings);
        add(Box.createHorizontalGlue());
        add(help);

        setUpGameMenu();
        setUpMovesMenu();
        setUpSettingsMenu();
        setUpHelpMenu();
    }

    private void setUpGameMenu() {

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem end = new JMenuItem("Close Game");

        newGame.addActionListener(e -> {
            mainWindow.dispose();
            new MainWindow(boardColor, pawnColor, typeOfBoard);
        });

        end.addActionListener(e -> mainWindow.dispose());

        newGame.setMnemonic(KeyEvent.VK_1);
        end.setMnemonic(KeyEvent.VK_2);

        game.add(newGame);
        game.addSeparator();
        game.add(end);
    }

    public void setUpMovesMenu() {
        JMenuItem up = new JMenuItem("Go up");
        JMenuItem down = new JMenuItem("Go down");
        JMenuItem right = new JMenuItem("Go right");
        JMenuItem left = new JMenuItem("Go left");

        up.addActionListener(e -> PointMover.goUp(pointMover));
        up.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_DOWN_MASK));

        down.addActionListener(e -> PointMover.goDown(pointMover));
        down.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));

        right.addActionListener(e -> PointMover.goRight(pointMover));
        right.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK));

        left.addActionListener(e -> PointMover.goLeft(pointMover));
        left.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));

        moves.add(up);
        moves.add(down);
        moves.add(right);
        moves.add(left);
    }

    private void setUpSettingsMenu() {
        ButtonGroup boardTypeChooser = new ButtonGroup();
        JMenuItem boardColorChooser = new JMenuItem("Choose board color");
        JMenuItem pawnColorChooser = new JMenuItem("Choose pawn color");

        JCheckBox fillChooser = new JCheckBox("Fill balls inside");
        fillChooser.setSelected(false);
        fillChooser.addItemListener(e -> {
            Object source = e.getItemSelectable();

            if (source == fillChooser && fillChooser.isSelected()) {
                gameBoardWindow.fillPoints();
                gameBoardWindow.repaint();
            } else if (source == fillChooser) {
                gameBoardWindow.unFillPoints();
                gameBoardWindow.repaint();
            }
        });

        JRadioButtonMenuItem britishType = new JRadioButtonMenuItem("British board");
        JRadioButtonMenuItem europeanType = new JRadioButtonMenuItem("European board");

        if (typeOfBoard == TypeOfBoard.BRITISH) britishType.setSelected(true);
        else europeanType.setSelected(true);

        britishType.addActionListener(e -> typeOfBoard = TypeOfBoard.BRITISH);
        europeanType.addActionListener(e -> typeOfBoard = TypeOfBoard.EUROPEAN);

        boardTypeChooser.add(britishType);
        boardTypeChooser.add(europeanType);

        boardColorChooser.addActionListener(e -> {
            JColorChooser colorChooser = new JColorChooser();
            colorChooser.getSelectionModel().setSelectedColor(Color.BLACK);
            ActionListener okListener = e1 -> boardColor = colorChooser.getColor();
            JDialog dialog = JColorChooser.createDialog(this, "Choose a board color", true, colorChooser, okListener, null);
            dialog.setVisible(true);
            gameBoardWindow.setBoardColor(boardColor);
            gameBoardWindow.repaint();
        });

        pawnColorChooser.addActionListener(e -> {
            JColorChooser colorChooser = new JColorChooser();
            colorChooser.getSelectionModel().setSelectedColor(Color.BLUE);
            ActionListener okListener = e1 -> pawnColor = colorChooser.getColor();
            JDialog dialog = JColorChooser.createDialog(this, "Choose a pawn color", true, colorChooser, okListener, null);
            dialog.setVisible(true);
            gameBoardWindow.setPawnColor(pawnColor);
            gameBoardWindow.repaint();
        });

        settings.add(britishType);
        settings.add(europeanType);
        settings.add(boardColorChooser);
        settings.add(pawnColorChooser);
        settings.add(fillChooser);
    }

    private void setUpHelpMenu() {

        JMenuItem aboutGame = new JMenuItem("About Game");
        JMenuItem aboutAuthor = new JMenuItem("About Author");

        aboutGame.addActionListener(e -> JOptionPane.showMessageDialog(mainWindow,
                "The standard game fills the entire board with pegs except for the central hole.\n" +
                        "The objective is, making valid moves (A valid move is to jump a peg\n" +
                        "orthogonally over an adjacent peg into a hole two positions away\n" +
                        "and then to remove the jumped peg),\n" +
                        "to empty the entire board except for a solitary peg in the central hole.",
                "About game", JOptionPane.INFORMATION_MESSAGE));

        aboutAuthor.addActionListener(e -> JOptionPane.showMessageDialog(mainWindow,
                "Author: Michał Sobecki\n" +
                        "Version: 1.0\n" +
                        "Creation date: 2020-12-01",
                "About Author", JOptionPane.INFORMATION_MESSAGE));

        help.add(aboutGame);
        help.add(aboutAuthor);
    }
}
