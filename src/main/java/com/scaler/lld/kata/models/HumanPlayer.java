package com.scaler.lld.kata.models;

import lombok.Builder;

import java.util.Scanner;

//Extrinsic state
public class HumanPlayer extends Player {

    private User user;

    @Builder.Default
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(GameSymbol symbol) {
        super(symbol);
    }

    public HumanPlayer(GameSymbol symbol, User user) {
        super(symbol);
    }

    @Override
    public BoardCell makeMove(Board board) {
        System.out.println("Enter the row and column");
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        return new BoardCell(row, column, getSymbol());

    }
}
