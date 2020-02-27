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
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.minecraft.client.util.ITooltipFlag.*;

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
        EntityPlayer player = event.getPlayer();
        BlockPos position = event.getPos();
        Block block = event.getPlacedBlock().getBlock();

        if(block == Blocks.SAPLING){
            //System.out.println("Sapling placed " + block.getDefaultState().getBlock());
            growBlocks.add(position);
        }
        else{
            //System.out.println("Block placed " + block.getDefaultState().getBlock());
            placedBlocks.add(position);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        //System.out.println(Colors.YELLOW + event.player.getDisplayNameString() + " has joined the game" + Colors.RESET);
        EntityPlayer player = event.player;
        IArcher mining = player.getCapability(ArcherProvider.ARCHER_LEVEL, null);
        System.out.println(mining.getLevel());

    }

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
    public void onItemRightClick(PlayerInteractEvent.RightClickItem event){
        Item item = event.getItemStack().getItem();
        EntityPlayer player = event.getEntityPlayer();
        if(item == ItemInit.HUNTERS_JOURNAL){
            Minecraft.getMinecraft().displayGuiScreen(new SkillsGUI(player));
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

            mining.set(oldmining.getExp());
            lumberjack.set(oldlumberjack.getExp());
            fisher.set(oldfisher.getExp());
            farmer.set(oldfarmer.getExp());
            knight.set(oldknight.getExp());
            archer.set(oldarcher.getExp());
            thief.set(oldthief.getExp());
            mage.set(oldmage.getExp());

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

            //System.out.println("Immediate Source: "+ source.getImmediateSource());
            //System.out.println("True Source: " + source.getTrueSource());
            //System.out.println("Damage Type: " + source.getDamageType());

            if(inHand.contains("sword")|| inHand.contains("axe")||inHand.contains("katana") || inHand.contains("hatchet")|| Lists.KNIGHT_WEAPONS.contains(itemStack)){
                IKnight knight = player.getCapability(KnightProvider.KNIGHT_LEVEL, null);
                int currentKnight = knight.getLevel();
                knight.add(health);
                //String m1 = String.format("%d.", health);
                //player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Knight Profession Exp Gained: " + m1));
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
                //String m1 = String.format("%d.", archerXP);
                //player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Archer Profession Exp Gained: " + m1));
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
                //String m1 = String.format("%d.", mageXP);
                //player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Mage Profession Exp Gained: " + m1));
                if(currentMage<mage.getLevel()){
                    String m2 = String.format("%d.", mage.getLevel());
                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Mage level has increased to " + TextFormatting.AQUA + m2));
                }

            }
            else if(inHand.contains("dagger")){
                IThief thief = player.getCapability(ThiefProvider.THIEF_LEVEL, null);
                int currentthief = thief.getLevel();
                thief.add(health);
                //String m1 = String.format("%d.", health);
                //player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Thief Profession Exp Gained: " + m1));
                if(currentthief<thief.getLevel()){
                    String m2 = String.format("%d.", thief.getLevel());
                    player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Thief level has increased to " + TextFormatting.AQUA + m2));
                }

            }

        }

    }

    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event){
        //event.getStack().set;
        ItemStack stack = event.crafting;
        ItemStack slot0 = event.craftMatrix.getStackInSlot(0);
        ItemStack slot1 = event.craftMatrix.getStackInSlot(1);
        ItemStack slot2 = event.craftMatrix.getStackInSlot(2);
        ItemStack slot3 = event.craftMatrix.getStackInSlot(3);
        ItemStack slot4 = event.craftMatrix.getStackInSlot(4);
        ItemStack slot5 = event.craftMatrix.getStackInSlot(5);
        ItemStack slot6 = event.craftMatrix.getStackInSlot(6);
        ItemStack slot7 = event.craftMatrix.getStackInSlot(7);
        ItemStack slot8 = event.craftMatrix.getStackInSlot(8);
        ItemStack result = event.crafting;

        //System.out.println(slot0.getDisplayName() + slot1.getDisplayName() + slot2.getDisplayName() + slot3.getDisplayName() + slot4.getDisplayName() + slot5.getDisplayName() + slot6.getDisplayName() + slot7.getDisplayName() + slot8.getDisplayName() + slot9.getDisplayName());


        if(Lists.CARPENTER_ITEMS.contains(event.crafting.getItem())) {
            result = SpecialFunctions.carpenterItem(slot0, slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, stack, event.player);
            event.player.inventory.setItemStack(result);


            NBTTagCompound test = stack.getTagCompound();


/*
            if (event.craftMatrix.getStackInSlot(7).getItem() == Items.STICK) {
                if (event.craftMatrix.getStackInSlot(7).getTagCompound() != null) {
                    NBTTagCompound dcheck = (NBTTagCompound) event.craftMatrix.getStackInSlot(7).getTagCompound().getTag("display");
                    if (dcheck.getTag("Lore").toString().contains("Crafted")) {
                        ItemStack result = event.crafting;
                        result.setTagCompound(nbt);
                        event.player.inventory.setItemStack(result);

                    }
                } else {
                    event.player.inventory.setItemStack(new ItemStack(Items.DIAMOND));
                }

            } else {
                event.player.inventory.setItemStack(new ItemStack(Items.DIAMOND));
            }


            if (event.crafting.getItem() == Items.STICK) {
                event.crafting.setTagCompound(nbt);
            }
        }

        if(event.crafting.getTagCompound().getString("Lore") == null){
            System.out.println("SUCCESS");
            NBTTagCompound dcheck = (NBTTagCompound) event.crafting.getTagCompound().getTag("display");
            System.out.println(dcheck.getTag("Lore"));
            System.out.println(dcheck.getTag("Lore").toString());
            if(dcheck.getTag("Lore").toString().toLowerCase().contains("crafted")){
                event.player.inventory.setItemStack(new ItemStack(Items.DIAMOND)); //Replaces Item in Hand
            }
        }*/
            //event.player.addItemStackToInventory(stack);

        /*
        ItemStack itemStack = SpecialFunctions.customItem(event.getStack(), event.player);
        itemStack.setTagInfo("Lore", );
        event.player.addItemStackToInventory();
        */

        }
    }

}
