package com.trustinlies.supernatural.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import java.util.StringTokenizer;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerItemRenderer(Item item, int meta, String id, String keyword)
    {
        String name = item.getRegistryName().toString();
        StringTokenizer tokens = new StringTokenizer(name, ":/");
        ModelResourceLocation location = new ModelResourceLocation(tokens.nextToken() + ":" + keyword + "/" + tokens.nextToken());
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, id));
    }

}
