package com.trustinlies.supernatural.util.objects.blocks;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material){

        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.BLOCKTAB);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory", "blocks");
    }
}
