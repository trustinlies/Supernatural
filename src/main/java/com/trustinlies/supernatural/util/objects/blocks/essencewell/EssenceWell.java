package com.trustinlies.supernatural.util.objects.blocks.essencewell;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.TileEntityEssenceWell;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.Random;

public class EssenceWell extends BlockContainer implements IHasModel, ITileEntityProvider {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    private final boolean isBurning;
    private static boolean keepInventory;


    public EssenceWell(String name, boolean isBurning){
        super(Material.ROCK);
        setSoundType(SoundType.STONE);
        setResistance(50.0F);
        setHardness(3.5F);
        setHarvestLevel("pickaxe", 0);
        setUnlocalizedName(name);
        setRegistryName(name);

        if(name.equals("essence_well_off")) {
            setCreativeTab(Main.BLOCKTAB);
        }
        this.setDefaultState(this.blockState.getBaseState());//.withProperty(FACING, EnumFacing.NORTH));
        this.isBurning = isBurning;

        BlockInit.BLOCKS.add(this);

        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(Main.instance, Reference.GUI_ESSENCE_WELL, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        //this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state){
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state, 2);//.withProperty(FACING, enumfacing), 2);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            worldIn.setBlockState(pos, BlockInit.ESSENCE_WELL_ON.getDefaultState(), 3);//.withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlockInit.ESSENCE_WELL_ON.getDefaultState(), 3);//.withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            worldIn.setBlockState(pos, BlockInit.ESSENCE_WELL_OFF.getDefaultState(), 3);//.withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, BlockInit.ESSENCE_WELL_OFF.getDefaultState(), 3);//.withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        System.out.println("Creating New Essence Well");
        return new TileEntityEssenceWell();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState();//.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state); //.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));

        if(stack.hasDisplayName()){
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if(tileEntity instanceof TileEntityEssenceWell){
                ((TileEntityEssenceWell)tileEntity).setCustomInventoryName(stack.getDisplayName());

            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(!keepInventory){
            TileEntityEssenceWell tileEntity = (TileEntityEssenceWell)worldIn.getTileEntity(pos);
            InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity);
            worldIn.updateComparatorOutputLevel(pos, this);
            super.breakBlock(worldIn, pos, state);
        }
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(BlockInit.ESSENCE_WELL_OFF);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumFacing = EnumFacing.getFront(meta);
        if(enumFacing.getAxis() == EnumFacing.Axis.Y){
            enumFacing = EnumFacing.NORTH;
        }

        return this.getDefaultState();//.withProperty(FACING, enumFacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;//((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state;//.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state;//.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this);//new IProperty[] {FACING});
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory", "blocks");
    }
}
