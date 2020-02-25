package com.trustinlies.supernatural.util.handlers;

import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.ContainerEssenceWell;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.GuiEssenceWell;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.TileEntityEssenceWell;
import com.trustinlies.supernatural.util.objects.guis.SkillsGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        if (tileEntity != null){
            if(ID == Reference.GUI_ESSENCE_WELL){
                return new ContainerEssenceWell(player.inventory, (TileEntityEssenceWell)world.getTileEntity(new BlockPos(x,y,z)));
            }
        }
        if(ID == Reference.GUI_PROFESSION_BOOK){
            return new SkillsGUI(player);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        if (tileEntity != null){
            if(ID == Reference.GUI_ESSENCE_WELL){
                return new GuiEssenceWell(player.inventory, (TileEntityEssenceWell)world.getTileEntity(new BlockPos(x,y,z)));
            }
        }
        if(ID == Reference.GUI_PROFESSION_BOOK){
            return new SkillsGUI(player);
        }
        return null;
    }
}
