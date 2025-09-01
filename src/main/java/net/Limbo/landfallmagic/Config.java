package net.Limbo.landfallmagic;

import java.util.List;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.BuiltInRegistries;

public class Config {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // --- Karma System Settings ---
    static {
        BUILDER.push("karma_system");

        // Node behavior settings
        NODE_TICK_DELAY = BUILDER
                .comment("The delay in ticks between each karma spread from a node (20 ticks = 1 second).")
                .defineInRange("nodeTickDelay", 20, 1, Integer.MAX_VALUE);

        NODE_SPREAD_AMOUNT = BUILDER
                .comment("The amount of karma a node generates and spreads each tick.")
                .defineInRange("nodeSpreadAmount", 5, 1, 1000);

        NODE_SPREAD_RADIUS = BUILDER
                .comment("The radius in chunks that karma spreads from a node (e.g., 5 means an 11x11 chunk area).")
                .defineInRange("nodeSpreadRadius", 5, 0, 16);

        // Karma value settings
        MAX_KARMA_VALUE = BUILDER
                .comment("The maximum karma value that can be stored in a single chunk.")
                .defineInRange("maxKarmaValue", 1000, 100, 10000);

        // Particle generation settings
        ENABLE_KARMA_PARTICLES = BUILDER
                .comment("Enable or disable particle effects from karma nodes.")
                .define("enableKarmaParticles", true);

        PARTICLE_INTENSITY_MULTIPLIER = BUILDER
                .comment("Multiplier for particle generation intensity (higher = more particles).")
                .defineInRange("particleIntensityMultiplier", 1.0, 0.0, 5.0);

        // Karma reset settings
        ENABLE_KARMA_RESET_COMMAND = BUILDER
                .comment("Enable admin commands for resetting karma in chunks or globally.")
                .define("enableKarmaResetCommand", true);

        AUTO_KARMA_RESET_THRESHOLD = BUILDER
                .comment("Automatically reset karma in a chunk when it exceeds this value (0 = disabled).")
                .defineInRange("autoKarmaResetThreshold", 0, 0, 50000);

        BUILDER.pop();
    }

    // --- Holographic GUI Settings ---
    static {
        BUILDER.push("holographic_gui");

        // Hologram visual settings
        HOLOGRAM_STABILITY_TIME = BUILDER
                .comment("Time in milliseconds for holographic interfaces to fully stabilize (default: 2000ms = 2 seconds).")
                .defineInRange("hologramStabilityTime", 2000, 500, 10000);

        HOLOGRAM_PARTICLE_COUNT = BUILDER
                .comment("Number of particles to maintain around holographic interfaces.")
                .defineInRange("hologramParticleCount", 30, 0, 100);

        HOLOGRAM_FLICKER_INTENSITY = BUILDER
                .comment("Intensity of holographic flickering effect (0.0 = no flicker, 1.0 = maximum flicker).")
                .defineInRange("hologramFlickerIntensity", 0.0, 0.0, 1.0);

        ENABLE_HOLOGRAM_SOUND = BUILDER
                .comment("Enable sound effects when holographic interfaces activate.")
                .define("enableHologramSound", true);

        ENABLE_SCANNING_LINES = BUILDER
                .comment("Enable animated scanning lines on holographic borders.")
                .define("enableScanningLines", true);

        HOLOGRAM_ALPHA_LEVEL = BUILDER
                .comment("Base transparency level for holographic interfaces (0-255).")
                .defineInRange("hologramAlphaLevel", 255, 50, 255);

        // Research Table specific settings
        RESEARCH_TABLE_KARMA_REQUIREMENT = BUILDER
                .comment("Minimum karma level required to maintain Research Table interface.")
                .defineInRange("researchTableKarmaRequirement", 100, 0, 1000);

        ENABLE_KARMA_FLUCTUATION = BUILDER
                .comment("Enable fluctuating karma readings in Research Table interface.")
                .define("enableKarmaFluctuation", true);

        KARMA_FLUCTUATION_RANGE = BUILDER
                .comment("Range of karma fluctuation in interface readings (+/- this value).")
                .defineInRange("karmaFluctuationRange", 10, 0, 100);

        // Grimoire specific settings
        GRIMOIRE_REQUIRES_OPEN = BUILDER
                .comment("Require the Grimoire book to be physically opened before accessing the interface.")
                .define("grimoireRequiresOpen", true);

        ENABLE_FLOATING_RUNES = BUILDER
                .comment("Enable floating runic particles around the Grimoire interface.")
                .define("enableFloatingRunes", true);

        RUNE_ORBIT_SPEED = BUILDER
                .comment("Speed multiplier for floating runes orbiting the Grimoire title.")
                .defineInRange("runeOrbitSpeed", 1.0, 0.1, 5.0);

        ENABLE_TYPEWRITER_EFFECT = BUILDER
                .comment("Enable typewriter effect for research notes in Grimoire.")
                .define("enableTypewriterEffect", true);

        BUILDER.pop();
    }

