package com.yuuki1293.antiq;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

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

    @SideOnly(Side.CLIENT)
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
    @SideOnly(Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.START)) {
            if (checkInventory()) {
                flag = true;
                q_count = 0;
                before_keydown = false;
            } else {
                flag = true;

                boolean keydown = TossKeyManager.keyBindDrop.isKeyDown();
                if (keydown && !before_keydown)
                    TossKeyManager.click();

                flag = false;
                before_keydown = keydown;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private static void click() {
        q_count++;
        if (q_count > 1)
            q_count = 0;
        LOGGER.debug("q_count:" + q_count + " before_keydown:" + before_keydown);
    }

    @SideOnly(Side.CLIENT)
    private static boolean checkInventory() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        String[] items = {"enchanted_golden_apple", "diamond_pickaxe", "diamond_shovel"};

        InventoryPlayer inv = player.inventory;

        if (inv != null) {
            ItemStack current = inv.getCurrentItem();
            return Arrays.stream(items)
                    .map(Item::getByNameOrId)
                    .noneMatch(item ->
                            current.getItem().equals(item)
                    );
        } else return false;
    }
}
