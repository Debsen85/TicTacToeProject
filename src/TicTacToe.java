import java.util.Scanner;

import Player.Player;

public class TicTacToe {

    String [][]grid;

    public TicTacToe() {
        this.grid = new String[3][3];
    }

    public void initializeGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.grid[i][j] = "_";
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Boolean validMove(int x, int y) {
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            return false;
        } else if (!this.grid[x - 1][y - 1].equals("_")) {
            return false;
        }
        return true;
    }

    public void makeMove(int x, int y, String symbol) {
        this.grid[x - 1][y - 1] = symbol;
    }

    public Boolean checkWin(String symbol) {
        if ((this.grid[0][0].equals(symbol) && this.grid[0][1].equals(symbol) && this.grid[0][2].equals(symbol)) || 
        (this.grid[1][0].equals(symbol) && this.grid[1][1].equals(symbol) && this.grid[1][2].equals(symbol)) ||
        (this.grid[2][0].equals(symbol) && this.grid[2][1].equals(symbol) && this.grid[2][2].equals(symbol)) || 
        (this.grid[0][0].equals(symbol) && this.grid[1][1].equals(symbol) && this.grid[2][2].equals(symbol)) ||
        (this.grid[2][0].equals(symbol) && this.grid[1][1].equals(symbol) && this.grid[0][2].equals(symbol)) ||
        (this.grid[0][0].equals(symbol) && this.grid[1][0].equals(symbol) && this.grid[2][0].equals(symbol)) ||
        (this.grid[0][1].equals(symbol) && this.grid[1][1].equals(symbol) && this.grid[2][1].equals(symbol)) ||
        (this.grid[0][2].equals(symbol) && this.grid[1][2].equals(symbol) && this.grid[2][2].equals(symbol))) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println();
        System.out.println("<--------Tic Tac Toe-------->");
        System.out.println();
        Scanner command = new Scanner(System.in);
        String playerOne[] = command.nextLine().split(" ");
        String playerTwo[] = command.nextLine().split(" ");
        Player playerX = new Player(playerOne[0], playerOne[1]);
        Player playerO = new Player(playerTwo[0], playerTwo[1]);
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.initializeGrid();
        System.out.println();
        ticTacToe.printGrid();
        System.out.println();

        Integer validMoves = 0;
        Boolean flag = false;
        Player currentPlayer = playerX;
        while(true) {
            String addSymbol[] = command.nextLine().split(" ");
            if (addSymbol[0].equals("exit")) {
                if (validMoves < 9) {
                    flag = true;
                }
                break;
            }
            Integer x = Integer.parseInt(addSymbol[0]);
            Integer y = Integer.parseInt(addSymbol[1]);
            if (ticTacToe.validMove(x, y)) {
                validMoves++;
                ticTacToe.makeMove(x, y, currentPlayer.symbol);
                ticTacToe.printGrid();
                System.out.println();
                if (ticTacToe.checkWin(currentPlayer.symbol)) {
                    System.out.println();
                    System.out.println(currentPlayer.name + " won the game.");
                    System.out.println();
                    flag = true;
                    break;
                }
                if (currentPlayer.symbol.equals("X")) {
                    currentPlayer = playerO;
                } else {
                    currentPlayer = playerX;
                }
            } else {
                System.out.println();
                System.out.println("Invalid Move");
                System.out.println();
            }
        }
        if (!flag) {
            System.out.println();
            System.out.println("Game Over");
            System.out.println();
        }
    }
}