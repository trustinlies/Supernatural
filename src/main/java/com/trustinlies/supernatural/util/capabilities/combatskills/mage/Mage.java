package com.trustinlies.supernatural.util.capabilities.combatskills.mage;

public class Mage implements IMage {

    private int mage = 0;

    @Override
    public void add(int points){
        this.mage += points;
    }

    @Override
    public void set(int points) {
        this.mage = points;
    }

    @Override
    public int getLevel(){
        int level = (int) Math.floor((Math.sqrt(100*(2* this.mage + 25)) + 50)/100);
        return Math.max(level, 0);
    }

    @Override
    public int getExp(){
        return this.mage;
    }
}