    // ADD THIS SECTION TO YOUR Config.java FILE

    // --- World Generation Settings ---
    static {
        BUILDER.push("world_generation");

        // Global world generation toggle
        ENABLE_KARMA_NODE_GENERATION = BUILDER
                .comment("Enable or disable karma node generation in the world.")
                .define("enableKarmaNodeGeneration", true);

        // Rarity multiplier for all nodes
        KARMA_NODE_RARITY_MULTIPLIER = BUILDER
                .comment("Multiplier for karma node spawn rates (higher = more common, lower = more rare).")
                .defineInRange("karmaNodeRarityMultiplier", 1.0, 0.1, 10.0);

        // Individual node type toggles
        ENABLE_COMMON_NODES = BUILDER
                .comment("Enable generation of common karma nodes (Fire, Water, Earth, Air).")
                .define("enableCommonNodes", true);

        ENABLE_UNCOMMON_NODES = BUILDER
                .comment("Enable generation of uncommon karma nodes (Light, Dark).")
                .define("enableUncommonNodes", true);

        ENABLE_RARE_NODES = BUILDER
                .comment("Enable generation of rare karma nodes (Order, Chaos).")
                .define("enableRareNodes", true);

        ENABLE_MYTHIC_NODES = BUILDER
                .comment("Enable generation of mythic karma nodes (Creation, Destruction). These spawn 10k+ blocks from spawn.")
                .define("enableMythicNodes", true);

        // Surface vs underground preference
        SURFACE_NODE_BIAS = BUILDER
                .comment("Bias towards surface placement (0.0 = underground preference, 1.0 = surface preference).")
                .defineInRange("surfaceNodeBias", 0.7, 0.0, 1.0);

        // Minimum distance between nodes
        MIN_NODE_DISTANCE = BUILDER
                .comment("Minimum distance in blocks between karma nodes of the same type.")
                .defineInRange("minNodeDistance", 512, 64, 2048);

        BUILDER.pop();
    }

    // World generation config values (add these as static final fields)
    public static final ModConfigSpec.BooleanValue ENABLE_KARMA_NODE_GENERATION;
    public static final ModConfigSpec.DoubleValue KARMA_NODE_RARITY_MULTIPLIER;
    public static final ModConfigSpec.BooleanValue ENABLE_COMMON_NODES;
    public static final ModConfigSpec.BooleanValue ENABLE_UNCOMMON_NODES;
    public static final ModConfigSpec.BooleanValue ENABLE_RARE_NODES;
    public static final ModConfigSpec.BooleanValue ENABLE_MYTHIC_NODES;
    public static final ModConfigSpec.DoubleValue SURFACE_NODE_BIAS;
    public static final ModConfigSpec.IntValue MIN_NODE_DISTANCE;

    // Node behavior
    public static final ModConfigSpec.IntValue NODE_TICK_DELAY;
    public static final ModConfigSpec.IntValue NODE_SPREAD_AMOUNT;
    public static final ModConfigSpec.IntValue NODE_SPREAD_RADIUS;

    // Karma values
    public static final ModConfigSpec.IntValue MAX_KARMA_VALUE;

    // Particle settings
    public static final ModConfigSpec.BooleanValue ENABLE_KARMA_PARTICLES;
    public static final ModConfigSpec.DoubleValue PARTICLE_INTENSITY_MULTIPLIER;

    // Reset settings
    public static final ModConfigSpec.BooleanValue ENABLE_KARMA_RESET_COMMAND;
    public static final ModConfigSpec.IntValue AUTO_KARMA_RESET_THRESHOLD;

    // Holographic GUI settings
    public static final ModConfigSpec.IntValue HOLOGRAM_STABILITY_TIME;
    public static final ModConfigSpec.IntValue HOLOGRAM_PARTICLE_COUNT;
    public static final ModConfigSpec.DoubleValue HOLOGRAM_FLICKER_INTENSITY;
    public static final ModConfigSpec.BooleanValue ENABLE_HOLOGRAM_SOUND;
    public static final ModConfigSpec.BooleanValue ENABLE_SCANNING_LINES;
    public static final ModConfigSpec.IntValue HOLOGRAM_ALPHA_LEVEL;

    // Research Table settings
    public static final ModConfigSpec.IntValue RESEARCH_TABLE_KARMA_REQUIREMENT;
    public static final ModConfigSpec.BooleanValue ENABLE_KARMA_FLUCTUATION;
    public static final ModConfigSpec.IntValue KARMA_FLUCTUATION_RANGE;

    // Grimoire settings
    public static final ModConfigSpec.BooleanValue GRIMOIRE_REQUIRES_OPEN;
    public static final ModConfigSpec.BooleanValue ENABLE_FLOATING_RUNES;
    public static final ModConfigSpec.DoubleValue RUNE_ORBIT_SPEED;
    public static final ModConfigSpec.BooleanValue ENABLE_TYPEWRITER_EFFECT;

    public static final ModConfigSpec SPEC = BUILDER.build();
}