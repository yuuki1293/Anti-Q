package com.yuuki1293.antiq;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.settings.IKeyConflictContext;

public class TossKeyManager {
    private static int q_count = 0;

    public static void init() {
        Minecraft
                .getMinecraft()
                .gameSettings
                .keyBindDrop
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

    public static void click() {
        q_count++;
        if (q_count > 1)
            q_count = 0;
    }
}
