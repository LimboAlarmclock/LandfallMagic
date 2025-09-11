package net.Limbo.landfallmagic.magic;

import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = landfallmagic.MODID)
public class MagicEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerMagicProvider.PLAYER_MAGIC).isPresent()) {
                // The player does not already have this capability, so attach it
                event.addCapability(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "magic_school"), new PlayerMagicProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IPlayerMagic.class);
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            // We are respawning, so copy the capability from the old player to the new one
            event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(newStore -> {
                    newStore.deserializeNBT(oldStore.serializeNBT());
                });
            });
        }
    }
}