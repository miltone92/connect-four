package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class C4UI {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    

    static String P1 = (ANSI_GREEN + "◉" + ANSI_RESET);
    static String P2 = (ANSI_PURPLE + "◉" + ANSI_RESET);

    static String turn = P1;

    public static void main(String[] args) throws NumberFormatException, IOException {

        
        C4Management.fillMatrix();
        play();

        out.println();

    }// End Main

   

    public static void chipCount() {

        String updateChip = "notNew";
        out.println();
        out.println(
                "Player " + turn + " has " + C4Management.updateChipCount(turn, P1, P2, updateChip) + " chips left");
    }

    public static void play() throws NumberFormatException, IOException {

        String mode = "PVP";
        String updateChip = "new";
        C4Management.updateChipCount(turn, P1, P2, updateChip);

        boolean playing = true;

        printBoard();

        while (playing) {

            // Ask for player input
            out.println();
            out.println("Player " + turn + " please enter a column ");
            int col = -1;
            String userMessage = in.readLine();

            // Check if the input is valid
            try {
                // Check if player wants to see menu

                if (userMessage.equalsIgnoreCase("m")) {
                    showMenu();
                    continue;
                } else {
                    col = Integer.parseInt(userMessage) - 1;
                }

                // Insert players move into the board
                C4Management.updateBoard(col, turn);
                printBoard();

                // Update chip count
                updateChip = "update";
                C4Management.updateChipCount(turn, P1, P2, updateChip);

            } catch (Exception e) {
                out.println("Selected column is not valid");
                continue;
            }

            // check win status (win or draw)
            if (C4Management.winStatus().equals("gameWon")) {
                playing = false;
                out.println();
                out.println("Player " + turn + " wins!!!!");
            } else if (C4Management.winStatus().equals("Draw")) {
                playing = false;
                out.println();
                out.println("Draw!!!!");
            }

            // Switch turns
            turn = C4Management.switchTurn(turn, P1, P2);

            // In case of win/draw; ask if player wants play again
            if (!playing) {
                playAgain(mode);
            }

        } // End while

    }// End play()

    public static void playAI() throws NumberFormatException, IOException {

        String mode = "AI";
        String updateChip = "new";
        C4Management.updateChipCount(turn, P1, P2, updateChip);

        boolean playing = true;

        printBoard();
        while (playing) {

            if (turn == P1) {
                // Ask for player input
                out.println();
                out.println("Player " + turn + " please enter a column ");
                int col = -1;
                String userMessage = in.readLine();

                // Check if the input is valid
                try {
                    // Check if player wants to see menu

                    if (userMessage.equalsIgnoreCase("m")) {
                        showMenu();
                        continue;
                    } else {
                        col = Integer.parseInt(userMessage) - 1;
                    }

                    // Insert players move into the board
                    C4Management.updateBoard(col, turn);
                    printBoard();

                    // Update chip count
                    updateChip = "update";
                    C4Management.updateChipCount(turn, P1, P2, updateChip);

                } catch (Exception e) {
                    out.println("Selected column is not valid");
                    continue;
                }

            } else {
                // Generate AI MOVE
                int col = -1;
                String userMessage = C4Management.randomNumberGenerator(1, C4Management.amountColumns);
                col = Integer.parseInt(userMessage) - 1;

                // Insert AI's move into the board
                C4Management.updateBoard(col, turn);
                printBoard();

                // Update chip count
                updateChip = "update";
                C4Management.updateChipCount(turn, P1, P2, updateChip);

            }
            // check win status (win or draw)
            if (C4Management.winStatus().equals("gameWon")) {
                playing = false;
                out.println();
                out.println("Player " + turn + " wins!!!!");
            } else if (C4Management.winStatus().equals("Draw")) {
                playing = false;
                out.println();
                out.println("Draw!!!!");
            }

            // Switch turns
            turn = C4Management.switchTurn(turn, P1, P2);

            // In case of win/draw; ask if player wants play again
            if (!playing) {
                playAgain(mode);
            }

        } // End while

    }

    private static void playAgain(String mode) throws IOException {

        out.println();
        out.println("Would you like to play again? y/n");
        String playAgain = in.readLine();
        if (playAgain.equalsIgnoreCase("y")) {
            C4Management.fillMatrix();
            turn = C4Management.switchTurn(turn, P1, P2);
            if (mode == "PVP") {
                play();
            } else {
                playAI();
            }

        } else {
            out.println();
            out.println(ANSI_CYAN + " ======== GAME OVER ========" + ANSI_RESET);
            System.exit(0);
        }
    }

    public static void playerColor() throws IOException {
        out.println();
        out.println(ANSI_CYAN + " ===== COLOR SELECT =====" + ANSI_RESET);
        out.println();
        out.println("1. " + ANSI_BLACK + "◉" + ANSI_RESET);
        out.println("2. " + ANSI_RED + "◉" + ANSI_RESET);
        out.println("3. " + ANSI_GREEN + "◉" + ANSI_RESET);
        out.println("4. " + ANSI_YELLOW + "◉" + ANSI_RESET);
        out.println("5. " + ANSI_BLUE + "◉" + ANSI_RESET);
        out.println("6. " + ANSI_PURPLE + "◉" + ANSI_RESET);
        out.println("7. " + ANSI_CYAN + "◉" + ANSI_RESET);
        out.println("8. " + ANSI_WHITE + "◉" + ANSI_RESET);
        out.println("9. RANDOM ");
        out.println();

        String playerColor;

        out.println("Player " + turn + " please select a color");

        playerColor = in.readLine();
        // Check if player wants random color
        if (playerColor.contentEquals("9")) {
            playerColor = C4Management.randomNumberGenerator(1, 8);
        }

        switch (playerColor) {

        case "1":
            if (turn == P1) {
                turn = P1 = (ANSI_BLACK + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                turn = P2 = (ANSI_BLACK + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "2":

            if (turn == P1) {
                P1 = (ANSI_RED + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_RED + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "3":

            if (turn == P1) {
                P1 = (ANSI_GREEN + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_GREEN + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "4":

            if (turn == P1) {
                P1 = (ANSI_YELLOW + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_YELLOW + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "5":

            if (turn == P1) {
                P1 = (ANSI_BLUE + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_BLUE + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "6":

            if (turn == P1) {
                P1 = (ANSI_PURPLE + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_PURPLE + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "7":

            if (turn == P1) {
                P1 = (ANSI_CYAN + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_CYAN + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        case "8":

            if (turn == P1) {
                P1 = (ANSI_WHITE + "◉" + ANSI_RESET);
                turn = P1;
            } else {
                P2 = (ANSI_WHITE + "◉" + ANSI_RESET);
                turn = P2;
            }

            break;

        default:
            break;
        }

        if (P1 == P2) {
            out.println();
            out.println("Color already taken! Try again");
            playerColor();
        }

        printBoard();
    }// End Player Color Menu

    public static void printBoard() {

        int i;
        int j;

        out.println();
        out.println(ANSI_CYAN + " ======= CONNECT FOUR =======" + ANSI_RESET);
        out.println();

        String[] colNum = new String[C4Management.board[0].length];
        // Print out column numbers
        for (i = 0; i < colNum.length; i++) {
            if (i == 0) {
                out.print(" ");
            }
            out.print(ANSI_CYAN + " " + (i + 1) + "  " + ANSI_RESET);
        }
        
        // Print out board contents
        out.print(C4Management.formatBoard());

    }// End printBoard Method

    public static void showMenu() throws IOException {

        out.println();
        out.println(ANSI_CYAN + " ======= Secret Menu =======" + ANSI_RESET);
        out.println();
        out.println("A: Play Friend");
        out.println("B: Play VS AI");
        out.println("C: Chip Count");
        out.println("R: Reset Board");
        out.println("S: Switch Color");
        out.println("Q: Quit Game");
        out.println();
        out.println("E: Exit Menu");

        String userOption;
        out.println();
        out.println("Player " + turn + " select menu option ");
        userOption = in.readLine();

        switch (userOption) {

        case "a":
        case "A":

            C4Management.fillMatrix();
            turn = C4Management.switchTurn(turn, P1, P2);
            play();

            break;

        case "b":
        case "B":

            C4Management.fillMatrix();
            turn = C4Management.switchTurn(turn, P1, P2);
            playAI();

            break;

        case "c":
        case "C":

            chipCount();

            break;

        case "r":
        case "R":

            C4Management.fillMatrix();
            printBoard();

            break;

        case "s":
        case "S":

            playerColor();

            break;

        case "q":
        case "Q":

            quitGame();

            break;

        case "e":
        case "E":

            printBoard();

            break;

        default:
            printBoard();

            break;

        }

    }// End showMenu

    public static void quitGame() throws IOException {

        out.println("Player " + turn + " quits game :(");
        turn = C4Management.switchTurn(turn, P1, P2);
        out.println("Player " + turn + " wins!!!!");
        out.println();
        out.println(ANSI_CYAN + " ======== GAME OVER ========" + ANSI_RESET);

        System.exit(0);// Ends app

    }// End quitGame

}// End program
