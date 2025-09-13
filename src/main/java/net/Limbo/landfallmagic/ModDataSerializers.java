package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

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


    // Simple registration method
    public static void register() {
        try {
            // Try to register with EntityDataSerializers
            EntityDataSerializers.registerSerializer(SPELL_SERIALIZER);
        } catch (Exception e) {
            // If registration fails, just log and continue - static access will still work
            System.out.println("EntityDataSerializer registration method not available - using static access only");
        }
    }
}