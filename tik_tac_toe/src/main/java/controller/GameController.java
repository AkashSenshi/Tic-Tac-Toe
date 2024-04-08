package controller;

import model.*;
import strategy.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class GameController {
    // 1. Start the game
    // taking player demographic, choosing symbol, turn setup, winning strategy setup
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws Exception {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void displayBoard(Game game) {
        game.display();
    }

    public void handleMove(Game game, Scanner scan) {
//        Player player = game.getPlayers().get(game.getNextTurn());
        Move move = game.makeMove(scan);
        game.addMove(move);
        handleGameStatus(game, move);
        game.turnForNextPlayer();
    }

    public void handleGameStatus(Game game, Move move) {
        // WINNER/
        for (WinningStrategy winningStrategy : game.getWinningStrategies()) {
            if (winningStrategy.checkWinner(move, game.getBoard())) {
                game.setGameStatus(GameStatus.WON);
            } else if (game.getMoves().size() == (game.getBoard().getSize() * game.getBoard().getSize())) {
                game.setGameStatus(GameStatus.DRAW);
            }
        }
    }

    public void handleUndoGameStatus(Game game, Move move) {
        // WINNER/
        for (WinningStrategy winningStrategy : game.getWinningStrategies()) {
            winningStrategy.handleUndo(move);
        }
    }


    public GameStatus checkGameState(Game game) {
        return game.getGameStatus();
    }
// 2. while the game state is in prgress , make the move
// 2.1 display the board
// 2.2 move
// 3. winner, draw/

    public String getWinner(Game game) {
        return game.getMoves().get(game.getMoves().size() - 1).getPlayer().getName();
    }
// undo

    public void undo(Game game) {
//        Move move = game.makeMove(scan);
//        game.addMove(move);
//        handleGameStatus(game, move);
//        game.turnForNextPlayer();
        game.reversPlayerTurn();

        Move lastMove = game.getMoves().get(game.getMoves().size() - 1);
        Cell lastCell = lastMove.getCell();
        game.setGameStatus(GameStatus.IN_PROGRESS);
        handleUndoGameStatus(game,lastMove);
        game.getMoves().remove(game.getMoves().size() - 1);

        lastCell.setSymbol(null);
        lastCell.setCellState(CellState.EMPTY);



    }


}
