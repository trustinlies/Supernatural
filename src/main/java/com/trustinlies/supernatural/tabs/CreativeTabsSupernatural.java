package com.trustinlies.supernatural.tabs;

import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsSupernatural extends CreativeTabs {

    private int tab;

    public CreativeTabsSupernatural(int par1, String par2Str, int par3) {
        super(par1, par2Str);
        tab = par3;
    }

    @Override
    public ItemStack getTabIconItem() {
        if (tab == 1) {
            return new ItemStack(ItemInit.APPLE_JUICE);
        }
        else if (tab == 2){
            return new ItemStack((BlockInit.BASALT));
        }
        else {
            return new ItemStack((ItemInit.CREEPER_ESSENCE));
        }
    }

}
