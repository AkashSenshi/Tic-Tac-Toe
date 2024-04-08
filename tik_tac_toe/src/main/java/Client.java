import controller.GameController;
import model.*;
import strategy.RowWinningStrategy;
import strategy.WinningStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
        int dimension = 3;

        // player formation
        Symbol symbol1 = new Symbol('X', "Red");
        Symbol symbol2 = new Symbol('0', "Red");
        Player player1 = new Player("1", "Akash", symbol1, PlayerType.HUMAN);
        Player player2 = new Bot("2", "Bot Alice", symbol2, BotDificulityLevel.EASY);
//        List<Player > players = new ArrayList<>();
        List<Player> players = Arrays.asList(new Player[]{player1, player2});

        List<WinningStrategy> winningStrategies = Arrays.asList(new WinningStrategy[]{new RowWinningStrategy()});

        Game game = gameController.startGame(dimension, players, winningStrategies);

        gameController.displayBoard(game);
        Scanner scan = new Scanner(System.in);
        while (gameController.checkGameState(game) == GameStatus.IN_PROGRESS) {
            gameController.handleMove(game, scan);
            gameController.displayBoard(game);

            if (gameController.checkGameState(game) == GameStatus.IN_PROGRESS) {
                System.out.println("Do You want to undo ? [y/n]");
                char ch = scan.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    gameController.undo(game);
                    gameController.displayBoard(game);
                }
            }
        }

        if (gameController.checkGameState(game) == GameStatus.DRAW) {
            System.out.println("Game Draw");
        } else {
            System.out.println("The Winner is : " + gameController.getWinner(game));
        }

    }
}
