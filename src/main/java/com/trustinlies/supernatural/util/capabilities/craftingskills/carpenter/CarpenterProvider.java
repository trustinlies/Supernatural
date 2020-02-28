package com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CarpenterProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(ICarpenter.class)
    public static final Capability<ICarpenter> CARPENTER_LEVEL = null;

    private ICarpenter instance = CARPENTER_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CARPENTER_LEVEL;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CARPENTER_LEVEL ? CARPENTER_LEVEL.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return CARPENTER_LEVEL.getStorage().writeNBT(CARPENTER_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CARPENTER_LEVEL.getStorage().readNBT(CARPENTER_LEVEL, this.instance, null, nbt);
    }
}
