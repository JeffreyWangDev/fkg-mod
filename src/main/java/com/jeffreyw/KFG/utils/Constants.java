package com.jeffreyw.KFG.utils;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String prefix = EnumChatFormatting.GOLD + "[" + EnumChatFormatting.YELLOW + "FKG-mod" + EnumChatFormatting.GOLD + "] " + EnumChatFormatting.RESET;
    public static final String[] itemIdPartsToIgnore = {
            "HOT_", "BURNING_", "FIERY_", "INFERNAL_"
    };
    public static final String[] supportedAttributes = {
            "Arachno", "AttackSpeed", "Combo", "Elite", "Ignition", "LifeRecovery",
            "MidasTouch", "Undead", "ManaSteal", "Ender", "ArachnoResistance",
            "BlazingResistance", "Experience", "Speed", "UndeadResistance", "Breeze",
            "Lifeline", "LifeRegeneration", "ManaPool", "Dominance", "EnderResistance",
            "Vitality", "ManaRegeneration", "Veteran", "BlazingFortune", "Blazing",
            "FishingExperience", "DoubleHook", "Fisherman", "FishingSpeed", "Hunter",
            "TrophyHunter", "Infection", "MagicFind", "Fortitude", "Warrior", "Deadeye"
    };

    public static final Map<String, String> aliases = new HashMap<>();
    static {
        aliases.put("br", "Breeze"); aliases.put("dom", "Dominance"); aliases.put("er", "EnderResistance");
        aliases.put("exp", "Experience"); aliases.put("fort", "Fortitude"); aliases.put("lr", "LifeRegeneration");
        aliases.put("ll", "Lifeline"); aliases.put("mf", "MagicFind"); aliases.put("mp", "ManaPool");
        aliases.put("mr", "ManaRegeneration"); aliases.put("sp", "Speed"); aliases.put("ur", "UndeadResistance");
        aliases.put("vet", "Veteran"); aliases.put("vit", "Vitality"); aliases.put("bf", "BlazingFortune");
        aliases.put("fe", "FishingExperience"); aliases.put("dh", "DoubleHook"); aliases.put("fs", "FishingSpeed");
        aliases.put("th", "TrophyHunter"); aliases.put("as", "AttackSpeed"); aliases.put("ms", "ManaSteal");
    }

    public static final String[] supportedItems = {
            "ATTRIBUTE_SHARD",
            // NORMAL ARMOR
            "CRIMSON_HELMET", "TERROR_HELMET", "AURORA_HELMET", "FERVOR_HELMET", "HOLLOW_HELMET",
            "CRIMSON_CHESTPLATE", "TERROR_CHESTPLATE", "AURORA_CHESTPLATE", "FERVOR_CHESTPLATE", "HOLLOW_CHESTPLATE",
            "CRIMSON_LEGGINGS", "TERROR_LEGGINGS", "AURORA_LEGGINGS", "FERVOR_LEGGINGS", "HOLLOW_LEGGINGS",
            "CRIMSON_BOOTS", "TERROR_BOOTS", "AURORA_BOOTS", "FERVOR_BOOTS", "HOLLOW_BOOTS",
            // FISHING ARMOR
            "MAGMA_LORD_HELMET", "THUNDER_HELMET", "TAURUS_HELMET",
            "MAGMA_LORD_CHESTPLATE", "THUNDER_CHESTPLATE", "FLAMING_CHESTPLATE",
            "MAGMA_LORD_LEGGINGS", "THUNDER_LEGGINGS", "MOOGMA_LEGGINGS",
            "MAGMA_LORD_BOOTS", "THUNDER_BOOTS", "SLUG_BOOTS",
            // EQUIPMENT
            "THUNDERBOLT_NECKLACE", "DELIRIUM_NECKLACE", "LAVA_SHELL_NECKLACE", "VANQUISHED_MAGMA_NECKLACE", "MAGMA_NECKLACE", "MOLTEN_NECKLACE",
            "SCOURGE_CLOAK", "VANQUISHED_GHAST_CLOAK", "GHAST_CLOAK", "MOLTEN_CLOAK",
            "SCOVILLE_BELT", "VANQUISHED_BLAZE_BELT", "BLAZE_BELT", "IMPLOSION_BELT", "MOLTEN_BELT",
            "VANQUISHED_GLOWSTONE_GAUNTLET", "GLOWSTONE_GAUNTLET", "GAUNTLET_OF_CONTAGION", "FLAMING_FIST", "MOLTEN_BRACELET",
            // SWORDS / WANDS
            "SWORD_OF_BAD_HEALTH", "BLADE_OF_THE_VOLCANO", "RAGNAROCK_AXE", "ENRAGER",
            "STAFF_OF_THE_VOLCANO", "FIRE_VEIL_WAND", "FIRE_FREEZE_STAFF", "FIRE_FURY_STAFF", "WAND_OF_STRENGTH", "HOLLOW_WAND",
            // BOWS
            "SULPHUR_BOW",
            // FISHING RODS
            "MAGMA_ROD", "INFERNO_ROD", "HELLFIRE_ROD"
    };
}