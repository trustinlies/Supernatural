package com.trustinlies.supernatural.recipes;

import com.google.common.collect.Maps;
import com.trustinlies.supernatural.init.ItemInit;
import net.minecraft.item.ItemStack;

import java.util.*;

public class WellRecipes {

    private static final WellRecipes essenceWell = new WellRecipes();
    private final Map focusList = Maps.newHashMap();
    private final Map experienceList = Maps.newHashMap();
    //public static Map<ItemStack, Float> outputExp;
    //public static Map<List<ItemStack>, ItemStack> inputs;
    //private static final List<ItemStack> fuck = new ArrayList<>();


    public static WellRecipes instance() {
        return essenceWell;
    }

    private WellRecipes() {
        addWellRecipe(
                new ItemStack(ItemInit.CREEPER_ESSENCE),
                new ItemStack(ItemInit.ZINC_INGOT), 0.7F);

    }

    public void addWellRecipe(ItemStack input1, ItemStack output, float exp) {
        focusList.put(input1, output);
        experienceList.put(output, exp);
        /*fuck.add(input1);
        inputs.put(fuck,output);
        outputExp.put(output, exp);*/

    }

    public ItemStack getWellResult(ItemStack itemStack){
        Iterator iterator = focusList.entrySet().iterator();
        Map.Entry entry;
        do{
            if(!iterator.hasNext()){
                return null;
            }
            entry = (Map.Entry)iterator.next();
        }
        while (!areItemStacksEqual(itemStack, (ItemStack)entry.getKey()));
        return  (ItemStack)entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack stack1, ItemStack stack2){
        return stack2.getItem() == stack1.getItem() && stack2.getMetadata() == stack1.getMetadata();
    }

    public Map getFocusList(){
        return focusList;
    }

    public float getExperienceList(ItemStack stack){
        Iterator iterator = experienceList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }
            entry = (Map.Entry)iterator.next();
        }
            while(!areItemStacksEqual(stack, (ItemStack)entry.getKey()));
            return((Float)entry.getValue()).floatValue();

    }

}
