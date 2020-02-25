package com.trustinlies.supernatural.util.capabilities.combatskills.mage;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MageStorage implements Capability.IStorage<IMage> {


    @Override
    public NBTBase writeNBT(Capability<IMage> capability, IMage instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<IMage> capability, IMage instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
