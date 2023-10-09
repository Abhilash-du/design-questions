package com.scaler.lld.kata.models;

import lombok.AllArgsConstructor;

// Instinsic state -- state which will not change
@AllArgsConstructor
public class User {

    private String name;
    private String email;
    private String photo;
}
