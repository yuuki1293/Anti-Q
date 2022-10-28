package com.yuuki1293.antiq;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.yuuki1293.antiq.AntiQ.*;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class AntiQ {
    public static final String MODID = "antiq";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
