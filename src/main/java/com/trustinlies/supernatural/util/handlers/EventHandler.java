package com.trustinlies.supernatural.util.handlers;

import com.trustinlies.supernatural.config.SkillsConfig;
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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

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
                    //player.sendMessage(new TextComponentString("Gained 2 Lumberjack Experience from tree"));
                    growBlocks.remove(position);
            }
        }

        else{
            if (block == Blocks.LOG || block == Blocks.LOG2) {

                if (placedBlocks.contains(position) && growBlocks.contains(position)) {
                    lumberjack.add(2);
                    //player.sendMessage(new TextComponentString("Gained 2 Lumberjack Experience from grown tree"));
                    growBlocks.remove(position);
                } /*else {
                    player.sendMessage(new TextComponentString(TextFormatting.RED + "This Block was placed by a player"));
                }*/
            }
            //else player.sendMessage(new TextComponentString(TextFormatting.RED + "This Block was placed by a player"));
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

        //String m3 = String.format("%d.", farmer.getLevel());
        //player.sendMessage(new TextComponentString(TextFormatting.GREEN + player.getDisplayNameString() + TextFormatting.RESET + " your Farmer level is " + TextFormatting.AQUA + m3));

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

}
