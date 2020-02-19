package com.trustinlies.supernatural.util.objects.blocks.essencewell;

import com.trustinlies.supernatural.util.objects.blocks.EssenceWell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEssenceWell extends Container {

    private final IInventory tileEssenceWell;
    private final int sizeInventory;
    private int ticksFocusingItemSoFar;
    private int ticksPerItem;
    private int timeCanFocus;

    public ContainerEssenceWell(InventoryPlayer playerInv, IInventory iInventory){
        System.out.println("ContainerEssenceWell constructor()");

        tileEssenceWell = iInventory;
        sizeInventory = tileEssenceWell.getSizeInventory();
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_1.ordinal(), 89, 10));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_2.ordinal(), 107, 10));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_3.ordinal(), 125, 19));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_4.ordinal(), 134, 37));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_5.ordinal(), 134, 55));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_6.ordinal(), 125, 73));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_7.ordinal(), 107, 82));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_8.ordinal(), 89, 82));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_9.ordinal(), 71, 82));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_10.ordinal(), 53, 82));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_11.ordinal(), 35, 73));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_12.ordinal(), 26, 55));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_13.ordinal(), 26, 37));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_14.ordinal(), 35, 19));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_15.ordinal(), 53, 10));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.INPUT_SLOT_16.ordinal(), 71, 10));
        addSlotToContainer(new Slot(tileEssenceWell, EssenceWellTileEntity.slotEnum.OUTPUT_SLOT.ordinal(), 80, 46));

        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(playerInv, j+i*9+9,
                        8+j*18, 84+i*18));
            }
        }

        // add hotbar slots
        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(playerInv, i, 8 + i * 18,
                    142));
        }
    }



    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (IContainerListener listener : this.listeners) {
            if (ticksFocusingItemSoFar != tileEssenceWell.getField(2))
                listener.sendWindowProperty(this, 2, tileEssenceWell.getField(2));
            if (timeCanFocus != tileEssenceWell.getField(0))
                listener.sendWindowProperty(this, 0, tileEssenceWell.getField(0));
            if (ticksPerItem!= tileEssenceWell.getField(1))
                listener.sendWindowProperty(this, 1, tileEssenceWell.getField(3));

        }

        ticksFocusingItemSoFar = tileEssenceWell.getField(2);
        timeCanFocus = tileEssenceWell.getField(0);
        ticksPerItem = tileEssenceWell.getField(3);
    }

    @Override
    public void updateProgressBar(int id, int data) {
        tileEssenceWell.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return tileEssenceWell.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 17) {
                if (!this.mergeItemStack(itemstack1, 17, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (!this.mergeItemStack(itemstack1, 0, 17, false)) {
                if (index < 17 + 27) {
                    if (!this.mergeItemStack(itemstack1, 17 + 27, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.mergeItemStack(itemstack1, 17, 17 + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }
    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;
        if (reverseDirection) {
            i = endIndex - 1;
        }
        if (stack.isStackable()) {
            while (!stack.isEmpty()) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }
                Slot slot = this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();
                if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && itemstack.getItem() == stack.getItem()
                        && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata())
                        && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.putStack(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.putStack(itemstack);
                        flag = true;
                    }
                }
                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        if (!stack.isEmpty()) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }
            while (true) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }
                Slot slot1 = this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();
                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                    if (stack.getCount() > slot1.getSlotStackLimit()) {
                        slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                    } else {
                        slot1.putStack(stack.splitStack(stack.getCount()));
                    }
                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }
                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        return flag;
    }
}
