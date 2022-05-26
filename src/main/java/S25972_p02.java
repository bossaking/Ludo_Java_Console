import java.io.Console;
import java.util.Scanner;

public class S25972_p02 {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        S25972_p02 s25972_p02 = new S25972_p02();
        s25972_p02.start();
    }

    public void start() {

        int playersCount = getPlayersCount();

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


    public void drawGameField(int playersCount) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                
            }
        }
    }


    public void printError(String message) {
        System.out.println("\033[0;31m" + message + "\u001B[0m");
    }
}
