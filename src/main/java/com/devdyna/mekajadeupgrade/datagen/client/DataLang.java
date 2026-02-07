package com.devdyna.mekajadeupgrade.datagen.client;

import static com.devdyna.mekajadeupgrade.Main.MODID;

import com.devdyna.mekajadeupgrade.Main;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class DataLang extends LanguageProvider {

    public DataLang(PackOutput o) {
        super(o, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("config.jade.plugin_" + Main.MODID + ".upgrades", "MekaJadeUpgrades Info");
    }

}
