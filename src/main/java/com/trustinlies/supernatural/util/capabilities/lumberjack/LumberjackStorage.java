package com.trustinlies.supernatural.util.capabilities.lumberjack;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class LumberjackStorage implements Capability.IStorage<ILumberjack> {


    @Override
    public NBTBase writeNBT(Capability<ILumberjack> capability, ILumberjack instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<ILumberjack> capability, ILumberjack instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
