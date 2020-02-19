package com.trustinlies.supernatural.util.objects.items;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class FoodBase extends ItemFood implements IHasModel {

    public FoodBase(String name, int amount, float saturation, Boolean isWolfFood, String keyword){
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(Main.ITEMTAB);
        ItemInit.ITEMS.add(this);

        if(keyword.contains("edible")){
            setAlwaysEdible();
        }


    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {

        String name = getUnlocalizedName(stack);

        if(!worldIn.isRemote) {
            if(name.contains("_pie")) {
                player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 30 * 20, 5, false, true));
            }
            else if(name.contains("_juice")){
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 30 * 20, 5, false, true));
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Item.getByNameOrId("glass_bottle")));
            }
            else if(name.contains("bad_boy")){
                player.setHealth(0.5F);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        String name = stack.getUnlocalizedName();
        return name.contains("_pie") || name.contains("_juice");
    }


    @Override
    public void registerModels(){

        Main.proxy.registerItemRenderer(this, 0, "inventory", "food");

    }

}
