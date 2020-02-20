package com.trustinlies.supernatural.util.objects.blocks.essencewell;

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

    private static final ResourceLocation ESSENCE_WELL_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/essence_well.png");
    private final InventoryPlayer playerInventory;
    private final IInventory tileWell;

    public GuiEssenceWell(InventoryPlayer playerInv, IInventory furnaceInv)
    {
        super(new ContainerEssenceWell(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.tileWell = furnaceInv;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileWell.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(ESSENCE_WELL_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (TileEntityEssenceWell.isBurning(this.tileWell))
        {
            int k = this.getBurnLeftScaled(15);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 15, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileWell.getField(2);
        int j = this.tileWell.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileWell.getField(1);
        if(i == 0)
        {
            i = 200;
        }
        return this.tileWell.getField(0) * pixels / i;
    }

}
