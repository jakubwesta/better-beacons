package com.betbea.mixin;

import com.betbea.Mod;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SyncedGuiDescription.class)
public class SyncedGuiDescriptionMixin {
    @ModifyArg(method = "addPainters()V", at = @At(value = "INVOKE", target = "Lio/github/cottonmc/cotton/gui/widget/WPanel;setBackgroundPainter(Lio/github/cottonmc/cotton/gui/client/BackgroundPainter;)Lio/github/cottonmc/cotton/gui/widget/WPanel;"), index = 0, remap = false)
    private BackgroundPainter removeBackground(BackgroundPainter painter) {
        // return BackgroundPainter.createColorful(0x00666666); Good
        return BackgroundPainter.createNinePatch(new Identifier(Mod.MODID, "textures/widget/panel_light.png"));
    }
}
