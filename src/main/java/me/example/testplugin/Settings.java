package me.example.testplugin;

import me.denarydev.crystal.config.BukkitConfigs;
import me.denarydev.crystal.config.CrystalConfigs;
import me.denarydev.crystal.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.nio.file.Path;

@ConfigSerializable
public class Settings {

    private static Settings instance;

    public static Settings get() {
        return instance;
    }

    public static void load(Path file) throws ConfigurateException {
        instance = CrystalConfigs.loadConfig(file, BukkitConfigs.serializers(), Settings.class, true);
    }

    @Comment("This is an example setting")
    public String exampleString = "Example String";

    @Comment("With crystal utils you can create items easily and save it to config using serializers!")
    public ItemStack exampleItem = ItemUtils.itemBuilder()
            .type(Material.NETHERITE_SWORD)
            .displayNameRich("<red>Super Sword")
            .enchantment(Enchantment.DAMAGE_ALL, 10)
            .enchantment(Enchantment.FIRE_ASPECT, 2)
            .enchantment(Enchantment.DURABILITY, 5)
            .enchantment(Enchantment.MENDING, 1)
            .build();
}
