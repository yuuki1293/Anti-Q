package com.yuuki1293.antiq;

import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvent {
    @SubscribeEvent
    public void onDrop(ItemTossEvent event){
        event.setCanceled(true);
    }
}
