import java.io.Console;
import java.util.Scanner;

public class S25972_p02 {

    Scanner scanner = new Scanner(System.in);

    int playersCount;
    int[][] gameField;

    public static void main(String[] args) {
        S25972_p02 s25972_p02 = new S25972_p02();
        s25972_p02.start();
    }

    public void start() {
        this.playersCount = getPlayersCount();
        setupGameField();
        drawGameField();
    }


    public int getPlayersCount() {
        int playersCount = 0;
        while (true) {
            System.out.print("Podaj liczbę graczy(2-4): ");
            try {
                playersCount = scanner.nextInt();
            } catch (Exception e) {
                printError("Podaj liczbę z zakresu od 2 do 4");
                scanner.nextLine();
                playersCount = 0;
            }

            if (playersCount >= 2 && playersCount <= 4) {
                return playersCount;
            } else {
                printError("Podaj liczbę z zakresu od 2 do 4");
            }
        }
    }


    public void drawGameField() {

        int fieldNumber = 0;

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0) { //pierwszy wiersz
                    if (j != 7) {
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    System.out.print("0");
                    continue;
                }
                if (i == 12) { //ostatni wiersz
                    if (j != 5) {
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    System.out.print("20");
                    continue;
                }
                if (i == 5 && j == 0) {
                    System.out.print("30");
                    continue;
                }
                if (i == 7 && j == 12) {
                    System.out.print("10");
                    continue;
                }

//                if(i == 1){
//                    if(j == 1 || j == 2){
//                        if(inHome(3, j - 1)){
//                            System.out.print("\u2009d\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                    if(j == 10 || j == 11){
//                        if(inHome(0, j - 1)){
//                            System.out.print("\u2009a\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                }
//
//                if(i == 2){
//                    if(j == 1 || j == 2){
//                        if(inHome(3, j + 1)){
//                            System.out.print("\u2009d\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                    if(j == 10 || j == 11){
//                        if(inHome(0, j + 1)){
//                            System.out.print("\u2009a\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                }
//
//                if(i == 10){
//                    if(j == 1 || j == 2){
//                        if(inHome(2, j - 1)){
//                            System.out.print("\u2009c\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                    if(j == 10 || j == 11){
//                        if(inHome(1, j - 1)){
//                            System.out.print("\u2009b\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                }
//
//                if(i == 11){
//                    if(j == 1 || j == 2){
//                        if(inHome(2, j + 1)){
//                            System.out.print("\u2009c\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                    if(j == 10 || j == 11){
//                        if(inHome(1, j + 1)){
//                            System.out.print("\u2009b\u2009");
//                            continue;
//                        }
//                        System.out.print(" ");
//                        continue;
//                    }
//                }

                if(i == 1 && (j >= 5 && j <= 7)){
                    int pos = (33 + i) % 40;
                    char pawn = getPawnOnFiled(pos);
                    if(pawn == 0){
                        System.out.print("\u2009x\u2009");
                        continue;
                    }
                    System.out.print("\u2009" + pawn + "\u2009");
                    continue;
                }

                if(j == 5 && i <= 5){
                    int pos = 39 - i;
                    char pawn = getPawnOnFiled(pos);
                    if(pawn == 0){
                        System.out.print("\u2009x\u2009");
                        continue;
                    }
                    System.out.print("\u2009" + pawn + "\u2009");
                    continue;
                }

                if(j == 5 && i >= 7){
                    int pos = 31 - i;
                    char pawn = getPawnOnFiled(pos);
                    if(pawn == 0){
                        System.out.print("\u2009x\u2009");
                        continue;
                    }
                    System.out.print("\u2009" + pawn + "\u2009");
                    continue;
                }

                System.out.print("\u2009 \u2009");


            }
            System.out.print("\n");
        }
    }

    private char getPawnOnFiled(int fieldNumber) {
        for (int i = 0; i < this.gameField.length; i++) {
            for (int j = i; j < 5; j++) {
                if (this.gameField[i][j] == fieldNumber) {
                    return (char) this.gameField[i][0];
                }
            }
        }
        return 0;
    }

    private boolean inHome(int color, int number) {
        if(color >= this.playersCount) return false;
        return this.gameField[color][number % 9] == -1;
    }

    private void setupGameField() {
        this.gameField = new int[playersCount][5];
        for (int i = 0; i < playersCount; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    this.gameField[i][j] = 97 + i;
                    continue;
                }
                this.gameField[i][j] = -1;
            }
        }
    }

    public void printError(String message) {
        System.out.println("\033[0;31m" + message + "\u001B[0m");
    }
}
