package com.trustinlies.supernatural.util.lists;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class Lists {

    public static final List<Block> FARM_BLOCKS = new ArrayList<>();
    public static final List<Block> MINE_BLOCKS = new ArrayList<>();
    public static final List<Block> LUMBER_BLOCKS = new ArrayList<>();

    public static final List<Item> KNIGHT_WEAPONS = new ArrayList<>();
    public static final List<Item> THIEF_WEAPONS = new ArrayList<>();
    public static final List<Item> ARCHER_WEAPONS = new ArrayList<>();
    public static final List<Item> MAGE_WEAPONS = new ArrayList<>();

    //private static final Item



    public void setKnightWeapons(){
        KNIGHT_WEAPONS.add(Items.DIAMOND_AXE);
        System.out.println("Added Diamond Axe");
        KNIGHT_WEAPONS.add(Items.DIAMOND_SWORD);
        KNIGHT_WEAPONS.add(Items.GOLDEN_AXE);
        KNIGHT_WEAPONS.add(Items.GOLDEN_SWORD);
        KNIGHT_WEAPONS.add(Items.STONE_AXE);
        KNIGHT_WEAPONS.add(Items.STONE_SWORD);
        KNIGHT_WEAPONS.add(Items.IRON_AXE);
        KNIGHT_WEAPONS.add(Items.IRON_SWORD);
        KNIGHT_WEAPONS.add(Items.WOODEN_AXE);
        KNIGHT_WEAPONS.add(Items.WOODEN_SWORD);
    }

}
