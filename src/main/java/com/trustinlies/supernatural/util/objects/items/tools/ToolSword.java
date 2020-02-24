package com.trustinlies.supernatural.util.objects.items.tools;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ToolSword extends ItemSword implements IHasModel {

    public static final List<Item> SHINY = new ArrayList<Item>();

    public ToolSword(String name, ToolMaterial material, String keyword){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.ITEMTAB);


        ItemInit.ITEMS.add(this);

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
        Main.proxy.registerItemRenderer(this, 0, "inventory", "weapons");
    }
}
