package com.trustinlies.supernatural.proxy;

import com.trustinlies.supernatural.util.capabilities.farmer.Farmer;
import com.trustinlies.supernatural.util.capabilities.farmer.FarmerStorage;
import com.trustinlies.supernatural.util.capabilities.farmer.IFarmer;
import com.trustinlies.supernatural.util.capabilities.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.lumberjack.Lumberjack;
import com.trustinlies.supernatural.util.capabilities.lumberjack.LumberjackStorage;
import com.trustinlies.supernatural.util.capabilities.miner.Mining;
import com.trustinlies.supernatural.util.capabilities.CapabilityHandler;
import com.trustinlies.supernatural.util.capabilities.miner.IMining;
import com.trustinlies.supernatural.util.capabilities.miner.MiningStorage;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id, String keyword)
    {

    }

    public void init(){
        CapabilityManager.INSTANCE.register(IMining.class, new MiningStorage(), Mining::new);
        CapabilityManager.INSTANCE.register(ILumberjack.class, new LumberjackStorage(), Lumberjack::new);
        CapabilityManager.INSTANCE.register(IFarmer.class, new FarmerStorage(), Farmer::new);

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

}
