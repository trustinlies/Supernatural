package com.trustinlies.supernatural.util.capabilities.combatskills.thief;

public class Thief implements IThief {

    private int thief = 0;

    @Override
    public void add(int points){
        this.thief += points;
    }

    @Override
    public void set(int points) {
        this.thief = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.thief + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.thief;
    }
}
