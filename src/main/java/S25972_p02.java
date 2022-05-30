import java.io.Console;
import java.util.Scanner;

public class S25972_p02 {

    Scanner scanner = new Scanner(System.in);

    int playersCount;
    int[][] gameField;
    int actualPlayerIndex;

    public static void main(String[] args) {
        S25972_p02 s25972_p02 = new S25972_p02();
        s25972_p02.start();
    }

    public void start() {
        this.playersCount = getPlayersCount();
        setupGameField();
        drawGameField();
        getFirstPlayer();
        game();
    }


    public int getPlayersCount() {
        int playersCount = 0;
        while (true) {
            printAnnouncement("Podaj liczbę graczy(2-4): ");
            try {
                playersCount = scanner.nextInt();
            } catch (Exception e) {
                printError("Podaj liczbę z zakresu od 2 do 4");
                scanner.nextLine();
                playersCount = 0;
            }

            if (playersCount >= 1 && playersCount <= 4) {
                return playersCount;
            } else {
                printError("Podaj liczbę z zakresu od 2 do 4");
            }
        }
    }


    public void drawGameField() {

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0) { //pierwszy wiersz
                    if (j != 7) {
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    System.out.print(" 0");
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
                    System.out.print("30 ");
                    continue;
                }
                if (i == 7 && j == 12) {
                    System.out.print("10");
                    continue;
                }

                if (i == 1) {
                    if (j == 1 || j == 2) {
                        if (inHome(3, j)) {
                            System.out.print("\u001B[33m\u2009d\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    if (j == 10 || j == 11) {
                        if (inHome(0, j - 9)) {
                            System.out.print("\u001B[34m\u2009a\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                }

                if (i == 2) {
                    if (j == 1 || j == 2) {
                        if (inHome(3, j + 2)) {
                            System.out.print("\u001B[33m\u2009d\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    if (j == 10 || j == 11) {
                        if (inHome(0, j - 7)) {
                            System.out.print("\u001B[34m\u2009a\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                }

                if (i == 10) {
                    if (j == 1 || j == 2) {
                        if (inHome(2, j)) {
                            System.out.print("\u001B[35m\u2009c\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    if (j == 10 || j == 11) {
                        if (inHome(1, j - 9)) {
                            System.out.print("\u001B[31m\u2009b\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                }

                if (i == 11) {
                    if (j == 1 || j == 2) {
                        if (inHome(2, j + 2)) {
                            System.out.print("\u001B[35m\u2009c\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                    if (j == 10 || j == 11) {
                        if (inHome(1, j - 7)) {
                            System.out.print("\u001B[31m\u2009b\u2009\u001B[0m");
                            continue;
                        }
                        System.out.print("\u2009 \u2009");
                        continue;
                    }
                }

                if (i == 1 && (j >= 5 && j <= 7)) { //trzy górne pola
                    int pos = (33 + j) % 40;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (i == 11 && (j >= 5 && j <= 7)) { //trzy dolne pola
                    int pos = 25 - j;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (j == 5 && i <= 5) { //lewy górny pion
                    int pos = 39 - i;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (j == 7 && i <= 5) { //prawy górny pion
                    int pos = i - 1;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (j == 5 && i >= 7) { //lewy dolny pion
                    int pos = 31 - i;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (j == 7 && i >= 7) { //prawy dolny pion
                    int pos = 7 + i;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (j == 1 && i >= 5 && i <= 7) { //try lewe pola
                    int pos = 35 - i;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (j == 11 && i >= 5 && i <= 7) { //try prawe pola
                    int pos = 3 + i;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (i == 5 && j <= 4) { //lewe górne poziome pola
                    int pos = 29 + j;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (i == 7 && j >= 2 && j <= 4) { //lewe dolne poziome pola
                    int pos = 29 - j;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (i == 5 && j >= 8 && j <= 10) { //prawe górne poziome pola
                    int pos = -3 + j;
                    printPawnOrEmpty(pos);
                    continue;
                }

                if (i == 7 && j >= 8) { //prawe dolne poziome pola
                    int pos = 21 - j;
                    printPawnOrEmpty(pos);
                    continue;
                }

                System.out.print("\u2009 \u2009");


            }
            System.out.print("\n");
        }
    }

    private char getPawnOnFiled(int fieldNumber) {
        for (int[] ints : this.gameField) {
            for (int j = 1; j < 5; j++) {
                if (ints[j] == fieldNumber) {
                    return (char) ints[0];
                }
            }
        }
        return 0;
    }

    private boolean inHome(int color, int number) {
        if (color >= this.playersCount) return false;
        return this.gameField[color][number % 9] == -1;
    }

    private boolean allPawnsAtHome(int color) {
        for (int i = 1; i < 5; i++) {
            if (gameField[color][i] != -1 && gameField[color][i] != -2) {
                return false;
            }
        }
        return true;
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

    private void printPawnOrEmpty(int position) {
        char pawn = getPawnOnFiled(position);
        if (pawn == 0) {
            System.out.print("\u2009x\u2009");
            return;
        }
        switch (pawn) {
            case 'a' -> System.out.print("\u001B[34m\u2009" + pawn + "\u2009\u001B[0m");
            case 'b' -> System.out.print("\u001B[31m\u2009" + pawn + "\u2009\u001B[0m");
            case 'c' -> System.out.print("\u001B[35m\u2009" + pawn + "\u2009\u001B[0m");
            case 'd' -> System.out.print("\u001B[33m\u2009" + pawn + "\u2009\u001B[0m");
        }
    }

    private void getFirstPlayer() {
        int first = (int) ((Math.random() * (playersCount)));
        actualPlayerIndex = first;
        printAnnouncement("Grę rozpoczyna gracz: " + (char) (first + 97));
        leaveHome(first);
    }

    private void game() {
        while (true) {
            drawGameField();
            actualPlayerIndex = actualPlayerIndex % playersCount;
            printAnnouncement("Ruch gracza: " + (char) (actualPlayerIndex + 97));
            int value;
            if (allPawnsAtHome(actualPlayerIndex)) {
                if (!tryLeaveHome(3)) {
                    actualPlayerIndex++;
                    continue;
                }
                continue;
            }
            value = getRandomMove();
            if (value == 6) {
                if (hasPawnAtHome(actualPlayerIndex)) {
                    while (true) {
                        printAnnouncement("Chcesz wyprowadzić pionek ze schowka czy poruszyć innym pionkiem?");
                        printAnnouncement("1. Wyprowadź pionek ze schowka\n2. Ruch pionkiem");
                        int val = scanner.nextInt();
                        if (val == 1) {
                            leaveHome(actualPlayerIndex);
                            break;
                        } else if (val == 2) {
                            playerMove(value);
                            break;
                        } else {
                            printError("Podaj numer opcji");
                        }
                    }
                } else {
                    playerMove(value);
                }
            } else {
                playerMove(value);
            }


            if (value != 6) {
                actualPlayerIndex++;
            }

        }
    }

    private void playerMove(int value) {
        printAnnouncement("Podaj numer pola na którym stoi pionek który należy przesunąć: ");
        int field = scanner.nextInt();
        int pawn = getPawnColorOnField(field);
        if (pawn == -1) {
            printError("Brak pionka na podanym polu");
            playerMove(value);
            return;
        }
        if (pawn != actualPlayerIndex) {
            printError("Nie można przesunąć pionek innego gracza");
            playerMove(value);
            return;
        }

        int newPos = (value + field) % 40;
        movePawn(field, newPos);
    }

    private boolean tryLeaveHome(int tries) {
        if (tries == 0) {
            printAnnouncement("Nie udało się wyjść ze schowka :(");
            return false;
        }
        int value = getRandomMove();
        if (value != 6) {
            return tryLeaveHome(tries - 1);
        }
        leaveHome(actualPlayerIndex);
        printAnnouncement("Udało się wyjść ze schowka! :)");
        return true;
    }

    private void leaveHome(int color) {

        switch (color) {
            case 0 -> movePawn(-1, 0);
            case 1 -> movePawn(-1, 10);
            case 2 -> movePawn(-1, 20);
            case 3 -> movePawn(-1, 30);
        }

    }

    private boolean hasPawnAtHome(int color) {
        for (int i = 1; i < 5; i++) {
            if (gameField[color][i] == -1) {
                return true;
            }
        }
        return false;
    }

    private int getPawnColorOnField(int pos) {
        for (int i = 0; i < playersCount; i++) {
            for (int j = 1; j < 5; j++) {
                if (gameField[i][j] == pos) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getRandomMove() {
        int value = (int) ((Math.random() * (6)) + 1);
        printAnnouncement("Wylosowano : " + value);
        return value;
    }

    private void movePawn(int oldPos, int newPos) {
        int pawn = getPawnColorOnField(newPos);
        if (pawn != -1 && pawn != actualPlayerIndex && newPos % 10 == 0) {
            movePawn(oldPos, newPos + 1);
            return;
        }
        if (pawn != -1 && pawn != actualPlayerIndex) {
            beatPawn(pawn, newPos);
        }
        if (oldPos != -1) {
            switch (actualPlayerIndex) {
                case 0:
                    if (oldPos > 30 && newPos < 10) {
                        getPawnFromGame(oldPos);
                        return;
                    }
                    break;
                case 1:
                    if (oldPos < 10 && newPos >= 10) {
                        getPawnFromGame(oldPos);
                        return;
                    }
                    break;
                case 2:
                    if (oldPos < 20 && newPos >= 20) {
                        getPawnFromGame(oldPos);
                        return;
                    }
                    break;
                case 3:
                    if (oldPos < 30 && newPos >= 30) {
                        getPawnFromGame(oldPos);
                        return;
                    }
                    break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (gameField[actualPlayerIndex][i] == oldPos) {
                gameField[actualPlayerIndex][i] = newPos;
                return;
            }
        }
    }

    private void beatPawn(int color, int position) {
        for (int i = 1; i < 5; i++) {
            if (gameField[color][i] == position) {
                gameField[color][i] = -1;
                return;
            }
        }
    }

    private void getPawnFromGame(int pos) {
        for (int i = 1; i < 5; i++) {
            if (gameField[actualPlayerIndex][i] == pos) {
                gameField[actualPlayerIndex][i] = -2;
                printAnnouncement("Pionek wszedł do domku!");
                return;
            }
        }
    }

    public void printError(String message) {
        System.out.println("\033[0;31m" + message + "\u001B[0m");
    }

    private void printAnnouncement(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }
}
