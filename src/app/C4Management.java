package app;

import java.util.Random;

public class C4Management {

    static int amountRows = 6;
    static int amountColumns = 7;

    static String[][] board = new String[amountRows][amountColumns];

    static int totalChips = (amountRows * amountColumns);
    static int chipsP1 = totalChips / 2;
    static int chipsP2 = totalChips / 2;

    // Method: switchTurn
    // Purpose: Switches turns
    // Returns: The next players turn

    public static String switchTurn(String pturn, String P1, String P2) {
        if (pturn == P1) {
            pturn = P2;
        } else {
            pturn = P1;

        }

        return pturn;

    }// End switchTurn

    // Method: boardFormat
    // Purpose: gives the matrix format so it looks like an actual board
    // Returns: a formated matrix using a "String"
    public static String formatBoard(){
        
         String boardFormat = "";
         int i;
        // Print out board contents
        for (i = 0; i < board.length; i++) {
           
            boardFormat += "\n";
            for (int j = 0; j < board[i].length; j++) {
                if (j == 0) {
                    
                    boardFormat+= "| ";
                }

                
                boardFormat += board[i][j] + " | ";
            }
        }
       return boardFormat;

    }//End boardFormat

     // Method: fillMatrix
    // Purpose: fills board with "_". Gives the "_" value to each index
    // Returns: void -just affects the matrix-

    public static void fillMatrix() {

        int row = 0;
        int col = 0;

        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                board[row][col] = "_";
            }
        }
    }// End fill

    // Method: randomNumberGenerator
    // Purpose: Generates a random number from a received rage (parameters)
    // Returns: generated random number
    public static String randomNumberGenerator(int bottomLimit, int topLimit) {
        String numbers = "";
        for (int i = bottomLimit; i <= topLimit; i++) {
            numbers += i;
        } 

        int N = numbers.length();
        String result = "";
        Random r = new Random();

        // Instead of 1; one could select the amount of number one desires back
        for (int i = 0; i < 1; i++) {
            int randomIndex = r.nextInt(N);
            char newNumber = numbers.charAt(randomIndex);
            result += newNumber;

            // result += numbers.charAt(r.nextInt(N));
        }

        return result;

    }// End of randomNumberGenerator

    // Method: verifyChip
    // Purpose: verifies chip's position is valid (within
    // board's bounds)
    // Returns: Either "null" (not valid) or a valaid position

    public static String verifyChip(int row, int col) {
        if (row < 0 || col < 0 || row >= amountRows || col >= amountColumns) {
            return "null";
        } else {
            return board[row][col];
        }

    }// End verify Chip Position

    // Method: updateBoard
    // Purpose: Receive's player's move adds the the chip to the matrix
    // Returns: void -just affects the matrix-

    public static void updateBoard(int col, String pturn) {

        for (int i = (board.length - 1); i >= 0; i--) {
            if (board[i][col] == "_") {
                board[i][col] = pturn;
                break;

            }
        }

    }// End updateBoard

    // Method: updateChipCount
    // Purpose: updates the amount of chips (moves) lesft for the respective
    // player's turn
    // Returns: the number of chips (moves) left
    public static int updateChipCount(String pturn, String P1, String P2, String updateChip) {
        if (updateChip.equals("new") && pturn == P1) {
            totalChips = (amountRows * amountColumns);
            chipsP1 = totalChips / 2;
            chipsP2 = totalChips / 2;
            return chipsP1;
        }else if (updateChip.equals("new") && pturn == P2) {
            totalChips = (amountRows * amountColumns);
            chipsP1 = totalChips / 2;
            chipsP2 = totalChips / 2;
            return chipsP1;
        }


        if (updateChip.equals("update")) {
            if (pturn == P1) {
                chipsP1--;
                return chipsP1;
            } else {
                chipsP2--;
                return chipsP2;
            }
        } else {
            if (pturn == P1) {
                return chipsP1;
            } else {
                return chipsP2;
            }
        }
        
    }

    // End updateBoard

    // Method: winStatus
    // Purpose: checks the matrix status to see if a player has 4 chips in a row
    // in any direction. It also checks whether or not there is a draw.
    // Returns: void wather the game has been won or not (4 chips in a row or not)

    public static String winStatus() {

        String winStatus = "";
        int row = 0;
        int col = 0;

        // Check row win
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                if (verifyChip(row, col) != "null" && verifyChip(row, col) != "_"
                        && verifyChip(row, col) == verifyChip(row, col + 1)
                        && verifyChip(row, col) == verifyChip(row, col + 2)
                        && verifyChip(row, col) == verifyChip(row, col + 3)) {
                    winStatus = "gameWon";
                    return winStatus;
                }

            }
        }

        // Check column win
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                if (verifyChip(row, col) != "null" && verifyChip(row, col) != "_"
                        && verifyChip(row, col) == verifyChip(row + 1, col)
                        && verifyChip(row, col) == verifyChip(row + 2, col)
                        && verifyChip(row, col) == verifyChip(row + 3, col)) {
                    winStatus = "gameWon";
                    return winStatus;
                }

            }
        }

        // Check diagonal win
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                for (int direction = -1; direction <= 1; direction += 2) {
                    if (verifyChip(row, col) != "null" && verifyChip(row, col) != "_"
                            && verifyChip(row, col) == verifyChip(row + 1 * direction, col + 1)
                            && verifyChip(row, col) == verifyChip(row + 2 * direction, col + 2)
                            && verifyChip(row, col) == verifyChip(row + 3 * direction, col + 3)) {
                        winStatus = "gameWon";
                        return winStatus;
                    }
                }

            }
        }

        // Check draw
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[row].length; col++) {
                if (board[row][col] == "_") {
                    winStatus = "No draw";
                    return winStatus;
                } else {
                    winStatus = "Draw";

                }
            }
        }

        return winStatus;

    }// End winGame

}