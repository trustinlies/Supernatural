package com.trustinlies.supernatural.proxy;

import com.trustinlies.supernatural.Main;
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
import com.trustinlies.supernatural.util.handlers.GuiHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.StringTokenizer;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id, String keyword)
    {
        String name = item.getRegistryName().toString();
        StringTokenizer tokens = new StringTokenizer(name, ":/");
        ModelResourceLocation location = new ModelResourceLocation(tokens.nextToken() + ":" + keyword + "/" + tokens.nextToken());
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, id));
    }





}
