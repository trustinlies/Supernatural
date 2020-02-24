package com.trustinlies.supernatural.util.capabilities.farmer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FarmerProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IFarmer.class)
    public static final Capability<IFarmer> FARMER_LEVEL = null;

    private IFarmer instance = FARMER_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == FARMER_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == FARMER_LEVEL ? FARMER_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return FARMER_LEVEL.getStorage().writeNBT(FARMER_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        FARMER_LEVEL.getStorage().readNBT(FARMER_LEVEL, this.instance, null, nbt);
    }
}
