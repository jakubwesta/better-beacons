package com.betbea.mixin;

import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "io.github.cottonmc.cotton.gui.widget.WTabPanel$WTab")
public interface WTabPanelWTabAccessor {
    @Accessor
    boolean isSelected();

    @Accessor
    WTabPanel.Tab getData();
}
