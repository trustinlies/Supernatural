package com.trustinlies.supernatural.util.capabilities.combatskills.archer;

public class Archer implements IArcher {

    private int archer = 0;

    @Override
    public void add(int points){
        this.archer += points;
    }

    @Override
    public void set(int points) {
        this.archer = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.archer + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.archer;
    }
}
