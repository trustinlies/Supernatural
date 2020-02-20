package com.trustinlies.supernatural.util.handlers;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.init.BlockInit;
import com.trustinlies.supernatural.init.ItemInit;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.interfaces.IHasModel;
import com.trustinlies.supernatural.util.objects.blocks.essencewell.TileEntityEssenceWell;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler {

    private static final ResourceLocation essenceWellGuiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/essence_well.png");

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel){
                ((IHasModel)block).registerModels();
            }
        }

    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event){

        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        GameRegistry.registerTileEntity(TileEntityEssenceWell.class, essenceWellGuiTextures);

    }

    public static void preInitRegistries()
    {

    }

    public static void initRegistries()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());

    }

    public static void postInitRegistries()
    {

    }

    public static void serverRegistries(FMLServerStartingEvent event)
    {

    }

}
