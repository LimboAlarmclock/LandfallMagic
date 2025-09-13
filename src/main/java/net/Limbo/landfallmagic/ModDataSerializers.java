package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = landfallmagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataSerializers {

    // EntityDataSerializer implementation - only implement required methods
    public static final EntityDataSerializer<Spell> SPELL_SERIALIZER = new EntityDataSerializer<Spell>() {

        public void write(FriendlyByteBuf buf, Spell spell) {
            Spell.STREAM_CODEC.encode(buf, spell);
        }

        public Spell read(FriendlyByteBuf buf) {
            return Spell.STREAM_CODEC.decode(buf);
        }

        public StreamCodec<FriendlyByteBuf, Spell> codec() {
            return Spell.STREAM_CODEC;
        }

        public Spell copy(Spell spell) {
            return spell; // Records are immutable
        }
    };

    @SubscribeEvent
    public static void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey().equals(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS)) {
            landfallmagic.LOGGER.info("Registering EntityDataSerializer for Spell");
            event.register(NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS,
                    helper -> helper.register(landfallmagic.modLoc("spell"), SPELL_SERIALIZER));
        }
    }

    // Backup registration method - call this if the event-based registration doesn't work
    public static void register() {
        try {
            // Try to register with EntityDataSerializers directly
            EntityDataSerializers.registerSerializer(SPELL_SERIALIZER);
            landfallmagic.LOGGER.info("Successfully registered SPELL_SERIALIZER via direct registration");
        } catch (Exception e) {
            landfallmagic.LOGGER.error("Failed to register EntityDataSerializer: ", e);
        }
    }
}