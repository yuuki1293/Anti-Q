package com.yuuki1293.antiq.proxy;

import com.yuuki1293.antiq.ClientEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    boolean active_q = false;

    public void preInit(FMLPreInitializationEvent event) {
        Minecraft
                .getMinecraft()
                .gameSettings
                .keyBindDrop
                .setKeyConflictContext(new IKeyConflictContext() {
                    @Override
                    public boolean isActive() {
                        return active_q;
                    }

                    @Override
                    public boolean conflicts(IKeyConflictContext other) {
                        return false;
                    }
                });
        MinecraftForge.EVENT_BUS.register(new ClientEvent());
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}