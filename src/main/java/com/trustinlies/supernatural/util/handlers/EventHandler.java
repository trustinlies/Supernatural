package com.trustinlies.supernatural.util.handlers;

import com.trustinlies.supernatural.Colors;
import com.trustinlies.supernatural.util.capabilities.IMining;
import com.trustinlies.supernatural.util.capabilities.MiningProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void onBreakEvent(BlockEvent.BreakEvent event){

        EntityPlayer player = event.getPlayer();
        IMining skills = player.getCapability(MiningProvider.MINING_LEVEL, null);
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        World world = event.getWorld();
        BlockPos position = event.getPos();
        Float hardness = block.getDefaultState().getBlockHardness(world,position);
        int current = skills.getLevel();

        if(block.getDefaultState().getMaterial() == Material.ROCK && block != Blocks.COBBLESTONE)
        //System.out.println(Colors.GREEN + event.getPlayer().getDisplayNameString() + " " + event.getPos() + " " + event.getWorld().getBlockState(event.getPos()).getBlock().getRegistryName() + Colors.RESET);
        skills.add(Math.round(hardness));

        String message = String.format("Hello there, your mining experience increased by %d and is now " + skills.getExp(), Math.round(hardness));
        player.sendMessage(new TextComponentString(message));

        if(current < skills.getLevel()){
            String m2 = String.format("Hello there, your mining level has increased to %d.", skills.getLevel());
            player.sendMessage(new TextComponentString(m2));
        }



    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        //System.out.println(Colors.YELLOW + event.player.getDisplayNameString() + " has joined the game" + Colors.RESET);
        EntityPlayer player = event.player;
        IMining skills = player.getCapability(MiningProvider.MINING_LEVEL, null);

        String message = String.format("Hello there, your mining level is %d.", skills.getLevel());
        player.sendMessage(new TextComponentString(message));
    }

}
