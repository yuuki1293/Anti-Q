package com.yuuki1293.antiq.black;

import net.minecraft.inventory.ClickType;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Field;

public class ModifyPacket {
    protected static <E extends Enum<E>> E[] getEnumsArray(Class<E> ec) throws Exception{
        Field field = ec.getDeclaredField("$VALUES");
        field.setAccessible(true);
        return (E[]) field.get(ec);
    }

    public static void invalidThrow() {
        try {
            ClickType[] clockTypes = getEnumsArray(ClickType.class);
            clockTypes[4] = null;
            for(ClickType c: ClickType.values()){
                LogManager.getLogger().debug(c.name() + ":" + c.ordinal());
            }
        } catch (Exception e) {
            LogManager.getLogger().error(e);
        }
        LogManager.getLogger().debug("done invalidThrow");
    }

    public static void validityThrow() {
        try {
            ClickType[] clockTypes = getEnumsArray(ClickType.class);
            clockTypes[4] = ClickType.THROW;
        } catch (Exception ignored) {}
    }
}
