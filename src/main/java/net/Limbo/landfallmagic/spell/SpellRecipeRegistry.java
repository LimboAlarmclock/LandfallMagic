package net.Limbo.landfallmagic.spell;

import net.Limbo.landfallmagic.landfallmagic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SpellRecipeRegistry {

    private static final List<Tier1SpellRecipe> TIER_1_RECIPES = new ArrayList<>();
    private static final List<Tier2SpellRecipe> TIER_2_RECIPES = new ArrayList<>();
    private static boolean recipesRegistered = false;

    public static void registerRecipes() {
        if (recipesRegistered) return;

        TIER_1_RECIPES.clear();
        TIER_2_RECIPES.clear();

        // --- TIER 1 RECIPES (Base Spells) ---
        // Projectiles
        Tier1SpellRecipe fireball = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.FIRE, "Ignition Bolt");
        Tier1SpellRecipe rockThrow = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.EARTH, "Rock Throw");
        Tier1SpellRecipe waterJet = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.WATER, "Water Jet");
        Tier1SpellRecipe gustBolt = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.AIR, "Gust Bolt");
        Tier1SpellRecipe healingRay = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.LIGHT, "Healing Ray");
        Tier1SpellRecipe shadowBolt = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.DARK, "Shadow Bolt");
        Tier1SpellRecipe bindingShot = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.ORDER, "Binding Shot");
        Tier1SpellRecipe unstableOrb = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.CHAOS, "Unstable Orb");
        Tier1SpellRecipe growthSpore = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.CREATION, "Growth Spore");
        Tier1SpellRecipe shatterBolt = addTier1Recipe(SpellForm.PROJECTILE, SpellElement.DESTRUCTION, "Shatter Bolt");

        // Self
        Tier1SpellRecipe fireResistance = addTier1Recipe(SpellForm.SELF, SpellElement.FIRE, "Fire Resistance");
        Tier1SpellRecipe stoneSkin = addTier1Recipe(SpellForm.SELF, SpellElement.EARTH, "Stone Skin");
        Tier1SpellRecipe waterBreathing = addTier1Recipe(SpellForm.SELF, SpellElement.WATER, "Water Breathing");
        Tier1SpellRecipe swiftStep = addTier1Recipe(SpellForm.SELF, SpellElement.AIR, "Swift Step");
        Tier1SpellRecipe regeneration = addTier1Recipe(SpellForm.SELF, SpellElement.LIGHT, "Regeneration");
        Tier1SpellRecipe cloakOfShadows = addTier1Recipe(SpellForm.SELF, SpellElement.DARK, "Cloak of Shadows");
        Tier1SpellRecipe steadfast = addTier1Recipe(SpellForm.SELF, SpellElement.ORDER, "Steadfast");
        Tier1SpellRecipe surge = addTier1Recipe(SpellForm.SELF, SpellElement.CHAOS, "Surge");
        Tier1SpellRecipe overgrowth = addTier1Recipe(SpellForm.SELF, SpellElement.CREATION, "Overgrowth");
        Tier1SpellRecipe frenzy = addTier1Recipe(SpellForm.SELF, SpellElement.DESTRUCTION, "Frenzy");

        // Zone
        Tier1SpellRecipe flamePatch = addTier1Recipe(SpellForm.ZONE, SpellElement.FIRE, "Flame Patch");
        Tier1SpellRecipe quicksand = addTier1Recipe(SpellForm.ZONE, SpellElement.EARTH, "Quicksand Field");
        Tier1SpellRecipe freezingRain = addTier1Recipe(SpellForm.ZONE, SpellElement.WATER, "Freezing Rain");
        Tier1SpellRecipe cyclone = addTier1Recipe(SpellForm.ZONE, SpellElement.AIR, "Cyclone");
        Tier1SpellRecipe sanctuary = addTier1Recipe(SpellForm.ZONE, SpellElement.LIGHT, "Sanctuary");
        Tier1SpellRecipe dreadField = addTier1Recipe(SpellForm.ZONE, SpellElement.DARK, "Dread Field");
        Tier1SpellRecipe barrier = addTier1Recipe(SpellForm.ZONE, SpellElement.ORDER, "Barrier");
        Tier1SpellRecipe anomalyField = addTier1Recipe(SpellForm.ZONE, SpellElement.CHAOS, "Anomaly Field");
        Tier1SpellRecipe bloomGarden = addTier1Recipe(SpellForm.ZONE, SpellElement.CREATION, "Bloom Garden");
        Tier1SpellRecipe collapse = addTier1Recipe(SpellForm.ZONE, SpellElement.DESTRUCTION, "Collapse");

        // Touch
        Tier1SpellRecipe ignite = addTier1Recipe(SpellForm.TOUCH, SpellElement.FIRE, "Ignite");
        Tier1SpellRecipe stoneFist = addTier1Recipe(SpellForm.TOUCH, SpellElement.EARTH, "Stone Fist");
        Tier1SpellRecipe frostStrike = addTier1Recipe(SpellForm.TOUCH, SpellElement.WATER, "Frost Strike");
        Tier1SpellRecipe shockPalm = addTier1Recipe(SpellForm.TOUCH, SpellElement.AIR, "Shock Palm");
        Tier1SpellRecipe healingHands = addTier1Recipe(SpellForm.TOUCH, SpellElement.LIGHT, "Healing Hands");
        Tier1SpellRecipe lifeDrain = addTier1Recipe(SpellForm.TOUCH, SpellElement.DARK, "Life Drain");
        Tier1SpellRecipe grapple = addTier1Recipe(SpellForm.TOUCH, SpellElement.ORDER, "Grapple");
        Tier1SpellRecipe wildStrike = addTier1Recipe(SpellForm.TOUCH, SpellElement.CHAOS, "Wild Strike");
        Tier1SpellRecipe blessingTouch = addTier1Recipe(SpellForm.TOUCH, SpellElement.CREATION, "Blessing Touch");
        Tier1SpellRecipe crushingBlow = addTier1Recipe(SpellForm.TOUCH, SpellElement.DESTRUCTION, "Crushing Blow");


        // --- TIER 2 RECIPES (Augmented Spells) ---

        // --- Fireball Augments ---
        addTier2Recipe(fireball.result(), SpellElement.FIRE, "Lingering Fireball");
        addTier2Recipe(fireball.result(), SpellElement.EARTH, "Heavy Fireball");
        addTier2Recipe(fireball.result(), SpellElement.WATER, "Sizzling Orb");
        addTier2Recipe(fireball.result(), SpellElement.AIR, "Swift Fireball");
        addTier2Recipe(fireball.result(), SpellElement.LIGHT, "Solar Flare");
        addTier2Recipe(fireball.result(), SpellElement.DARK, "Leeching Flame");
        addTier2Recipe(fireball.result(), SpellElement.ORDER, "Homing Fireball");
        addTier2Recipe(fireball.result(), SpellElement.CHAOS, "Unstable Fireball");
        addTier2Recipe(fireball.result(), SpellElement.CREATION, "Multiplying Flame");
        addTier2Recipe(fireball.result(), SpellElement.DESTRUCTION, "Explosive Fireball");

        // --- Rock Throw Augments ---
        addTier2Recipe(rockThrow.result(), SpellElement.FIRE, "Magma Rock");
        addTier2Recipe(rockThrow.result(), SpellElement.EARTH, "Bouldering Blast");
        addTier2Recipe(rockThrow.result(), SpellElement.WATER, "Mud Ball");
        addTier2Recipe(rockThrow.result(), SpellElement.AIR, "Scattering Shot");
        addTier2Recipe(rockThrow.result(), SpellElement.LIGHT, "Blessed Stone");
        addTier2Recipe(rockThrow.result(), SpellElement.DARK, "Cursed Pebble");
        addTier2Recipe(rockThrow.result(), SpellElement.ORDER, "Returning Rock");
        addTier2Recipe(rockThrow.result(), SpellElement.CHAOS, "Wild Rock");
        addTier2Recipe(rockThrow.result(), SpellElement.CREATION, "Growing Boulder");
        addTier2Recipe(rockThrow.result(), SpellElement.DESTRUCTION, "Shatterstone");

        // --- Water Jet Augments ---
        addTier2Recipe(waterJet.result(), SpellElement.FIRE, "Scalding Jet");
        addTier2Recipe(waterJet.result(), SpellElement.EARTH, "High-Pressure Jet");
        addTier2Recipe(waterJet.result(), SpellElement.WATER, "Freezing Jet");
        addTier2Recipe(waterJet.result(), SpellElement.AIR, "Piercing Jet");
        addTier2Recipe(waterJet.result(), SpellElement.LIGHT, "Purifying Stream");
        addTier2Recipe(waterJet.result(), SpellElement.DARK, "Corrupting Stream");
        addTier2Recipe(waterJet.result(), SpellElement.ORDER, "Bending Stream");
        addTier2Recipe(waterJet.result(), SpellElement.CHAOS, "Forking Stream");
        addTier2Recipe(waterJet.result(), SpellElement.CREATION, "Regenerating Jet");
        addTier2Recipe(waterJet.result(), SpellElement.DESTRUCTION, "Erosive Jet");

        // --- Gust Bolt Augments ---
        addTier2Recipe(gustBolt.result(), SpellElement.FIRE, "Searing Wind");
        addTier2Recipe(gustBolt.result(), SpellElement.EARTH, "Sandblast");
        addTier2Recipe(gustBolt.result(), SpellElement.WATER, "Chilling Gust");
        addTier2Recipe(gustBolt.result(), SpellElement.AIR, "Concussive Gust");
        addTier2Recipe(gustBolt.result(), SpellElement.LIGHT, "Zephyr's Blessing");
        addTier2Recipe(gustBolt.result(), SpellElement.DARK, "Suffocating Gale");
        addTier2Recipe(gustBolt.result(), SpellElement.ORDER, "Guided Wind");
        addTier2Recipe(gustBolt.result(), SpellElement.CHAOS, "Turbulent Blast");
        addTier2Recipe(gustBolt.result(), SpellElement.CREATION, "Lasting Cyclone");
        addTier2Recipe(gustBolt.result(), SpellElement.DESTRUCTION, "Flaying Wind");

        // --- Healing Ray Augments ---
        addTier2Recipe(healingRay.result(), SpellElement.FIRE, "Cauterizing Ray");
        addTier2Recipe(healingRay.result(), SpellElement.EARTH, "Earthen Ward");
        addTier2Recipe(healingRay.result(), SpellElement.WATER, "Soothing Mist");
        addTier2Recipe(healingRay.result(), SpellElement.AIR, "Swift Renewal");
        addTier2Recipe(healingRay.result(), SpellElement.LIGHT, "Radiant Ray");
        addTier2Recipe(healingRay.result(), SpellElement.DARK, "Sacrificial Ray");
        addTier2Recipe(healingRay.result(), SpellElement.ORDER, "Channeled Heal");
        addTier2Recipe(healingRay.result(), SpellElement.CHAOS, "Erratic Light");
        addTier2Recipe(healingRay.result(), SpellElement.CREATION, "Chain Heal");
        addTier2Recipe(healingRay.result(), SpellElement.DESTRUCTION, "Purging Light");

        // --- Shadow Bolt Augments ---
        addTier2Recipe(shadowBolt.result(), SpellElement.FIRE, "Soulfire Bolt");
        addTier2Recipe(shadowBolt.result(), SpellElement.EARTH, "Weighted Shadow");
        addTier2Recipe(shadowBolt.result(), SpellElement.WATER, "Numbing Shadow");
        addTier2Recipe(shadowBolt.result(), SpellElement.AIR, "Fleeting Shadow");
        addTier2Recipe(shadowBolt.result(), SpellElement.LIGHT, "Twilight Bolt");
        addTier2Recipe(shadowBolt.result(), SpellElement.DARK, "Hungering Bolt");
        addTier2Recipe(shadowBolt.result(), SpellElement.ORDER, "Seeking Shadow");
        addTier2Recipe(shadowBolt.result(), SpellElement.CHAOS, "Echoing Shadow");
        addTier2Recipe(shadowBolt.result(), SpellElement.CREATION, "Splitting Shadow");
        addTier2Recipe(shadowBolt.result(), SpellElement.DESTRUCTION, "Agonizing Bolt");

        // --- Binding Shot Augments ---
        addTier2Recipe(bindingShot.result(), SpellElement.FIRE, "Burning Chains");
        addTier2Recipe(bindingShot.result(), SpellElement.EARTH, "Stone Fetters");
        addTier2Recipe(bindingShot.result(), SpellElement.WATER, "Frozen Roots");
        addTier2Recipe(bindingShot.result(), SpellElement.AIR, "Grasping Wind");
        addTier2Recipe(bindingShot.result(), SpellElement.LIGHT, "Hallowed Ground");
        addTier2Recipe(bindingShot.result(), SpellElement.DARK, "Tethers of Shadow");
        addTier2Recipe(bindingShot.result(), SpellElement.ORDER, "Unerring Shot");
        addTier2Recipe(bindingShot.result(), SpellElement.CHAOS, "Chaotic Snare");
        addTier2Recipe(bindingShot.result(), SpellElement.CREATION, "Spreading Roots");
        addTier2Recipe(bindingShot.result(), SpellElement.DESTRUCTION, "Shattering Bind");

        // --- Unstable Orb Augments ---
        addTier2Recipe(unstableOrb.result(), SpellElement.FIRE, "Volatile Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.EARTH, "Gravitic Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.WATER, "Miasma Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.AIR, "Erratic Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.LIGHT, "Prismatic Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.DARK, "Maddening Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.ORDER, "Semi-Stable Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.CHAOS, "Pandemonium Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.CREATION, "Generative Orb");
        addTier2Recipe(unstableOrb.result(), SpellElement.DESTRUCTION, "Detonating Orb");

        // --- Growth Spore Augments ---
        addTier2Recipe(growthSpore.result(), SpellElement.FIRE, "Cinder Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.EARTH, "Bark Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.WATER, "Soothing Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.AIR, "Haste Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.LIGHT, "Rejuvenating Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.DARK, "Blight Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.ORDER, "Loyal Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.CHAOS, "Mutating Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.CREATION, "Fertile Spore");
        addTier2Recipe(growthSpore.result(), SpellElement.DESTRUCTION, "Rupturing Spore");

        // --- Shatter Bolt Augments ---
        addTier2Recipe(shatterBolt.result(), SpellElement.FIRE, "Meltdown Bolt");
        addTier2Recipe(shatterBolt.result(), SpellElement.EARTH, "Armor Piercer");
        addTier2Recipe(shatterBolt.result(), SpellElement.WATER, "Brittle-Bolt");
        addTier2Recipe(shatterBolt.result(), SpellElement.AIR, "Forceful Bolt");
        addTier2Recipe(shatterBolt.result(), SpellElement.LIGHT, "Purifying Shatter");
        addTier2Recipe(shatterBolt.result(), SpellElement.DARK, "Soul Breaker");
        addTier2Recipe(shatterBolt.result(), SpellElement.ORDER, "Seeking Breaker");
        addTier2Recipe(shatterBolt.result(), SpellElement.CHAOS, "Ricochet Bolt");
        addTier2Recipe(shatterBolt.result(), SpellElement.CREATION, "Replicating Bolt");
        addTier2Recipe(shatterBolt.result(), SpellElement.DESTRUCTION, "Annihilator Bolt");

        // --- Fire Resistance Augments ---
        addTier2Recipe(fireResistance.result(), SpellElement.FIRE, "Immolation");
        addTier2Recipe(fireResistance.result(), SpellElement.EARTH, "Earthen Resilience");
        addTier2Recipe(fireResistance.result(), SpellElement.WATER, "Steam Shroud");
        addTier2Recipe(fireResistance.result(), SpellElement.AIR, "Updraft");
        addTier2Recipe(fireResistance.result(), SpellElement.LIGHT, "Phoenix Blessing");
        addTier2Recipe(fireResistance.result(), SpellElement.DARK, "Ashen Form");
        addTier2Recipe(fireResistance.result(), SpellElement.ORDER, "Fire Attunement");
        addTier2Recipe(fireResistance.result(), SpellElement.CHAOS, "Unstable Heart");
        addTier2Recipe(fireResistance.result(), SpellElement.CREATION, "Cinder Guard");
        addTier2Recipe(fireResistance.result(), SpellElement.DESTRUCTION, "Retaliating Flames");

        // --- Stone Skin Augments ---
        addTier2Recipe(stoneSkin.result(), SpellElement.FIRE, "Obsidian Skin");
        addTier2Recipe(stoneSkin.result(), SpellElement.EARTH, "Mountainous Form");
        addTier2Recipe(stoneSkin.result(), SpellElement.WATER, "Wetstone Form");
        addTier2Recipe(stoneSkin.result(), SpellElement.AIR, "Zephyr Form");
        addTier2Recipe(stoneSkin.result(), SpellElement.LIGHT, "Marble Form");
        addTier2Recipe(stoneSkin.result(), SpellElement.DARK, "Shadowed Stone");
        addTier2Recipe(stoneSkin.result(), SpellElement.ORDER, "Perfected Guard");
        addTier2Recipe(stoneSkin.result(), SpellElement.CHAOS, "Shifting Sand");
        addTier2Recipe(stoneSkin.result(), SpellElement.CREATION, "Living Rock");
        addTier2Recipe(stoneSkin.result(), SpellElement.DESTRUCTION, "Spiked Armor");

        // --- Water Breathing Augments ---
        addTier2Recipe(waterBreathing.result(), SpellElement.FIRE, "Hot Spring");
        addTier2Recipe(waterBreathing.result(), SpellElement.EARTH, "Depth Dweller");
        addTier2Recipe(waterBreathing.result(), SpellElement.WATER, "Aquatic Grace");
        addTier2Recipe(waterBreathing.result(), SpellElement.AIR, "Air Bubble");
        addTier2Recipe(waterBreathing.result(), SpellElement.LIGHT, "Luminous Skin");
        addTier2Recipe(waterBreathing.result(), SpellElement.DARK, "Abyssal Form");
        addTier2Recipe(waterBreathing.result(), SpellElement.ORDER, "Hydro-Stability");
        addTier2Recipe(waterBreathing.result(), SpellElement.CHAOS, "Schooling");
        addTier2Recipe(waterBreathing.result(), SpellElement.CREATION, "Gill Generation");
        addTier2Recipe(waterBreathing.result(), SpellElement.DESTRUCTION, "Depth Charge");

        // --- Swift Step Augments ---
        addTier2Recipe(swiftStep.result(), SpellElement.FIRE, "Comet Dash");
        addTier2Recipe(swiftStep.result(), SpellElement.EARTH, "Grounded Step");
        addTier2Recipe(swiftStep.result(), SpellElement.WATER, "Skim");
        addTier2Recipe(swiftStep.result(), SpellElement.AIR, "Cyclone Step");
        addTier2Recipe(swiftStep.result(), SpellElement.LIGHT, "Blessed Celerity");
        addTier2Recipe(swiftStep.result(), SpellElement.DARK, "Phantom Pace");
        addTier2Recipe(swiftStep.result(), SpellElement.ORDER, "Rhythmic Stride");
        addTier2Recipe(swiftStep.result(), SpellElement.CHAOS, "Blink Step");
        addTier2Recipe(swiftStep.result(), SpellElement.CREATION, "Slipstream");
        addTier2Recipe(swiftStep.result(), SpellElement.DESTRUCTION, "Forceful Step");

        // --- Regeneration Augments ---
        addTier2Recipe(regeneration.result(), SpellElement.FIRE, "Cauterize");
        addTier2Recipe(regeneration.result(), SpellElement.EARTH, "Earthen Renewal");
        addTier2Recipe(regeneration.result(), SpellElement.WATER, "Soothing Waters");
        addTier2Recipe(regeneration.result(), SpellElement.AIR, "Invigorate");
        addTier2Recipe(regeneration.result(), SpellElement.LIGHT, "Divine Blessing");
        addTier2Recipe(regeneration.result(), SpellElement.DARK, "Blood Magic");
        addTier2Recipe(regeneration.result(), SpellElement.ORDER, "Focused Mending");
        addTier2Recipe(regeneration.result(), SpellElement.CHAOS, "Unstable Renewal");
        addTier2Recipe(regeneration.result(), SpellElement.CREATION, "Miraculous Growth");
        addTier2Recipe(regeneration.result(), SpellElement.DESTRUCTION, "Vengeful Light");

        // --- Cloak of Shadows Augments ---
        addTier2Recipe(cloakOfShadows.result(), SpellElement.FIRE, "Smokescreen");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.EARTH, "Stillness");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.WATER, "Murky Veil");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.AIR, "Silent Wind");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.LIGHT, "Dazzling Escape");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.DARK, "True Invisibility");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.ORDER, "Shadow Stalk");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.CHAOS, "Shifting Shade");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.CREATION, "Shadow Decoy");
        addTier2Recipe(cloakOfShadows.result(), SpellElement.DESTRUCTION, "Umbral Ambush");

        // --- Steadfast Augments ---
        addTier2Recipe(steadfast.result(), SpellElement.FIRE, "Steadfast Resolve");
        addTier2Recipe(steadfast.result(), SpellElement.EARTH, "Unmovable");
        addTier2Recipe(steadfast.result(), SpellElement.WATER, "Fluid Stance");
        addTier2Recipe(steadfast.result(), SpellElement.AIR, "Feather Balance");
        addTier2Recipe(steadfast.result(), SpellElement.LIGHT, "Holy Aegis");
        addTier2Recipe(steadfast.result(), SpellElement.DARK, "Defiant Shadow");
        addTier2Recipe(steadfast.result(), SpellElement.ORDER, "Perfect Balance");
        addTier2Recipe(steadfast.result(), SpellElement.CHAOS, "Erratic Stance");
        addTier2Recipe(steadfast.result(), SpellElement.CREATION, "Steadfast Ward");
        addTier2Recipe(steadfast.result(), SpellElement.DESTRUCTION, "Retributive Force");

        // --- Surge Augments ---
        addTier2Recipe(surge.result(), SpellElement.FIRE, "Fire-Blessed");
        addTier2Recipe(surge.result(), SpellElement.EARTH, "Earth-Blessed");
        addTier2Recipe(surge.result(), SpellElement.WATER, "Water-Blessed");
        addTier2Recipe(surge.result(), SpellElement.AIR, "Air-Blessed");
        addTier2Recipe(surge.result(), SpellElement.LIGHT, "Light-Blessed");
        addTier2Recipe(surge.result(), SpellElement.DARK, "Dark-Blessed");
        addTier2Recipe(surge.result(), SpellElement.ORDER, "Controlled Surge");
        addTier2Recipe(surge.result(), SpellElement.CHAOS, "Pure Chaos");
        addTier2Recipe(surge.result(), SpellElement.CREATION, "Proliferating Surge");
        addTier2Recipe(surge.result(), SpellElement.DESTRUCTION, "Destructive Surge");

        // --- Overgrowth Augments ---
        addTier2Recipe(overgrowth.result(), SpellElement.FIRE, "Burning Thorns");
        addTier2Recipe(overgrowth.result(), SpellElement.EARTH, "Ironwood Thorns");
        addTier2Recipe(overgrowth.result(), SpellElement.WATER, "Numbing Thorns");
        addTier2Recipe(overgrowth.result(), SpellElement.AIR, "Whipping Vines");
        addTier2Recipe(overgrowth.result(), SpellElement.LIGHT, "Blessed Thorns");
        addTier2Recipe(overgrowth.result(), SpellElement.DARK, "Withering Thorns");
        addTier2Recipe(overgrowth.result(), SpellElement.ORDER, "Symmetrical Thorns");
        addTier2Recipe(overgrowth.result(), SpellElement.CHAOS, "Wild Growth");
        addTier2Recipe(overgrowth.result(), SpellElement.CREATION, "Prolific Growth");
        addTier2Recipe(overgrowth.result(), SpellElement.DESTRUCTION, "Explosive Thorns");

        // --- Frenzy Augments ---
        addTier2Recipe(frenzy.result(), SpellElement.FIRE, "Pyre of Madness");
        addTier2Recipe(frenzy.result(), SpellElement.EARTH, "Juggernaut's Rage");
        addTier2Recipe(frenzy.result(), SpellElement.WATER, "Bloodlust");
        addTier2Recipe(frenzy.result(), SpellElement.AIR, "Zephyr's Fury");
        addTier2Recipe(frenzy.result(), SpellElement.LIGHT, "Holy Wrath");
        addTier2Recipe(frenzy.result(), SpellElement.DARK, "Vampiric Haste");
        addTier2Recipe(frenzy.result(), SpellElement.ORDER, "Focused Rage");
        addTier2Recipe(frenzy.result(), SpellElement.CHAOS, "Unpredictable Fury");
        addTier2Recipe(frenzy.result(), SpellElement.CREATION, "Endless Wrath");
        addTier2Recipe(frenzy.result(), SpellElement.DESTRUCTION, "Reckless Abandon");

        // --- Flame Patch Augments ---
        addTier2Recipe(flamePatch.result(), SpellElement.FIRE, "Inferno");
        addTier2Recipe(flamePatch.result(), SpellElement.EARTH, "Tar Pit");
        addTier2Recipe(flamePatch.result(), SpellElement.WATER, "Steam Vent");
        addTier2Recipe(flamePatch.result(), SpellElement.AIR, "Firenado");
        addTier2Recipe(flamePatch.result(), SpellElement.LIGHT, "Cleansing Fire");
        addTier2Recipe(flamePatch.result(), SpellElement.DARK, "Ashen Ground");
        addTier2Recipe(flamePatch.result(), SpellElement.ORDER, "Wall of Fire");
        addTier2Recipe(flamePatch.result(), SpellElement.CHAOS, "Roaming Flames");
        addTier2Recipe(flamePatch.result(), SpellElement.CREATION, "Everlasting Flame");
        addTier2Recipe(flamePatch.result(), SpellElement.DESTRUCTION, "Volatile Patch");

        // --- Quicksand Augments ---
        addTier2Recipe(quicksand.result(), SpellElement.FIRE, "Ash Field");
        addTier2Recipe(quicksand.result(), SpellElement.EARTH, "Grasping Earth");
        addTier2Recipe(quicksand.result(), SpellElement.WATER, "Mud Field");
        addTier2Recipe(quicksand.result(), SpellElement.AIR, "Dust Devil");
        addTier2Recipe(quicksand.result(), SpellElement.LIGHT, "Hallowed Ground");
        addTier2Recipe(quicksand.result(), SpellElement.DARK, "Desecrated Earth");
        addTier2Recipe(quicksand.result(), SpellElement.ORDER, "Stone Trap");
        addTier2Recipe(quicksand.result(), SpellElement.CHAOS, "Unstable Terrain");
        addTier2Recipe(quicksand.result(), SpellElement.CREATION, "Spreading Mire");
        addTier2Recipe(quicksand.result(), SpellElement.DESTRUCTION, "Earthen Spikes");

        // --- Freezing Rain Augments ---
        addTier2Recipe(freezingRain.result(), SpellElement.FIRE, "Thermal Shock");
        addTier2Recipe(freezingRain.result(), SpellElement.EARTH, "Sleet Storm");
        addTier2Recipe(freezingRain.result(), SpellElement.WATER, "Blizzard");
        addTier2Recipe(freezingRain.result(), SpellElement.AIR, "Driving Hail");
        addTier2Recipe(freezingRain.result(), SpellElement.LIGHT, "Purifying Mist");
        addTier2Recipe(freezingRain.result(), SpellElement.DARK, "Enervating Fog");
        addTier2Recipe(freezingRain.result(), SpellElement.ORDER, "Rime Barrier");
        addTier2Recipe(freezingRain.result(), SpellElement.CHAOS, "Chaotic Sleet");
        addTier2Recipe(freezingRain.result(), SpellElement.CREATION, "Lingering Fog");
        addTier2Recipe(freezingRain.result(), SpellElement.DESTRUCTION, "Razor Ice");

        // --- Cyclone Augments ---
        addTier2Recipe(cyclone.result(), SpellElement.FIRE, "Firestorm");
        addTier2Recipe(cyclone.result(), SpellElement.EARTH, "Sandstorm");
        addTier2Recipe(cyclone.result(), SpellElement.WATER, "Waterspout");
        addTier2Recipe(cyclone.result(), SpellElement.AIR, "Raging Tempest");
        addTier2Recipe(cyclone.result(), SpellElement.LIGHT, "Soothing Breeze");
        addTier2Recipe(cyclone.result(), SpellElement.DARK, "Withering Wind");
        addTier2Recipe(cyclone.result(), SpellElement.ORDER, "Static Vortex");
        addTier2Recipe(cyclone.result(), SpellElement.CHAOS, "Wild Tornado");
        addTier2Recipe(cyclone.result(), SpellElement.CREATION, "Wandering Wind");
        addTier2Recipe(cyclone.result(), SpellElement.DESTRUCTION, "Blade Storm");

        // --- Sanctuary Augments ---
        addTier2Recipe(sanctuary.result(), SpellElement.FIRE, "Hallowed Flames");
        addTier2Recipe(sanctuary.result(), SpellElement.EARTH, "Sacred Ground");
        addTier2Recipe(sanctuary.result(), SpellElement.WATER, "Fountain of Life");
        addTier2Recipe(sanctuary.result(), SpellElement.AIR, "Zephyr's Haven");
        addTier2Recipe(sanctuary.result(), SpellElement.LIGHT, "Consecration");
        addTier2Recipe(sanctuary.result(), SpellElement.DARK, "Beacon of Hope");
        addTier2Recipe(sanctuary.result(), SpellElement.ORDER, "Protective Circle");
        addTier2Recipe(sanctuary.result(), SpellElement.CHAOS, "Unstable Font");
        addTier2Recipe(sanctuary.result(), SpellElement.CREATION, "Lasting Sanctuary");
        addTier2Recipe(sanctuary.result(), SpellElement.DESTRUCTION, "Purge Zone");

        // --- Dread Field Augments ---
        addTier2Recipe(dreadField.result(), SpellElement.FIRE, "Field of Terror");
        addTier2Recipe(dreadField.result(), SpellElement.EARTH, "Weight of Despair");
        addTier2Recipe(dreadField.result(), SpellElement.WATER, "Field of Lethargy");
        addTier2Recipe(dreadField.result(), SpellElement.AIR, "Field of Silence");
        addTier2Recipe(dreadField.result(), SpellElement.LIGHT, "Zone of Penance");
        addTier2Recipe(dreadField.result(), SpellElement.DARK, "Void Zone");
        addTier2Recipe(dreadField.result(), SpellElement.ORDER, "Focused Misery");
        addTier2Recipe(dreadField.result(), SpellElement.CHAOS, "Maddening Field");
        addTier2Recipe(dreadField.result(), SpellElement.CREATION, "Lingering Dread");
        addTier2Recipe(dreadField.result(), SpellElement.DESTRUCTION, "Agony Field");

        // --- Barrier Augments ---
        addTier2Recipe(barrier.result(), SpellElement.FIRE, "Wall of Fire");
        addTier2Recipe(barrier.result(), SpellElement.EARTH, "Earthen Wall");
        addTier2Recipe(barrier.result(), SpellElement.WATER, "Wall of Ice");
        addTier2Recipe(barrier.result(), SpellElement.AIR, "Wind Wall");
        addTier2Recipe(barrier.result(), SpellElement.LIGHT, "Holy Bulwark");
        addTier2Recipe(barrier.result(), SpellElement.DARK, "Shadow Fence");
        addTier2Recipe(barrier.result(), SpellElement.ORDER, "Arcane Wall");
        addTier2Recipe(barrier.result(), SpellElement.CHAOS, "Prismatic Wall");
        addTier2Recipe(barrier.result(), SpellElement.CREATION, "Regenerating Wall");
        addTier2Recipe(barrier.result(), SpellElement.DESTRUCTION, "Explosive Ward");

        // --- Anomaly Field Augments ---
        addTier2Recipe(anomalyField.result(), SpellElement.FIRE, "Field of Combustion");
        addTier2Recipe(anomalyField.result(), SpellElement.EARTH, "Field of Tectonics");
        addTier2Recipe(anomalyField.result(), SpellElement.WATER, "Field of Flux");
        addTier2Recipe(anomalyField.result(), SpellElement.AIR, "Field of Turbulence");
        addTier2Recipe(anomalyField.result(), SpellElement.LIGHT, "Field of Miracles");
        addTier2Recipe(anomalyField.result(), SpellElement.DARK, "Field of Horror");
        addTier2Recipe(anomalyField.result(), SpellElement.ORDER, "Contained Chaos");
        addTier2Recipe(anomalyField.result(), SpellElement.CHAOS, "Maelstrom of Chaos");
        addTier2Recipe(anomalyField.result(), SpellElement.CREATION, "Spawning Field");
        addTier2Recipe(anomalyField.result(), SpellElement.DESTRUCTION, "Unstable Expanse");

        // --- Bloom Garden Augments ---
        addTier2Recipe(bloomGarden.result(), SpellElement.FIRE, "Sungrown Garden");
        addTier2Recipe(bloomGarden.result(), SpellElement.EARTH, "Stonewood Garden");
        addTier2Recipe(bloomGarden.result(), SpellElement.WATER, "Dew Garden");
        addTier2Recipe(bloomGarden.result(), SpellElement.AIR, "Swiftbloom Garden");
        addTier2Recipe(bloomGarden.result(), SpellElement.LIGHT, "Sacred Grove");
        addTier2Recipe(bloomGarden.result(), SpellElement.DARK, "Briar Patch");
        addTier2Recipe(bloomGarden.result(), SpellElement.ORDER, "Symmetrical Garden");
        addTier2Recipe(bloomGarden.result(), SpellElement.CHAOS, "Wild Garden");
        addTier2Recipe(bloomGarden.result(), SpellElement.CREATION, "Verdant Paradise");
        addTier2Recipe(bloomGarden.result(), SpellElement.DESTRUCTION, "Explosive Flora");

        // --- Collapse Augments ---
        addTier2Recipe(collapse.result(), SpellElement.FIRE, "Volcanic Eruption");
        addTier2Recipe(collapse.result(), SpellElement.EARTH, "Earthquake");
        addTier2Recipe(collapse.result(), SpellElement.WATER, "Geyser");
        addTier2Recipe(collapse.result(), SpellElement.AIR, "Upheaval");
        addTier2Recipe(collapse.result(), SpellElement.LIGHT, "Hallowed Eruption");
        addTier2Recipe(collapse.result(), SpellElement.DARK, "Void Rupture");
        addTier2Recipe(collapse.result(), SpellElement.ORDER, "Focused Collapse");
        addTier2Recipe(collapse.result(), SpellElement.CHAOS, "Unstable Fissure");
        addTier2Recipe(collapse.result(), SpellElement.CREATION, "Aftergrowth");
        addTier2Recipe(collapse.result(), SpellElement.DESTRUCTION, "Chain Eruption");

        // --- Ignite Augments ---
        addTier2Recipe(ignite.result(), SpellElement.FIRE, "Wildfire Touch");
        addTier2Recipe(ignite.result(), SpellElement.EARTH, "Tarring Touch");
        addTier2Recipe(ignite.result(), SpellElement.WATER, "Flash Steam");
        addTier2Recipe(ignite.result(), SpellElement.AIR, "Leaping Flames");
        addTier2Recipe(ignite.result(), SpellElement.LIGHT, "Cauterizing Touch");
        addTier2Recipe(ignite.result(), SpellElement.DARK, "Ashen Touch");
        addTier2Recipe(ignite.result(), SpellElement.ORDER, "Focused Burn");
        addTier2Recipe(ignite.result(), SpellElement.CHAOS, "Erratic Flames");
        addTier2Recipe(ignite.result(), SpellElement.CREATION, "Lingering Heat");
        addTier2Recipe(ignite.result(), SpellElement.DESTRUCTION, "Detonating Touch");

        // --- Stone Fist Augments ---
        addTier2Recipe(stoneFist.result(), SpellElement.FIRE, "Magma Fist");
        addTier2Recipe(stoneFist.result(), SpellElement.EARTH, "Crushing Fist");
        addTier2Recipe(stoneFist.result(), SpellElement.WATER, "Muddy Blow");
        addTier2Recipe(stoneFist.result(), SpellElement.AIR, "Gale Fist");
        addTier2Recipe(stoneFist.result(), SpellElement.LIGHT, "Guardian's Push");
        addTier2Recipe(stoneFist.result(), SpellElement.DARK, "Weakening Blow");
        addTier2Recipe(stoneFist.result(), SpellElement.ORDER, "Forceful Palm");
        addTier2Recipe(stoneFist.result(), SpellElement.CHAOS, "Unstable Blow");
        addTier2Recipe(stoneFist.result(), SpellElement.CREATION, "Earthen Gauntlets");
        addTier2Recipe(stoneFist.result(), SpellElement.DESTRUCTION, "Shattering Fist");

        // --- Frost Strike Augments ---
        addTier2Recipe(frostStrike.result(), SpellElement.FIRE, "Thermal Shock");
        addTier2Recipe(frostStrike.result(), SpellElement.EARTH, "Grasping Ice");
        addTier2Recipe(frostStrike.result(), SpellElement.WATER, "Flash Freeze");
        addTier2Recipe(frostStrike.result(), SpellElement.AIR, "Numbing Cold");
        addTier2Recipe(frostStrike.result(), SpellElement.LIGHT, "Soothing Chill");
        addTier2Recipe(frostStrike.result(), SpellElement.DARK, "Corrupting Ice");
        addTier2Recipe(frostStrike.result(), SpellElement.ORDER, "Precise Chill");
        addTier2Recipe(frostStrike.result(), SpellElement.CHAOS, "Creeping Frost");
        addTier2Recipe(frostStrike.result(), SpellElement.CREATION, "Rimeheart");
        addTier2Recipe(frostStrike.result(), SpellElement.DESTRUCTION, "Icebreaker");

        // --- Shock Palm Augments ---
        addTier2Recipe(shockPalm.result(), SpellElement.FIRE, "Static Discharge");
        addTier2Recipe(shockPalm.result(), SpellElement.EARTH, "Grounding Palm");
        addTier2Recipe(shockPalm.result(), SpellElement.WATER, "Conductive Shock");
        addTier2Recipe(shockPalm.result(), SpellElement.AIR, "Thunder Clap");
        addTier2Recipe(shockPalm.result(), SpellElement.LIGHT, "Reviving Shock");
        addTier2Recipe(shockPalm.result(), SpellElement.DARK, "Draining Shock");
        addTier2Recipe(shockPalm.result(), SpellElement.ORDER, "Focused Push");
        addTier2Recipe(shockPalm.result(), SpellElement.CHAOS, "Unstable Jolt");
        addTier2Recipe(shockPalm.result(), SpellElement.CREATION, "Charged Hands");
        addTier2Recipe(shockPalm.result(), SpellElement.DESTRUCTION, "Fulminating Palm");

        // --- Healing Hands Augments ---
        addTier2Recipe(healingHands.result(), SpellElement.FIRE, "Cauterizing Hands");
        addTier2Recipe(healingHands.result(), SpellElement.EARTH, "Earthen Vigor");
        addTier2Recipe(healingHands.result(), SpellElement.WATER, "Cleansing Touch");
        addTier2Recipe(healingHands.result(), SpellElement.AIR, "Swift Recovery");
        addTier2Recipe(healingHands.result(), SpellElement.LIGHT, "Miraculous Touch");
        addTier2Recipe(healingHands.result(), SpellElement.DARK, "Blood Bond");
        addTier2Recipe(healingHands.result(), SpellElement.ORDER, "Channeled Mending");
        addTier2Recipe(healingHands.result(), SpellElement.CHAOS, "Font of Vitality");
        addTier2Recipe(healingHands.result(), SpellElement.CREATION, "Chain Heal");
        addTier2Recipe(healingHands.result(), SpellElement.DESTRUCTION, "Sacrifice");

        // --- Life Drain Augments ---
        addTier2Recipe(lifeDrain.result(), SpellElement.FIRE, "Cauterizing Drain");
        addTier2Recipe(lifeDrain.result(), SpellElement.EARTH, "Siphoning Grasp");
        addTier2Recipe(lifeDrain.result(), SpellElement.WATER, "Sluggish Drain");
        addTier2Recipe(lifeDrain.result(), SpellElement.AIR, "Miasma");
        addTier2Recipe(lifeDrain.result(), SpellElement.LIGHT, "Shared Vitality");
        addTier2Recipe(lifeDrain.result(), SpellElement.DARK, "Insatiable Touch");
        addTier2Recipe(lifeDrain.result(), SpellElement.ORDER, "Focused Siphon");
        addTier2Recipe(lifeDrain.result(), SpellElement.CHAOS, "Erratic Leech");
        addTier2Recipe(lifeDrain.result(), SpellElement.CREATION, "Spreading Plague");
        addTier2Recipe(lifeDrain.result(), SpellElement.DESTRUCTION, "Agonizing Drain");

        // --- Grapple Augments ---
        addTier2Recipe(grapple.result(), SpellElement.FIRE, "Chains of Fire");
        addTier2Recipe(grapple.result(), SpellElement.EARTH, "Stone Grip");
        addTier2Recipe(grapple.result(), SpellElement.WATER, "Shackles of Ice");
        addTier2Recipe(grapple.result(), SpellElement.AIR, "Vortex Tether");
        addTier2Recipe(grapple.result(), SpellElement.LIGHT, "Pacifying Touch");
        addTier2Recipe(grapple.result(), SpellElement.DARK, "Binding Shadows");
        addTier2Recipe(grapple.result(), SpellElement.ORDER, "Unbreakable Grasp");
        addTier2Recipe(grapple.result(), SpellElement.CHAOS, "Chaotic Tether");
        addTier2Recipe(grapple.result(), SpellElement.CREATION, "Binding Chain");
        addTier2Recipe(grapple.result(), SpellElement.DESTRUCTION, "Crushing Grip");

        // --- Wild Strike Augments ---
        addTier2Recipe(wildStrike.result(), SpellElement.FIRE, "Wildfire");
        addTier2Recipe(wildStrike.result(), SpellElement.EARTH, "Wild Impact");
        addTier2Recipe(wildStrike.result(), SpellElement.WATER, "Wild Current");
        addTier2Recipe(wildStrike.result(), SpellElement.AIR, "Wild Gale");
        addTier2Recipe(wildStrike.result(), SpellElement.LIGHT, "Wild Blessing");
        addTier2Recipe(wildStrike.result(), SpellElement.DARK, "Wild Curse");
        addTier2Recipe(wildStrike.result(), SpellElement.ORDER, "Tamed Strike");
        addTier2Recipe(wildStrike.result(), SpellElement.CHAOS, "Stroke of Chaos");
        addTier2Recipe(wildStrike.result(), SpellElement.CREATION, "Wild Echo");
        addTier2Recipe(wildStrike.result(), SpellElement.DESTRUCTION, "Wild Power");

        // --- Blessing Touch Augments ---
        addTier2Recipe(blessingTouch.result(), SpellElement.FIRE, "Blessing of Ash");
        addTier2Recipe(blessingTouch.result(), SpellElement.EARTH, "Blessing of Stone");
        addTier2Recipe(blessingTouch.result(), SpellElement.WATER, "Blessing of Flow");
        addTier2Recipe(blessingTouch.result(), SpellElement.AIR, "Blessing of Alacrity");
        addTier2Recipe(blessingTouch.result(), SpellElement.LIGHT, "Blessing of Mending");
        addTier2Recipe(blessingTouch.result(), SpellElement.DARK, "Blessing of Thorns");
        addTier2Recipe(blessingTouch.result(), SpellElement.ORDER, "Focused Blessing");
        addTier2Recipe(blessingTouch.result(), SpellElement.CHAOS, "Blessing of Fortune");
        addTier2Recipe(blessingTouch.result(), SpellElement.CREATION, "Shared Blessing");
        addTier2Recipe(blessingTouch.result(), SpellElement.DESTRUCTION, "Blessing of Fury");

        // --- Crushing Blow Augments ---
        addTier2Recipe(crushingBlow.result(), SpellElement.FIRE, "Searing Blow");
        addTier2Recipe(crushingBlow.result(), SpellElement.EARTH, "Earthshatter");
        addTier2Recipe(crushingBlow.result(), SpellElement.WATER, "Draining Blow");
        addTier2Recipe(crushingBlow.result(), SpellElement.AIR, "Concussive Blow");
        addTier2Recipe(crushingBlow.result(), SpellElement.LIGHT, "Banishing Strike");
        addTier2Recipe(crushingBlow.result(), SpellElement.DARK, "Soul Crush");
        addTier2Recipe(crushingBlow.result(), SpellElement.ORDER, "Sundering Blow");
        addTier2Recipe(crushingBlow.result(), SpellElement.CHAOS, "Unstable Strike");
        addTier2Recipe(crushingBlow.result(), SpellElement.CREATION, "Echoing Blow");
        addTier2Recipe(crushingBlow.result(), SpellElement.DESTRUCTION, "Annihilating Strike");

        recipesRegistered = true;
    }

    private static Tier1SpellRecipe addTier1Recipe(SpellForm form, SpellElement element, String name) {
        Tier1SpellRecipe recipe = new Tier1SpellRecipe(form, element, new Spell(form, element, name));
        TIER_1_RECIPES.add(recipe);
        return recipe;
    }

    private static void addTier2Recipe(Spell baseSpell, SpellElement augment, String newName) {
        Spell augmentedSpell = new Spell(baseSpell.form(), baseSpell.primaryElement(), augment, newName);
        TIER_2_RECIPES.add(new Tier2SpellRecipe(baseSpell, augment, augmentedSpell));
    }

    public static Optional<Tier1SpellRecipe> findTier1Recipe(SpellForm form, SpellElement element) {
        try {
            landfallmagic.LOGGER.info("findTier1Recipe called with Form: {}, Element: {}", form, element);

            if (form == null || element == null) {
                landfallmagic.LOGGER.info("Form or element is null, returning empty");
                return Optional.empty();
            }

            if (TIER_1_RECIPES == null) {
                landfallmagic.LOGGER.error("TIER_1_RECIPES list is null!");
                return Optional.empty();
            }

            landfallmagic.LOGGER.info("Searching {} tier 1 recipes", TIER_1_RECIPES.size());

            for (Tier1SpellRecipe recipe : TIER_1_RECIPES) {
                if (recipe.form() == form && recipe.element() == element) {
                    landfallmagic.LOGGER.info("Found matching recipe: {}", recipe.getResult().name());
                    return Optional.of(recipe);
                }
            }

            landfallmagic.LOGGER.info("No matching recipe found");
            return Optional.empty();

        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error in findTier1Recipe: ", e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<Tier2SpellRecipe> findTier2Recipe(Spell baseSpell, SpellElement augment) {
        if (baseSpell == null || augment == null || baseSpell.isAugmented()) return Optional.empty();
        // CORRECTED THIS LINE
        return TIER_2_RECIPES.stream()
                .filter(r -> Objects.equals(r.baseSpell().name(), baseSpell.name()) && r.augment() == augment)
                .findFirst();
    }

    public static void debugRecipes() {
        landfallmagic.LOGGER.info("=== RECIPE REGISTRY DEBUG ===");
        landfallmagic.LOGGER.info("Recipes registered: {}", recipesRegistered);
        landfallmagic.LOGGER.info("Tier 1 recipes count: {}", TIER_1_RECIPES.size());
        landfallmagic.LOGGER.info("Tier 2 recipes count: {}", TIER_2_RECIPES.size());

        // List first few Tier 1 recipes
        landfallmagic.LOGGER.info("First 5 Tier 1 recipes:");
        TIER_1_RECIPES.stream()
                .limit(5)
                .forEach(recipe -> landfallmagic.LOGGER.info("  {} + {} = {}",
                        recipe.form(), recipe.element(), recipe.getResult().name()));

        // Check if specific recipe exists
        Optional<Tier1SpellRecipe> testRecipe = findTier1Recipe(SpellForm.PROJECTILE, SpellElement.FIRE);
        if (testRecipe.isPresent()) {
            landfallmagic.LOGGER.info("Test recipe (PROJECTILE + FIRE) found: {}", testRecipe.get().getResult().name());
        } else {
            landfallmagic.LOGGER.error("Test recipe (PROJECTILE + FIRE) NOT FOUND!");
        }
    }
}