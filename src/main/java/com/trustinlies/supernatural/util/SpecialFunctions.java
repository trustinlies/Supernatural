package com.trustinlies.supernatural.util;

public class SpecialFunctions {
    
    public static int nextLevel(int currentLevel){
        return ((currentLevel + 1) * (currentLevel + 1) + currentLevel + 1)/2 *100 - (currentLevel+1)*100;
    }
    
}
