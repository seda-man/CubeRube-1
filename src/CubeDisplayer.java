import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CubeDisplayer extends JFrame implements ActionListener {
    private static final long serialVersionUID = -3198702237161500498L;
    CubePainter cubePainter; //The JPanel that will handle painting and user input
    JMenuBar menuBar;
    JMenu modes;
    JMenuItem colorSelection, scramble;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CubeDisplayer();
            }
        });
    }


    public CubeDisplayer() {
        setTitle("Cube Rubik");
        setLayout(new BorderLayout());
        setSize(700, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setIgnoreRepaint(true);

        menuBar = new JMenuBar();
        modes = new JMenu("Mode Selection");
        colorSelection = new JMenuItem("Color Selection Mode");
        scramble = new JMenuItem("Text Scramble Mode");
        modes.add(colorSelection);
        modes.add(scramble);
        colorSelection.addActionListener(this);
        scramble.addActionListener(this);
        menuBar.add(modes);
        setJMenuBar(menuBar);

        //Create a new CubePainter JPanel
        cubePainter = new CubePainter();
        add(cubePainter);
        cubePainter.setVisible(true);
        cubePainter.setEnabled(true);

        menuBar.setVisible(true);
        setVisible(true);
        this.repaint();


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == colorSelection) {
            cubePainter.setInSolution(false);
            cubePainter.updateMode(CubePainter.COLOR_SELECTION);
        } else if (e.getSource() == scramble) {
            cubePainter.setInSolution(true);
            cubePainter.updateMode(CubePainter.TEXT_SCRAMBLE);
        }
    }
}

