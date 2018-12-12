import java.awt.*;

public class Cube {

    private Cubie[][][] cubePosition = new Cubie[3][3][3];

    public Cube() {
        //Up, Front Row
        cubePosition[0][0][0] = new Cubie(0, 0, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('R', 'L'), new CubieColor('G', 'F')}, true, false);
        cubePosition[1][0][0] = new Cubie(1, 0, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('G', 'F')}, false, true);
        cubePosition[2][0][0] = new Cubie(2, 0, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('G', 'F'), new CubieColor('O', 'R')}, true, false);

        //Front, E Row
        cubePosition[0][0][1] = new Cubie(0, 0, 1,
                new CubieColor[]{new CubieColor('R', 'L'), new CubieColor('G', 'F')}, false, true);
        cubePosition[1][0][1] = new Cubie(1, 0, 1,
                new CubieColor[]{new CubieColor('G', 'F')}, false, false);
        cubePosition[2][0][1] = new Cubie(2, 0, 1,
                new CubieColor[]{new CubieColor('G', 'F'), new CubieColor('O', 'R')}, false, true);

        //Down, Front Row
        cubePosition[0][0][2] = new Cubie(0, 0, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('R', 'L'), new CubieColor('G', 'F')}, true, false);
        cubePosition[1][0][2] = new Cubie(1, 0, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('G', 'F')}, false, true);
        cubePosition[2][0][2] = new Cubie(2, 0, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('G', 'F'), new CubieColor('O', 'R')}, true, false);

        //Up, S Row
        cubePosition[0][1][0] = new Cubie(0, 1, 0,
                new CubieColor[]{new CubieColor('R', 'L'), new CubieColor('Y', 'U')}, false, true);
        cubePosition[1][1][0] = new Cubie(1, 1, 0,
                new CubieColor[]{new CubieColor('Y', 'U')}, false, false);
        cubePosition[2][1][0] = new Cubie(2, 1, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('O', 'R')}, false, true);

        //E, S Row
        cubePosition[0][1][1] = new Cubie(0, 1, 1,
                new CubieColor[]{new CubieColor('R', 'L')}, false, false);
        cubePosition[1][1][1] = new Cubie(1, 1, 1,
                new CubieColor[]{new CubieColor('A', 'A')}, //Just giving random, non-legitimate values for color and direction
                false, false);
        cubePosition[2][1][1] = new Cubie(2, 1, 1,
                new CubieColor[]{new CubieColor('O', 'R')}, false, false);

        //Down, S Row
        cubePosition[0][1][2] = new Cubie(0, 1, 2,
                new CubieColor[]{new CubieColor('R', 'L'), new CubieColor('W', 'D')}, false, true);
        cubePosition[1][1][2] = new Cubie(1, 1, 2,
                new CubieColor[]{new CubieColor('W', 'D')}, false, false);
        cubePosition[2][1][2] = new Cubie(2, 1, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('O', 'R')}, false, true);

        //Up, Back Row
        cubePosition[0][2][0] = new Cubie(0, 2, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('R', 'L'), new CubieColor('B', 'B')}, true, false);
        cubePosition[1][2][0] = new Cubie(1, 2, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('B', 'B')}, false, true);
        cubePosition[2][2][0] = new Cubie(2, 2, 0,
                new CubieColor[]{new CubieColor('Y', 'U'), new CubieColor('B', 'B'), new CubieColor('O', 'R')}, true, false);

        //E, Back Row
        cubePosition[0][2][1] = new Cubie(0, 2, 1,
                new CubieColor[]{new CubieColor('R', 'L'), new CubieColor('B', 'B')}, false, true);
        cubePosition[1][2][1] = new Cubie(1, 2, 1,
                new CubieColor[]{new CubieColor('B', 'B')}, false, false);
        cubePosition[2][2][1] = new Cubie(2, 2, 1,
                new CubieColor[]{new CubieColor('B', 'B'), new CubieColor('O', 'R')}, false, true);

        //Down, Back Row
        cubePosition[0][2][2] = new Cubie(0, 2, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('R', 'L'), new CubieColor('B', 'B')}, true, false);
        cubePosition[1][2][2] = new Cubie(1, 2, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('B', 'B')}, false, true);
        cubePosition[2][2][2] = new Cubie(2, 2, 2,
                new CubieColor[]{new CubieColor('W', 'D'), new CubieColor('B', 'B'), new CubieColor('O', 'R')}, true, false);

    }

    //turn the rotation of cube - getting all states
    public void turn(String turn) {
        char[] beforeChange;
        char[] afterChange;
        Cubie[][] matrix = new Cubie[3][3]; //matrix for rotation

        switch (turn) {
            case "B":
                beforeChange = new char[]{'B', 'U', 'R', 'D', 'L'};
                afterChange = new char[]{'B', 'L', 'U', 'R', 'D'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[Math.abs(j - 2)][2][i];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[Math.abs(j - 2)][2][i] = matrix[i][j];
                    }
                }
                break;

            case "B'":
                beforeChange = new char[]{'B', 'U', 'R', 'D', 'L'};
                afterChange = new char[]{'B', 'R', 'D', 'L', 'U'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[Math.abs(j - 2)][2][i];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[Math.abs(j - 2)][2][i] = matrix[i][j];
                    }
                }
                break;

            case "D":
                beforeChange = new char[]{'D', 'L', 'B', 'R', 'F'};
                afterChange = new char[]{'D', 'F', 'L', 'B', 'R'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][i][2];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][i][2] = matrix[i][j];
                    }
                }
                break;

            case "D'":
                beforeChange = new char[]{'D', 'F', 'L', 'B', 'R'};
                afterChange = new char[]{'D', 'L', 'B', 'R', 'F'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][i][2];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][i][2] = matrix[i][j];
                    }
                }
                break;

            case "E":
                beforeChange = new char[]{'L', 'B', 'R', 'F'};
                afterChange = new char[]{'F', 'L', 'B', 'R'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][i][1];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][i][1] = matrix[i][j];
                    }
                }
                break;

            case "E'":
                beforeChange = new char[]{'F', 'L', 'B', 'R'};
                afterChange = new char[]{'L', 'B', 'R', 'F'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][i][1];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][i][1] = matrix[i][j];
                    }
                }
                break;

            case "F":
                beforeChange = new char[]{'F', 'U', 'R', 'D', 'L'};
                afterChange = new char[]{'F', 'R', 'D', 'L', 'U'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][0][i];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][0][i] = matrix[i][j];
                    }
                }
                break;

            case "F'":
                beforeChange = new char[]{'F', 'U', 'R', 'D', 'L'};
                afterChange = new char[]{'F', 'L', 'U', 'R', 'D'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][0][i];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][0][i] = matrix[i][j];
                    }
                }
                break;

            case "L":
                beforeChange = new char[]{'L', 'B', 'D', 'F', 'U'};
                afterChange = new char[]{'L', 'U', 'B', 'D', 'F'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[0][Math.abs(j - 2)][i];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[0][Math.abs(j - 2)][i] = matrix[i][j];
                    }
                }
                break;

            case "L'":
                beforeChange = new char[]{'L', 'U', 'B', 'D', 'F'};
                afterChange = new char[]{'L', 'B', 'D', 'F', 'U'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[0][Math.abs(j - 2)][i];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[0][Math.abs(j - 2)][i] = matrix[i][j];
                    }
                }
                break;

            case "M":
                beforeChange = new char[]{'B', 'D', 'F', 'U'};
                afterChange = new char[]{'U', 'B', 'D', 'F'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[1][Math.abs(j - 2)][i];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[1][Math.abs(j - 2)][i] = matrix[i][j];
                    }
                }
                break;

            case "M'":
                beforeChange = new char[]{'U', 'B', 'D', 'F'};
                afterChange = new char[]{'B', 'D', 'F', 'U'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[1][Math.abs(j - 2)][i];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[1][Math.abs(j - 2)][i] = matrix[i][j];
                    }
                }
                break;

            case "R":
                beforeChange = new char[]{'R', 'U', 'B', 'D', 'F'};
                afterChange = new char[]{'R', 'B', 'D', 'F', 'U'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[2][j][i];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[2][j][i] = matrix[i][j];
                    }
                }
                break;

            case "R'":
                beforeChange = new char[]{'R', 'B', 'D', 'F', 'U'};
                afterChange = new char[]{'R', 'U', 'B', 'D', 'F'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[2][j][i];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[2][j][i] = matrix[i][j];
                    }
                }
                break;

            case "S":
                beforeChange = new char[]{'U', 'R', 'D', 'L'};
                afterChange = new char[]{'R', 'D', 'L', 'U'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][1][i];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][1][i] = matrix[i][j];
                    }
                }
                break;

            case "S'":
                beforeChange = new char[]{'U', 'R', 'D', 'L'};
                afterChange = new char[]{'L', 'U', 'R', 'D'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][1][i];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][1][i] = matrix[i][j];
                    }
                }
                break;

            case "U":
                beforeChange = new char[]{'U', 'F', 'L', 'B', 'R'};
                afterChange = new char[]{'U', 'L', 'B', 'R', 'F'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][Math.abs(i - 2)][0];
                    }
                }
                matrix = rotateMatrix(matrix, 90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][Math.abs(i - 2)][0] = matrix[i][j];
                    }
                }
                break;

            case "U'":
                beforeChange = new char[]{'U', 'L', 'B', 'R', 'F'};
                afterChange = new char[]{'U', 'F', 'L', 'B', 'R'};
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = cubePosition[j][Math.abs(i - 2)][0];
                    }
                }
                matrix = rotateMatrix(matrix, -90, beforeChange, afterChange);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        cubePosition[j][Math.abs(i - 2)][0] = matrix[i][j];
                    }
                }
                break;

            case "x":
                performMoves("R M' L'");
                //turn("R"); turn("M'"); turn("L'");
                break;

            case "x'":
                performMoves("R' M L");
                break;

            case "y":
                performMoves("U E' D'");
                break;

            case "y'":
                performMoves("U' E D");
                break;

            case "z":
                performMoves("F S B'");
                break;

            case "z'":
                performMoves("F' S' B");
                break;

        }


    }

    private Cubie[][] rotateMatrix(Cubie[][] orig, int degrees, char[] preChange, char[] postChange) {
        Cubie[][] rotated = new Cubie[3][3];
        if (degrees == 90) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotated[i][j] = orig[j][i];
                }
            }
            //Reverse all the rows
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < rotated[0].length / 2; j++) {
                    Cubie tempCubie = rotated[i][3 - j - 1];
                    rotated[i][3 - j - 1] = rotated[i][j];
                    rotated[i][j] = tempCubie;
                }
            }
        } else if (degrees == -90) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rotated[i][j] = orig[j][i];
                }
            }

            //Reverse all the columns
            for (int i = 0; i < rotated[0].length / 2; i++) {
                for (int j = 0; j < 3; j++) {
                    Cubie tempCubie = rotated[3 - i - 1][j];
                    rotated[3 - i - 1][j] = rotated[i][j];
                    rotated[i][j] = tempCubie;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CubieColor[] tempColors = rotated[i][j].getColors();
                for (int k = 0; k < tempColors.length; k++) {
                    int index = 6;
                    for (int x = 0; x < preChange.length; x++) {
                        if (tempColors[k].getDir() == preChange[x]) {
                            index = x;
                        }
                    }
                    if (index < postChange.length)
                        tempColors[k].setDir(postChange[index]);
                }
                rotated[i][j].setColors(tempColors);
            }
        }
        return rotated;
    }

    public String performMoves(String moves) {
        for (int i = 0; i < moves.length(); i++) {
            if (moves.substring(i, i + 1) != " ") {
                if (i != moves.length() - 1) {
                    if (moves.substring(i + 1, i + 2).compareTo("2") == 0) {
                        turn(moves.substring(i, i + 1));
                        turn(moves.substring(i, i + 1));
                        i++;
                    } else if (moves.substring(i + 1, i + 2).compareTo("'") == 0) {
                        turn(moves.substring(i, i + 2));
                        i++;
                    } else {
                        turn(moves.substring(i, i + 1));
                    }
                } else {
                    turn(moves.substring(i, i + 1));
                }
            }
        }
        return moves;
    }

    public void reverseMoves(String moves) {
        for (int i = 0; i < moves.length(); i++) {
            if (moves.substring(i, i + 1) != " ") {
                if (i != moves.length() - 1) {
                    if (moves.substring(i + 1, i + 2).compareTo("2") == 0) {
                        turn(moves.substring(i, i + 1));
                        turn(moves.substring(i, i + 1));
                        i++;
                    } else if (moves.substring(i + 1, i + 2).compareTo("'") == 0) {
                        turn(moves.substring(i, i + 1));
                        i++;
                    } else {
                        turn(moves.substring(i, i + 1) + "'");
                    }
                } else {
                    turn(moves.substring(i, i + 1) + "'");
                }
            }
        }
    }


    public String optimizeMoves(String moves) {
        for (int i = 0; i < moves.length(); i++) {
            String move = moves.substring(i, i + 1);
            if (!move.equals(" ") && !move.equals("'") && !move.equals("2")) {
                if (i <= moves.length() - 3) {
                    if (moves.substring(i + 1, i + 2).compareTo("2") == 0) {
                        if (i <= moves.length() - 4 && moves.charAt(i + 3) == moves.charAt(i)) {
                            if (i <= moves.length() - 5) {
                                if (moves.substring(i + 4, i + 5).compareTo("2") == 0) {
                                    moves = moves.substring(0, i) + moves.substring(i + 5);
                                    i--;
                                } else if (moves.substring(i + 4, i + 5).compareTo("'") == 0) {
                                    moves = moves.substring(0, i) + moves.substring(i, i + 1)
                                            + moves.substring(i + 5);
                                    i--;
                                } else {
                                    moves = moves.substring(0, i) + moves.substring(i, i + 1) + "'"
                                            + moves.substring(i + 4);
                                    i--;
                                }
                            } else {
                                moves = moves.substring(0, i) + moves.substring(i, i + 1) + "'"
                                        + moves.substring(i + 4);
                                i--;
                            }
                        }
                    } else if (moves.substring(i + 1, i + 2).compareTo("'") == 0) {
                        if (i <= moves.length() - 4 && moves.charAt(i + 3) == moves.charAt(i)) {
                            if (i <= moves.length() - 5) {
                                if (moves.substring(i + 4, i + 5).compareTo("2") == 0) {
                                    moves = moves.substring(0, i) + moves.substring(i, i + 1)
                                            + moves.substring(i + 5);
                                    i--;
                                } else if (moves.substring(i + 4, i + 5).compareTo("'") == 0) {
                                    moves = moves.substring(0, i) + moves.substring(i, i + 1) + "2"
                                            + moves.substring(i + 5);
                                    i--;
                                } else {
                                    moves = moves.substring(0, i) + moves.substring(i + 4);
                                    i--;
                                }
                            } else {
                                moves = moves.substring(0, i) + moves.substring(i + 4);
                                i--;
                            }
                        }
                    } else {
                        if (i <= moves.length() - 3 && moves.charAt(i + 2) == moves.charAt(i)) {
                            if (i <= moves.length() - 4) {
                                if (moves.substring(i + 3, i + 4).compareTo("2") == 0) {
                                    moves = moves.substring(0, i) + moves.substring(i, i + 1) + "'"
                                            + moves.substring(i + 4);
                                    i--;
                                } else if (moves.substring(i + 3, i + 4).compareTo("'") == 0) {
                                    moves = moves.substring(0, i) + moves.substring(i + 4);
                                    i--;
                                } else {
                                    moves = new String(moves.substring(0, i) + moves.substring(i, i + 1) + "2"
                                            + moves.substring(i + 3));
                                    i--;
                                }
                            } else {
                                moves = new String(moves.substring(0, i) + moves.substring(i, i + 1) + "2"
                                        + moves.substring(i + 3));
                                i--;
                            }
                        }

                    }
                }
            }
        }

        return moves;
    }

    //generate random scramble
    public String randScramble() {
        String scramble = new String();
        char[] possMoves = new char[]{'U', 'D', 'R', 'L', 'F', 'B'}; //The allowed set of moves
        char prevMove = possMoves[(int) (Math.random() * 6)]; //random moves
        char secondLastMove = possMoves[(int) (Math.random() * 6)];
        for (int numMoves = 0; numMoves < 20; ) {
            char move = possMoves[(int) (Math.random() * 6)];
            if (move != prevMove && move != secondLastMove) {
                int rand = (int) (Math.random() * 100);
                if (rand < 33) {
                    scramble += move + "2 ";
                } else if (rand < 67) {
                    scramble += move + "' ";
                } else {
                    scramble += move + " ";
                }
                secondLastMove = prevMove;
                prevMove = move;
                numMoves++;
            }
        }
        scramble(scramble);
        return scramble;
    }

    public void scramble(String scramble) {
        performMoves("z2 " + scramble + " z2");
    }

    public String makeWhiteCross() {
        String moves = new String();

        while (numWhiteEdgesOriented() != 0) { //Turn sunflower into cross until no white edges remain in the U layer
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cubePosition[i][j][0].isEdgeCubie()) {
                        CubieColor[] tempColors = cubePosition[i][j][0].getColors();
                        if (tempColors[0].getColor() == 'W' || tempColors[1].getColor() == 'W') {
                            for (int k = 0; k < 2; k++) {
                                if ((tempColors[k].getColor() == 'R' && tempColors[k].getDir() == 'L') ||
                                        (tempColors[k].getColor() == 'G' && tempColors[k].getDir() == 'F') ||
                                        (tempColors[k].getColor() == 'O' && tempColors[k].getDir() == 'R') ||
                                        (tempColors[k].getColor() == 'B' && tempColors[k].getDir() == 'B')) {
                                    moves += performMoves(cubePosition[i][j][0].verticalFace(i, j) + "2 ");
                                }
                            }
                        }
                    }
                }
            }
            moves += performMoves("U ");
        }
        return optimizeMoves(moves);
    }

    public String makeSunflower() {
        String moves = new String();

        //Brings up white edges in D Layer with white facing down
        if (numWhiteEdgesOriented() < 5) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cubePosition[i][j][2].isEdgeCubie()) {
                        if (cubePosition[i][j][2].getDirOfColor('W') == 'D') {
                            moves += prepareSlot(i, j, 0, 'W');
                            char turnToMake = cubePosition[i][j][2].verticalFace(i, j);
                            moves += performMoves("" + turnToMake + "2 ");
                        }
                    }
                }
            }
        }

        //Orients white edges in D Layer with white NOT facing down
        if (numWhiteEdgesOriented() < 5) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cubePosition[i][j][2].isEdgeCubie()) {
                        if (cubePosition[i][j][2].getDirOfColor('W') != 'A' && cubePosition[i][j][2].getDirOfColor('W') != 'D') {
                            char vert = cubePosition[i][j][2].verticalFace(i, j);
                            moves += prepareSlot(i, j, 0, 'W');
                            if (vert == 'F') {
                                moves += performMoves("F' U' R ");
                            } else if (vert == 'R') {
                                moves += performMoves("R' U' B ");
                            } else if (vert == 'B') {
                                moves += performMoves("B' U' L ");
                            } else if (vert == 'L') {
                                moves += performMoves("L' U' F ");
                            }
                        }
                    }
                }

            }
        }

        if (numWhiteEdgesOriented() < 5) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cubePosition[i][j][1].isEdgeCubie()) {
                        CubieColor[] tempColors = cubePosition[i][j][1].getColors();
                        for (int k = 0; k < 2; k++) {
                            if (tempColors[k].getColor() == 'W') {
                                if (i == 0 && j == 0) {
                                    if (tempColors[k].getDir() == 'L') {
                                        moves += prepareSlot(1, 0, 0, 'W') + performMoves("F ");
                                    } else {
                                        moves += prepareSlot(0, 1, 0, 'W') + performMoves("L' ");
                                    }
                                } else if (i == 2 && j == 0) {
                                    if (tempColors[k].getDir() == 'F') {
                                        moves += prepareSlot(2, 1, 0, 'W') + performMoves("R ");
                                    } else {
                                        moves += prepareSlot(1, 0, 0, 'W') + performMoves("F' ");
                                    }
                                } else if (i == 2 && j == 2) {
                                    if (tempColors[k].getDir() == 'B') {
                                        moves += prepareSlot(2, 1, 0, 'W') + performMoves("R' ");
                                    } else {
                                        moves += prepareSlot(1, 2, 0, 'W') + performMoves("B ");
                                    }
                                } else {
                                    if (tempColors[k].getDir() == 'B') {
                                        moves += prepareSlot(0, 1, 0, 'W') + performMoves("L ");
                                    } else {
                                        moves += prepareSlot(1, 2, 0, 'W') + performMoves("B' ");
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        if (numWhiteEdgesOriented() < 5) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cubePosition[i][j][0].isEdgeCubie()) {
                        if (cubePosition[i][j][0].getDirOfColor('W') != 'A' && cubePosition[i][j][0].getDirOfColor('W') != 'U') {
                            char vert = cubePosition[i][j][0].verticalFace(i, j);
                            if (vert == 'F') {
                                moves += performMoves("F U' R ");
                            } else if (vert == 'R') {
                                moves += performMoves("R U' B ");
                            } else if (vert == 'B') {
                                moves += performMoves("B U' L ");
                            } else if (vert == 'L') {
                                moves += performMoves("L U' F ");
                            }
                        }
                    }
                }

            }
        }

        if (numWhiteEdgesOriented() < 4) {
            moves += makeSunflower();
        }

        return optimizeMoves(moves);
    }

    public String prepareSlot(int x, int y, int z, char color) {
        int numUTurns = 0;
        CubieColor[] tempColor = cubePosition[x][y][z].getColors();
        while ((tempColor[0].getColor() == color || tempColor[1].getColor() == color) && numUTurns < 5) {
            performMoves("U");
            tempColor = cubePosition[x][y][z].getColors();
            numUTurns++;
        }

        if (numUTurns == 0 || numUTurns == 4) {
            return "";
        } else if (numUTurns == 1) {
            return "U ";
        } else if (numUTurns == 2) {
            return "U2 ";
        } else return "U' ";
    }

    public int numWhiteEdgesOriented() {
        int numOriented = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cubePosition[i][j][0].isEdgeCubie()) {
                    if (cubePosition[i][j][0].getDirOfColor('W') == 'U') {
                        numOriented++;
                    }
                }
            }
        }
        return numOriented;
    }

    public String finishWhiteLayer() {
        String moves = new String();
        moves += insertCornersInU();
        moves += "\n";
        moves += insertMisorientedCorners();
        moves += "\n";
        while (whiteCornerinU()) {
            moves += insertCornersInU();
            moves += "\n";
            moves += insertMisorientedCorners();
            moves += "\n";
        }
        return optimizeMoves(moves);
    }

    public boolean whiteCornerinU() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cubePosition[i][j][0].isCornerCubie()) {
                    if (cubePosition[i][j][0].getDirOfColor('W') != 'A')
                        return true;
                }
            }
        }
        return false;
    }

    public String insertCornersInU() {
        String moves = new String();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (cubePosition[x][y][0].isCornerCubie() && cubePosition[x][y][0].isWhiteCorner()) {
                    if (x == 0) {
                        if (y == 0) {
                            moves += performMoves("U' ");
                        } else {
                            moves += performMoves("U2 ");
                        }
                    } else {
                        if (y == 2) {
                            moves += performMoves("U ");
                        }
                    }
                    y = 0;
                    x = 0;

                    int numUTurns = 0;
                    int yRotations = 0;
                    while (!whiteCornerPrepared()) {
                        performMoves("U y'");
                        numUTurns++;
                        yRotations++;
                    }
                    if (numUTurns == 1) {
                        moves += "U ";
                    } else if (numUTurns == 2) {
                        moves += "U2 ";
                    } else if (numUTurns == 3) {
                        moves += "U' ";
                    }
                    if (yRotations == 1) {
                        moves += "y' ";
                    } else if (yRotations == 2) {
                        moves += "y2 ";
                    } else if (yRotations == 3) {
                        moves += "y ";
                    }

                    //Insert the cubie
                    int numberOfMoves = 0;
                    while (!cornerInserted(2, 0, 2)) {
                        performMoves("R U R' U'");
                        numberOfMoves++;
                    }
                    if (numberOfMoves == 5) {
                        moves += "U R U' R' ";
                    } else {
                        for (int k = 0; k < numberOfMoves; k++) {
                            moves += "R U R' U' ";
                        }
                    }
                }
            }
        }

        return moves;
    }

    public String insertMisorientedCorners() {
        String moves = new String();
        for (int i = 0; i < 4; i++) {
            moves += performMoves("y ");
            if (!cornerInserted(2, 0, 2)) {
                if (cubePosition[2][0][2].isWhiteCorner()) {
                    if (!cornerInserted(2, 0, 2)) {
                        moves += performMoves("R U R' U' ");
                        moves += insertCornersInU();
                    }
                }
            }
        }
        return moves;
    }

    public boolean whiteCornerPrepared() {
        boolean whiteUp = false;

        if (cubePosition[2][0][0].isCornerCubie() && cubePosition[2][0][0].getDirOfColor('W') == 'A') {
            return false;
        }

        if (cubePosition[2][0][0].getDirOfColor('W') == 'U')
            whiteUp = true;

        if (whiteUp) {
            return (cubePosition[2][0][0].getColorOfDir('R') == cubePosition[1][0][1].getColors()[0].getColor() &&
                    cubePosition[2][0][0].getColorOfDir('F') == cubePosition[2][1][1].getColors()[0].getColor());
        } else {
            return (cubePosition[2][0][0].getColorOfDir('R') == cubePosition[2][1][1].getColors()[0].getColor() ||
                    cubePosition[2][0][0].getColorOfDir('F') == cubePosition[1][0][1].getColors()[0].getColor());
        }
    }

    public boolean cornerInserted(int x, int y, int z) {
        if (cubePosition[x][y][z].isCornerCubie()) {
            return (cubePosition[x][y][z].getColorOfDir('D') == cubePosition[1][1][2].getColors()[0].getColor() &&
                    cubePosition[x][y][z].getColorOfDir('F') == cubePosition[1][0][1].getColors()[0].getColor() &&
                    cubePosition[x][y][z].getColorOfDir('R') == cubePosition[2][1][1].getColors()[0].getColor());
        }
        return false;
    }

    public String insertAllEdges() {
        String moves = new String();
        moves += insertEdgesInU();
        moves += "\n";
        moves += insertMisorientedEdges();
        moves += "\n";
        while (isYellowEdgesInU()) {
            moves += insertEdgesInU();
            moves += "\n";
            moves += insertMisorientedEdges();
            moves += "\n";
        }
        return optimizeMoves(moves);
    }

    public boolean isYellowEdgesInU() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cubePosition[i][j][0].isEdgeCubie()) {
                    //If a cubie does not have a color, getDirOfColor returns 'A'
                    if (cubePosition[i][j][0].getDirOfColor('Y') == 'A')
                        return true;
                }
            }
        }
        return false;
    }

    public String insertEdgesInU() {
        String moves = new String();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cubePosition[i][j][0].isEdgeCubie() && cubePosition[i][j][0].getDirOfColor('Y') == 'A') {
                    if (j == 1) {
                        if (i == 0) {
                            moves += performMoves("U' ");
                        } else {
                            moves += performMoves("U ");
                        }
                    } else if (j == 2) {
                        moves += performMoves("U2 ");
                    }

                    int numUTurns = 0;
                    int yRotations = 0;
                    while (cubePosition[1][0][0].getColorOfDir('F') != cubePosition[1][0][1].getColors()[0].getColor()) {
                        performMoves("U y' ");
                        numUTurns++;
                        yRotations++;
                    }
                    if (numUTurns == 1) {
                        moves += "U ";
                    } else if (numUTurns == 2) {
                        moves += "U2 ";
                    } else if (numUTurns == 3) {
                        moves += "U' ";
                    }
                    if (yRotations == 1) {
                        moves += "y' ";
                    } else if (yRotations == 2) {
                        moves += "y2 ";
                    } else if (yRotations == 3) {
                        moves += "y ";
                    }

                    if (cubePosition[1][0][0].getColorOfDir('U') == cubePosition[0][1][1].getColors()[0].getColor()) {
                        moves += performMoves("U' L' U L U F U' F' ");
                    } else if (cubePosition[1][0][0].getColorOfDir('U') == cubePosition[2][1][1].getColors()[0].getColor()) {
                        moves += performMoves("U R U' R' U' F' U F ");
                    }

                    i = 0;
                    j = 0;
                }
            }
        }
        return moves;
    }

    public String insertMisorientedEdges() {
        String moves = new String();
        for (int i = 0; i < 4; i++) {
            moves += performMoves("y ");
            if (cubePosition[2][0][1].getDirOfColor('Y') == 'A' &&
                    cubePosition[2][0][1].getColorOfDir('F') != cubePosition[1][0][1].getColors()[0].getColor()) {
                if (cubePosition[2][0][1].getColorOfDir('F') == cubePosition[2][1][1].getColors()[0].getColor() &&
                        cubePosition[2][0][1].getColorOfDir('R') == cubePosition[1][0][1].getColors()[0].getColor()) {
                    moves += performMoves("R U R' U2 R U2 R' U F' U' F ");
                } else {
                    moves += performMoves("R U R' U' F' U' F ");
                    moves += insertEdgesInU();
                }
            }
        }
        return moves;
    }

    public int numYellowEdgesOriented() {
        int numOriented = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cubePosition[i][j][0].isEdgeCubie() && cubePosition[i][j][0].getDirOfColor('Y') == 'U')
                    numOriented++;
            }
        }
        return numOriented;
    }

    public int numYellowCornersOriented() {
        int numberOfOrientation = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cubePosition[i][j][0].isCornerCubie() && cubePosition[i][j][0].getDirOfColor('Y') == 'U')
                    numberOfOrientation++;
            }
        }
        return numberOfOrientation;
    }

    public String yellowEdgeOrientation() {
        String status = new String();
        int numOriented = numYellowEdgesOriented();

        if (numOriented == 4) {
            status = "Cross";
        } else if (numOriented == 0) {
            status = "Dot";
        } else if (numOriented == 2) {
            int[] xValues = new int[2];
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cubePosition[i][j][0].isEdgeCubie() && cubePosition[i][j][0].getDirOfColor('Y') == 'U') {
                        xValues[index] = i;
                        index++;
                    }
                }
            }
            if (Math.abs(xValues[0] - xValues[1]) % 2 == 0) {
                status = "Bar";
            } else {
                status = "L";
            }
        }

        return status;
    }

    public String makeYellowCross() {
        String moves = new String();
        String status = yellowEdgeOrientation();

        if (status.compareTo("Dot") == 0) {
            moves += performMoves("F R U R' U' F' U2 F U R U' R' F' ");
        } else if (status.compareTo("L") == 0) {
            while (cubePosition[0][1][0].getDirOfColor('Y') != 'U' || cubePosition[1][2][0].getDirOfColor('Y') != 'U') {
                moves += performMoves("U ");
            }
            moves += performMoves("F U R U' R' F' ");
        } else if (status.compareTo("Bar") == 0) {
            while (cubePosition[0][1][0].getDirOfColor('Y') != 'U' || cubePosition[2][1][0].getDirOfColor('Y') != 'U') {
                moves += performMoves("U ");
            }
            moves += performMoves("F R U R' U' F' ");
        }
        return optimizeMoves(moves);
    }

    public String orientLastLayer() {
        String moves = new String();
        int numOriented = numYellowCornersOriented();
        while (numOriented != 4) {
            if (numOriented == 0) {
                while (cubePosition[0][0][0].getDirOfColor('Y') != 'L') {
                    moves += performMoves("U ");
                }
                moves += performMoves("R U R' U R U2 R' ");
            } else if (numOriented == 1) {
                while (cubePosition[0][0][0].getDirOfColor('Y') != 'U') {
                    moves += performMoves("U ");
                }
                moves += performMoves("R U R' U R U2 R' ");
            } else if (numOriented == 2) {
                while (cubePosition[0][0][0].getDirOfColor('Y') != 'F') {
                    moves += performMoves("U ");
                }
                moves += performMoves("R U R' U R U2 R' ");
            }
            numOriented = numYellowCornersOriented();
        }
        return optimizeMoves(moves);
    }

    public String permuteLastLayer() {
        String moves = new String();
        int numHeadlights = 0;
        for (int i = 0; i < 4; i++) {
            turn("y");
            if (cubePosition[0][0][0].getColorOfDir('F') == cubePosition[2][0][0].getColorOfDir('F'))
                numHeadlights++;
        }

        if (numHeadlights == 0) {
            moves += performMoves("R' F R' B2 R F' R' B2 R2 ");
            numHeadlights = 1;
        }
        if (numHeadlights == 1) {
            while (cubePosition[0][2][0].getColorOfDir('B') != cubePosition[2][2][0].getColorOfDir('B')) {
                moves += performMoves("U ");
            }
            moves += performMoves("R' F R' B2 R F' R' B2 R2 ");
        }

        int numSolved = 0;
        for (int i = 0; i < 4; i++) {
            turn("y");
            if (cubePosition[0][0][0].getColorOfDir('F') == cubePosition[1][0][0].getColorOfDir('F'))
                numSolved++;
        }
        if (numSolved == 0) {
            moves += performMoves("R2 U R U R' U' R' U' R' U R' ");
            numSolved = 1;
        }
        if (numSolved == 1) {
            while (cubePosition[0][2][0].getColorOfDir('B') != cubePosition[1][2][0].getColorOfDir('B')) {
                moves += performMoves("U ");
            }
            if (cubePosition[1][0][0].getColorOfDir('F') == cubePosition[0][0][0].getColorOfDir('L')) {
                moves += performMoves("R2 U R U R' U' R' U' R' U R' ");
            } else {
                moves += performMoves("R U' R U R U R U' R' U' R2 ");
            }
        }

        while (cubePosition[0][0][0].getColorOfDir('F') != cubePosition[1][0][1].getColors()[0].getColor()) {
            moves += performMoves("U ");
        }

        return optimizeMoves(moves);
    }

    public char[][][] getColors() {
        char[][][] allSets = new char[6][3][3];
        char[][] left = new char[3][3];
        char[][] up = new char[3][3];
        char[][] front = new char[3][3];
        char[][] back = new char[3][3];
        char[][] right = new char[3][3];
        char[][] down = new char[3][3];

        for (int y = 2; y >= 0; y--) {
            for (int z = 2; z >= 0; z--) {
                left[Math.abs(y - 2)][Math.abs(z - 2)] = cubePosition[0][y][z].getColorOfDir('L');
            }
        }
        for (int x = 0; x <= 2; x++) {
            for (int y = 2; y >= 0; y--) {
                up[Math.abs(y - 2)][x] = cubePosition[x][y][0].getColorOfDir('U');
            }
        }
        for (int z = 0; z <= 2; z++) {
            for (int x = 0; x <= 2; x++) {
                front[z][x] = cubePosition[x][0][z].getColorOfDir('F');
            }
        }
        for (int x = 0; x <= 2; x++) {
            for (int z = 2; z >= 0; z--) {
                back[Math.abs(z - 2)][x] = cubePosition[x][2][z].getColorOfDir('B');
            }
        }
        for (int y = 2; y >= 0; y--) {
            for (int z = 0; z <= 2; z++) {
                right[Math.abs(y - 2)][z] = cubePosition[2][y][z].getColorOfDir('R');
            }
        }
        for (int x = 2; x >= 0; x--) {
            for (int y = 2; y >= 0; y--) {
                down[Math.abs(y - 2)][Math.abs(x - 2)] = cubePosition[x][y][2].getColorOfDir('D');
            }
        }

        allSets[0] = left;
        allSets[1] = up;
        allSets[2] = front;
        allSets[3] = back;
        allSets[4] = right;
        allSets[5] = down;

        return allSets;
    }

    public void setAllColors(char[][][] colors) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubePosition[0][Math.abs(j - 2)][i].setColorOfDir('L', colors[0][i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubePosition[j][Math.abs(i - 2)][0].setColorOfDir('U', colors[1][i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubePosition[j][0][i].setColorOfDir('F', colors[2][i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubePosition[Math.abs(j - 2)][2][i].setColorOfDir('B', colors[3][i][j]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubePosition[2][j][i].setColorOfDir('R', colors[4][i][j]);
                colors[4][i][j] = cubePosition[2][j][i].getColorOfDir('R');
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubePosition[j][i][2].setColorOfDir('D', colors[5][i][j]);
            }
        }
    }

    public void paintComponent(Graphics g) {

        int valueOfX = 50;
        int valueOfY = 300;
        int size = CubePainter.CUBIE_SIZE;
        for (int y = 2; y >= 0; y--) {
            for (int z = 2; z >= 0; z--) {
                g.setColor(getColor(cubePosition[0][y][z].getColorOfDir('L')));
                g.fillRect(valueOfX + Math.abs(z - 2) * size, valueOfY + Math.abs(y - 2) * size, size, size);
            }
        }

        valueOfX += size * 3;
        for (int x = 0; x <= 2; x++) {
            for (int y = 2; y >= 0; y--) {
                g.setColor(getColor(cubePosition[x][y][0].getColorOfDir('U')));
                g.fillRect(valueOfX + x * size, valueOfY + Math.abs(y - 2) * size, size, size);
            }
        }

        valueOfY += size * 3;
        for (int z = 0; z <= 2; z++) {
            for (int x = 0; x <= 2; x++) {
                g.setColor(getColor(cubePosition[x][0][z].getColorOfDir('F')));
                g.fillRect(valueOfX + x * size, valueOfY + z * size, size, size);
            }
        }

        valueOfY -= size * 6;
        for (int x = 0; x <= 2; x++) {
            for (int z = 2; z >= 0; z--) {
                g.setColor(getColor(cubePosition[x][2][z].getColorOfDir('B')));
                g.fillRect(valueOfX + x * size, valueOfY + Math.abs(z - 2) * size, size, size);
            }
        }

        valueOfX += size * 3;
        valueOfY += size * 3;
        for (int y = 2; y >= 0; y--) {
            for (int z = 0; z <= 2; z++) {
                g.setColor(getColor(cubePosition[2][y][z].getColorOfDir('R')));
                g.fillRect(valueOfX + z * size, valueOfY + Math.abs(y - 2) * size, size, size);
            }
        }

        valueOfX += size * 3;
        for (int x = 2; x >= 0; x--) {
            for (int y = 2; y >= 0; y--) {
                g.setColor(getColor(cubePosition[x][y][2].getColorOfDir('D')));
                g.fillRect(valueOfX + Math.abs(x - 2) * size, valueOfY + Math.abs(y - 2) * size, size, size);
            }
        }

        ((Graphics2D) g).setStroke(CubePainter.s);
        g.setColor(Color.BLACK);
        for (int k = 0; k < 6; k++) {
            switch (k) {
                case (0):
                    valueOfX = 50;
                    valueOfY = 300;
                    break;
                case (1):
                    valueOfX += size * 3;
                    break;
                case (2):
                    valueOfY += size * 3;
                    break;
                case (3):
                    valueOfY -= size * 6;
                    break;
                case (4):
                    valueOfX += size * 3;
                    valueOfY += size * 3;
                    break;
                case (5):
                    valueOfX += size * 3;
                    break;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    g.drawRect(valueOfX + j * size, valueOfY + i * size, size, size);
                }
            }
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

}
