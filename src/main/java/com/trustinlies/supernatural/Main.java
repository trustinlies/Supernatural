package com.trustinlies.supernatural;

import com.trustinlies.supernatural.config.SkillsConfig;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.proxy.CommonProxy;
import com.trustinlies.supernatural.recipes.SmeltingRecipes;
import com.trustinlies.supernatural.tabs.CreativeTabsSupernatural;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.commands.CommandProfessions;
import com.trustinlies.supernatural.util.lists.Lists;
import com.trustinlies.supernatural.util.handlers.EventHandler;
import com.trustinlies.supernatural.util.handlers.RegistryHandler;
import com.trustinlies.supernatural.world.gen.Ores;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class Main {

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    private static Logger logger;

    private static Lists itemLists = new Lists();

    public static CreativeTabs BLOCKTAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalBlocksTab", 1);
    public static CreativeTabs ITEMTAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalItemsTab", 2);
    public static CreativeTabs ESSENCETAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalEssencesTab", 3);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();
        System.out.println(("PreInit"));
        GameRegistry.registerWorldGenerator(new Ores(), 4);
        System.out.println("World Generation Registered");
        RegistryHandler.preInitRegistries();
        System.out.println("Pre Init Registry Complete");
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        System.out.println("Block Break Handler Registered");
        SkillsConfig.init(event.getSuggestedConfigurationFile());
        System.out.println("Skills Config registered");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Basalt >> {}", BlockInit.BASALT.getRegistryName());
        System.out.println(Colors.CYAN + "Skills Reset: " + SkillsConfig.resetLevelsOnDeath + Colors.RESET);
        System.out.println(("Init"));
        SmeltingRecipes.init();
        System.out.println("Smelting Registries Initiated");
        RegistryHandler.initRegistries();
        System.out.println("Init Registry Complete");
        itemLists.setKnightWeapons();
        proxy.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

        System.out.println(("PostInit"));
        RegistryHandler.postInitRegistries();
        System.out.println("Post Init Registry Complete");

    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event){

        event.registerServerCommand(new CommandProfessions());

    }
    //Testing Commit 2


}
