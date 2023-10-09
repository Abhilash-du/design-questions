package com.scaler.lld.kata;

import com.scaler.lld.kata.models.Board;
import com.scaler.lld.kata.models.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {

    @Test
    public void testCreateGame(){
//        Board board=new Board();
        Game game=game.builder()
                .w
    }

    @Test
    public void testCreateBoard(){
        Board board=new Board(3);
        int rowSize=board.getCells().size();
        Assertions.assertEquals(3, rowSize,
                "If the ctor of board is called with n it should create n rows");

        int colSize=board.getCells().get(0).size();
        Assertions.assertEquals(3, colSize,
                "If the ctor of board is called with n it should create n cells");


    }
}
