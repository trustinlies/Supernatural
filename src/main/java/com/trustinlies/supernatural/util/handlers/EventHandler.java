package com.trustinlies.supernatural.util.handlers;

import com.trustinlies.supernatural.config.SkillsConfig;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.SpecialFunctions;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.ArcherProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.IArcher;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.IKnight;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.KnightProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.IMage;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.MageProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.IThief;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.ThiefProvider;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.CarpenterProvider;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.ICarpenter;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.FarmerProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.IFarmer;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.FisherProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.IFisher;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.LumberjackProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.IMining;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.MiningProvider;
import com.trustinlies.supernatural.util.lists.Lists;
import com.trustinlies.supernatural.util.objects.guis.SkillsGUI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber
public class EventHandler {

    private static List<BlockPos> placedBlocks = new ArrayList<>();
    private static List<BlockPos> growBlocks = new ArrayList<>();

    @SubscribeEvent
    public static void onBreakEvent(BlockEvent.BreakEvent event){

        //Event Variables
        EntityPlayer player = event.getPlayer();
        World world = event.getWorld();
        BlockPos position = event.getPos();
        Block block = world.getBlockState(position).getBlock();
        Float hardness = block.getDefaultState().getBlockHardness(world,position);

        //Skill Variables (that contribute to block break)
        IMining mining = player.getCapability(MiningProvider.MINING_LEVEL, null);
        ILumberjack lumberjack = player.getCapability(LumberjackProvider.LUMBERJACK_LEVEL, null);
        IFarmer farmer = player.getCapability(FarmerProvider.FARMER_LEVEL, null);

        //Get current levels for comparison
        int currentMining = mining.getLevel();
        int currentLumberjack = lumberjack.getLevel();
        int currentFarmer = farmer.getLevel();



        if(!placedBlocks.contains(position)) {
            if (block.getDefaultState().getMaterial() == Material.ROCK) mining.add(Math.round(hardness));

            if (block == Blocks.SAPLING) growBlocks.remove(position);

            if (block == Blocks.LOG || block == Blocks.LOG2) {
                    lumberjack.add(2);
                    growBlocks.remove(position);
            }
        }

        else{
            if (block == Blocks.LOG || block == Blocks.LOG2) {

                if (placedBlocks.contains(position) && growBlocks.contains(position)) {
                    lumberjack.add(5);
                    growBlocks.remove(position);
                }
            }
        }

        placedBlocks.remove(position);
        growBlocks.remove(position);

        if(currentMining < mining.getLevel()){
            String m2 = String.format("%d.", mining.getLevel());

            player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Miner level has increased to " + TextFormatting.AQUA + m2));
        }

        if(currentLumberjack < lumberjack.getLevel()){
            String m2 = String.format("%d.", lumberjack.getLevel());

            player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Lumberjack level has increased to " + TextFormatting.AQUA + m2));

        }


    }

    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.PlaceEvent event){
        BlockPos position = event.getPos();
        Block block = event.getPlacedBlock().getBlock();

        if(block == Blocks.SAPLING){
            growBlocks.add(position);
        }
        else{
            placedBlocks.add(position);
        }
    }

    /*
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;
    }

     */

    @SubscribeEvent
    public void onItemFished(ItemFishedEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = event.getEntityPlayer();
            IFisher fisher = player.getCapability(FisherProvider.FISHER_LEVEL, null);
            int currentFisher = fisher.getLevel();

            if(currentFisher >= 10){
                player.addItemStackToInventory(new ItemStack(Items.FISH));
            }

            //update after testing + add check for different levels of Fishing Charms
            fisher.add(500);

            if (currentFisher < fisher.getLevel()) {
                String m2 = String.format("%d.", fisher.getLevel());

                player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Fisher level has increased to " + TextFormatting.AQUA + m2));

            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onItemRightClick(PlayerInteractEvent.RightClickItem event){
        if(!event.getWorld().isRemote) {
            Item item = event.getItemStack().getItem();
            EntityPlayer player = event.getEntityPlayer();

            if (item == ItemInit.HUNTERS_JOURNAL) {
                Minecraft.getMinecraft().displayGuiScreen(new SkillsGUI(player));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event){
        EntityPlayer player = event.getEntityPlayer();
        if(!SkillsConfig.resetLevelsOnDeath || !event.isWasDeath()) {

            IMining mining = player.getCapability(MiningProvider.MINING_LEVEL, null);
            IMining oldmining = event.getOriginal().getCapability(MiningProvider.MINING_LEVEL, null);

            ILumberjack lumberjack = player.getCapability(LumberjackProvider.LUMBERJACK_LEVEL, null);
            ILumberjack oldlumberjack = event.getOriginal().getCapability(LumberjackProvider.LUMBERJACK_LEVEL, null);

            IFisher fisher = player.getCapability(FisherProvider.FISHER_LEVEL, null);
            IFisher oldfisher = event.getOriginal().getCapability(FisherProvider.FISHER_LEVEL, null);

            IFarmer farmer = player.getCapability(FarmerProvider.FARMER_LEVEL, null);
            IFarmer oldfarmer = event.getOriginal().getCapability(FarmerProvider.FARMER_LEVEL, null);

            IKnight knight = player.getCapability(KnightProvider.KNIGHT_LEVEL, null);
            IKnight oldknight = event.getOriginal().getCapability(KnightProvider.KNIGHT_LEVEL, null);

            IArcher archer = player.getCapability(ArcherProvider.ARCHER_LEVEL, null);
            IArcher oldarcher = event.getOriginal().getCapability(ArcherProvider.ARCHER_LEVEL, null);

            IMage mage = player.getCapability(MageProvider.MAGE_LEVEL, null);
            IMage oldmage = event.getOriginal().getCapability(MageProvider.MAGE_LEVEL, null);

            IThief thief = player.getCapability(ThiefProvider.THIEF_LEVEL, null);
            IThief oldthief = event.getOriginal().getCapability(ThiefProvider.THIEF_LEVEL, null);

            ICarpenter carpenter = player.getCapability(CarpenterProvider.CARPENTER_LEVEL, null);
            ICarpenter oldcarpenter = event.getOriginal().getCapability(CarpenterProvider.CARPENTER_LEVEL, null);

            mining.set(oldmining.getExp());
            lumberjack.set(oldlumberjack.getExp());
            fisher.set(oldfisher.getExp());
            farmer.set(oldfarmer.getExp());
            knight.set(oldknight.getExp());
            archer.set(oldarcher.getExp());
            thief.set(oldthief.getExp());
            mage.set(oldmage.getExp());
            carpenter.set(oldcarpenter.getExp());

        }
        else{
            player.sendMessage(new TextComponentString(TextFormatting.RED + "Profession Levels have been reset due to config settings."));
        }
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event){
        DamageSource source = event.getSource();
        EntityLivingBase victim = event.getEntityLiving();
        //Make multiplier a config option
        int health = (int) (victim.getMaxHealth()*1.5);

        if(source.getTrueSource() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) source.getTrueSource();
            ItemStack itemStack = player.getHeldItemMainhand();
            String inHand = itemStack.getUnlocalizedName().toLowerCase();

            if(inHand.contains("sword")|| inHand.contains("axe")||inHand.contains("katana") || inHand.contains("hatchet")|| Lists.KNIGHT_WEAPONS.contains(itemStack)){
                IKnight knight = player.getCapability(KnightProvider.KNIGHT_LEVEL, null);
                int currentKnight = knight.getLevel();
                knight.add(health);
                if(currentKnight<knight.getLevel()){
                    String m2 = String.format("%d.", knight.getLevel());
                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Knight level has increased to " + TextFormatting.AQUA + m2));
                }
            }
            else if(source.getDamageType().equals("arrow") && !inHand.contains("wand")){
                IArcher archer = player.getCapability(ArcherProvider.ARCHER_LEVEL, null);
                int currentArcher = archer.getLevel();
                int archerXP = health*2;
                archer.add(archerXP);
                if(currentArcher<archer.getLevel()){
                    String m2 = String.format("%d.", archer.getLevel());
                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Archer level has increased to " + TextFormatting.AQUA + m2));
                }

            }
            else if(source.getDamageType().equals("indirectMagic")||source.getDamageType().equals("magic") || (source.getDamageType().equals("arrow") && inHand.contains("wand"))){
                IMage mage = player.getCapability(MageProvider.MAGE_LEVEL, null);
                int currentMage = mage.getLevel();
                int mageXP = health*3;
                mage.add(mageXP);
                if(currentMage<mage.getLevel()){
                    String m2 = String.format("%d.", mage.getLevel());
                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Mage level has increased to " + TextFormatting.AQUA + m2));
                }

            }
            else if(inHand.contains("dagger")){
                IThief thief = player.getCapability(ThiefProvider.THIEF_LEVEL, null);
                int currentthief = thief.getLevel();
                thief.add(health);
                if(currentthief<thief.getLevel()){
                    String m2 = String.format("%d.", thief.getLevel());
                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Thief level has increased to " + TextFormatting.AQUA + m2));
                }

            }

        }

    }

    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event) throws InterruptedException {
        ItemStack stack = event.crafting;
        ItemStack result;
        EntityPlayer player = event.player;

        if(Lists.CARPENTER_ITEMS.contains(event.crafting.getItem())) {
            ICarpenter carpenter = player.getCapability(CarpenterProvider.CARPENTER_LEVEL, null);
            int currentcarpenter = carpenter.getLevel();
            result = SpecialFunctions.carpenterItem(stack, player);
            player.inventory.setItemStack(result);

            if(currentcarpenter< carpenter.getLevel()){
                String m2 = String.format("%d.", carpenter.getLevel());
                player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Carpenter level has increased to " + TextFormatting.AQUA + m2));
            }
        }
    }

}
