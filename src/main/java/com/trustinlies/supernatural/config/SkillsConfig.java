package com.trustinlies.supernatural.config;

import com.google.common.collect.Maps;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class SkillsConfig {

    public static boolean resetLevelsOnDeath = false;
    public static int poorQualityChance = 10;
    public static int superiorQualityChance = 3;

    private static Configuration cfg;

    private static Map<String, Property> PROP_SYNC = Maps.newHashMap();

    private static Path configDir;

    public static void init(File file){
        configDir = file.getParentFile().toPath().resolve("supernatural");
        cfg = new Configuration(file);

        PROP_SYNC.put("levelreset", cfg.get(Configuration.CATEGORY_GENERAL, "Reset Levels on Death", resetLevelsOnDeath, "Does the player lose levels when they die?"));
        poorQualityChance = cfg.getInt("Poor Quality Crafting Chance", "crafting", poorQualityChance, 0, 100, "Chance of getting worse than average crafting results. Default: 10");
        superiorQualityChance = cfg.getInt("Superior Quality Crafting Chance", "crafting", superiorQualityChance, 0, 100, "Chance of getting better than average crafting results. Default: 3");
        if (cfg.hasChanged()) cfg.save();
        useServerProperties();
    }

    public static NBTTagCompound getServerProperties() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("levelreset", PROP_SYNC.get("levelreset").getBoolean());
        return tag;
    }

    public static void useServerProperties() {
        resetLevelsOnDeath = PROP_SYNC.get("levelreset").getBoolean();
    }

    public static void useServerProperties(NBTTagCompound tag) {
        resetLevelsOnDeath = tag.getBoolean("levelreset");
        //SkillRegistry.resetForNewProps();
    }




}
