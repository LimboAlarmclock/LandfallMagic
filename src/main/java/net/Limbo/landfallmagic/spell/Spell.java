package net.Limbo.landfallmagic.spell;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record Spell(SpellForm form, SpellElement primaryElement, SpellElement augmentElement, String name) {

    public static final Codec<Spell> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.xmap(SpellForm::valueOf, SpellForm::name).fieldOf("form").forGetter(Spell::form),
            Codec.STRING.xmap(SpellElement::valueOf, SpellElement::name).fieldOf("primaryElement").forGetter(Spell::primaryElement),
            Codec.STRING.xmap(SpellElement::valueOf, SpellElement::name).optionalFieldOf("augmentElement").forGetter(spell -> Optional.ofNullable(spell.augmentElement)),
            Codec.STRING.fieldOf("name").forGetter(Spell::name)
    ).apply(instance, (form, primary, augment, name) -> new Spell(form, primary, augment.orElse(null), name)));

    // THIS IS THE CORRECTED CODEC
    // We manually define how to read/write each part of the spell to the network buffer.
    public static final StreamCodec<FriendlyByteBuf, Spell> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8.map(SpellForm::valueOf, SpellForm::name),
            Spell::form,
            ByteBufCodecs.STRING_UTF8.map(SpellElement::valueOf, SpellElement::name),
            Spell::primaryElement,
            ByteBufCodecs.optional(ByteBufCodecs.STRING_UTF8.map(SpellElement::valueOf, SpellElement::name)),
            spell -> Optional.ofNullable(spell.augmentElement),
            ByteBufCodecs.STRING_UTF8,
            Spell::name,
            (form, primary, augment, name) -> new Spell(form, primary, augment.orElse(null), name)
    );

    public Spell(SpellForm form, SpellElement primaryElement, String name) {
        this(form, primaryElement, null, name);
    }

    public boolean isAugmented() {
        return augmentElement != null;
    }

    public Component getDisplayName() {
        return Component.literal(name);
    }
}