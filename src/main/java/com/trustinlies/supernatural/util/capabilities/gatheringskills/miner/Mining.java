package com.trustinlies.supernatural.util.capabilities.gatheringskills.miner;

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

    public int getNextExp(){
        int test =  (((this.getLevel()+1)^2+(this.getLevel()+1))/2*100-((this.getLevel()+1)*100));
        return test;
    }
}
