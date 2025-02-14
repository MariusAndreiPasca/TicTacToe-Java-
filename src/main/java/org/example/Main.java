package org.example;

import java.util.*;

public class Main {
    // players moves array list
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
        // tic tac toe board pattern
        char [][] gameBoard = { {' ', '|',' ','|', ' '},
                                {'-', '+','-','+', '-'},
                                {' ', '|',' ','|', ' '},
                                {'-', '+','-','+', '-'},
                                {' ', '|',' ','|', ' '}};
        // printing board in console
        printGameBoard(gameBoard);

        Scanner scna = new Scanner(System.in);
        // while loop for game turns
        while (true) {
            System.out.println("Enter your placement (1-9): ");
            // player moves
            int position = scna.nextInt();
            // verify if position is alreday taken
            while (playerPosition.contains(position) || cpuPosition.contains(position)) {
                System.out.println("Position take! Enter a correct position!");
                position = scna.nextInt();
            }

            System.out.println(position);
            // places the position/move
            placePiece(gameBoard,position,"player");
            // check if there is a winner after player movew
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            // cpu random moves generator
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            // checks if pos is available
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            // places cpu move
            placePiece(gameBoard, cpuPos,"cpu");
            // prints the board with the taken moves
            printGameBoard(gameBoard);
            // checks if there is a winner after the cpu move
            result = checkWinner();
            // prints the winner or if there is a tie
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }

    }

    // printing board methode
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard){
            for (char c : row) {
                System.out.print(c);
            }

            System.out.println();
        }
    }

    // X + 0 logic
    public static void placePiece(char[][] gameBoard, int position, String user) {

        char symbol = ' ';

        if(user.equals("player")) {
            symbol = 'X';
            playerPosition.add(position);
        } else if (user.equals("cpu")) {
            symbol = '0';
            cpuPosition.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    // win conditions
    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        // checks the winner(player or cpu)
        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Congratulation you won!";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU wins! Sorry!";
              // checks the tie
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "CAT!";
            }
        }

        return "";
    }
}