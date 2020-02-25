package com.trustinlies.supernatural.util.capabilities.combatskills.thief;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ThiefProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IThief.class)
    public static final Capability<IThief> THIEF_LEVEL = null;

    private IThief instance = THIEF_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == THIEF_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == THIEF_LEVEL ? THIEF_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return THIEF_LEVEL.getStorage().writeNBT(THIEF_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        THIEF_LEVEL.getStorage().readNBT(THIEF_LEVEL, this.instance, null, nbt);
    }
}
