package com.betbea.mixin;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(WPanel.class)
public class WPanelMixin {
    /*
    @ModifyArg(
            method = "paint(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lio/github/cottonmc/cotton/gui/client/BackgroundPainter;paintBackground(Lnet/minecraft/client/util/math/MatrixStack;IILio/github/cottonmc/cotton/gui/widget/WWidget;)V"
            ),
            index = 2,
            remap = false
    )
    private int mixinPaint(int y) {
        return 0;
    }

     */

    @Inject(method = "getBackgroundPainter()Lio/github/cottonmc/cotton/gui/client/BackgroundPainter;", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private void removeBackground(CallbackInfoReturnable<BackgroundPainter> cir) {
        cir.setReturnValue(BackgroundPainter.createColorful(0xFF777777));
    }
}
