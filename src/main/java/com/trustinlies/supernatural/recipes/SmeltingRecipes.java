package com.trustinlies.supernatural.recipes;

import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipes {

    public static void init(){

        //Ore smelting
        GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_SILVER), new ItemStack(ItemInit.SILVER_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_COPPER), new ItemStack(ItemInit.COPPER_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_ZINC), new ItemStack(ItemInit.ZINC_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_TIN), new ItemStack(ItemInit.TIN_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_LEAD), new ItemStack(ItemInit.LEAD_INGOT), 1.0F);
        GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_CINNABAR), new ItemStack(ItemInit.MERCURY_VIAL), 1.0F);

    }
}
