package net.Limbo.landfallmagic.spell;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;

import java.util.Optional;

public class Spell {

    public final SpellForm form;
    public final SpellElement primaryElement;
    public final SpellElement augmentElement;
    public final String name;

    // CORRECTED CODEC
    public static final Codec<Spell> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.xmap(SpellForm::valueOf, SpellForm::name).fieldOf("form").forGetter(spell -> spell.form),
            Codec.STRING.xmap(SpellElement::valueOf, SpellElement::name).fieldOf("primaryElement").forGetter(spell -> spell.primaryElement),
            Codec.STRING.xmap(SpellElement::valueOf, SpellElement::name).optionalFieldOf("augmentElement").forGetter(spell -> Optional.ofNullable(spell.augmentElement)),
            Codec.STRING.fieldOf("name").forGetter(spell -> spell.name)
    ).apply(instance, (form, primary, augment, name) -> new Spell(form, primary, augment.orElse(null), name)));

    // ADD THIS CONSTRUCTOR BACK - For Tier 1 Spells
    public Spell(SpellForm form, SpellElement primaryElement, String name) {
        this(form, primaryElement, null, name);
    }

    // Full constructor for Augmented Spells (Tier 2)
    public Spell(SpellForm form, SpellElement primaryElement, SpellElement augmentElement, String name) {
        this.form = form;
        this.primaryElement = primaryElement;
        this.augmentElement = augmentElement;
        this.name = name;
    }

    public boolean isAugmented() {
        return augmentElement != null;
    }

    public Component getDisplayName() {
        return Component.literal(name);
    }
}