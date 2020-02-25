package com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class FisherStorage implements Capability.IStorage<IFisher> {


    @Override
    public NBTBase writeNBT(Capability<IFisher> capability, IFisher instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<IFisher> capability, IFisher instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
