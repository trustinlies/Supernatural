package com.trustinlies.supernatural.util.capabilities;

import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.capabilities.lumberjack.LumberjackProvider;
import com.trustinlies.supernatural.util.capabilities.miner.MiningProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

    public static final ResourceLocation MINING_SKILL = new ResourceLocation(Reference.MOD_ID, "miningSkill");
    public static final ResourceLocation LUMBERJACK_SKILL = new ResourceLocation(Reference.MOD_ID, "lumberjackSkill");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {

            event.addCapability(MINING_SKILL, new MiningProvider());
            event.addCapability(LUMBERJACK_SKILL, new LumberjackProvider());
        }
    }

}
