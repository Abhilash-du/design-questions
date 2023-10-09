package com.scaler.lld.kata.exceptions;

public class InvalidSymbolException extends Throwable {
    public InvalidSymbolException(){
        throw new RuntimeException("Invalid Symbol: (Enter O or X)");
    }
}
