
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
@Produces(OreFacet.Rutile.class)
public class Rutile extends OreProvider {
    protected int index = 17;
    
    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(OreFacet.Rutile.class);
        OreFacet facet = baseProcess(region, border, index);
        OreFacet.Rutile classFacet = facet.new Rutile(region.getRegion(), border);
        classFacet.set(facet.getInternal());
        region.setRegionFacet(OreFacet.Rutile.class, classFacet);
    }

    @Override
    public void setSeed(long seed) {
        oreNoise = new SimplexNoise(seed + index*57);
    }
}
