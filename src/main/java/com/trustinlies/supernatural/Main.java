package com.trustinlies.supernatural;

import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.proxy.CommonProxy;
import com.trustinlies.supernatural.recipes.SmeltingRecipes;
import com.trustinlies.supernatural.recipes.WellRecipes;
import com.trustinlies.supernatural.tabs.CreativeTabsSupernatural;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.EssenceWellTileEntity;
import com.trustinlies.supernatural.world.gen.Ores;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
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

    public static CreativeTabs ITEMTAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalItemsTab", 1);
    public static CreativeTabs BLOCKTAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalBlocksTab", 2);
    public static CreativeTabs ESSENCETAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalEssencesTab", 3);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        logger = event.getModLog();
        System.out.println(("PreInit"));
        GameRegistry.registerWorldGenerator(new Ores(), 4);

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Basalt >> {}", BlockInit.BASALT.getRegistryName());
        System.out.println(("Init"));
        SmeltingRecipes.init();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

        System.out.println(("PostInit"));

    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event){

    }

}
