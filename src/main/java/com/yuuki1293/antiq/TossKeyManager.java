package com.yuuki1293.antiq;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TossKeyManager {
    public static KeyBinding keyBindDrop =
            Minecraft
                    .getMinecraft()
                    .gameSettings
                    .keyBindDrop;

    private static int q_count = 0;

    public static void init() {
        keyBindDrop
                .setKeyConflictContext(new IKeyConflictContext() {
                    @Override
                    public boolean isActive() {
                        return q_count >= 1;
                    }

                    @Override
                    public boolean conflicts(IKeyConflictContext other) {
                        return false;
                    }
                });
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END) && TossKeyManager.keyBindDrop.isPressed())
            TossKeyManager.click();
    }

    private static void click() {
        q_count++;
        if (q_count > 1)
            q_count = 0;
    }
}
