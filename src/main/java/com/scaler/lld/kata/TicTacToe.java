package com.scaler.lld.kata;

import com.scaler.lld.kata.exceptions.InvalidSymbolException;
import com.scaler.lld.kata.models.*;
import com.scaler.lld.kata.strategies.playing.RandomPlayingStrategy;

import java.util.InputMismatchException;
import java.util.Scanner;

// This will be client code
public class TicTacToe {

    public static void main(String[] args) {
        System.out.println("Welcom to TicTacToe");

        //Ask for user input -name, email and symbol
        HumanPlayer human=getUserInput();

        // Create a game
        Game game=createGame(human);

        //Iteratively call make move
        // Until -> Game is WON or DRAWN

        while(game.getStatus()==GameStatus.IN_PROGRESS){
            Player player=game.getNextPlayer();
            System.out.println("Next Player: "+player.getSymbol());

            game.makeMove();
        }
    }

    public static HumanPlayer getUserInput() {

        Scanner scannner = new Scanner(System.in);
        System.out.println("Enter Name");

        String name = scannner.nextLine();

        System.out.println("Enter Email");
        String email = scannner.nextLine();

        System.out.println("Enter Symbol (O or X):");
        GameSymbol symbol;
        try {
            symbol = GameSymbol.valueOf(scannner.nextLine());
        } catch (InputMismatchException e) {
            return null;
        }
        User user = new User(name, email, null);

        return new HumanPlayer(symbol, user);

    }

    private static final int BOARD_SIZE=9;
    private static Game createGame(HumanPlayer humanPlayer) {
        return Game.builder()
                .withSize(BOARD_SIZE)
                .withPlayer(humanPlayer)
                .withPlayer(
                        BotPlayer.builder()
                                .symbol(decideBotSymbol(humanPlayer.getSymbol()))
                                .level(GameLevel.EASY)
                                .playingStrategy(new RandomPlayingStrategy())
                                .build()
                )
                .build();
    }

    // Get the list of all symbols
    // Filter out the user's symbol
    // Randomly select one from the list
    private static GameSymbol decideBotSymbol(GameSymbol symbol) {
        if (symbol == GameSymbol.O) {
            return GameSymbol.X;
        }

        return GameSymbol.O;
    }
}
