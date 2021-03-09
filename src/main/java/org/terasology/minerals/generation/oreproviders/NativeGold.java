
package org.terasology.minerals.generation.oreproviders;

import org.terasology.engine.utilities.procedural.SimplexNoise;
import org.terasology.engine.world.generation.Border3D;
import org.terasology.engine.world.generation.Facet;
import org.terasology.engine.world.generation.GeneratingRegion;
import org.terasology.engine.world.generation.Produces;
import org.terasology.engine.world.generation.Requires;
import org.terasology.engine.world.generation.facets.ElevationFacet;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.minerals.generation.OreFacet;
import org.terasology.minerals.generation.OreProvider;

@RegisterPlugin
@Requires(@Facet(ElevationFacet.class))
@Produces(OreFacet.NativeGold.class)
public class NativeGold extends OreProvider {
    protected int index = 14;
    
    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(OreFacet.NativeGold.class);
        OreFacet facet = baseProcess(region, border, index);
        OreFacet.NativeGold classFacet = facet.new NativeGold(region.getRegion(), border);
        classFacet.set(facet.getInternal());
        region.setRegionFacet(OreFacet.NativeGold.class, classFacet);
    }

    @Override
    public void setSeed(long seed) {
        oreNoise = new SimplexNoise(seed + index*57);
    }
}
