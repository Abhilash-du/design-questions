package com.scaler.lld.kata.exceptions;

public class InvalidPlayersException extends RuntimeException{
    public InvalidPlayersException(){
        throw new RuntimeException("Invalid list of players!");
    }

}
