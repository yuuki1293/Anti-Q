package com.yuuki1293.antiq;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = AntiQ.MODID)
public class TossKeyManager {
    static Logger LOGGER = LogManager.getLogger();

    public static KeyBinding keyBindDrop =
            Minecraft
                    .getMinecraft()
                    .gameSettings
                    .keyBindDrop;

    private static int q_count = 0;
    private static boolean flag = false;
    private static boolean before_keydown = false;

    public TossKeyManager() {
    }

    public static void init() {
        keyBindDrop
                .setKeyConflictContext(new IKeyConflictContext() {
                    @Override
                    public boolean isActive() {
                        return q_count >= 1 || flag;
                    }

                    @Override
                    public boolean conflicts(IKeyConflictContext other) {
                        return false;
                    }
                });
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        flag = true;

        boolean keydown = TossKeyManager.keyBindDrop.isKeyDown();
        if (event.phase.equals(TickEvent.Phase.END) && keydown && !before_keydown)
            TossKeyManager.click();

        flag = false;
        before_keydown = keydown;
    }

    private static void click() {
        q_count++;
        if (q_count > 1)
            q_count = 0;
        LOGGER.debug("q_count:" + q_count + " before_keydown:" + before_keydown);
    }
}
