package com.trustinlies.supernatural.init;

import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.objects.items.*;
import com.trustinlies.supernatural.util.objects.items.tools.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Material Groups
    public static final ItemArmor.ArmorMaterial ARMOR_SILVER = EnumHelper.addArmorMaterial("armor_silver", Reference.MOD_ID + ":silver", 500, new int[] {4, 7, 9, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.5F);
    public static final ItemArmor.ArmorMaterial ARMOR_COPPER = EnumHelper.addArmorMaterial("armor_copper", Reference.MOD_ID + ":copper", 200, new int[] {4, 6, 8, 5}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.3F);
    public static final ItemArmor.ArmorMaterial ARMOR_TIN = EnumHelper.addArmorMaterial("armor_tin", Reference.MOD_ID + ":tin", 128, new int[] {2, 4, 7, 3}, 6, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static final ItemArmor.ArmorMaterial ARMOR_ZINC = EnumHelper.addArmorMaterial("armor_zinc", Reference.MOD_ID + ":zinc", 190, new int[] {3, 5, 8, 4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1F);
    public static final ItemArmor.ArmorMaterial ARMOR_BRONZE = EnumHelper.addArmorMaterial("armor_bronze", Reference.MOD_ID + ":bronze", 250, new int[] {4, 7, 9, 5}, 4, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F);
    public static final ItemArmor.ArmorMaterial ARMOR_BRASS = EnumHelper.addArmorMaterial("armor_brass", Reference.MOD_ID + ":brass", 200, new int[] {3, 5, 8, 4}, 29, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F);
    public static final ItemArmor.ArmorMaterial ARMOR_CIRON = EnumHelper.addArmorMaterial("armor_ciron", Reference.MOD_ID + ":ciron", 500, new int[] {5, 8, 10, 6}, 40, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 7.0F);
    public static final ItemArmor.ArmorMaterial ARMOR_LEAD = EnumHelper.addArmorMaterial("armor_lead", Reference.MOD_ID + ":lead", 2000, new int[] {10, 15, 20, 10}, 30, SoundEvents.BLOCK_ANVIL_HIT, 10.0F);

    public static final Item.ToolMaterial TOOL_CIRON = EnumHelper.addToolMaterial("tool_ciron", 3, 500, 5.0F, 10.0F, 40);
    public static final Item.ToolMaterial TOOL_BRASS = EnumHelper.addToolMaterial("tool_brass", 2, 200, 5.0F, 6.0F, 29);
    public static final Item.ToolMaterial TOOL_BRONZE = EnumHelper.addToolMaterial("tool_bronze", 3, 250, 5.0F, 8.0F, 4);
    public static final Item.ToolMaterial TOOL_COPPER = EnumHelper.addToolMaterial("tool_copper", 2, 200, 5.0F, 6.0F, 12);
    public static final Item.ToolMaterial TOOL_LEAD = EnumHelper.addToolMaterial("tool_lead", 2, 500, 5.0F, 12.0F, 30);
    public static final Item.ToolMaterial TOOL_SILVER = EnumHelper.addToolMaterial("tool_silver", 2, 500, 5.0F, 8.0F, 25);
    public static final Item.ToolMaterial TOOL_TIN = EnumHelper.addToolMaterial("tool_tin", 2, 128, 5.0F, 3.0F, 6);
    public static final Item.ToolMaterial TOOL_ZINC = EnumHelper.addToolMaterial("tool_zinc", 2, 190, 5.0F, 4.0F, 9);

    //Foods
    //Pies give saturation effect for 30 seconds. Juice gives Movement Speed for 30 seconds.
    public static final Item APPLE_JUICE = new FoodBase("apple_juice", 3, 0.2F, false, "edible"); //"apple_juice" is the name for resources
    public static final Item APPLE_PIE = new FoodBase("apple_pie", 8, 0.8F, false, "edible");
    public static final Item BERRY_JUICE = new FoodBase("berry_juice", 3, 0.2F, false, "edible");
    public static final Item BERRY_PIE = new FoodBase("berry_pie", 8, 0.8F, false, "edible");
    public static final Item BAD_BOY = new FoodBase("bad_boy", -8, 0.0F, false, "edible");

    //Utility
    public static final Item GRINDER = new ItemBase("grinder", "grinder");
    //public static final Item OBSIDIAN_SHARD = new ItemBase("obsidian_shard", "none");
    public static final Item OBSIDIAN_SOUL_GEM_EMPTY = new ItemBase("obsidian_soul_gem_empty", "none");
    //public static final Item OBSIDIAN_SOUL_GEM_FULL = new ItemBase("obsidian_soul_gem_full", "none");
    public static final Item FOCUS_EMPTY = new ItemBase("focus_empty", "none");

    //Ingots
    public static final Item SILVER_INGOT = new ItemBase("silver_ingot", "none");
    public static final Item BRASS_INGOT = new ItemBase("brass_ingot", "none");
    public static final Item COPPER_INGOT = new ItemBase("copper_ingot", "none");
    public static final Item ZINC_INGOT = new ItemBase("zinc_ingot", "none");
    public static final Item TIN_INGOT = new ItemBase("tin_ingot", "none");
    public static final Item BRONZE_INGOT = new ItemBase("bronze_ingot", "none");
    public static final Item LEAD_INGOT = new ItemBase("lead_ingot", "none");
    public static final Item CONSECRATED_IRON_INGOT = new ItemBase("consecrated_iron_ingot", "shiny");
    //public static final Item PHOENIX_GLASS = new ItemBase("phoenix_glass", "none");

    //Dust
    /*public static final Item SILVER_DUST = new ItemBase("silver_dust", "none");
    public static final Item BRASS_DUST = new ItemBase("brass_dust", "none");
    public static final Item COPPER_DUST = new ItemBase("copper_dust", "none");
    public static final Item ZINC_DUST = new ItemBase("zinc_dust", "none");
    public static final Item TIN_DUST = new ItemBase("tin_dust", "none");
    public static final Item BRONZE_DUST = new ItemBase("bronze_dust", "none");
    public static final Item CONSECRATED_IRON_DUST = new ItemBase("consecrated_iron_dust", "none");
    public static final Item GOLD_DUST = new ItemBase("gold_dust", "none");
    public static final Item SALT = new ItemBase("salt", "none");
    public static final Item PHOENIX_ASH = new ItemBase("phoenix_ash", "none");
    public static final Item SULFUR = new ItemBase("sulfur", "none");

    //Plants
    public static final Item ROSEMARY = new ItemBase("rosemary", "none");
    public static final Item SAGE = new ItemBase("sage", "none");
    public static final Item ANCIENT_SPICES = new ItemBase("ancient_spices", "none");
    public static final Item LUCKY_CLOVER = new ItemBase("lucky_clover", "none");
    public static final Item ROSEMARY_SEEDS = new ItemBase("rosemary_seeds", "none");
    public static final Item SAGE_SEEDS = new ItemBase("sage_seeds", "none");
    public static final Item ANCIENT_SPICE_SEEDS = new ItemBase("ancient_spice_seeds", "none");


    //Misc
    public static final Item SMOLDERING_COAL = new ItemBase("smoldering_coal", "none");
    public static final Item ECTOPLASM = new ItemBase("ectoplasm", "none");
    public static final Item BAT_WING = new ItemBase("bat_wing", "none");
    public static final Item GUANO = new ItemBase("guano", "none");

    //Materials
    public static final Item GOLDEN_FLEECE = new ItemBase("golden_fleece", "none");
    public static final Item YETI_FUR = new ItemBase("yeti_fur", "none");
    public static final Item STYMPHALIAN_FEATHER = new ItemBase("stymphalian_feather", "none");
    public static final Item PHOENIX_FEATHER = new ItemBase("phoenix_feather", "none");
    public static final Item MANTICORE_LEATHER = new ItemBase("manticore_leather", "none");
    public static final Item MANTICORE_STINGER = new ItemBase("manticore_stinger", "none");
    public static final Item CHIMERA_HIDE = new ItemBase("chimera_hide", "none");
    public static final Item CHIMERA_FLAME_GLAND = new ItemBase("chimera_flame_gland", "none");
    public static final Item WITHER_BONE = new ItemBase("wither_bone", "none");
    public static final Item WEREWOLF_HIDE = new ItemBase("werewolf_hide", "none");
    public static final Item ARACHNE_CHITIN = new ItemBase("arachne_chitin", "none");
    */

    //Nuggets
    public static final Item SILVER_NUGGET = new ItemBase("silver_nugget", "none");
    public static final Item BRASS_NUGGET = new ItemBase("brass_nugget", "none");
    public static final Item COPPER_NUGGET = new ItemBase("copper_nugget", "none");
    public static final Item ZINC_NUGGET = new ItemBase("zinc_nugget", "none");
    public static final Item TIN_NUGGET = new ItemBase("tin_nugget", "none");
    public static final Item BRONZE_NUGGET = new ItemBase("bronze_nugget", "none");
    public static final Item LEAD_NUGGET = new ItemBase("lead_nugget", "none");
    public static final Item ENDER_NUGGET = new ItemBase("ender_nugget", "none");
    public static final Item CONSECRATED_IRON_NUGGET = new ItemBase("consecrated_iron_nugget", "shiny");
    /*

    //Weapons
    public static final Item SILVER_DAGGER = new ItemBase("silver_dagger", "none");
    public static final Item GOLD_DAGGER = new ItemBase("gold_dagger", "none");
    public static final Item STONE_DAGGER = new ItemBase("stone_dagger", "none");
    public static final Item IRON_DAGGER = new ItemBase("iron_dagger", "none");
    public static final Item CONSECRATED_IRON_DAGGER = new ItemBase("consecrated_iron_dagger", "none");
    public static final Item WOOD_DAGGER = new ItemBase("wood_dagger", "none");
    public static final Item BAMBOO_DAGGER = new ItemBase("bamboo_dagger", "none");
    public static final Item BRASS_DAGGER = new ItemBase("brass_dagger", "none");
    public static final Item COPPER_DAGGER = new ItemBase("copper_dagger", "none");
    public static final Item LEAD_DAGGER = new ItemBase("lead_dagger", "none");
    public static final Item MANTICORE_STINGER_DAGGER = new ItemBase("manticore_stinger_dagger", "none");
    public static final Item PHOENIX_GLASS_DAGGER = new ItemBase("phoenix_glass_dagger", "none");
    public static final Item SILVER_KATANA = new ItemBase("silver_katana", "none");
    public static final Item IRON_KATANA = new ItemBase("iron_katana", "none");
    public static final Item CONSECRATED_IRON_KATANA = new ItemBase("consecrated_iron_katana", "none");
    public static final Item PHOENIX_GLASS_KATANA = new ItemBase("phoenix_glass_katana", "none");
    public static final Item WHIP = new ItemBase("whip", "none");
    public static final Item ASWANG_WHIP = new ItemBase("aswang_whip", "none");
    public static final Item MIRROR = new ItemBase("mirror", "none");
    public static final Item BLOWPIPE = new ItemBase("blowpipe", "none");


    //Books
    public static final Item HUNTERS_JOURNAL = new ItemBase("hunters_journal", "none");
    public static final Item MONSTER_NOTES = new ItemBase("monster_notes", "none");

*/
    //Vials
    public static final Item LAMBS_BLOOD_VIAL = new ItemBase("lambs_blood_vial", "none");
    public static final Item FRESH_WATER_VIAL = new ItemBase("fresh_water_vial", "none");
    public static final Item GRIFFIN_TEARS_VIAL = new ItemBase("griffin_tears_vial", "none");
    public static final Item HOLY_WATER_VIAL = new ItemBase("holy_water_vial", "projectile, shiny");
    public static final Item ASWANG_OIL_VIAL = new ItemBase("aswang_oil_vial", "none");
    public static final Item MERCURY_VIAL = new ItemBase("mercury_vial", "none");
    public static final Item TROLL_BLOOD_VIAL = new ItemBase("troll_blood_vial", "none");
    public static final Item PHOENIX_TEAR_VIAL = new ItemBase("phoenix_tear_vial", "projectile");
    public static final Item MANTICORE_VENOM_VIAL = new ItemBase("manticore_venom_vial", "projectile");
    public static final Item KRAKEN_INK_VIAL = new ItemBase("kraken_ink_vial", "projectile");


    //Essence - Minecraft Hostile
    public static final Item ZOMBIE_ESSENCE = new ItemBase("zombie_essence", "none");
    public static final Item SKELETON_ESSENCE = new ItemBase("skeleton_essence", "none");
    public static final Item CREEPER_ESSENCE = new ItemBase("creeper_essence", "none");
    public static final Item ENDERMAN_ESSENCE = new ItemBase("enderman_essence", "none");
    public static final Item ENDERMITE_ESSENCE = new ItemBase("endermite_essence", "none");
    public static final Item BLAZE_ESSENCE = new ItemBase("blaze_essence", "none");
    public static final Item SPIDER_ESSENCE = new ItemBase("spider_essence", "none");
    public static final Item CAVE_SPIDER_ESSENCE = new ItemBase("cave_spider_essence", "none");
    public static final Item ZOMBIE_PIGMAN_ESSENCE = new ItemBase("zombie_pigman_essence", "none");
    public static final Item ELDER_GUARDIAN_ESSENCE = new ItemBase("elder_guardian_essence", "none");
    public static final Item GUARDIAN_ESSENCE = new ItemBase("guardian_essence", "none");
    public static final Item EVOKER_ESSENCE = new ItemBase("evoker_essence", "none");
    public static final Item GHAST_ESSENCE = new ItemBase("ghast_essence", "none");
    public static final Item MAGMA_CUBE_ESSENCE = new ItemBase("magma_cube_essence", "none");
    public static final Item SHULKER_ESSENCE = new ItemBase("shulker_essence", "none");
    public static final Item SILVERFISH_ESSENCE = new ItemBase("silverfish_essence", "none");
    public static final Item SLIME_ESSENCE = new ItemBase("slime_essence", "none");
    public static final Item STRAY_ESSENCE = new ItemBase("stray_essence", "none");
    public static final Item WITCH_ESSENCE = new ItemBase("witch_essence", "none");
    public static final Item WITHER_SKELETON_ESSENCE = new ItemBase("wither_skeleton_essence", "none");
    public static final Item WITHER_ESSENCE = new ItemBase("wither_essence", "none");
    public static final Item ENDER_DRAGON_ESSENCE = new ItemBase("ender_dragon_essence", "none");
    //public static final Item DROWNED_ESSENCE = new ItemBase("drowned_essence", "none");
    //public static final Item HUSK_ESSENCE = new ItemBase("husk_essence", "none");


    //Essence - Minecraft Passive
    public static final Item COW_ESSENCE = new ItemBase("cow_essence", "none");
    public static final Item PIG_ESSENCE = new ItemBase("pig_essence", "none");
    public static final Item CHICKEN_ESSENCE = new ItemBase("chicken_essence", "none");
    public static final Item SHEEP_ESSENCE = new ItemBase("sheep_essence", "none");
    public static final Item RABBIT_ESSENCE = new ItemBase("rabbit_essence", "none");
    public static final Item BAT_ESSENCE = new ItemBase("bat_essence", "none");
    public static final Item HORSE_ESSENCE = new ItemBase("horse_essence", "none");
    public static final Item MOOSHROOM_ESSENCE = new ItemBase("mooshroom_essence", "none");
    public static final Item POLAR_BEAR_ESSENCE = new ItemBase("polar_bear_essence", "none");
    public static final Item SQUID_ESSENCE = new ItemBase("squid_essence", "none");
    public static final Item VILLAGER_ESSENCE = new ItemBase("villager_essence", "none");
    //public static final Item IRON_GOLEM_ESSENCE = new ItemBase("iron_golem_essence", "none");
    //public static final Item SNOW_GOLEM_ESSENCE = new ItemBase("snow_golem_essence", "none");

/*
    //Essence - Mod
    public static final Item WEASEL_ESSENCE = new ItemBase("weasel_essence", "none");
    public static final Item PHOENIX_ESSENCE = new ItemBase("phoenix_essence", "none");
    public static final Item GOLEM_ESSENCE = new ItemBase("golem_essence", "none");
    public static final Item WEREWOLF_ESSENCE = new ItemBase("werewolf_essence", "none");
    public static final Item VAMPIRE_ESSENCE = new ItemBase("vampire_essence", "none");
    public static final Item WENDIGO_ESSENCE = new ItemBase("wendigo_essence", "none");
    public static final Item ARACHNE_ESSENCE = new ItemBase("arachne_essence", "none");
    public static final Item BANSHEE_ESSENCE = new ItemBase("banshee_essence", "none");
    public static final Item BAKU_ESSENCE = new ItemBase("baku_essence", "none");
    public static final Item CHANGELING_ESSENCE = new ItemBase("changeling_essence", "none");
    public static final Item CHUPACABRA_ESSENCE = new ItemBase("chupacabra_essence", "none");
    public static final Item CROCOTTA_ESSENCE = new ItemBase("crocotta_essence", "none");
    public static final Item DJINN_ESSENCE = new ItemBase("djinn_essence", "none");
    public static final Item GHOUL_ESSENCE = new ItemBase("ghoul_essence", "none");
    public static final Item KITSUNE_ESSENCE = new ItemBase("kitsune_essence", "none");
    public static final Item KRAKEN_ESSENCE = new ItemBase("kraken_essence", "none");
    public static final Item LAMIA_ESSENCE = new ItemBase("lamia_essence", "none");
    public static final Item MARID_ESSENCE = new ItemBase("marid_essence", "none");
    public static final Item MUSCA_ESSENCE = new ItemBase("musca_essence", "none");
    public static final Item OKAMI_ESSENCE = new ItemBase("okami_essence", "none");
    public static final Item PISHTACO_ESSENCE = new ItemBase("pishtaco_essence", "none");
    public static final Item RAKSHASA_ESSENCE = new ItemBase("rakshasa_essence", "none");
    public static final Item RUGARU_ESSENCE = new ItemBase("rugaru_essence", "none");
    public static final Item SIREN_ESSENCE = new ItemBase("siren_essence", "none");
    public static final Item SKINWALKER_ESSENCE = new ItemBase("skinwalker_essence", "none");
    public static final Item VETALA_ESSENCE = new ItemBase("vetala_essence", "none");
    public static final Item WRAITH_ESSENCE = new ItemBase("wraith_essence", "none");
    public static final Item GORGON_ESSENCE = new ItemBase("gorgon_essence", "none");
    public static final Item NAGA_ESSENCE = new ItemBase("naga_essence", "none");
    public static final Item ASWANG_ESSENCE = new ItemBase("aswang_essence", "none");
    public static final Item BISAAN_ESSENCE = new ItemBase("bisaan_essence", "none");
    public static final Item CHIMERA_ESSENCE = new ItemBase("chimera_essence", "none");
    public static final Item GHOST_ESSENCE = new ItemBase("ghost_essence", "none");
    public static final Item HELLHOUND_ESSENCE = new ItemBase("hellhound_essence", "none");
    public static final Item HARPY_ESSENCE = new ItemBase("harpy_essence", "none");
    public static final Item KAPPA_ESSENCE = new ItemBase("kappa_essence", "none");
    public static final Item ONI_ESSENCE = new ItemBase("oni_essence", "none");
    public static final Item REDCAP_ESSENCE = new ItemBase("redcap_essence", "none");
    public static final Item REVENANT_ESSENCE = new ItemBase("revenant_essence", "none");
    public static final Item SUCCUBUS_ESSENCE = new ItemBase("succubus_essence", "none");
    public static final Item DEMON_ESSENCE = new ItemBase("demon_essence", "none");
    public static final Item DRYAD_ESSENCE = new ItemBase("dryad_essence", "none");
    public static final Item NYAD_ESSENCE = new ItemBase("nyad_essence", "none");
    public static final Item MANTICORE_ESSENCE = new ItemBase("manticore_essence", "none");
    public static final Item GOLDEN_RAM_ESSENCE = new ItemBase("golden_ram_essence", "none");
    public static final Item STYMPHALIAN_ESSENCE = new ItemBase("stymphalian_essence", "none");
    public static final Item BASILISK_ESSENCE = new ItemBase("basilisk_essence", "none");
    public static final Item COCKATRICE_ESSENCE = new ItemBase("cockatrice_essence", "none");
    public static final Item GRIFFIN_ESSENCE = new ItemBase("griffin_essence", "none");
    public static final Item YETI_ESSENCE = new ItemBase("yeti_essence", "none");
    public static final Item FLYING_HEAD_ESSENCE = new ItemBase("flying_head_essence", "none");
    public static final Item NEKOMATA_ESSENCE = new ItemBase("nekomata_essence", "none");
    public static final Item YARAMAYHAWHO_ESSENCE = new ItemBase("yaramayhawho_essence", "none");
    public static final Item PSOGLAV_ESSENCE = new ItemBase("psoglav_essence", "none");
    public static final Item TROLL_ESSENCE = new ItemBase("troll_essence", "none");
    public static final Item OGRE_ESSENCE = new ItemBase("ogre_essence", "none");
    public static final Item NUCKELAVEE_ESSENCE = new ItemBase("nuckelavee_essence", "none");

*/
    //Armor
    public static final Item HELMET_SILVER = new ArmorBase("helmet_silver", ARMOR_SILVER, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_SILVER = new ArmorBase("chestplate_silver", ARMOR_SILVER, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_SILVER = new ArmorBase("leggings_silver", ARMOR_SILVER, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_SILVER = new ArmorBase("boots_silver", ARMOR_SILVER, 1, EntityEquipmentSlot.FEET, "none");
    public static final Item HELMET_COPPER = new ArmorBase("helmet_copper", ARMOR_COPPER, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_COPPER = new ArmorBase("chestplate_copper", ARMOR_COPPER, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_COPPER = new ArmorBase("leggings_copper", ARMOR_COPPER, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_COPPER = new ArmorBase("boots_copper", ARMOR_COPPER, 1, EntityEquipmentSlot.FEET, "none");
    public static final Item HELMET_TIN = new ArmorBase("helmet_tin", ARMOR_TIN, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_TIN = new ArmorBase("chestplate_tin", ARMOR_TIN, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_TIN = new ArmorBase("leggings_tin", ARMOR_TIN, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_TIN = new ArmorBase("boots_tin", ARMOR_TIN, 1, EntityEquipmentSlot.FEET, "none");
    public static final Item HELMET_ZINC = new ArmorBase("helmet_zinc", ARMOR_ZINC, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_ZINC = new ArmorBase("chestplate_zinc", ARMOR_ZINC, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_ZINC = new ArmorBase("leggings_zinc", ARMOR_ZINC, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_ZINC = new ArmorBase("boots_zinc", ARMOR_ZINC, 1, EntityEquipmentSlot.FEET, "none");
    public static final Item HELMET_BRONZE = new ArmorBase("helmet_bronze", ARMOR_BRONZE, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_BRONZE = new ArmorBase("chestplate_bronze", ARMOR_BRONZE, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_BRONZE = new ArmorBase("leggings_bronze", ARMOR_BRONZE, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_BRONZE = new ArmorBase("boots_bronze", ARMOR_BRONZE, 1, EntityEquipmentSlot.FEET, "none");
    public static final Item HELMET_BRASS = new ArmorBase("helmet_brass", ARMOR_BRASS, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_BRASS = new ArmorBase("chestplate_brass", ARMOR_BRASS, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_BRASS = new ArmorBase("leggings_brass", ARMOR_BRASS, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_BRASS = new ArmorBase("boots_brass", ARMOR_BRASS, 1, EntityEquipmentSlot.FEET, "none");
    public static final Item HELMET_CIRON = new ArmorBase("helmet_ciron", ARMOR_CIRON, 1, EntityEquipmentSlot.HEAD, "shiny");
    public static final Item CHESTPLATE_CIRON = new ArmorBase("chestplate_ciron", ARMOR_CIRON, 1, EntityEquipmentSlot.CHEST, "shiny");
    public static final Item LEGGINGS_CIRON = new ArmorBase("leggings_ciron", ARMOR_CIRON, 2, EntityEquipmentSlot.LEGS, "shiny");
    public static final Item BOOTS_CIRON = new ArmorBase("boots_ciron", ARMOR_CIRON, 1, EntityEquipmentSlot.FEET, "shiny");
    public static final Item HELMET_LEAD = new ArmorBase("helmet_lead", ARMOR_LEAD, 1, EntityEquipmentSlot.HEAD, "none");
    public static final Item CHESTPLATE_LEAD = new ArmorBase("chestplate_lead", ARMOR_LEAD, 1, EntityEquipmentSlot.CHEST, "none");
    public static final Item LEGGINGS_LEAD = new ArmorBase("leggings_lead", ARMOR_LEAD, 2, EntityEquipmentSlot.LEGS, "none");
    public static final Item BOOTS_LEAD = new ArmorBase("boots_lead", ARMOR_LEAD, 1, EntityEquipmentSlot.FEET, "none");

    //Tools
    public static final Item CIRON_AXE = new ToolAxe("ciron_axe", TOOL_CIRON, "shiny");
    public static final Item CIRON_HOE = new ToolHoe("ciron_hoe", TOOL_CIRON, "shiny");
    public static final Item CIRON_PICKAXE = new ToolPickaxe("ciron_pickaxe", TOOL_CIRON, "shiny");
    public static final Item CIRON_SHOVEL = new ToolShovel("ciron_shovel", TOOL_CIRON, "shiny");
    public static final Item CIRON_SWORD = new ToolSword("ciron_sword", TOOL_CIRON, "shiny");
    public static final Item BRASS_AXE = new ToolAxe("brass_axe", TOOL_BRASS, "none");
    public static final Item BRASS_HOE = new ToolHoe("brass_hoe", TOOL_BRASS, "none");
    public static final Item BRASS_PICKAXE = new ToolPickaxe("brass_pickaxe", TOOL_BRASS, "none");
    public static final Item BRASS_SHOVEL = new ToolShovel("brass_shovel", TOOL_BRASS, "none");
    public static final Item BRASS_SWORD = new ToolSword("brass_sword", TOOL_BRASS, "none");
    public static final Item BRONZE_AXE = new ToolAxe("bronze_axe", TOOL_BRONZE, "none");
    public static final Item BRONZE_HOE = new ToolHoe("bronze_hoe", TOOL_BRONZE, "none");
    public static final Item BRONZE_PICKAXE = new ToolPickaxe("bronze_pickaxe", TOOL_BRONZE, "none");
    public static final Item BRONZE_SHOVEL = new ToolShovel("bronze_shovel", TOOL_BRONZE, "none");
    public static final Item BRONZE_SWORD = new ToolSword("bronze_sword", TOOL_BRONZE, "none");
    public static final Item LEAD_AXE = new ToolAxe("lead_axe", TOOL_LEAD, "none");
    public static final Item LEAD_HOE = new ToolHoe("lead_hoe", TOOL_LEAD, "none");
    public static final Item LEAD_PICKAXE = new ToolPickaxe("lead_pickaxe", TOOL_LEAD, "none");
    public static final Item LEAD_SHOVEL = new ToolShovel("lead_shovel", TOOL_LEAD, "none");
    public static final Item LEAD_SWORD = new ToolSword("lead_sword", TOOL_LEAD, "none");
    public static final Item COPPER_AXE = new ToolAxe("copper_axe", TOOL_COPPER, "none");
    public static final Item COPPER_HOE = new ToolHoe("copper_hoe", TOOL_COPPER, "none");
    public static final Item COPPER_PICKAXE = new ToolPickaxe("copper_pickaxe", TOOL_COPPER, "none");
    public static final Item COPPER_SHOVEL = new ToolShovel("copper_shovel", TOOL_COPPER, "none");
    public static final Item COPPER_SWORD = new ToolSword("copper_sword", TOOL_COPPER, "none");
    public static final Item SILVER_AXE = new ToolAxe("silver_axe", TOOL_SILVER, "none");
    public static final Item SILVER_HOE = new ToolHoe("silver_hoe", TOOL_SILVER, "none");
    public static final Item SILVER_PICKAXE = new ToolPickaxe("silver_pickaxe", TOOL_SILVER, "none");
    public static final Item SILVER_SHOVEL = new ToolShovel("silver_shovel", TOOL_SILVER, "none");
    public static final Item SILVER_SWORD = new ToolSword("silver_sword", TOOL_SILVER, "none");
    public static final Item TIN_AXE = new ToolAxe("tin_axe", TOOL_TIN, "none");
    public static final Item TIN_HOE = new ToolHoe("tin_hoe", TOOL_TIN, "none");
    public static final Item TIN_PICKAXE = new ToolPickaxe("tin_pickaxe", TOOL_TIN, "none");
    public static final Item TIN_SHOVEL = new ToolShovel("tin_shovel", TOOL_TIN, "none");
    public static final Item TIN_SWORD = new ToolSword("tin_sword", TOOL_TIN, "none");
    public static final Item ZINC_AXE = new ToolAxe("zinc_axe", TOOL_ZINC, "none");
    public static final Item ZINC_HOE = new ToolHoe("zinc_hoe", TOOL_ZINC, "none");
    public static final Item ZINC_PICKAXE = new ToolPickaxe("zinc_pickaxe", TOOL_ZINC, "none");
    public static final Item ZINC_SHOVEL = new ToolShovel("zinc_shovel", TOOL_ZINC, "none");
    public static final Item ZINC_SWORD = new ToolSword("zinc_sword", TOOL_ZINC, "none");





}
