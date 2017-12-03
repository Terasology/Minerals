
package org.terasology.minerals.generation;

import org.terasology.math.geom.Vector3i;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

@Requires(@Facet(SurfaceHeightFacet.class))
public abstract class OreProvider implements FacetProviderPlugin {
    
    protected Noise oreNoise;
    
    //Index of each ore in arrays and used for noise generation to avoid all of them overlapping
    protected int index;
    
    //These values are placed here at the beginning so that they can be adjusted easily:
    //Change these values to adjust rarities: Higher value = more rare, 1 = ore never generates
    final protected float[] RARITY = {
            0.8f,   //Acanthite
            0.8f,   //Bauxite
            0.8f,   //BituminousCoal
            0.8f,   //Cassiterite
            0.8f,   //Chalcopyrite
            0.8f,   //Chlorargyrite
            0.8f,   //Chrysocolla
            0.8f,   //Coal
            0.8f,   //Goethite
            0.8f,   //Hematite
            0.8f,   //Limonite
            0.8f,   //Magnetite
            0.8f,   //NativeCopper
            0.8f,   //NativeElectrum
            0.8f,   //NativeGold
            0.8f,   //NativeSilver
            0.8f,   //Pyrargyrite
            0.8f,   //Rutile
            0.8f,   //Siderite
            0.8f,   //Stibnite
            0.8f    //Titanite
    };
    
    //Maximum y value the ore should generate at
    final protected int[] MAX_HEIGHT = {
            -5,   //Acanthite
            -5,   //Bauxite
            -5,   //BituminousCoal
            -5,   //Cassiterite
            -5,   //Chalcopyrite
            -5,   //Chlorargyrite
            -5,   //Chrysocolla
            -5,   //Coal
            -5,   //Goethite
            -5,   //Hematite
            -5,   //Limonite
            -5,   //Magnetite
            -5,   //NativeCopper
            -5,   //NativeElectrum
            -5,   //NativeGold
            -5,   //NativeSilver
            -5,   //Pyrargyrite
            -5,   //Rutile
            -5,   //Siderite
            -5,   //Stibnite
            -5    //Titanite
    };
    
    //Maximum size of each ore vein
    final protected int[] MAX_SIZE = {
            5,   //Acanthite
            5,   //Bauxite
            5,   //BituminousCoal
            5,   //Cassiterite
            5,   //Chalcopyrite
            5,   //Chlorargyrite
            5,   //Chrysocolla
            5,   //Coal
            5,   //Goethite
            5,   //Hematite
            5,   //Limonite
            5,   //Magnetite
            5,   //NativeCopper
            5,   //NativeElectrum
            5,   //NativeGold
            5,   //NativeSilver
            5,   //Pyrargyrite
            5,   //Rutile
            5,   //Siderite
            5,   //Stibnite
            5    //Titanite
    };

    @Override
    public void setSeed(long seed) {
        oreNoise = new SimplexNoise(seed + index);
    }
    
    protected OreFacet baseProcess(GeneratingRegion region, Border3D border, int index) {

        OreFacet facet = new OreFacet(region.getRegion(), border);
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        
        for (Vector3i block : region.getRegion())
            if(block.y < MAX_HEIGHT[index] && block.y < surfaceHeightFacet.getWorld(block.x, block.z)-5) {
                float noiseLevel = oreNoise.noise(block.x, block.y, block.z);
                if(noiseLevel <= RARITY[index])
                    facet.setWorld(block, 0);
                else {
                    float subNoiseLevel = (1-noiseLevel) / (1-RARITY[index]);
                    float interval = subNoiseLevel / MAX_SIZE[index];
                    for(int i=MAX_SIZE[index]; i>0; i--)
                        if(subNoiseLevel > interval*(i-1)) {
                            facet.setWorld(block, i);
                            break;
                        }
                }
            }
        
        return facet;
    }
}
