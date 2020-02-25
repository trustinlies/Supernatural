package com.trustinlies.supernatural.util.capabilities.combatskills.archer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class ArcherStorage implements Capability.IStorage<IArcher> {


    @Override
    public NBTBase writeNBT(Capability<IArcher> capability, IArcher instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<IArcher> capability, IArcher instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
