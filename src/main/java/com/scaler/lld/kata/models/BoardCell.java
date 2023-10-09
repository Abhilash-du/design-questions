package com.scaler.lld.kata.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder(toBuilder = true)
@Setter
@AllArgsConstructor
public class BoardCell {

    private int row;
    private int column;

    private GameSymbol symbol;

    public BoardCell(int row, int column) {
        this.row = row;
        this.column = column;
    }

}
