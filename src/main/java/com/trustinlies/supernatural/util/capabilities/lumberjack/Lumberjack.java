package com.trustinlies.supernatural.util.capabilities.lumberjack;

public class Lumberjack implements ILumberjack {

    private int lumberjack = 0;

    @Override
    public void add(int points){
        this.lumberjack += points;
    }

    @Override
    public void set(int points) {
        this.lumberjack = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.lumberjack + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.lumberjack;
    }
}
