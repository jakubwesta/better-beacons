package com.betbea.mixin;

import com.betbea.common.gui.BeaconBasicPanel;
import com.betbea.util.ColumnMaterial;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Made this monstrosity once and never going to look into it again...

@Mixin(targets = "io.github.cottonmc.cotton.gui.widget.WTabPanel$WTab")
public class WTabPanelWTabMixin {
    @Redirect(method = "paint(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lio/github/cottonmc/cotton/gui/client/BackgroundPainter;paintBackground(Lnet/minecraft/client/util/math/MatrixStack;IILio/github/cottonmc/cotton/gui/widget/WWidget;)V"
            )
    )
    private void mixinPaintBackground(BackgroundPainter instance, MatrixStack matrixStack, int left, int top, WWidget panel) {
        if (((WTabPanelWTabAccessor) this).getData().getWidget().getClass().getSimpleName().equals("BeaconBasicPanel")) {
            getBackgroundPainter((((BeaconBasicPanel) ((WTabPanelWTabAccessor) this).getData().getWidget()).getMaterial())).paintBackground(matrixStack, left, top, panel);
        }
    }

    private BackgroundPainter getBackgroundPainter(ColumnMaterial panelType) {
        return BackgroundPainter.createColorful(panelType.getPanelColor(), 0.35f);
    }
}
