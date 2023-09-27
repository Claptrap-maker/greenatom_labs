package greenatom.projects.battleship;

public class Player {
    private final String playerName;
    private Board enemyBoard;
    private Board playerBoard;

    public Player(String playerName) {
        this.playerName = playerName;
        this.enemyBoard = new Board();
        this.playerBoard = new Board();

    }

    public String getPlayerName() {
        return playerName;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public void fillBoard() {
        getPlayerBoard().generateShip(4, 1);
        getPlayerBoard().generateShip(3, 2);
        getPlayerBoard().generateShip(2, 3);
        getPlayerBoard().generateShip(1, 4);
    }
}
