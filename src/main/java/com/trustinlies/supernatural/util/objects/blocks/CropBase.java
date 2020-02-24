package com.trustinlies.supernatural.util.objects.blocks;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class CropBase extends BlockCrops implements IHasModel {

    public CropBase(String name, Material material){
        super();
        setTickRandomly(true);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(null);
        setHardness(0.0F);

        BlockInit.BLOCKS.add(this);

    }


    @Override
    public void registerModels() {

    }
}
