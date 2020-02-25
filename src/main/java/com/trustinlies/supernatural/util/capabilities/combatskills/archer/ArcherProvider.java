package com.trustinlies.supernatural.util.capabilities.combatskills.archer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ArcherProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IArcher.class)
    public static final Capability<IArcher> ARCHER_LEVEL = null;

    private IArcher instance = ARCHER_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == ARCHER_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == ARCHER_LEVEL ? ARCHER_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return ARCHER_LEVEL.getStorage().writeNBT(ARCHER_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        ARCHER_LEVEL.getStorage().readNBT(ARCHER_LEVEL, this.instance, null, nbt);
    }
}
