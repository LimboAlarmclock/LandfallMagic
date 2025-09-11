package net.Limbo.landfallmagic.magic;

import net.Limbo.landfallmagic.landfallmagic;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, landfallmagic.MODID);

    // Using lambda to be explicit about which builder method to use
    public static final Supplier<AttachmentType<PlayerMagic>> PLAYER_MAGIC =
            ATTACHMENT_TYPES.register("player_magic", () -> AttachmentType.builder(() -> new PlayerMagic())
                    .serialize(PlayerMagic.CODEC)
                    .copyOnDeath()
                    .build());
}