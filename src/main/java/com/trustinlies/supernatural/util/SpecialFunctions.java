package com.trustinlies.supernatural.util;

import com.trustinlies.supernatural.config.SkillsConfig;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.CarpenterProvider;
import com.trustinlies.supernatural.util.capabilities.craftingskills.carpenter.ICarpenter;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.LumberjackProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class SpecialFunctions {
    
    public static int nextLevel(int currentLevel){
        return ((currentLevel + 1) * (currentLevel + 1) + currentLevel + 1)/2 *100 - (currentLevel+1)*100;
    }

    public static ItemStack carpenterItem(ItemStack result, EntityPlayer player) {

        ICarpenter carpenter = player.getCapability(CarpenterProvider.CARPENTER_LEVEL, null);

        NBTTagCompound nbt = result.getTagCompound();
        if (nbt == null) nbt = new NBTTagCompound();
        NBTTagList lore = new NBTTagList();

        int currentcarpenter = carpenter.getLevel();
        int quality = (int) (Math.random()*100);
        double floor = carpenter.getLevel()/10;
        int craftLevel = (int) Math.floor(floor);

        //System.out.println(quality + "/" + currentcarpenter + "/" + floor + "/" + craftLevel + "/" + carpenter.getLevel());

        lore.appendTag(getQuality(quality, craftLevel, player));
        lore.appendTag(new NBTTagString("Crafted by: " + player.getDisplayNameString()));

        NBTTagCompound display = new NBTTagCompound();
        display.setTag("Lore", lore);
        nbt.setTag("display", display);
        result.setTagCompound(nbt);

        return result;
    }

    private static NBTTagString getQuality(int quality, int craftLevel, EntityPlayer player){
        ICarpenter carpenter = player.getCapability(CarpenterProvider.CARPENTER_LEVEL, null);
        if(quality >= (100 - SkillsConfig.superiorQualityChance)) {
            carpenter.add(craftLevel*3 + (quality/10)*3);
            switch (craftLevel){
                case 0:
                    return new NBTTagString(TextFormatting.GRAY + "Quality: Fair");
                case 1:
                    return new NBTTagString(TextFormatting.WHITE + "Quality: Average");
                case 2:
                    return new NBTTagString(TextFormatting.DARK_GREEN + "Quality: Superior");
                case 3:
                    return new NBTTagString(TextFormatting.GREEN + "Quality: Epic");
                case 4:
                    return new NBTTagString(TextFormatting.DARK_AQUA + "Quality: Legendary");
                case 5:
                    return new NBTTagString(TextFormatting.AQUA + "Quality: Mythic");
                case 6:
                    return new NBTTagString(TextFormatting.YELLOW + "Quality: Relic");
                case 7:
                    return new NBTTagString(TextFormatting.GOLD + "Quality: Godly");
                default:
                    return new NBTTagString(TextFormatting.RED + "Quality: Primordial");
            }
        }
        else if(quality > SkillsConfig.poorQualityChance){
            carpenter.add(craftLevel*2 + (quality/10)*2);
            switch (craftLevel){
                case 0:
                    return new NBTTagString(TextFormatting.GRAY + "Quality: Poor");
                case 1:
                    return new NBTTagString(TextFormatting.GRAY + "Quality: Fair");
                case 2:
                    return new NBTTagString(TextFormatting.WHITE + "Quality: Average");
                case 3:
                    return new NBTTagString(TextFormatting.DARK_GREEN + "Quality: Superior");
                case 4:
                    return new NBTTagString(TextFormatting.GREEN + "Quality: Epic");
                case 5:
                    return new NBTTagString(TextFormatting.DARK_AQUA + "Quality: Legendary");
                case 6:
                    return new NBTTagString(TextFormatting.AQUA + "Quality: Mythic");
                case 7:
                    return new NBTTagString(TextFormatting.YELLOW + "Quality: Relic");
                case 8:
                    return new NBTTagString(TextFormatting.GOLD + "Quality: Godly");
                default:
                    return new NBTTagString(TextFormatting.RED + "Quality: Primordial");
            }
        }
        else{
            carpenter.add(craftLevel + quality/10);
            switch (craftLevel){
                case 0:
                    return new NBTTagString(TextFormatting.DARK_GRAY + "Quality: Trash");
                case 1:
                    return new NBTTagString(TextFormatting.GRAY + "Quality: Poor");
                case 2:
                    return new NBTTagString(TextFormatting.GRAY + "Quality: Fair");
                case 3:
                    return new NBTTagString(TextFormatting.WHITE + "Quality: Average");
                case 4:
                    return new NBTTagString(TextFormatting.DARK_GREEN + "Quality: Superior");
                case 5:
                    return new NBTTagString(TextFormatting.GREEN + "Quality: Epic");
                case 6:
                    return new NBTTagString(TextFormatting.DARK_AQUA + "Quality: Legendary");
                case 7:
                    return new NBTTagString(TextFormatting.AQUA + "Quality: Mythic");
                case 8:
                    return new NBTTagString(TextFormatting.YELLOW + "Quality: Relic");
                default:
                    return new NBTTagString(TextFormatting.GOLD + "Quality: Godly");
            }
        }
                
    }
    
}
