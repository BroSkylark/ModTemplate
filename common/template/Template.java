package template;

import java.io.File;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import template.CreativeTabs.CreativeTabGeneric;
import template.Handler.ConfigurationHandler;
import template.Handler.ForgeHooksHandler;
import template.Handler.GuiHandler;
import template.Handler.LocalizationHandler;
import template.Handler.PacketHandler;
import template.Handler.Addons.AddonHandler;
import template.Library.Reference;
import template.Proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(   modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.MOD_VERSION,
        dependencies = Reference.MOD_DEPENDENCIES)
@NetworkMod(channels = {Reference.MOD_CHANNEL}, 
            clientSideRequired = true, 
            serverSideRequired = false, 
            packetHandler = PacketHandler.class)
public class Template
{
    @Instance(Reference.MOD_ID)
    public static Template instance;
    
    @SidedProxy(clientSide = Reference.PROXY_CLIENT_CLASS, serverSide = Reference.PROXY_SERVER_CLASS)
    public static CommonProxy proxy;
    
    public static final CreativeTabs tabFurniture = new CreativeTabGeneric(CreativeTabs.getNextID(), 
            Reference.MOD_ID);
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        LocalizationHandler.execute();
        
        ConfigurationHandler.execute(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator
                + Reference.MOD_ID + File.separator + Reference.MOD_ID + ".cfg"));
        
        proxy.handleItems();
        proxy.handleBlocks();
        proxy.handleCrafting();
        
        AddonHandler.executePreInit();
    }
    
    @Init
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
        
        MinecraftForge.EVENT_BUS.register(new ForgeHooksHandler());
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        AddonHandler.executePostInit();
    }
}
