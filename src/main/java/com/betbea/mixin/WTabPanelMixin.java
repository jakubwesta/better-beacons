package com.betbea.mixin;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WTabPanel.class)
public abstract class WTabPanelMixin {
    /*
    @ModifyArg(method = "setSize(II)V", at = @At(value = "INVOKE", target = "Lio/github/cottonmc/cotton/gui/widget/WBox;setSize(II)V"), index = 1, remap = false)
    private int mixinSize(int x) {
        return 10;
    }

     */

    @ModifyArg(method = "addPainters()V", at = @At(value = "INVOKE", target = "Lio/github/cottonmc/cotton/gui/widget/WCardPanel;setBackgroundPainter(Lio/github/cottonmc/cotton/gui/client/BackgroundPainter;)Lio/github/cottonmc/cotton/gui/widget/WPanel;"),index = 0,remap = false)
    private BackgroundPainter removeBackgroundAtt1(BackgroundPainter painter) {
        return BackgroundPainter.createColorful(0xFF555555);
    }

    @Inject(method = "add(Lio/github/cottonmc/cotton/gui/widget/WWidget;II)V", at = @At(value = "INVOKE", target = "Lio/github/cottonmc/cotton/gui/widget/WWidget;setLocation(II)V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void removeBackgroundAtt2(WWidget widget, int x, int y, CallbackInfo ci) {
        //ci.cancel();
    }

    @ModifyVariable(method = "add(Lio/github/cottonmc/cotton/gui/widget/WWidget;II)V", at = @At("HEAD"), ordinal = 1, remap = false)
    private int removeBackgroundAtt3(int y) {
        if (y == 30) {
            return 30;
        }
        return 0;
    }

    @Inject(method = "<init>()V", at = @At("TAIL"))
    private void removeBackgroundAtt4(CallbackInfo ci) {

    }
}
