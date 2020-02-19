package com.trustinlies.supernatural.util.objects.blocks;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class SpecialBlockBase extends Block implements IHasModel {

    public SpecialBlockBase (String name, Material material, float hardness, float resistance, float lightlevel, int opacity, String toolClass, int level, SoundType sound){

        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.BLOCKTAB);
        setHardness(hardness);
        setResistance(resistance);
        setLightLevel(lightlevel);
        setLightOpacity(opacity);
        setHarvestLevel(toolClass, level);
        setSoundType(sound);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

    }

    @Override
    public boolean isFullBlock(IBlockState state){
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return true;
    }

    private boolean canBlockStay(World world, BlockPos pos){

        return world.getBlockState(pos.down()).isSideSolid(world, pos, EnumFacing.UP);

    }

    @Override
    public void observedNeighborChange(IBlockState observerState, World world, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos) {

        if(!this.canBlockStay(world, observerPos)){

            world.setBlockToAir(observerPos);
            InventoryHelper.spawnItemStack(world, observerPos.getX(), observerPos.getY(), observerPos.getZ(), new ItemStack(this));

        }

    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    /*@Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }
    //normal blocks dont need this, only translucent blocks


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL
    }
    //this is only for non regular blocks

    */

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }

    @Override
    public void registerModels(){

        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory", "ore");

    }

}
