package greenatom.projects.battleship;

import java.util.Scanner;

public class SeaBattle {

    private static Logic logic = new Logic();

    private static int status = -1;

    public static void main(String[] args) {
        System.out.println("******** SeaBattle Game *************");
        System.out.println("Введите имя игрока 1: ");
        Scanner scanner = new Scanner(System.in);
        Player firstPlayer = new Player(scanner.nextLine());
        System.out.println("Введите имя игрока 2: ");
        Player secondPlayer = new Player(scanner.nextLine());
        firstPlayer.fillBoard();
        secondPlayer.fillBoard();
        while (true) {
            System.out.println("*****************************");
            do {
                status = logic.fireShot(firstPlayer, secondPlayer);
            } while (status == -1 || status == 3);
            if (status == 4) {
                System.out.println("**************** Game Over *****************");
                System.out.println("Победил игрок: " + firstPlayer.getPlayerName()
                        + " размер флота другого игрока - " + secondPlayer.getPlayerBoard().getFleet().size());
                break;
            }
            do {
                status = logic.fireShot(secondPlayer, firstPlayer);
            } while (status == -1 || status == 3);
            if (status == 4) {
                System.out.println("**************** Game Over *****************");
                System.out.println("Победил игрок: " + secondPlayer.getPlayerName()
                        + " размер флота второго игрока - " + firstPlayer.getPlayerBoard().getFleet().size());
                break;
            }
        }
    }
}
