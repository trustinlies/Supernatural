package com.trustinlies.supernatural.util.objects.items;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;


public class ArmorBase extends ItemArmor implements IHasModel {

    public static final List<Item> SHINY = new ArrayList<Item>();

    public ArmorBase(String name, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot, String keyword){

        super(material, renderIndex, equipmentSlot);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.ITEMTAB);
        ItemInit.ITEMS.add(this);

        //Special Keywords
        if(keyword.contains("shiny")){
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

        Main.proxy.registerItemRenderer(this, 0, "inventory", "armor");

    }
}
