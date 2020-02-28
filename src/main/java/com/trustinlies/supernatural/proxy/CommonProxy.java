package com.trustinlies.supernatural.proxy;

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
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.Mining;
import com.trustinlies.supernatural.util.capabilities.CapabilityHandler;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.IMining;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.MiningStorage;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id, String keyword)
    {

    }


    public void init(){

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

}
