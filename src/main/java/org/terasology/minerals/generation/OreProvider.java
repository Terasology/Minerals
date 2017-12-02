
package org.terasology.minerals.generation;

import org.terasology.math.geom.Vector3i;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@Requires(@Facet(SurfaceHeightFacet.class))
public abstract class OreProvider implements FacetProviderPlugin {
    
    protected Noise oreNoise;
    
    //Index of each ore in arrays and used for noise generation to avoid all of them overlapping
    protected int index;
    
    //These values are placed here at the beginning so that they can be adjusted easily:
    //Change these values to adjust rarities: Higher value = more rare, 1 = ore never generates
    final protected float[] RARITY = {
            0.995f,   //Acanthite
            0.995f,   //Bauxite
            0.995f,   //BituminousCoal
            0.995f,   //Cassiterite
            0.995f,   //Chalcopyrite
            0.995f,   //Chlorargyrite
            0.995f,   //Chrysocolla
            0.995f,   //Coal
            0.995f,   //Goethite
            0.995f,   //Hematite
            0.995f,   //Limonite
            0.995f,   //Magnetite
            0.995f,   //NativeCopper
            0.995f,   //NativeElectrum
            0.995f,   //NativeGold
            0.995f,   //NativeSilver
            0.995f,   //Pyrargyrite
            0.995f,   //Rutile
            0.995f,   //Siderite
            0.995f,   //Stibnite
            0.995f    //Titanite
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
        oreNoise = new SimplexNoise(seed+index);
    }
    
    protected OreFacet baseProcess(GeneratingRegion region) {

        Border3D border = region.getBorderForFacet(OreFacet.class);
        OreFacet facet = new OreFacet(region.getRegion(), border);
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        
        for (Vector3i block : region.getRegion())
            if(block.y < MAX_HEIGHT[index] && block.y < surfaceHeightFacet.getWorld(block.x, block.z)) {
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
    
    @RegisterPlugin
    @Produces(OreFacet.Acanthite.class)
    public class Acanthite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Acanthite.class, (OreFacet.Acanthite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Bauxite.class)
    public class Bauxite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Bauxite.class, (OreFacet.Bauxite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.BituminousCoal.class)
    public class BituminousCoal extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.BituminousCoal.class, (OreFacet.BituminousCoal)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Cassiterite.class)
    public class Cassiterite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Cassiterite.class, (OreFacet.Cassiterite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Chalcopyrite.class)
    public class Chalcopyrite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Chalcopyrite.class, (OreFacet.Chalcopyrite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Chlorargyrite.class)
    public class Chlorargyrite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Chlorargyrite.class, (OreFacet.Chlorargyrite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Chrysocolla.class)
    public class Chrysocolla extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Chrysocolla.class, (OreFacet.Chrysocolla)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Coal.class)
    public class Coal extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Coal.class, (OreFacet.Coal)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Goethite.class)
    public class Goethite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Goethite.class, (OreFacet.Goethite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Hematite.class)
    public class Hematite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Hematite.class, (OreFacet.Hematite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Limonite.class)
    public class Limonite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Limonite.class, (OreFacet.Limonite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Magnetite.class)
    public class Magnetite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Magnetite.class, (OreFacet.Magnetite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.NativeCopper.class)
    public class NativeCopper extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.NativeCopper.class, (OreFacet.NativeCopper)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.NativeElectrum.class)
    public class NativeElectrum extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.NativeElectrum.class, (OreFacet.NativeElectrum)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.NativeGold.class)
    public class NativeGold extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.NativeGold.class, (OreFacet.NativeGold)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.NativeSilver.class)
    public class NativeSilver extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.NativeSilver.class, (OreFacet.NativeSilver)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Pyrargyrite.class)
    public class Pyrargyrite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Pyrargyrite.class, (OreFacet.Pyrargyrite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Rutile.class)
    public class Rutile extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Rutile.class, (OreFacet.Rutile)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Siderite.class)
    public class Siderite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Siderite.class, (OreFacet.Siderite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Stibnite.class)
    public class Stibnite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Stibnite.class, (OreFacet.Stibnite)baseProcess(region));
        }
    }
    
    @RegisterPlugin
    @Produces(OreFacet.Titanite.class)
    public class Titanite extends OreProvider {
        protected int index = 0;

        @Override
        public void process(GeneratingRegion region) {
            region.setRegionFacet(OreFacet.Titanite.class, (OreFacet.Titanite)baseProcess(region));
        }
    }
}
