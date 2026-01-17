package com.devdyna.mekajadeupgrade.client;

import com.devdyna.mekajadeupgrade.client.provider.UpgradeProvider;

import mekanism.common.block.prefab.BlockTile;
import snownee.jade.api.*;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {
   
    @Override
    public void registerClient(IWailaClientRegistration r) {
        r.registerBlockComponent(UpgradeProvider.INSTANCE,
                BlockTile.class);
    }

    @Override
    public void register(IWailaCommonRegistration r) {
        r.registerBlockDataProvider(UpgradeProvider.INSTANCE,
                BlockTile.class);
    }
}
