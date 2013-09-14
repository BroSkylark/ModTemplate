package template.Packets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public abstract class PacketGeneric
{
    private static final Map<Integer, Class<? extends PacketGeneric>> nameToClass;
    private static final Map<Class<? extends PacketGeneric>, Integer> classToName;
    public final boolean isChunkDataPacket;

    public PacketGeneric() { this(false); }
    public PacketGeneric(boolean isChunkDataPacket)
    {
        this.isChunkDataPacket = isChunkDataPacket;
    }
    
    public abstract void execute(INetworkManager manager, Player player);

    public byte[] populate()
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try
        {
            dos.writeByte(getID(this));
            
            writeData(dos);
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }

        return bos.toByteArray();
    }

    public static PacketGeneric reconstruct(byte[] data)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream dis = null;
        PacketGeneric p = null;
        
        try
        {
            p = getInstance(bais.read());
            dis = new DataInputStream(bais);
            
            p.readData(dis);
        }
        catch(IOException e)
        {
            e.printStackTrace(System.err);
        }
        finally
        {
            if(dis != null)
            {
                try {dis.close();} catch(Exception e) {}
            }
        }
        
        return p;
    }

    public void readData(DataInputStream data) throws IOException
    {
    }

    public void writeData(DataOutputStream dos) throws IOException
    {
    }

    @SuppressWarnings("unused")
    private static void addMapping(Class<? extends PacketGeneric> c, int i)
    {
        nameToClass.put(Integer.valueOf(i), c);
        classToName.put(c, Integer.valueOf(i));
    }

    private static <T extends PacketGeneric> int getID(T t) { return getID(t.getClass()); }
    public static int getID(Class<? extends PacketGeneric> c)
    { 
        if(classToName.containsKey(c))
        {
            return classToName.get(c).intValue();
        }
        else
        {
            (new IllegalArgumentException("ERR: Not registered PacketGeneric-class!")).printStackTrace(System.err);

            return -1;
        }
    }

    public static PacketGeneric getInstance(int id)
    {
        try
        {
            return getClass(id).newInstance();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public static Class<? extends PacketGeneric> getClass(int id)
    {
        if(nameToClass.containsKey(Integer.valueOf(id)))
        {
            return nameToClass.get(Integer.valueOf(id));
        }
        else
        {
            (new IllegalArgumentException("ERR: Invalid PacketGeneric-ID! (" + id + ")")).printStackTrace(System.err);
            return null;
        }
    }

    static
    {
        nameToClass = new HashMap<Integer, Class<? extends PacketGeneric>>();
        classToName = new HashMap<Class<? extends PacketGeneric>, Integer>();
    }
}
