package com.trustinlies.supernatural.init;

import com.trustinlies.supernatural.util.objects.blocks.BlockBase;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.EssenceWell;
import com.trustinlies.supernatural.util.objects.blocks.SpecialBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

    public static final List<Block> BLOCKS = new ArrayList<Block>();
    //public static final List<Block> TEBLOCKS = new ArrayList<Block>();

    //SpecialBlockBase (String name, Material material, float hardness, float resistance, float lightlevel, int opacity, float slippery, String toolClass, int level, SoundType sound)

    //Plain Blocks
    public static final Block BASALT = new BlockBase("basalt", Material.ROCK);
    public static final Block BASALT_SMOOTH = new BlockBase("basalt_smooth", Material.ROCK);
    public static final Block SLATE = new BlockBase("slate", Material.ROCK);
    public static final Block SLATE_SMOOTH = new BlockBase("slate_smooth", Material.ROCK);
    public static final Block QUARTZITE = new BlockBase("quartzite", Material.ROCK);
    public static final Block QUARTZITE_SMOOTH = new BlockBase("quartzite_smooth", Material.ROCK);
    //public static final Block ECTOPLASM_BLOCK = new SpecialBlockBase("ectoplasm_block", Material.GROUND, 1.0F, 10.0F, 15.0F, 0, "pickaxe", 0, SoundType.SLIME);

    //Ores
    public static final Block ORE_SILVER = new SpecialBlockBase("silver_ore", Material.ROCK, 4.0F, 25.0F, 0.0F, 15, "pickaxe", 2, SoundType.STONE);
    public static final Block ORE_TIN = new SpecialBlockBase("tin_ore", Material.ROCK, 4.0F, 25.0F, 0.0F, 15, "pickaxe", 2, SoundType.STONE);
    public static final Block ORE_COPPER = new SpecialBlockBase("copper_ore", Material.ROCK, 4.0F, 25.0F, 0.0F, 15, "pickaxe", 1, SoundType.STONE);
    public static final Block ORE_CINNABAR = new SpecialBlockBase("cinnabar_ore", Material.ROCK, 6.0F, 30.0F, 1.0F, 15, "pickaxe", 3, SoundType.METAL);
    public static final Block ORE_LEAD = new SpecialBlockBase("lead_ore", Material.ROCK, 4.0F, 25.0F, 0.0F, 15, "pickaxe", 2, SoundType.STONE);
    public static final Block ORE_ZINC = new SpecialBlockBase("zinc_ore", Material.ROCK, 4.0F, 25.0F, 0.0F, 15, "pickaxe", 2, SoundType.STONE);

    //Crafting Stations
    public static final Block ESSENCE_WELL_OFF = new EssenceWell("essence_well_off", false);
    public static final Block ESSENCE_WELL_ON = new EssenceWell("essence_well_on", true);
    //public static final Block INCUBATOR = new BlockBase("incubator", Material.IRON);
    //public static final Block SOUL_GENERATOR = new BlockBase("soul_generator", Material.IRON);
    //public static final Block RUNE_INSCRIBER = new BlockBase("rune_inscriber", Material.WOOD);

    //Rune Blocks
    //public static final Block RUNEBLOCK_ENOCHIAN = new SpecialBlockBase("runeblock_enochian", Material.IRON, 4.0F, 50.0F,15.0F, 0, "pickaxe", 1, SoundType.METAL);
    //public static final Block RUNEBLOCK_NORSE = new SpecialBlockBase("runeblock_norse", Material.IRON, 4.0F, 50.0F,15.0F, 0, "pickaxe", 1, SoundType.METAL);
    //public static final Block RUNEBLOCK_CELTIC = new SpecialBlockBase("runeblock_celtic", Material.IRON, 4.0F, 50.0F,15.0F, 0, "pickaxe", 1, SoundType.METAL);

}
