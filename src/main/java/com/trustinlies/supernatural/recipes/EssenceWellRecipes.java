package com.trustinlies.supernatural.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.trustinlies.supernatural.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class EssenceWellRecipes {

    private static final EssenceWellRecipes COOKING_BASE = new EssenceWellRecipes();
    private final Map<ItemStack, ItemStack> cookingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static EssenceWellRecipes instance(){
        return COOKING_BASE;
    }

    public EssenceWellRecipes(){

        this.addCookingRecipe(new ItemStack(ItemInit.BAT_ESSENCE), new ItemStack(ItemInit.FOCUS_BAT), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.BLAZE_ESSENCE), new ItemStack(ItemInit.FOCUS_BLAZE), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.CAVE_SPIDER_ESSENCE), new ItemStack(ItemInit.FOCUS_CAVE_SPIDER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.CHICKEN_ESSENCE), new ItemStack(ItemInit.FOCUS_CHICKEN), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.COW_ESSENCE), new ItemStack(ItemInit.FOCUS_COW), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.CREEPER_ESSENCE), new ItemStack(ItemInit.FOCUS_CREEPER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.ELDER_GUARDIAN_ESSENCE), new ItemStack(ItemInit.FOCUS_ELDER_GUARDIAN), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.ENDER_DRAGON_ESSENCE), new ItemStack(ItemInit.FOCUS_ENDER_DRAGON), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.ENDERMAN_ESSENCE), new ItemStack(ItemInit.FOCUS_ENDERMAN), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.ENDERMITE_ESSENCE), new ItemStack(ItemInit.FOCUS_ENDERMITE), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.EVOKER_ESSENCE), new ItemStack(ItemInit.FOCUS_EVOKER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.GHAST_ESSENCE), new ItemStack(ItemInit.FOCUS_GHAST), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.GUARDIAN_ESSENCE), new ItemStack(ItemInit.FOCUS_GUARDIAN), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.HORSE_ESSENCE), new ItemStack(ItemInit.FOCUS_HORSE), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.LLAMA_ESSENCE), new ItemStack(ItemInit.FOCUS_LLAMA), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.MAGMA_CUBE_ESSENCE), new ItemStack(ItemInit.FOCUS_MAGMA_CUBE), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.MOOSHROOM_ESSENCE), new ItemStack(ItemInit.FOCUS_MOOSHROOM), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.PIG_ESSENCE), new ItemStack(ItemInit.FOCUS_PIG), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.POLAR_BEAR_ESSENCE), new ItemStack(ItemInit.FOCUS_POLAR_BEAR), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.RABBIT_ESSENCE), new ItemStack(ItemInit.FOCUS_RABBIT), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SHEEP_ESSENCE), new ItemStack(ItemInit.FOCUS_SHEEP), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SHULKER_ESSENCE), new ItemStack(ItemInit.FOCUS_SHULKER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SILVERFISH_ESSENCE), new ItemStack(ItemInit.FOCUS_SILVERFISH), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SKELETON_ESSENCE), new ItemStack(ItemInit.FOCUS_SKELETON), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SLIME_ESSENCE), new ItemStack(ItemInit.FOCUS_SLIME), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SPIDER_ESSENCE), new ItemStack(ItemInit.FOCUS_SPIDER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.SQUID_ESSENCE), new ItemStack(ItemInit.FOCUS_SQUID), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.STRAY_ESSENCE), new ItemStack(ItemInit.FOCUS_STRAY), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.VEX_ESSENCE), new ItemStack(ItemInit.FOCUS_VEX), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.VILLAGER_ESSENCE), new ItemStack(ItemInit.FOCUS_VILLAGER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.VINDICATOR_ESSENCE), new ItemStack(ItemInit.FOCUS_VINDICATOR), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.WITCH_ESSENCE), new ItemStack(ItemInit.FOCUS_WITCH), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.WITHER_ESSENCE), new ItemStack(ItemInit.FOCUS_WITHER), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.WITHER_SKELETON_ESSENCE), new ItemStack(ItemInit.FOCUS_WITHER_SKELETON), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.WOLF_ESSENCE), new ItemStack(ItemInit.FOCUS_WOLF), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.ZOMBIE_ESSENCE), new ItemStack(ItemInit.FOCUS_ZOMBIE), 0.6f);
        this.addCookingRecipe(new ItemStack(ItemInit.ZOMBIE_PIGMAN_ESSENCE), new ItemStack(ItemInit.FOCUS_ZOMBIE_PIGMAN), 0.6f);

    }

    public void addCookingRecipeForBlock(Block input, ItemStack stack, float experience){
        this.addCooking(Item.getItemFromBlock(input), stack, experience);
    }

    public void addCooking(Item input, ItemStack stack, float experience){
        this.addCookingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    public void addCookingRecipe(ItemStack input, ItemStack stack, float experience){
        if(getCookingResult(input) != ItemStack.EMPTY){
            net.minecraftforge.fml.common.FMLLog.info("Ignored Cooking Recipe with conflicting input : {} = {}", input, stack); return;
        }
        this.cookingList.put(input, stack);
        this.experienceList.put(stack, experience);
    }

    public ItemStack getCookingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.cookingList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getCookingList()
    {
        return this.cookingList;
    }

    public float getCookingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return (Float) entry.getValue();
            }
        }
        return 0.0F;
    }

}
