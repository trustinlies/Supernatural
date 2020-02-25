package com.trustinlies.supernatural.util.capabilities.combatskills.knight;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class KnightStorage implements Capability.IStorage<IKnight> {


    @Override
    public NBTBase writeNBT(Capability<IKnight> capability, IKnight instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<IKnight> capability, IKnight instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
