import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class CubePainter extends JPanel implements ActionListener, ChangeListener, MouseListener {
    private static final long serialVersionUID = -8879300942801280752L;

    private JButton start, stop, applyScramble, randomize;
    private JButton skip, rewind;
    private JButton resetCubeInputs, setInputs;
    private JSlider animSpeed;
    private JComboBox<String> sideChoser;
    private String[] instructions;
    private JTextField inputScramble;
    private Timer frameTimer;
    final static BasicStroke s = new BasicStroke(5.0f, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 10.0f);
    private final static Font font = new Font("Monospace", Font.BOLD, 35);
    public final static int DELAY = 1500;
    final static int CUBIE_SIZE = 50;

    private String mode = new String();
    public final static String TEXT_SCRAMBLE = "Text Scramble";
    public final static String COLOR_SELECTION = "Color";
    private char colorSelected;
    private char sideChosen;
    private char[][][] colorsInputed;
    private boolean inSolution;

    private Cube cube = new Cube();
    private final String DEFAULT_SCRAMBLE = "F2 D' B U' D L2 B2 R B L' B2 L2 B2 D' R2 F2 D' R2 U' ";
    private String scramble = new String(DEFAULT_SCRAMBLE),
            sunflower = new String(), whiteCross = new String(),
            whiteCorners = new String(), secondLayer = new String(),
            yellowCross = new String(), OLL = new String(), PLL = new String();
    private String movesToPerform = new String(), movesPerformed = new String();

    private int phase = 0;
    private String phaseString;
    private int movesIndex = 0;

    public CubePainter() {
        setLayout(null);
        setSize(getPreferredSize());
        setIgnoreRepaint(true);
        setVisible(true);
        mode = TEXT_SCRAMBLE;
        inSolution = true;
        phaseString = "Sunflower";
        colorSelected = 'R';
        instructions = new String[]{"Red", "Yellow", "White"};
        sideChosen = 'L';
        colorsInputed = new char[6][3][3];
        resetCubeInputs();
        addMouseListener(this);

        initializeComponents();
        resetScramble(inputScramble.getText());
        frameTimer = new javax.swing.Timer(CubePainter.DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inSolution) {
                    performNextMove();
                    repaint();
                }
            }
        });
    }

    public void resetCubeInputs() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(colorsInputed[0][i], 'R');
            Arrays.fill(colorsInputed[1][i], 'Y');
            Arrays.fill(colorsInputed[2][i], 'G');
            Arrays.fill(colorsInputed[3][i], 'B');
            Arrays.fill(colorsInputed[4][i], 'O');
            Arrays.fill(colorsInputed[5][i], 'W');
        }
    }

    public void initializeComponents() {
        start = new JButton("Start");
        start.setLocation(50, 10);
        start.setSize(60, 20);
        add(start);
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.setLocation(130, 10);
        stop.setSize(60, 20);
        add(stop);
        stop.addActionListener(this);


//        skip = new JButton(icon1);
//        skip.setLocation(240, 8);
//        skip.setSize(icon1.getIconWidth(), icon1.getIconHeight());
//        skip.setBackground(this.getBackground());
//        skip.setBorder(null);
//        skip.addActionListener(this);
//        add(skip);
//
//        rewind = new JButton(icon2);
//        rewind.setLocation(210, 8);
//        rewind.setSize(icon2.getIconWidth(), icon2.getIconHeight());
//        rewind.setBackground(this.getBackground());
//        rewind.setBorder(null);
//        rewind.addActionListener(this);
//        add(rewind);

        animSpeed = new JSlider(1, 10);
        animSpeed.setValue(1);
        animSpeed.setMinorTickSpacing(1);
        animSpeed.setPaintTicks(true);
        animSpeed.setSnapToTicks(true);
        animSpeed.setLocation(500, 0);
        animSpeed.setSize(200, 40);
        add(animSpeed);
        animSpeed.addChangeListener(this);

        inputScramble = new JTextField(scramble);
        inputScramble.setLocation(170, 40);
        inputScramble.setSize(400, 40);
        inputScramble.setFocusable(true);
        inputScramble.setBorder(BorderFactory.createLineBorder(Color.black));
        inputScramble.setFont(new Font("Monospace", Font.BOLD, 15));
        add(inputScramble);

        applyScramble = new JButton("APPLY");
        applyScramble.setLocation(590, 40);
        applyScramble.setSize(100, 20);
        add(applyScramble);
        applyScramble.addActionListener(this);

        randomize = new JButton("RANDOM");
        randomize.setLocation(590, 70);
        randomize.setSize(100, 20);
        add(randomize);
        randomize.addActionListener(this);

        sideChoser = new JComboBox<String>(new String[]{"Left", "Up", "Back", "Front", "Right", "Down"});
        sideChoser.setLocation(270, 50);
        sideChoser.setSize(100, 30);
        add(sideChoser);
        sideChoser.addActionListener(this);
        sideChoser.setVisible(false);
        sideChoser.setEnabled(false);

        resetCubeInputs = new JButton("RESET");
        resetCubeInputs.setLocation(200, 650);
        resetCubeInputs.setSize(100, 30);
        add(resetCubeInputs);
        resetCubeInputs.addActionListener(this);
        resetCubeInputs.setVisible(false);
        resetCubeInputs.setEnabled(false);

        setInputs = new JButton("PROCEED");
        setInputs.setLocation(300, 650);
        setInputs.setSize(100, 30);
        add(setInputs);
        setInputs.addActionListener(this);
        setInputs.setVisible(false);
        setInputs.setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            frameTimer.start();
        } else if (e.getSource() == stop) {
            frameTimer.stop();
        } else if (e.getSource() == skip) {
            performNextMove();
            repaint();
        } else if (e.getSource() == rewind) {
            boolean flag = false;
            int prevIndex = movesIndex;
            while (movesIndex > 1 && !flag) {
                movesIndex--;
                if (movesToPerform.substring(movesIndex - 1, movesIndex).equals(" ")) {
                    flag = !flag;
                }
                System.out.println(movesIndex);
            }
            if (movesIndex == 1) {
                movesIndex = 0;
            }
            movesPerformed = movesToPerform.substring(0, movesIndex);
            if (movesPerformed.length() >= 35) {
                movesPerformed = movesPerformed.substring(movesPerformed.length() - 33);
            }
            cube.reverseMoves(movesToPerform.substring(movesIndex, prevIndex));
            repaint();
        } else if (e.getSource() == sideChoser) {
            sideChosen = ((String) sideChoser.getSelectedItem()).charAt(0);
            instructions = getInstructions();
            repaint();
        } else if (e.getSource() == applyScramble) {
            frameTimer.stop();
            setVisible(false);
            resetScramble(inputScramble.getText());
            inSolution = true;
            updateElements();
            repaint();
            setVisible(true);
        } else if (e.getSource() == randomize) {
            cube = new Cube();
            inputScramble.setText(cube.randScramble());
            scramble = inputScramble.getText();
            setVisible(false);
            resetScramble(inputScramble.getText());
            inSolution = true;
            updateElements();
            repaint();
            setVisible(true);
        } else if (e.getSource() == resetCubeInputs) {
            resetCubeInputs();
            repaint();
        } else if (e.getSource() == setInputs) {
            frameTimer.stop();
            setVisible(false);
            cube.setAllColors(colorsInputed);
            resetScrambleByColorInputs();
            inSolution = true;
            updateElements();
            repaint();
            setVisible(true);
        }
    }


    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == animSpeed) {
            frameTimer.setDelay(DELAY / animSpeed.getValue());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(700, 770);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mode.equals(TEXT_SCRAMBLE)) {
            g.setFont(new Font("Monospace", Font.BOLD, 25));
            g.drawString("Scramble: ", 30, 70);
        }

        if (!inSolution) {
            ((Graphics2D) g).setStroke(s);
            int valueOfX = 100;
            int valueOfY = 450;
            for (int i = 0; i < 6; i++) {
                switch (i) {
                    case (0):
                        g.setColor(Color.RED);
                        break;
                    case (1):
                        g.setColor(Color.GREEN);
                        break;
                    case (2):
                        g.setColor(Color.BLUE);
                        break;
                    case (3):
                        g.setColor(Color.YELLOW);
                        break;
                    case (4):
                        g.setColor(Color.ORANGE);
                        break;
                    case (5):
                        g.setColor(Color.WHITE);
                        break;
                }
                g.fillRect(valueOfX, valueOfY, CUBIE_SIZE, CUBIE_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(valueOfX, valueOfY, CUBIE_SIZE, CUBIE_SIZE);
                valueOfX += CUBIE_SIZE * 1.5;
            }

            valueOfX = 250;
            valueOfY = 200;
            char[][] sideColors = colorsInputed[getIndexOfSide(sideChosen)];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    g.setColor(getColor(sideColors[i][j]));
                    g.fillRect(valueOfX + j * CUBIE_SIZE, valueOfY + i * CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(valueOfX + j * CUBIE_SIZE, valueOfY + i * CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);
                }
            }

            g.setColor(Color.BLACK);
            g.drawString("Hold the cube such that " + instructions[0] + " is facing up, " +
                            instructions[1] + " is to the back, and " + instructions[2] + " is in front.",
                    50, 130);
            g.drawString("Enter the top colors.",
                    50, 150);

            g.setFont(font);
            g.drawString("Selected Color:", 100, 500 + CUBIE_SIZE * 2);
            g.setColor(getColor(colorSelected));
            g.fillRect(400, 465 + CUBIE_SIZE * 2, CUBIE_SIZE, CUBIE_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(400, 465 + CUBIE_SIZE * 2, CUBIE_SIZE, CUBIE_SIZE);
        } else if (inSolution) {
            g.setFont(new Font("Monospace", Font.BOLD, 25));
            g.drawString("Phase: " + phaseString, 30, 120);

            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString(movesPerformed, 50, 700);
            g.setColor(Color.BLACK);
            if (movesIndex <= movesToPerform.length() - 1) {
                if (movesToPerform.substring(movesIndex).length() >= 33) {
                    g.drawString(movesToPerform.substring(movesIndex, movesIndex + 33), 40, 650);
                } else {
                    g.drawString(movesToPerform.substring(movesIndex), 40, 650);
                }
            }

            ((Graphics2D) g).setStroke(s);
            cube.paintComponent(g);

        }

    }

    private Color getColor(char color) {
        switch (color) {
            case 'W':
                return Color.WHITE;
            case 'Y':
                return Color.YELLOW;
            case 'B':
                return Color.BLUE;
            case 'G':
                return Color.GREEN;
            case 'R':
                return Color.RED;
            case 'O':
                return Color.ORANGE;
        }
        return Color.BLACK;
    }

    private int getIndexOfSide(char side) {
        switch (side) {
            case ('L'):
                return 0;
            case ('U'):
                return 1;
            case ('F'):
                return 2;
            case ('B'):
                return 3;
            case ('R'):
                return 4;
            case ('D'):
                return 5;
        }
        return 6;
    }

    private String[] getInstructions() {
        String[] colors = new String[3];
        switch (sideChosen) {
            case ('L'):
                colors[0] = "Red";
                colors[1] = "Yellow";
                colors[2] = "White";
                break;
            case ('U'):
                colors[0] = "Yellow";
                colors[1] = "Blue";
                colors[2] = "Green";
                break;
            case ('F'):
                colors[0] = "Green";
                colors[1] = "Yellow";
                colors[2] = "White";
                break;
            case ('B'):
                colors[0] = "Blue";
                colors[1] = "Yellow";
                colors[2] = "White";
                break;
            case ('R'):
                colors[0] = "Orange";
                colors[1] = "Yellow";
                colors[2] = "White";
                break;
            case ('D'):
                colors[0] = "White";
                colors[1] = "Green";
                colors[2] = "Blue";
        }
        return colors;
    }

    public void resetScramble(String s) {
        scramble = s;
        cube = new Cube();
        cube.scramble(scramble);
        sunflower = cube.makeSunflower();
        whiteCross = cube.makeWhiteCross();
        whiteCorners = cube.finishWhiteLayer();
        secondLayer = cube.insertAllEdges();
        yellowCross = cube.makeYellowCross();
        OLL = cube.orientLastLayer();
        PLL = cube.permuteLastLayer();

        movesToPerform = sunflower;
        movesPerformed = new String();

        cube = new Cube();
        cube.scramble(scramble);
        movesIndex = 0;
        phase = 0;
        phaseString = "Sunflower";
        repaint();
    }

    public void resetScrambleByColorInputs() {
        cube.setAllColors(colorsInputed);
        sunflower = cube.makeSunflower();
        whiteCross = cube.makeWhiteCross();
        whiteCorners = cube.finishWhiteLayer();
        secondLayer = cube.insertAllEdges();
        yellowCross = cube.makeYellowCross();
        OLL = cube.orientLastLayer();
        PLL = cube.permuteLastLayer();

        movesToPerform = sunflower;
        movesPerformed = new String();

        movesIndex = 0;
        phase = 0;
        phaseString = "Sunflower";
        cube.setAllColors(colorsInputed);
        repaint();
    }


    public void performNextMove() {
        updatePhase();

        while (movesIndex < movesToPerform.length() - 1 && movesToPerform.substring(movesIndex, movesIndex + 1).compareTo(" ") == 0) {
            movesIndex++;
        }
        if (movesToPerform.length() > 0 && movesToPerform.substring(movesIndex, movesIndex + 1) != " ") {
            if (movesIndex != movesToPerform.length() - 1) {
                if (movesToPerform.substring(movesIndex + 1, movesIndex + 2).compareTo("2") == 0) {
                    cube.turn(movesToPerform.substring(movesIndex, movesIndex + 1));
                    cube.turn(movesToPerform.substring(movesIndex, movesIndex + 1));
                    movesIndex++;
                } else if (movesToPerform.substring(movesIndex + 1, movesIndex + 2).compareTo("'") == 0) {
                    cube.turn(movesToPerform.substring(movesIndex, movesIndex + 2));
                    movesIndex++;
                } else {
                    cube.turn(movesToPerform.substring(movesIndex, movesIndex + 1));
                }
            } else {
                cube.turn(movesToPerform.substring(movesIndex, movesIndex + 1));
            }
        }
        movesIndex++;
        if (movesToPerform.length() > 0) {
            movesPerformed = movesToPerform.substring(0, movesIndex);
        }
        if (movesPerformed.length() >= 35) {
            movesPerformed = movesPerformed.substring(movesPerformed.length() - 33);
        }
    }


    public void updateElements() {
        if (mode.equals(TEXT_SCRAMBLE)) {
            start.setEnabled(true);
            start.setVisible(true);
            stop.setEnabled(true);
            stop.setVisible(true);
            animSpeed.setEnabled(true);
            animSpeed.setVisible(true);
            inputScramble.setEnabled(true);
            inputScramble.setVisible(true);
            applyScramble.setEnabled(true);
            applyScramble.setVisible(true);
//            skip.setEnabled(true);
//            skip.setVisible(true);
//            rewind.setEnabled(true);
//            rewind.setVisible(true);
            randomize.setEnabled(true);
            randomize.setVisible(true);

            //Disable all components specific to color selection mode
            sideChoser.setVisible(false);
            sideChoser.setEnabled(false);
            resetCubeInputs.setVisible(false);
            resetCubeInputs.setEnabled(false);
            setInputs.setVisible(false);
            setInputs.setEnabled(false);
        } else if (mode.equals(COLOR_SELECTION)) {
            if (inSolution) {
                start.setEnabled(true);
                start.setVisible(true);
                stop.setEnabled(true);
                stop.setVisible(true);
                animSpeed.setEnabled(true);
                animSpeed.setVisible(true);
                skip.setEnabled(true);
                skip.setVisible(true);
                rewind.setEnabled(true);
                rewind.setVisible(true);

                randomize.setEnabled(false);
                randomize.setVisible(false);
                sideChoser.setVisible(false);
                sideChoser.setEnabled(false);
                resetCubeInputs.setVisible(false);
                resetCubeInputs.setEnabled(false);
                setInputs.setVisible(false);
                setInputs.setEnabled(false);
            } else if (!inSolution) {
                start.setEnabled(false);
                start.setVisible(false);
                stop.setEnabled(false);
                stop.setVisible(false);
                animSpeed.setEnabled(false);
                animSpeed.setVisible(false);
                randomize.setEnabled(false);
                randomize.setVisible(false);

                skip.setEnabled(false);
                skip.setVisible(false);
                rewind.setEnabled(false);
                rewind.setVisible(false);
                sideChoser.setVisible(true);
                sideChoser.setEnabled(true);
                resetCubeInputs.setVisible(true);
                resetCubeInputs.setEnabled(true);
                setInputs.setVisible(true);
                setInputs.setEnabled(true);
            }
            //Disable all components specific to text scramble mode
            inputScramble.setEnabled(false);
            inputScramble.setVisible(false);
            applyScramble.setEnabled(false);
            applyScramble.setVisible(false);
        }
    }

    public void updateMode(String str) {
        if (!mode.equals(str)) {
            mode = new String(str);
            cube = new Cube();
            if (mode.equals(TEXT_SCRAMBLE)) {
                scramble = DEFAULT_SCRAMBLE;
                resetScramble(scramble);
                inSolution = true;
            }
            updateElements();
            repaint();
        }
    }

    public void setInSolution(boolean inSoln) {
        inSolution = inSoln;
    }

    public void updatePhase() {
        if (movesIndex >= movesToPerform.length()) {
            switch (phase) {
                case 0:
                    movesToPerform = whiteCross;
                    phaseString = "White Cross";
                    break;
                case 1:
                    movesToPerform = whiteCorners;
                    phaseString = "White Corners";
                    break;
                case 2:
                    movesToPerform = secondLayer;
                    phaseString = "Second Layer";
                    break;
                case 3:
                    movesToPerform = yellowCross;
                    phaseString = "Yellow Cross";
                    break;
                case 4:
                    movesToPerform = OLL;
                    phaseString = "OLL";
                    break;
                case 5:
                    movesToPerform = PLL;
                    phaseString = "PLL";
                    break;
                case 6:
                    movesToPerform = " ";
                    phaseString = "Solved";
                    phase--;
                    frameTimer.stop();
            }
            phase++;
            movesIndex = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mousePressed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mode.equals(COLOR_SELECTION) && !inSolution) {
            if (e.getY() > 200 && e.getY() < 200 + CUBIE_SIZE * 3) {
                int i = (e.getY() - 200) / CUBIE_SIZE;
                int j = (e.getX() - 250) / CUBIE_SIZE;
                colorsInputed[getIndexOfSide(sideChosen)][i][j] = colorSelected;
                repaint();
            } else if (e.getY() > 450 && e.getY() < 450 + CUBIE_SIZE) {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = image.createGraphics();
                this.paint(g2);
                int color = image.getRGB(e.getX(), e.getY());
                g2.dispose();
                switch (color) {
                    case (-65536):
                        colorSelected = 'R';
                        break; //Red
                    case (-16711936):
                        colorSelected = 'G';
                        break; //Green
                    case (-16776961):
                        colorSelected = 'B';
                        break; //Blue
                    case (-256):
                        colorSelected = 'Y';
                        break; //Yellow
                    case (-14336):
                        colorSelected = 'O';
                        break; //Orange
                    case (-1):
                        colorSelected = 'W';
                        break; //White
                }
                repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}



