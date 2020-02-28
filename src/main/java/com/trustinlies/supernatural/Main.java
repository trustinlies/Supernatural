package com.trustinlies.supernatural;

import com.trustinlies.supernatural.config.SkillsConfig;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.proxy.ClientProxy;
import com.trustinlies.supernatural.proxy.CommonProxy;
import com.trustinlies.supernatural.recipes.SmeltingRecipes;
import com.trustinlies.supernatural.tabs.CreativeTabsSupernatural;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.capabilities.CapabilityHandler;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.Archer;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.ArcherStorage;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.IArcher;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.IKnight;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.Knight;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.KnightStorage;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.IMage;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.Mage;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.MageStorage;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.IThief;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.Thief;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.ThiefStorage;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.Carpenter;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.CarpenterStorage;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.ICarpenter;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.Farmer;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.FarmerStorage;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.IFarmer;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.Fisher;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.FisherStorage;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.IFisher;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.Lumberjack;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.LumberjackStorage;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.IMining;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.Mining;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.MiningStorage;
import com.trustinlies.supernatural.util.commands.CommandProfessions;
import com.trustinlies.supernatural.util.lists.Lists;
import com.trustinlies.supernatural.util.handlers.EventHandler;
import com.trustinlies.supernatural.util.handlers.RegistryHandler;
import com.trustinlies.supernatural.world.gen.Ores;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class Main {

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    private static Logger logger;

    private static Lists itemLists = new Lists();

    public static CreativeTabs BLOCKTAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalBlocksTab", 1);
    public static CreativeTabs ITEMTAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalItemsTab", 2);
    public static CreativeTabs ESSENCETAB = new CreativeTabsSupernatural(CreativeTabs.getNextID(), "SupernaturalEssencesTab", 3);

    public static SimpleNetworkWrapper NETWORK;

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

        /*
        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        NETWORK.registerMessage(PlayerMessageHandler.class, PlayerCapabilitiesMessage.class, 1, Side.CLIENT);

         */


    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Basalt >> {}", BlockInit.BASALT.getRegistryName());
        System.out.println(Colors.CYAN + "Skills Reset: " + SkillsConfig.resetLevelsOnDeath + Colors.RESET);
        System.out.println(Colors.CYAN + "Superior Chance: " + SkillsConfig.superiorQualityChance + Colors.RESET);
        System.out.println(("Init"));
        SmeltingRecipes.init();
        System.out.println("Smelting Registries Initiated");
        RegistryHandler.initRegistries();
        System.out.println("Init Registry Complete");
        itemLists.setLists();
        //proxy.init();

        //Register Gathering Skills
        CapabilityManager.INSTANCE.register(IMining.class, new MiningStorage(), Mining::new);
        CapabilityManager.INSTANCE.register(ILumberjack.class, new LumberjackStorage(), Lumberjack::new);
        CapabilityManager.INSTANCE.register(IFarmer.class, new FarmerStorage(), Farmer::new);
        CapabilityManager.INSTANCE.register(IFisher.class, new FisherStorage(), Fisher::new);
        CapabilityManager.INSTANCE.register(IKnight.class, new KnightStorage(), Knight::new);
        CapabilityManager.INSTANCE.register(IArcher.class, new ArcherStorage(), Archer::new);
        CapabilityManager.INSTANCE.register(IMage.class, new MageStorage(), Mage::new);
        CapabilityManager.INSTANCE.register(IThief.class, new ThiefStorage(), Thief::new);
        CapabilityManager.INSTANCE.register(ICarpenter.class, new CarpenterStorage(), Carpenter::new);

        //Register Capability Handler
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

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
