package com.betbea.common.gui;

import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EffectButton extends WButton {
    private final String tooltip;

    public EffectButton(Identifier iconIdentifier, String tooltip) {
        this.setIcon(new TextureIcon(iconIdentifier));
        this.tooltip = tooltip;
    }

    @Override
    public void addTooltip(TooltipBuilder tooltipBuilder) {
        tooltipBuilder.add(Text.of(tooltip));
    }
}
