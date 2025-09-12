package net.Limbo.landfallmagic.spell;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;

import java.util.Optional;

public record Spell(SpellForm form, SpellElement primaryElement, SpellElement augmentElement, String name) {

    public static final Codec<Spell> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.xmap(SpellForm::valueOf, SpellForm::name).fieldOf("form").forGetter(Spell::form),
            Codec.STRING.xmap(SpellElement::valueOf, SpellElement::name).fieldOf("primaryElement").forGetter(Spell::primaryElement),
            Codec.STRING.xmap(SpellElement::valueOf, SpellElement::name).optionalFieldOf("augmentElement").forGetter(spell -> Optional.ofNullable(spell.augmentElement)),
            Codec.STRING.fieldOf("name").forGetter(Spell::name)
    ).apply(instance, (form, primary, augment, name) -> new Spell(form, primary, augment.orElse(null), name)));

    // Constructor for Tier 1 Spells (without augment)
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