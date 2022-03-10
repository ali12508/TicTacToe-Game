package com.example.tictactoegame;

public class PlayerHandler {
    char player;
    public PlayerHandler(){
        player='X';
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }
    public void changePlayer(char player) {
        if(player=='X')
        this.player = 'O';
        else
            this.player='X';
    }
}
