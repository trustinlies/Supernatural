package com.trustinlies.supernatural.util.capabilities.combatskills.thief;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ThiefStorage implements Capability.IStorage<IThief> {


    @Override
    public NBTBase writeNBT(Capability<IThief> capability, IThief instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<IThief> capability, IThief instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
