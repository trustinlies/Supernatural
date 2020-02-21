package com.trustinlies.supernatural.util.capabilities;

import com.trustinlies.supernatural.util.capabilities.IMining;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class MiningStorage implements Capability.IStorage<IMining> {


    @Override
    public NBTBase writeNBT(Capability<IMining> capability, IMining instance, EnumFacing side) {
        return new NBTTagInt(instance.getLevel());
    }

    @Override
    public void readNBT(Capability<IMining> capability, IMining instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
