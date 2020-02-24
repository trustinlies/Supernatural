package com.trustinlies.supernatural.util.capabilities.fisher;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FisherProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IFisher.class)
    public static final Capability<IFisher> FISHER_LEVEL = null;

    private IFisher instance = FISHER_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == FISHER_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == FISHER_LEVEL ? FISHER_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return FISHER_LEVEL.getStorage().writeNBT(FISHER_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        FISHER_LEVEL.getStorage().readNBT(FISHER_LEVEL, this.instance, null, nbt);
    }
}
