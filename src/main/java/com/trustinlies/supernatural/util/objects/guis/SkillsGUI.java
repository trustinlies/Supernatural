package com.trustinlies.supernatural.util.objects.guis;

import com.trustinlies.supernatural.Colors;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.ArcherProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.archer.IArcher;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.IKnight;
import com.trustinlies.supernatural.util.capabilities.combatskills.knight.KnightProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.IMage;
import com.trustinlies.supernatural.util.capabilities.combatskills.mage.MageProvider;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.IThief;
import com.trustinlies.supernatural.util.capabilities.combatskills.thief.ThiefProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.FarmerProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.farmer.IFarmer;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.FisherProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.fisher.IFisher;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.ILumberjack;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.lumberjack.LumberjackProvider;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.IMining;
import com.trustinlies.supernatural.util.capabilities.gatheringskills.miner.MiningProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class SkillsGUI extends GuiScreen {

    private final int bookImageHeight = 192;
    private final int bookImageWidth = 192;
    private int currPage = 0;
    private static final int bookTotalPages = 2;
    private static ResourceLocation bookPageTextures = new ResourceLocation(Reference.MOD_ID +":textures/gui/book.png");
    private static String[] stringPageText = new String[bookTotalPages];
    private GuiButton buttonDone;
    private GuiButton buttonNextPage;
    private GuiButton buttonPreviousPage;

    public SkillsGUI(EntityPlayer player){

        IMining mining = player.getCapability(MiningProvider.MINING_LEVEL, null);
        ILumberjack lumberjack = player.getCapability(LumberjackProvider.LUMBERJACK_LEVEL, null);
        IFisher fisher = player.getCapability(FisherProvider.FISHER_LEVEL, null);
        IFarmer farmer = player.getCapability(FarmerProvider.FARMER_LEVEL, null);
        IKnight knight = player.getCapability(KnightProvider.KNIGHT_LEVEL, null);
        IArcher archer = player.getCapability(ArcherProvider.ARCHER_LEVEL, null);
        IMage mage = player.getCapability(MageProvider.MAGE_LEVEL, null);
        IThief thief = player.getCapability(ThiefProvider.THIEF_LEVEL, null);

        //nt nextMiningLevel = mining.getLevel()+1;


        stringPageText[0] = TextFormatting.BOLD + "" + TextFormatting.DARK_AQUA + "     Supernatural \n      Professions" + "\n\n\n\n\n\n\n\n\n\n\n\n" +"Hunter: " + player.getDisplayNameString();
        System.out.println(mining.getNextExp());
        stringPageText[1] = TextFormatting.GOLD + "Miner Level: " + TextFormatting.RESET + mining.getLevel() + "\n" + TextFormatting.GOLD + "Miner Experience: " + TextFormatting.BLACK + mining.getExp() + TextFormatting.GOLD + "\nNext Level at: " + TextFormatting.BLACK + mining.getNextExp();

    }

    @Override
    public void initGui()
    {
        // DEBUG
        System.out.println("GuiMysteriousStranger initGUI()");
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        buttonDone = new GuiButton(0, width / 2 + 2, 4 + bookImageHeight,
                98, 20, I18n.format("gui.done", new Object[0]));

        buttonList.add(buttonDone);
        int offsetFromScreenLeft = (width - bookImageWidth) / 2;
        buttonList.add(buttonNextPage = new GuiButton(1,
                offsetFromScreenLeft + 170, 156, 20, 20, ">>>"));
        buttonList.add(buttonPreviousPage = new GuiButton(2,
                offsetFromScreenLeft - 5, 156, 20, 20,  "<<<"));
    }

    @Override
    public void updateScreen()
    {
        buttonDone.visible = (currPage == bookTotalPages - 1);
        buttonNextPage.visible = (currPage < bookTotalPages - 1);
        buttonPreviousPage.visible = currPage > 0;
    }

    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (currPage == 0)
        {
            mc.getTextureManager().bindTexture(bookPageTextures);
        }
        else
        {
            mc.getTextureManager().bindTexture(bookPageTextures);
        }
        int offsetFromScreenLeft = (width - bookImageWidth ) / 2;
        drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth,
                bookImageHeight);
        int widthOfString;
        String stringPageIndicator = I18n.format("book.pageIndicator",
                new Object[] {Integer.valueOf(currPage + 1), bookTotalPages});
        widthOfString = fontRenderer.getStringWidth(stringPageIndicator);
        fontRenderer.drawString(stringPageIndicator,
                offsetFromScreenLeft - widthOfString + bookImageWidth - 44,
                18, 0);
        fontRenderer.drawSplitString(stringPageText[currPage],
                offsetFromScreenLeft + 36, 34, 116, 0);
        super.drawScreen(parWidth, parHeight, p_73863_3_);

    }
    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY,
                                  int parLastButtonClicked, long parTimeSinceMouseClick)
    {

    }

    @Override
    protected void actionPerformed(GuiButton parButton)
    {
        if (parButton == buttonDone)
        {
            // You can send a packet to server here if you need server to do
            // something
            mc.displayGuiScreen((GuiScreen)null);
        }
        else if (parButton == buttonNextPage)
        {
            if (currPage < bookTotalPages - 1)
            {
                ++currPage;
            }
        }
        else if (parButton == buttonPreviousPage)
        {
            if (currPage > 0)
            {
                --currPage;
            }
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    @Override
    public void onGuiClosed()
    {

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in
     * single-player
     */
    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }


    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton implements com.trustinlies.supernatural.util.objects.guis.NextPageButton {
        private final boolean isNextButton;

        public NextPageButton(int parButtonId, int parPosX, int parPosY,
                              boolean parIsNextButton) {
            super(parButtonId, parPosX, parPosY, 23, 13, ">>");
            isNextButton = parIsNextButton;
        }

        /**
         * Draws this button to the screen.
         */

        @Override
        public void drawButton(Minecraft mc, int parX, int parY) {
            if (visible) {
                boolean isButtonPressed = (parX >= x
                        && parY >= y
                        && parX < x + width
                        && parY < y + height);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(bookPageTextures);
                int textureX = 0;
                int textureY = 192;

                if (isButtonPressed) {
                    textureX += 23;
                }

                if (!isNextButton) {
                    textureY += 13;
                }

                drawTexturedModalRect(x, y,
                        textureX, textureY,
                        23, 13);
            }
        }
    }
}
