package com.trustinlies.supernatural.util.capabilities.farmer;

public class Farmer implements IFarmer {

    private int farmer = 0;

    @Override
    public void add(int points){
        this.farmer += points;
    }

    @Override
    public void set(int points) {
        this.farmer = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.farmer + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.farmer;
    }
}
