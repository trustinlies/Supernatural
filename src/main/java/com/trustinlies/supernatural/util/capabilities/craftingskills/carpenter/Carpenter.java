package com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter;

public class Carpenter implements ICarpenter {

    private int carpenter = 0;

    @Override
    public void add(int points){
        this.carpenter += points;
    }

    @Override
    public void set(int points) {
        this.carpenter = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.carpenter + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.carpenter;
    }
}
