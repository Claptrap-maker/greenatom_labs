package greenatom.projects.battleship;

import java.util.Scanner;

public class Logic {
    public int fireShot(Player player1, Player player2) {
        if (isGameOver(player1, player2)) {
            return 4;
        }
        System.out.println("Ходит игрок " + player1.getPlayerName());
        System.out.println("Поле игрока: " + player1.getPlayerName());
        player1.getPlayerBoard().printBoard();
        System.out.println("Поле игрока: " + player2.getPlayerName());
        player1.getEnemyBoard().printBoard();
        Scanner scanner = new Scanner(System.in);
        int x = -1;
        int y = -1;
        int size = player1.getPlayerBoard().getSize();
        while (x < 0 || y < 0 || x >= size || y >= size) {
            System.out.println("Введите координату по вертикали для выстрела");
            x = scanner.nextInt();
            System.out.println("Введите координату по горизонтали для выстрела");
            y = scanner.nextInt();
        }
        try {
            return player2.getPlayerBoard().placeShot(new Cell(x, y), player1.getEnemyBoard());
        } catch (InvalidShotException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public boolean isGameOver(Player player1, Player player2) {
        return player1.getPlayerBoard().getFleet().isEmpty()
                || player2.getPlayerBoard().getFleet().isEmpty();
    }
}
