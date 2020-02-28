package com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class CarpenterStorage implements Capability.IStorage<ICarpenter> {


    @Override
    public NBTBase writeNBT(Capability<ICarpenter> capability, ICarpenter instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<ICarpenter> capability, ICarpenter instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
