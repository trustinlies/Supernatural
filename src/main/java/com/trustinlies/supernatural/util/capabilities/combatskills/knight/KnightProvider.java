package com.trustinlies.supernatural.util.capabilities.combatskills.knight;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class KnightProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IKnight.class)
    public static final Capability<IKnight> KNIGHT_LEVEL = null;

    private IKnight instance = KNIGHT_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == KNIGHT_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == KNIGHT_LEVEL ? KNIGHT_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return KNIGHT_LEVEL.getStorage().writeNBT(KNIGHT_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        KNIGHT_LEVEL.getStorage().readNBT(KNIGHT_LEVEL, this.instance, null, nbt);
    }
}
