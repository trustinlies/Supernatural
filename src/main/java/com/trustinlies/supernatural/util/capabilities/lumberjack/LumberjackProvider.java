package com.trustinlies.supernatural.util.capabilities.lumberjack;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LumberjackProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ILumberjack.class)
    public static final Capability<ILumberjack> LUMBERJACK_LEVEL = null;

    private ILumberjack instance = LUMBERJACK_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == LUMBERJACK_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == LUMBERJACK_LEVEL ? LUMBERJACK_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return LUMBERJACK_LEVEL.getStorage().writeNBT(LUMBERJACK_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        LUMBERJACK_LEVEL.getStorage().readNBT(LUMBERJACK_LEVEL, this.instance, null, nbt);
    }
}
