package com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class FarmerStorage implements Capability.IStorage<IFarmer> {


    @Override
    public NBTBase writeNBT(Capability<IFarmer> capability, IFarmer instance, EnumFacing side) {
        return new NBTTagInt(instance.getExp());
    }

    @Override
    public void readNBT(Capability<IFarmer> capability, IFarmer instance, EnumFacing side, NBTBase nbt) {
        instance.set(((NBTPrimitive) nbt).getInt());
    }
}
