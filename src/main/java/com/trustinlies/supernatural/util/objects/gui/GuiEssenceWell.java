package com.trustinlies.supernatural.util.objects.gui;

import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.ContainerEssenceWell;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEssenceWell extends GuiContainer {

    private static final ResourceLocation essenceWellGuiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/essence_well.png");
    private final InventoryPlayer playerInv;
    private final IInventory tileEssenceWell;

    public GuiEssenceWell(InventoryPlayer inventoryPlayer, IInventory iInventory){
        super(new ContainerEssenceWell(inventoryPlayer, iInventory));
        playerInv = inventoryPlayer;
        tileEssenceWell = iInventory;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = tileEssenceWell.getDisplayName().getFormattedText();
        fontRenderer.drawString(s, xSize/2-fontRenderer.getStringWidth(s)/2, 6, 421075);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(),8, ySize -96 +2, 421075);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        mc.getTextureManager().bindTexture(essenceWellGuiTextures);
        int marginHorizontal = (width - xSize)/2;
        int marginVertical = (height - ySize)/2;
        drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);

        int progressLevel = getProgressLevel(24);
        drawTexturedModalRect(marginHorizontal + 79, marginVertical + 34, 176, 14, progressLevel +1, 16);
    }

    private int getProgressLevel(int progressIndicatorPixelWidth){
        int ticksFocusingItemSoFar = tileEssenceWell.getField(2);
        int ticksPerItem = tileEssenceWell.getField(3);
        return ticksPerItem != 0 && ticksFocusingItemSoFar !=0 ? ticksFocusingItemSoFar*progressIndicatorPixelWidth/ticksPerItem : 0;
    }
}
