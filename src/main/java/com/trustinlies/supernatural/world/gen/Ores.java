package com.trustinlies.supernatural.world.gen;

import com.google.common.base.Predicate;
import com.trustinlies.supernatural.init.BlockInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import java.util.Random;

public class Ores implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){

        switch(world.provider.getDimension()) {
            //Nether
            case -1:
                generateOre(BlockInit.BASALT.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 0, 165, random.nextInt(10) + 10, 45, BlockMatcher.forBlock(Blocks.NETHERRACK));
                break;
            //Overworld
            case 0:
                generateOre(BlockInit.ORE_CINNABAR.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 0, 10, random.nextInt(2) + 2, 10, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.ORE_COPPER.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 10, 120, random.nextInt(7) + 4, 45, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.ORE_SILVER.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 0, 45, random.nextInt(4) + 3, 15, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.ORE_LEAD.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 20, 60, random.nextInt(4) + 3, 20, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.ORE_TIN.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 25, 100, random.nextInt(5) + 4, 25, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.ORE_ZINC.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 25, 100, random.nextInt(5) + 4, 25, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.SLATE.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 21, 165, random.nextInt(10) + 10, 45, BlockMatcher.forBlock(Blocks.STONE));
                generateOre(BlockInit.QUARTZITE.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 0, 20, random.nextInt(10) + 10, 25, BlockMatcher.forBlock(Blocks.STONE));
                break;
            //End
            case 1:
                break;
            //Everything else
            default:
                break;
        }

    }

   private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances, Predicate<IBlockState> blockToReplace) {

        int deltaY = maxY - minY;

        for(int i = 0; i < chances; i++){

            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size, blockToReplace);
            generator.generate(world, random, pos);
        }

    }

}
