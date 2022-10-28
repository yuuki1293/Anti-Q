package com.yuuki1293.antiq;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.yuuki1293.antiq.AntiQ.*;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class AntiQ {
    public static final String MODID = "antiq";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    public static final String CLIENT_PROXY = "com.yuuki1293." + MODID + ".proxy.ClientProxy";

    @SidedProxy(clientSide = CLIENT_PROXY)
    public static ClientProxy proxy;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
    }


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
