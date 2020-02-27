package com.trustinlies.supernatural.util;

import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.LumberjackProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class SpecialFunctions {
    
    public static int nextLevel(int currentLevel){
        return ((currentLevel + 1) * (currentLevel + 1) + currentLevel + 1)/2 *100 - (currentLevel+1)*100;
    }

    public static ItemStack carpenterItem(ItemStack slot0, ItemStack slot1,ItemStack slot2,ItemStack slot3,ItemStack slot4,ItemStack slot5,ItemStack slot6,ItemStack slot7 ,ItemStack slot8, ItemStack result,  EntityPlayer player){
        ItemStack modifiedResult = result;

        ILumberjack lumberjack = player.getCapability(LumberjackProvider.LUMBERJACK_LEVEL, null);
        double quality = Math.random();

        //Change Quality Names based on skill level

        if(quality >= 0.97) {

            NBTTagCompound nbt = modifiedResult.getTagCompound();
            if (nbt == null) nbt = new NBTTagCompound();

            NBTTagList lore = new NBTTagList();
            lore.appendTag(new NBTTagString("Quality: Superior"));
            lore.appendTag(new NBTTagString("Crafted by: " + player.getDisplayNameString()));

            NBTTagCompound display = new NBTTagCompound();
            display.setTag("Lore", lore);
            nbt.setTag("display", display);
            modifiedResult.setTagCompound(nbt);
        }
        else if(quality >= 0.11){
            NBTTagCompound nbt = modifiedResult.getTagCompound();
            if (nbt == null) nbt = new NBTTagCompound();

            NBTTagList lore = new NBTTagList();
            lore.appendTag(new NBTTagString(TextFormatting.GOLD + "Quality: Average"));
            lore.appendTag(new NBTTagString("Crafted by: " + player.getDisplayNameString()));

            NBTTagCompound display = new NBTTagCompound();
            display.setTag("Lore", lore);
            nbt.setTag("display", display);
            modifiedResult.setTagCompound(nbt);
        } else{
            NBTTagCompound nbt = modifiedResult.getTagCompound();
            if (nbt == null) nbt = new NBTTagCompound();

            NBTTagList lore = new NBTTagList();
            lore.appendTag(new NBTTagString("Quality: Low"));
            lore.appendTag(new NBTTagString("Crafted by: " + player.getDisplayNameString()));

            NBTTagCompound display = new NBTTagCompound();
            display.setTag("Lore", lore);
            nbt.setTag("display", display);
            modifiedResult.setTagCompound(nbt);
        }


        return modifiedResult;
    }
    
}
