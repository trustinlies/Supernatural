package com.trustinlies.supernatural.util.objects.items;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ItemBase extends Item implements IHasModel {

    public static final List<Item> SHINY = new ArrayList<Item>();

    public ItemBase(String name, String keyword) {

        setUnlocalizedName(name);
        setRegistryName(name);

        //Set Creative Tab Based on name
        if(name.contains("_essence")){
            setCreativeTab(Main.ESSENCETAB);
        }
        else {
            setCreativeTab(Main.ITEMTAB);
        }

        ItemInit.ITEMS.add(this);


        //Special Keywords
        if(keyword.contains("grinder")){
            this.setContainerItem(this);
        }
        else if(keyword.contains("shiny")||name.contains("_essence")){
            SHINY.add(this);
        }



    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        if(SHINY.contains(stack.getItem())) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void registerModels() {
        //Changes subfolder of model location based on name - For Organization
        if(this.getUnlocalizedName().contains("_vial")){
            Main.proxy.registerItemRenderer(this, 0, "inventory", "vials");
        }
        else if(this.getUnlocalizedName().contains("_essence")){
            Main.proxy.registerItemRenderer(this, 0, "inventory", "essence");
        }
        else {
            Main.proxy.registerItemRenderer(this, 0, "inventory", "items");
        }

    }
}