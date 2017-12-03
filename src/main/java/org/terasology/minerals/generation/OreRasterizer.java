
package org.terasology.minerals.generation;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.WorldRasterizerPlugin;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Requires(@Facet(SurfaceHeightFacet.class))
public class OreRasterizer implements WorldRasterizerPlugin {
    
    protected Block acanthite, bauxite, bituminousCoal, cassiterite, chalcopyrite, chlorargyrite, chrysocolla, coal, goethite,
            hematite, limonite, magnetite, nativeCopper, nativeElectrum, nativeGold, nativeSilver, pyrargyrite, rutile,
            siderite, stibnite, titanite;
    
    @Override
    public void initialize() {
        acanthite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:AcanthiteOre");
        bauxite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:BauxiteOre");
        bituminousCoal = CoreRegistry.get(BlockManager.class).getBlock("Minerals:BituminousCoalOre");
        cassiterite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:CassiteriteOre");
        chalcopyrite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:ChalcopyriteOre");
        chlorargyrite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:ChlorargyriteOre");
        chrysocolla = CoreRegistry.get(BlockManager.class).getBlock("Minerals:ChrysocollaOre");
        coal = CoreRegistry.get(BlockManager.class).getBlock("Minerals:CoalOre");
        goethite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:GoethiteOre");
        hematite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:HematiteOre");
        limonite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:LimoniteOre");
        magnetite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:MagnetiteOre");
        nativeCopper = CoreRegistry.get(BlockManager.class).getBlock("Minerals:NativeCopperOre");
        nativeElectrum = CoreRegistry.get(BlockManager.class).getBlock("Minerals:NativeElectrumOre");
        nativeGold = CoreRegistry.get(BlockManager.class).getBlock("Minerals:NativeGoldOre");
        nativeSilver = CoreRegistry.get(BlockManager.class).getBlock("Minerals:NativeSilverOre");
        pyrargyrite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:PyrargyriteOre");
        rutile = CoreRegistry.get(BlockManager.class).getBlock("Minerals:RutileOre");
        siderite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:SideriteOre");
        stibnite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:StibniteOre");
        titanite = CoreRegistry.get(BlockManager.class).getBlock("Minerals:TitaniteOre");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        generateChunk(chunk, chunkRegion, acanthite, chunkRegion.getFacet( OreFacet.Acanthite.class));
        generateChunk(chunk, chunkRegion, bauxite, chunkRegion.getFacet(OreFacet.Bauxite.class));
        generateChunk(chunk, chunkRegion, bituminousCoal, chunkRegion.getFacet(OreFacet.BituminousCoal.class));
        generateChunk(chunk, chunkRegion, cassiterite, chunkRegion.getFacet(OreFacet.Cassiterite.class));
        generateChunk(chunk, chunkRegion, chalcopyrite, chunkRegion.getFacet(OreFacet.Chalcopyrite.class));
        generateChunk(chunk, chunkRegion, chlorargyrite, chunkRegion.getFacet(OreFacet.Chlorargyrite.class));
        generateChunk(chunk, chunkRegion, chrysocolla, chunkRegion.getFacet(OreFacet.Chrysocolla.class));
        generateChunk(chunk, chunkRegion, coal, chunkRegion.getFacet(OreFacet.Coal.class));
        generateChunk(chunk, chunkRegion, goethite, chunkRegion.getFacet(OreFacet.Goethite.class));
        generateChunk(chunk, chunkRegion, hematite, chunkRegion.getFacet(OreFacet.Hematite.class));
        generateChunk(chunk, chunkRegion, limonite, chunkRegion.getFacet(OreFacet.Limonite.class));
        generateChunk(chunk, chunkRegion, magnetite, chunkRegion.getFacet(OreFacet.Magnetite.class));
        generateChunk(chunk, chunkRegion, nativeCopper, chunkRegion.getFacet(OreFacet.NativeCopper.class));
        generateChunk(chunk, chunkRegion, nativeElectrum, chunkRegion.getFacet(OreFacet.NativeElectrum.class));
        generateChunk(chunk, chunkRegion, nativeGold, chunkRegion.getFacet(OreFacet.NativeGold.class));
        generateChunk(chunk, chunkRegion, nativeSilver, chunkRegion.getFacet(OreFacet.NativeSilver.class));
        generateChunk(chunk, chunkRegion, pyrargyrite, chunkRegion.getFacet(OreFacet.Pyrargyrite.class));
        generateChunk(chunk, chunkRegion, rutile, chunkRegion.getFacet(OreFacet.Rutile.class));
        generateChunk(chunk, chunkRegion, siderite, chunkRegion.getFacet(OreFacet.Siderite.class));
        generateChunk(chunk, chunkRegion, stibnite, chunkRegion.getFacet(OreFacet.Stibnite.class));
        generateChunk(chunk, chunkRegion, titanite, chunkRegion.getFacet(OreFacet.Titanite.class));
    }
    
    protected void generateChunk(CoreChunk chunk, Region chunkRegion, Block ore, OreFacet oreFacet) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        
        for(Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            int veinSize = (int)oreFacet.getWorld(position);
            
            switch(veinSize) {
                case(1):
                    chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                break;
                
                case(2): {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, -1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                }
                break;
                
                case(3): {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, -1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, 0, -1).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                }
                break;
                
                case(4): {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, -1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, 0, -1).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, 1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                }
                break;
                
                case(5): {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, -1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, 0, -1).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(0, 1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                    if(position.add(1, -1, 0).y<surfaceHeight-5 && chunkRegion.getRegion().encompasses(position))
                        chunk.setBlock(ChunkMath.calcBlockPos(position), ore);
                }
                break;
                
                default: break;
            }
        }
    }
}
