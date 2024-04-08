package model;

import strategy.BotPlayingStrategy;
import strategy.WinningStrategy;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Game {
    Board board;
    List<Player> players;
    GameStatus gameStatus;
    Player winner;
    int nextTurnIdx;
    List<Move> moves;
    List<WinningStrategy> winningStrategies;
    // undo should be in game controller


    public static class Builder {
        int dimension;
        List<Player> players;
        List<WinningStrategy> winningStrategies;

        public Builder() {
            dimension = 3;
            players = new ArrayList<>();
            winningStrategies = new ArrayList<>();
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public void playAndBoardValidation() throws RuntimeException {
            if (this.players.size() != dimension - 1) {
                throw new RuntimeException("no of player and Board Size not compaitable");
            }
        }

        public void botVaildation() throws RuntimeException {
            int noOfBot = 0;
            for (Player player : players) {
                if (player.getPlayerType() == PlayerType.BOT) {
                    noOfBot++;
                }
            }
            if (noOfBot > 1) {
                throw new RuntimeException("no of bot is greater than 1 : that is : " + noOfBot);
            }
        }

        public void validateWinningStrategy() throws RuntimeException {
            //validate size
            if (winningStrategies == null || winningStrategies.size() == 0) {
                throw new RuntimeException("atleast one winning strategy needed");
            }

            // you can also validate duplicay in winning strategy

        }

        public void symbolValidation() {
            HashMap<Character, Integer> symbolFrequency = new HashMap<>();
            for (Player player : players) {
                symbolFrequency.put(player.getSymbol().getSym(),
                        symbolFrequency.getOrDefault(player.getSymbol().getSym(), 0) + 1);
            }

            for (char sym : symbolFrequency.keySet()) {
                int frequency = symbolFrequency.get(sym);
                if (frequency > 1) {
                    throw new RuntimeException("Two player can't have same symbol : " + sym);
                }
            }
        }

        public void validation() {
            playAndBoardValidation();
            botVaildation();
            symbolValidation();
            validateWinningStrategy();
        }


        public Game build() throws RuntimeException {
            validation();
            return new Game(dimension, players, winningStrategies);
        }
    }

    public static Builder getBuilder() throws Exception {
        // validation
        //1 no of player  = dimension - 1
        // winningStrategies.size()>1
        // there should at most one bot,
        // every player should have different symbol


        return new Builder();

    }

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
        winner = null;
        nextTurnIdx = 0;
        moves = new ArrayList<>();

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextTurn() {
        return nextTurnIdx;
    }

    public void setNextTurn(int nextTurnIdx) {
        this.nextTurnIdx = nextTurnIdx;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }


    public void display() {
        board.displayBoard();
    }


    public boolean validateInput(int row, int col) {
        if (row < 0 || col < 0 || row >= board.getSize() || col >= board.getSize()) {
            return false;
        }

        Cell selectedCell = board.getGrid().get(row).get(col);
        if (selectedCell.cellState != CellState.EMPTY) {
            return false;
        }
        return true;
    }

    public Move makeMove(Scanner scan) {
        Player player = players.get(nextTurnIdx);
        int row = 0;
        int column = 0;

        System.out.println("Its " + player.getName() + " turn : ");
        if (player.getPlayerType() == PlayerType.HUMAN) {
            row = scan.nextInt();
            column = scan.nextInt();
            while (!validateInput(row, column)) {
                System.out.println("You have made invalid move, choose row,col again : ");
                row = scan.nextInt();
                column = scan.nextInt();
            }
        } else if (player.getPlayerType() == PlayerType.BOT) {
            Bot bot = (Bot) player;
            BotPlayingStrategy botPlayingStrategy = bot.getBotPlayingStrategy();
            Cell selectedCell = botPlayingStrategy.makeMove(board);
            row = selectedCell.getRow();
            column = selectedCell.getColumn();
        }


        Cell newCell = new Cell(row, column);
        newCell.cellState = CellState.OCCUPIED;
        newCell.setSymbol(player.getSymbol());

        // change to grid
        board.getGrid().get(row).set(column, newCell);

        Move move = new Move(player, newCell);
        return move;


        //storeMove
        //storeMove(player, newCell);

        // next player
//        nextTurnIdx += 1;
//        nextTurnIdx = nextTurnIdx % players.size();

    }

    public void turnForNextPlayer() {
        nextTurnIdx += 1;
        nextTurnIdx = nextTurnIdx % players.size();
    }

    public void reversPlayerTurn() {
        nextTurnIdx -= 1;
        nextTurnIdx = (nextTurnIdx + players.size()) % players.size();
    }

    public void addMove(Move move) {
        moves.add(move);
    }

}
