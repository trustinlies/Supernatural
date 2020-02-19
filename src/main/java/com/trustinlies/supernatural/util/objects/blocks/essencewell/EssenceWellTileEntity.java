package com.trustinlies.supernatural.util.objects.blocks.essencewell;

import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.recipes.WellRecipes;
import com.trustinlies.supernatural.util.objects.blocks.EssenceWell;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class EssenceWellTileEntity extends TileEntityLockable implements ISidedInventory, ITickable {

    //enumerate slots
    public enum slotEnum{

        INPUT_SLOT_1,INPUT_SLOT_2,INPUT_SLOT_3,INPUT_SLOT_4,INPUT_SLOT_5,INPUT_SLOT_6,INPUT_SLOT_7,INPUT_SLOT_8,INPUT_SLOT_9,INPUT_SLOT_10,INPUT_SLOT_11,INPUT_SLOT_12,INPUT_SLOT_13,INPUT_SLOT_14,INPUT_SLOT_15,INPUT_SLOT_16,OUTPUT_SLOT

    }

    private static final int[] slotsTop = new int[]{
            slotEnum.INPUT_SLOT_1.ordinal(), slotEnum.INPUT_SLOT_2.ordinal(), slotEnum.INPUT_SLOT_3.ordinal(), slotEnum.INPUT_SLOT_4.ordinal(), slotEnum.INPUT_SLOT_5.ordinal(), slotEnum.INPUT_SLOT_6.ordinal(), slotEnum.INPUT_SLOT_7.ordinal(), slotEnum.INPUT_SLOT_8.ordinal(), slotEnum.INPUT_SLOT_9.ordinal(), slotEnum.INPUT_SLOT_10.ordinal(), slotEnum.INPUT_SLOT_11.ordinal(), slotEnum.INPUT_SLOT_12.ordinal(), slotEnum.INPUT_SLOT_13.ordinal(), slotEnum.INPUT_SLOT_14.ordinal(), slotEnum.INPUT_SLOT_15.ordinal(), slotEnum.INPUT_SLOT_16.ordinal()
    };
    private static final int[] slotsBottom = new int[]{
            slotEnum.OUTPUT_SLOT.ordinal()
    };
    private static final int[] slotsSides = new int[]{};
    private ItemStack[] essenceWellItemStackArray = new ItemStack[17];
    private int timeCanFocus;
    private int currentFocusTime;
    private int ticksFocusingSoFar;
    private int ticksPerItem;
    //public static final int SIZE = 1;
    private String wellCustomName;
    /*public ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE){
        @Override
        protected void onContentsChanged(int slot) {
            update();
        }
    };*/

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return essenceWellItemStackArray.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return essenceWellItemStackArray[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(essenceWellItemStackArray[index] != null){
            ItemStack itemStack;

            if(essenceWellItemStackArray[index].getCount() <= count){
                itemStack = essenceWellItemStackArray[index];
                essenceWellItemStackArray[index] = null;
            }
            else{
                itemStack = essenceWellItemStackArray[index].splitStack(count);
                if(essenceWellItemStackArray[index].getCount() == 0){
                    essenceWellItemStackArray[index] = null;
                }
            }
            return itemStack;

        }
        else{
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        boolean isSameItemStackAlreadyInSlot = stack != null && stack.isItemEqual(essenceWellItemStackArray[index]) && ItemStack.areItemStackTagsEqual(stack, essenceWellItemStackArray[index]);
        if (stack != null && stack.getCount() > getInventoryStackLimit()){
            stack.setCount(getInventoryStackLimit());
        }

        if(index == slotEnum.INPUT_SLOT_1.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_2.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_3.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_4.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_5.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_6.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_7.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_8.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_9.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_10.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_11.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_12.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_13.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_14.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_15.ordinal() && !isSameItemStackAlreadyInSlot || index == slotEnum.INPUT_SLOT_16.ordinal() && !isSameItemStackAlreadyInSlot){
            ticksPerItem = timeToFocusOneItem(stack);
            ticksFocusingSoFar = 0;
            markDirty();
        }

    }

    @Override
    public String getName() {
        return hasCustomName() ? wellCustomName : "container.well";
    }

    @Override
    public boolean hasCustomName() {
        return wellCustomName != null && wellCustomName.length() > 0;
    }
/*
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbtTagList = compound.getTagList("Items", 10);
        essenceWellItemStackArray = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i){
            NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
            byte b0 = nbtTagCompound.getByte("Slot");

            if (b0 >= 0 && b0 < essenceWellItemStackArray.length){
                essenceWellItemStackArray[b0] = itemStackHandler.setStackInSlot(b0, );
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if(compound == null) compound = new NBTTagCompound();
        compound.setTag("Items", itemStackHandler.serializeNBT());
        return compound;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return(T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }
*/
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean focusingSomething(){
        return true;
    }

    @SideOnly(Side.CLIENT)
    public static boolean func1(IInventory parIInventory){
        return true;
    }

    @Override
    public void update() {
        boolean hasBeenFocusing = focusingSomething();
        boolean changedFocusingState = false;

        if(focusingSomething()){

        }
        if(!world.isRemote){
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()] != null){
                if(!focusingSomething() && canFocus()){
                    timeCanFocus = 150;
                    if(focusingSomething()){
                        changedFocusingState = true;
                    }
                }
                if (focusingSomething() && canFocus()){
                    ++ticksFocusingSoFar;
                    if(ticksFocusingSoFar == ticksPerItem){
                        ticksFocusingSoFar = 0;
                        ticksPerItem = timeToFocusOneItem(essenceWellItemStackArray[0]);
                        focusItem();
                        changedFocusingState = true;
                    }
                }
                else{
                    ticksFocusingSoFar = 0;
                }
            }
            if (hasBeenFocusing != focusingSomething()){
                changedFocusingState = true;
                //EssenceWell.changeBlockBasedOnWellStatus(focusingSomething(), world, pos);
            }
        }
        if(changedFocusingState){
            markDirty();
        }
    }

    public int timeToFocusOneItem(ItemStack parItemStack){
        return 200;
    }

    private boolean canFocus(){
        Item slot1 = essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()].getItem();
        if (essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_2.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_3.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_4.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_5.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_6.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_7.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_8.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_9.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_10.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_11.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_12.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_13.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_14.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_15.ordinal()] == null ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_16.ordinal()] == null ||essenceWellItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()] == null){
            return false;
        }
        else if (essenceWellItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()].getItem() != ItemInit.FOCUS_EMPTY){
            return false;
        }
        else if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_2.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_3.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_4.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_5.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_6.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_7.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_8.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_9.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_10.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_11.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_12.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_13.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_14.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_15.ordinal()].getItem() != slot1 ||essenceWellItemStackArray[slotEnum.INPUT_SLOT_16.ordinal()].getItem() != slot1){
            return false;
        }
        else {
            ItemStack itemStackToOutput = WellRecipes.instance().getWellResult(essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()]);
            if(itemStackToOutput == null) return false;
            else return true;
            }

    }

    public void focusItem(){
        if(canFocus()){
            ItemStack stack = WellRecipes.instance().getWellResult(essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()]);
            if(essenceWellItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()].getItem() == ItemInit.FOCUS_EMPTY){
                essenceWellItemStackArray[slotEnum.OUTPUT_SLOT.ordinal()] = stack.copy();
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_1.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_2.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_2.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_3.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_3.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_4.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_4.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_5.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_5.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_6.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_6.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_7.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_7.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_8.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_8.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_9.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_9.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_10.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_10.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_11.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_11.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_12.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_12.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_13.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_13.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_14.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_14.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_15.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_15.ordinal()] = null;
            }
            if(essenceWellItemStackArray[slotEnum.INPUT_SLOT_16.ordinal()].getCount() <= 0){
                essenceWellItemStackArray[slotEnum.INPUT_SLOT_16.ordinal()] = null;
            }
        }
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX()+0.5D, pos.getY()+0.5D, pos.getY()+0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == slotEnum.INPUT_SLOT_1.ordinal() ? true: false;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public String getGuiID() {
        return "supernatural:essence_well";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        System.out.println("TileEntityEssenceWell createContainer()");
        return new ContainerEssenceWell(playerInventory, this);
    }

    @Override
    public int getField(int id) {
        switch (id){
            case 0:
                return timeCanFocus;
            case 1:
                return currentFocusTime;
            case 2:
                return ticksFocusingSoFar;
            case 3:
                return ticksPerItem;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0:
                timeCanFocus = value;
                break;
            case 1:
                currentFocusTime = value;
                break;
            case 2:
                ticksFocusingSoFar = value;
                break;
            case 3:
                ticksPerItem = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        for (int i = 0; i < essenceWellItemStackArray.length; ++i){
            essenceWellItemStackArray[i] = null;
        }
    }
}
