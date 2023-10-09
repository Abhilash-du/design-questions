package com.scaler.lld.kata.models;

import com.scaler.lld.kata.exceptions.InvalidMoveException;
import com.scaler.lld.kata.exceptions.InvalidPlayersException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class Game {

    private static final int PLAYER_COUNT = 2;
    private Board board; //private as we don't want to update directly
    private List<Player> players = new ArrayList<>();  // to avoid NPE We initialize collections
    private GameStatus status;
    private static final GameStatus DEFAULT_STATUS = GameStatus.IN_PROGRESS;

    private int nextPlayerIndex = 0;

    public static Builder builder() {

        return new Builder();
    }

    public void makeMove() {
        // Get next move
        BoardCell move = getNextMove();

        // Validate the move - Check if the cell was already filled or not
        validateMove(move);

        // Update the board
        board.update(move);

        // Check for a winner
        if (checkWinner()) {
            status = GameStatus.FINISHED;
        }

        // Check for a draw
        if (checkDraw()) {
            status = GameStatus.DRAWN;
        }

        // Update the next player
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

    }

//    public void update(BoardCell move) {
//        return cell.get(move.ge)
//    }

    private void validateMove(BoardCell move) {
        if (board.isEmpty(move.getRow(), move.getColumn())) {
            throw new InvalidMoveException(move.getRow(), move.getColumn());
        }
    }

    private BoardCell getNextMove() {
        Player player = players.get(nextPlayerIndex);
        BoardCell move = player.makeMove(board);
        validateMove(move);
        return move;
    }

    public void play() {
    }

    private boolean checkWinner() {
        return false;

    }

    private boolean checkDraw() {
        return false;
    }

    public static class Builder {
        private Game game;

        private Builder() {
            game = new Game();
        }

        public Builder withSize(int size) {
            this.game.board = new Board(size);
            return this;
        }

        public Builder withPlayer(Player player) {
            game.getPlayers().add(player);
            return this;
        }

        public Game build() {
            boolean isValid = validate();
            if (!isValid) {
                throw new InvalidPlayersException();
            }

            Game newGame = new Game();
            newGame.board = game.board;
            newGame.players = game.players;
            newGame.status = DEFAULT_STATUS;

            return newGame;
        }

        private boolean validate() {

            List<Player> players = game.players;
            if (players.size() != PLAYER_COUNT) {
                return false;
            }

            // If symbols are unique
            Set<GameSymbol> symbols = players.stream()
                    .map(Player::getSymbol)
                    .collect(Collectors.toSet());

            return symbols.size() == PLAYER_COUNT;
        }
    }

    public void start() {
        // Assign a random value to the nextPlayerIndex
        // Random value -> 0 or 1
        // 0.5*2 = 1.0 = 1
        // 0.1*2 = 0.2 = 0
        // 0.8*2 = 1.6 = 1
        nextPlayerIndex = (int) (Math.random() * players.size());

        //set the status to IN_PROGRESS
        status = GameStatus.IN_PROGRESS;
    }

    public Player getNextPlayer() {
        return players.get(nextPlayerIndex);
    }

}

