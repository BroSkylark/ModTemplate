package template.Handler;

import java.io.File;
import java.util.logging.Level;

import template.Library.Reference;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
    public static Configuration configuration;
    
    public static void execute(File file)
    {
        configuration = new Configuration(file);

        configuration.load();
        
        try
        {
        	// TODO Load IDs for blocks, items, etc...
//        	ItemIDs.ITEM_XXX_ID = configuration.getItem(Reference.ITEM_XXX_NAME, 
//                    ItemIDs.ITEM_BAG_ID_DEFAULT).getInt(ItemIDs.ITEM_XXX_ID_DEFAULT);
//            BlockIDs.BLOCK_XXX_ID = configuration.getBlock(Reference.BLOCK_XXX_NAME, 
//                    BlockIDs.BlOCK_XXX_ID_DEFAULT).getInt(BlockIDs.BlOCK_XXX_ID_DEFAULT);
        }
        catch(Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "Mod '%s' experienced problems while loading its configuration.", Reference.MOD_ID);
        }
        finally
        {
            configuration.save();
        }
    }


    public static void set(String categoryName, String propertyName, String newValue)
    {
        configuration.load();
        if(configuration.getCategoryNames().contains(categoryName))
        {
            if(configuration.getCategory(categoryName).containsKey(propertyName))
            {
                configuration.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        configuration.save();
    }
}
