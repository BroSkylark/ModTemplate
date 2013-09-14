package template.Library;

public final class Reference
{
    public static final String MOD_ID = "template";
    public static final String MOD_NAME = "Mod Template";
    public static final String MOD_VERSION = "a0.0";
    public static final String MOD_DEPENDENCIES = "required-after:Forge@[7.8.0.703,)";
    public static final String MOD_CHANNEL = MOD_ID;

    public static final String PROXY_SERVER_CLASS = "template.Proxy.CommonProxy";
    public static final String PROXY_CLIENT_CLASS = "template.Proxy.ClientProxy";
    
    public static final int FLAG_BLOCKSET_NONE = 0;
    public static final int FLAG_BLOCKSET_UPDATEBLOCK = 1;
    public static final int FLAG_BLOCKSET_UPDATECLIENTS = 2;
    public static final int FLAG_BLOCKSET_PREVENTRERENDER = 4;
    
    public static final String getIconID(String blockName) { return MOD_ID.toLowerCase() + ":" + blockName.toLowerCase(); }
    
    private Reference() { }
}
