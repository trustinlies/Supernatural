package com.trustinlies.supernatural.util.handlers;

import com.trustinlies.supernatural.util.capabilities.farmer.FarmerProvider;
import com.trustinlies.supernatural.util.capabilities.farmer.IFarmer;
import com.trustinlies.supernatural.util.capabilities.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.lumberjack.LumberjackProvider;
import com.trustinlies.supernatural.util.capabilities.miner.IMining;
import com.trustinlies.supernatural.util.capabilities.miner.MiningProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
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
            System.out.println("Sapling placed " + block.getDefaultState().getBlock());
            growBlocks.add(position);
        }
        else{
            System.out.println("Block placed " + block.getDefaultState().getBlock());
            placedBlocks.add(position);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        //System.out.println(Colors.YELLOW + event.player.getDisplayNameString() + " has joined the game" + Colors.RESET);
        EntityPlayer player = event.player;
        IMining skills = player.getCapability(MiningProvider.MINING_LEVEL, null);

        //String message = String.format("Hello there, your mining level is %d.", skills.getExp());
        //player.sendMessage(new TextComponentString(message));
    }

    @SubscribeEvent
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {
        EntityPlayer player = event.getEntityPlayer();
        IMining mining = player.getCapability(MiningProvider.MINING_LEVEL, null);
        IMining oldmining = event.getOriginal().getCapability(MiningProvider.MINING_LEVEL, null);

        mining.set(oldmining.getExp());
    }

}
