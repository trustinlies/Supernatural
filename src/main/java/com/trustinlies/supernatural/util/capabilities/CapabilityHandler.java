package com.trustinlies.supernatural.util.capabilities;

import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.Archer;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.ArcherProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.KnightProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.MageProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.ThiefProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.FarmerProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.FisherProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.LumberjackProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.MiningProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

    public static final ResourceLocation MINING_SKILL = new ResourceLocation(Reference.MOD_ID, "miningSkill");
    public static final ResourceLocation LUMBERJACK_SKILL = new ResourceLocation(Reference.MOD_ID, "lumberjackSkill");
    public static final ResourceLocation FARMER_SKILL = new ResourceLocation(Reference.MOD_ID, "farmerSkill");
    public static final ResourceLocation FISHER_SKILL = new ResourceLocation(Reference.MOD_ID, "fisherSkill");
    public static final ResourceLocation KNIGHT_SKILL = new ResourceLocation(Reference.MOD_ID, "knightSkill");
    public static final ResourceLocation ARCHER_SKILL = new ResourceLocation(Reference.MOD_ID, "archerSkill");
    public static final ResourceLocation MAGE_SKILL = new ResourceLocation(Reference.MOD_ID, "mageSkill");
    public static final ResourceLocation THIEF_SKILL = new ResourceLocation(Reference.MOD_ID, "thiefSkill");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {

            //Attach Gathering Skills
            event.addCapability(MINING_SKILL, new MiningProvider());
            event.addCapability(LUMBERJACK_SKILL, new LumberjackProvider());
            event.addCapability(FARMER_SKILL, new FarmerProvider());
            event.addCapability(FISHER_SKILL, new FisherProvider());
            event.addCapability(KNIGHT_SKILL, new KnightProvider());
            event.addCapability(ARCHER_SKILL, new ArcherProvider());
            event.addCapability(MAGE_SKILL, new MageProvider());
            event.addCapability(THIEF_SKILL, new ThiefProvider());


        }
    }

}
