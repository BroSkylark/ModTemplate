package template.Handler.Addons;

public abstract class AddonHandler
{
    protected AddonHandler() {}
    
    public abstract void handlePreInit();
    public abstract void handlePostInit();
    
    public static final void executePreInit()
    {
//        if(Loader.isModLoaded(Reference.ADDON_MOD_ID)) MODHandler.instance.handlePreInit();
    }
    
    public static final void executePostInit()
    {
//        if(Loader.isModLoaded(Reference.ADDON_MOD_ID)) MODHandler.instance.handlePostInit();
    }
}
