package com.trustinlies.supernatural.util.capabilities.combatskills.knight;

public class Knight implements IKnight {

    private int knight = 0;

    @Override
    public void add(int points){
        this.knight += points;
    }

    @Override
    public void set(int points) {
        this.knight = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.knight + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.knight;
    }
}
