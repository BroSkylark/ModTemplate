package template.Proxy;

import net.minecraft.world.World;
import template.Handler.BlockHandler;
import template.Handler.ItemHandler;

public class CommonProxy
{
    public void handleBlocks()
    {
        BlockHandler.initBlocks();
        BlockHandler.registerBlocks();
        BlockHandler.registerTileEntities();
    }
    
    public void handleItems()
    {
        ItemHandler.initItem();
        ItemHandler.registerItem();
    }
    
    public void handleCrafting()
    {
        ItemHandler.registerRecipies();
        BlockHandler.registerRecipes();
    }
    
    public World getClientWorld()
    {
        return null;
    }
}
