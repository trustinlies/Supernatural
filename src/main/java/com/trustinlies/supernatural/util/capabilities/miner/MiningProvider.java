package com.trustinlies.supernatural.util.capabilities.miner;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MiningProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IMining.class)
    public static final Capability<IMining> MINING_LEVEL = null;

    private IMining instance = MINING_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == MINING_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == MINING_LEVEL ? MINING_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MINING_LEVEL.getStorage().writeNBT(MINING_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        MINING_LEVEL.getStorage().readNBT(MINING_LEVEL, this.instance, null, nbt);
    }
}
