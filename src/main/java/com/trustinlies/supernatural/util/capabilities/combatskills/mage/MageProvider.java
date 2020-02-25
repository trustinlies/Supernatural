package com.trustinlies.supernatural.util.capabilities.combatskills.mage;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MageProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IMage.class)
    public static final Capability<IMage> MAGE_LEVEL = null;

    private IMage instance = MAGE_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == MAGE_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == MAGE_LEVEL ? MAGE_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MAGE_LEVEL.getStorage().writeNBT(MAGE_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        MAGE_LEVEL.getStorage().readNBT(MAGE_LEVEL, this.instance, null, nbt);
    }
}
