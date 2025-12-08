package com.devdyna.mekajadeupgrade.client.provider;

import java.util.ArrayList;
import java.util.List;
import com.devdyna.mekajadeupgrade.Main;

import mekanism.common.tile.prefab.TileEntityConfigurableMachine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.api.*;
import mekanism.api.Upgrade;
import mekanism.common.item.ItemUpgrade;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registries.MekanismItems;

@SuppressWarnings("null")
public enum UpgradeProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag tag = accessor.getServerData();
        List<IElement> elements = new ArrayList<>();

        int x = 0;

        for (Upgrade upgrade : Upgrade.values()) {
            if (tag.contains(upgrade.getSerializedName())) {
                int count = tag.getInt(upgrade.getSerializedName());

                elements.add(item(upgrade, x));
                elements.add(countText(count, x));
                x += 10;
            }
        }

        tooltip.add(elements);
    }

    public IElement item(Upgrade upgrade, int x) {
        return IElementHelper.get()
                .item(new ItemStack(getUpgrade(upgrade).get()), 0.55f)
                .size(new Vec2(10, 10))
                .translate(new Vec2(x, -2))
                .message(null);
    }

    public IElement countText(int count, int x) {
        return IElementHelper.get()
                .text(Component.literal("x" + count))
                .size(new Vec2(5, 5))
                .translate(new Vec2(x, -1))
                .message(null);
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {

        TileEntityConfigurableMachine be = (TileEntityConfigurableMachine) accessor.getBlockEntity();

        for (Upgrade upgrade : Upgrade.values())
            if (be.getComponent().isUpgradeInstalled(upgrade))
                data.putInt(upgrade.getSerializedName(), be.getComponent().getUpgrades(upgrade));

    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(Main.MODID, "upgrades");
    }

    public ItemRegistryObject<ItemUpgrade> getUpgrade(Upgrade upgrade) {
        return switch (upgrade) {
            case Upgrade.SPEED -> MekanismItems.SPEED_UPGRADE;
            case Upgrade.ENERGY -> MekanismItems.ENERGY_UPGRADE;
            case Upgrade.CHEMICAL -> MekanismItems.CHEMICAL_UPGRADE;
            case Upgrade.ANCHOR -> MekanismItems.ANCHOR_UPGRADE;
            case Upgrade.FILTER -> MekanismItems.FILTER_UPGRADE;
            case Upgrade.MUFFLING -> MekanismItems.MUFFLING_UPGRADE;
            case Upgrade.STONE_GENERATOR -> MekanismItems.STONE_GENERATOR_UPGRADE;
            default -> null;
        };
    }

}