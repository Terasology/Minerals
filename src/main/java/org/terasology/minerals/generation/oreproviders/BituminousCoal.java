
package org.terasology.minerals.generation.oreproviders;

import org.terasology.minerals.generation.OreFacet;
import org.terasology.minerals.generation.OreProvider;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.ElevationFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Requires(@Facet(ElevationFacet.class))
@Produces(OreFacet.BituminousCoal.class)
public class BituminousCoal extends OreProvider {
    protected int index = 2;
    
    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(OreFacet.BituminousCoal.class);
        OreFacet facet = baseProcess(region, border, index);
        OreFacet.BituminousCoal classFacet = facet.new BituminousCoal(region.getRegion(), border);
        classFacet.set(facet.getInternal());
        region.setRegionFacet(OreFacet.BituminousCoal.class, classFacet);
    }

    @Override
    public void setSeed(long seed) {
        oreNoise = new SimplexNoise(seed + index*57);
    }
}
