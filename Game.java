import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Game{
    static boolean winner = false;
    static boolean draw = false;
    static String turn = "O";
    static Scanner in;
    static int numInput;

    public static void main(String[] args) {
        Board myBoard = new Board();
        in = new Scanner(System.in);

        myBoard.printInstructions();
        myBoard.InitEmptyBoard();
        myBoard.printBoard();
        while (checkWinner(myBoard.board)== false) {
            System.out.println("It is " + turn + " 's turn. Please input a number to take the slot.");
            AskForInputs(myBoard.board, turn, false);
            myBoard.printBoard();
            if (draw == true) {
                break;
            }
        }
        if (draw == true) {
            System.out.println("It is a Draw!");
        } else {
            System.out.println(turn + " is the winner!");
        }
}

    static boolean checkWinner(String[] board) {
        boolean winner = false;
        for (int i = 0;i < 9; i++){
            String line = null;
            switch (i) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
                case 8:
                    line = board[0] + board[4] + board[8];
                    break;
            }
            if (line.equals("XXX")) {
                turn = "X";
                winner = true;
            } else if (line.equals("OOO")) {
                turn = "O";
                winner = true;
            }
            for (int a = 0; a < 9; a++) {
                if (Arrays.asList(board).contains(String.valueOf(a+1))) {
                    break;
                }
                else if (a == 8) {
                    draw = true;
                    break;
                }
            }
        }
        return winner;
    }

    static void AskForInputs(String[] board, String chess, boolean winner) {
        IsInputValid();
        if (winner == false) {
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = chess;
                if (chess.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }
            } else {
                System.out.println("Invalid input, slot already taken, please enter another number.\n");
            }
        }
    }

    static void IsInputValid() {
        try{
            numInput = in.nextInt();
            if (!(numInput > 0 && numInput <= 9)) {
                System.out.println("Invalid input, please re-enter integer values (between 1 - 9).");
            }
        } catch (InputMismatchException e) {
            throw new ArithmeticException("Number exceeds 1-9");
        } 
    }
}