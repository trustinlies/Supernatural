package com.trustinlies.supernatural.util.capabilities;

public class Mining implements IMining {

    private int mining = 0;

    @Override
    public void add(int points){
        this.mining += points;
    }

    @Override
    public void set(int points) {
        this.mining = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.mining + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.mining;
    }
}
