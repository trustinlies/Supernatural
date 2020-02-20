package com.trustinlies.supernatural.util.objects.blocks.essencewell;

import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.recipes.EssenceWellRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityEssenceWell extends TileEntityLockable implements ISidedInventory, ITickable {

    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};

    private NonNullList<ItemStack> wellItems = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    private int wellBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String wellCustomName;

    public static final String RED = "\u001B[31m";

    @Override
    public int getSizeInventory() {
        return this.wellItems.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.wellItems){
            if(!stack.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        System.out.println("Stack in slot " + this.wellItems.get(index).getDisplayName());
        return this.wellItems.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(wellItems, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(wellItems, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = this.wellItems.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemStackTagsEqual(stack, itemStack);
        this.wellItems.set(index, stack);

        if(stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }
        if(index == 0 && !flag){
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.wellCustomName : "container.essence_well";
    }

    @Override
    public boolean hasCustomName() {
        return this.wellCustomName != null && !this.wellCustomName.isEmpty();
    }

    public void setCustomInventoryName(String wellCustomName){
        this.wellCustomName = wellCustomName;
    }

    public static void registerFixesWell(DataFixer fixer){
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityEssenceWell.class, new String[] {"Items"}));
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.wellItems = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.wellItems);
        this.wellBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.wellItems.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.wellCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.wellBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.wellItems);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.wellCustomName);
        }

        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning(){
        //System.out.println("Checking If Burning" + wellBurnTime);
        return this.wellBurnTime > 0;

    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory){
        System.out.println(inventory.getField(0));
        return inventory.getField(0) > 0;
    }

    public void update()
    {
        //System.out.println("Updating Block");
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.wellBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.wellItems.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.wellItems.get(0)).isEmpty())
            {
                if (!this.isBurning() && this.canFocus())
                {
                    this.wellBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.wellBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.wellItems.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canFocus())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.wellItems.get(0));
                        this.focusItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                EssenceWell.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int getCookTime(ItemStack stack) {
        return 300;
    }

    private boolean canFocus()
    {
        if (this.wellItems.get(0).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = EssenceWellRecipes.instance().getCookingResult(this.wellItems.get(0));

            //System.out.println("Checking If Burning");

            if (itemstack.isEmpty())
            {
                System.out.println(RED + "No Recipe");
                return false;
            }
            else if(itemstack.getCount() >= 15){
                System.out.println("Fewer than 16");
                return false;
            }
            else{
                ItemStack itemstack1 = this.wellItems.get(2);
                System.out.println("Recipe found for " + itemstack1.getDisplayName());

                if (itemstack1.isEmpty())
                {
                    System.out.println("Can Focus");
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    System.out.println("Something Else in output");
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())
                {
                    System.out.println("Same Result in Output");
                    return true;
                }
                else
                {
                    System.out.println("Same Result in Output 2");
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        }
    }

    public void focusItem()
    {
        if (this.canFocus())
        {
            ItemStack itemstack = this.wellItems.get(0);
            ItemStack itemstack1 = EssenceWellRecipes.instance().getCookingResult(itemstack);
            ItemStack itemstack2 = this.wellItems.get(2);

            if (itemstack2.isEmpty())
            {
                this.wellItems.set(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(16);
        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item == ItemInit.FOCUS_EMPTY)
            {
                return 300;
            } else return 0;

        }
    }

    public static boolean isItemFuel(ItemStack stack){
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if(this.world.getTileEntity(this.pos) != this){
            return false;
        }
        else{
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player)
    {

    }

    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 2){
            return false;
        }
        else if(index != 1){
            return stack.getDisplayName().contains("_essence");
        }
        else{
            ItemStack itemStack = this.wellItems.get(1);
            return isItemFuel(stack);
        }
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        if(side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if(direction == EnumFacing.DOWN && index == 1){
            return false;
        }
        else return index != 0;
    }

    @Override
    public String getGuiID() {
        return "supernatural:essence_well";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerEssenceWell(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.wellBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.wellBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int getFieldCount()
    {
        return 4;
    }

    @Override
    public void clear()
    {
        this.wellItems.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

}
