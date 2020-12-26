
package org.terasology.minerals.generation;

import org.joml.Vector3ic;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.facets.ElevationFacet;

public abstract class OreProvider implements FacetProviderPlugin {
    
    protected Noise oreNoise;
    
    //Index of each ore in arrays and used for noise generation to avoid all of them overlapping
    protected int index;
    
    //These values are placed here at the beginning so that they can be adjusted easily:
    //Change these values to adjust rarities: Higher value = more rare, 1 = ore never generates
    final protected float[] RARITY = {
            0.9f,   //Acanthite
            0.8f,   //Bauxite
            0.7f,   //BituminousCoal
            0.6f,   //Cassiterite
            0.5f,   //Chalcopyrite
            0.4f,   //Chlorargyrite
            0.3f,   //Chrysocolla
            0.2f,   //CoalOre
            0.8f,   //Goethite
            0.9f,   //Hematite
            0.8f,   //Limonite
            0.7f,   //Magnetite
            0.6f,   //NativeCopper
            0.5f,   //NativeElectrum
            0.4f,   //NativeGold
            0.3f,   //NativeSilver
            0.2f,   //Pyrargyrite
            0.9f,   //Rutile
            0.8f,   //Siderite
            0.7f,   //Stibnite
            0.6f,    //Titanite
    };
    
    //Maximum y value the ore should generate at
    final protected int[] MAX_HEIGHT = new int[]{
            -305,   //Acanthite
            -65,    //Bauxite
            -165,   //BituminousCoal
            -105,   //Cassiterite
            -85,    //Chalcopyrite
            -225,   //Chlorargyrite
            -165,   //Chrysocolla
            -5,     //CoalOre
            -25,    //Goethite
            -145,   //Hematite
            -185,   //Limonite
            -325,   //Magnetite
            -205,   //NativeCopper
            -345,   //NativeElectrum
            -45,    //NativeGold
            -125,   //NativeSilver
            -265,   //Pyrargyrite
            -365,   //Rutile
            -345,   //Siderite
            -285,   //Stibnite
            -385    //Titanite
    };
    
    //Maximum size of each ore vein
    final protected int[] MAX_SIZE = {
            3,   //Acanthite
            2,   //Bauxite
            1,   //BituminousCoal
            4,   //Cassiterite
            5,   //Chalcopyrite
            3,   //Chlorargyrite
            2,   //Chrysocolla
            1,   //CoalOre
            4,   //Goethite
            5,   //Hematite
            3,   //Limonite
            2,   //Magnetite
            1,   //NativeCopper
            4,   //NativeElectrum
            5,   //NativeGold
            3,   //NativeSilver
            2,   //Pyrargyrite
            1,   //Rutile
            4,   //Siderite
            5,   //Stibnite
            3    //Titanite
    };

    @Override
    public void setSeed(long seed) {
        oreNoise = new SimplexNoise(seed + index);
    }
    
    protected OreFacet baseProcess(GeneratingRegion region, Border3D border, int index) {

        OreFacet facet = new OreFacet(region.getRegion(), border);
        ElevationFacet elevationFacet = region.getRegionFacet(ElevationFacet.class);
        
        for (Vector3ic block : region.getRegion())
            if(block.y() < MAX_HEIGHT[index] && block.y() < elevationFacet.getWorld(block.x(), block.z())-5) {
                float noiseLevel = oreNoise.noise(block.x(), block.y(), block.z());
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
