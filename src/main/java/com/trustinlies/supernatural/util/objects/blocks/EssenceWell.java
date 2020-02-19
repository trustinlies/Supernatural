package com.trustinlies.supernatural.util.objects.blocks;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.ContainerEssenceWell;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.EssenceWellTileEntity;
import com.trustinlies.supernatural.util.objects.gui.GuiEssenceWell;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


import java.util.Random;

public class EssenceWell extends BlockContainer implements IHasModel, ITileEntityProvider {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    //public static final PropertyBool Burning = PropertyBool.create("burning");

    public EssenceWell(String name, Material material){
        super(material);
        setSoundType(SoundType.STONE);
        setResistance(50.0F);
        setHardness(3.5F);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.BLOCKTAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

        BlockInit.BLOCKS.add(this);

        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));


    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(BlockInit.ESSENCE_WELL);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state){
        return new ItemStack(BlockInit.ESSENCE_WELL);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if(!worldIn.isRemote && !playerIn.isSneaking()){
            //playerIn.displayGui(GuiEssenceWell, playerIn);
            playerIn.openGui(Main.instance, Reference.GUI_ENUM.Essence_Well.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
            System.out.println("Player interacting with Essence Well");
        }

        return true;

        //return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){

        //super.onBlockAdded(worldIn, pos, state);

        if(!worldIn.isRemote){

            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState east = worldIn.getBlockState(pos.east());
            IBlockState west = worldIn.getBlockState(pos.west());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);

        }

    }

    public static void setState(boolean active, World worldIn, BlockPos pos){

        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(active){
            worldIn.setBlockState(pos, BlockInit.ESSENCE_WELL.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        }
        else {
            worldIn.setBlockState(pos, BlockInit.ESSENCE_WELL.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        }

        if(tileEntity != null){
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
        }

    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new EssenceWellTileEntity();
    }


    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory", "blocks");
    }

}
