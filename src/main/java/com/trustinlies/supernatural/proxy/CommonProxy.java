package com.trustinlies.supernatural.proxy;

import com.trustinlies.supernatural.util.capabilities.Mining;
import com.trustinlies.supernatural.util.capabilities.CapabilityHandler;
import com.trustinlies.supernatural.util.capabilities.IMining;
import com.trustinlies.supernatural.util.capabilities.MiningStorage;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id, String keyword)
    {

    }

    public void init(){
        CapabilityManager.INSTANCE.register(IMining.class, new MiningStorage(), Mining::new);

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

}
