package com.betbea.mixin;

import com.betbea.Mod;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    public int maxJumpAmount = 2;
    private int jumpCount = 0;
    @Shadow protected abstract void jump();

    @Shadow private int jumpingCooldown;

    @Inject(method = "tickMovement()V", at = @At("TAIL"))
    private void jumpingInvoker(CallbackInfo ci) {
        if (((LivingEntity)(Object)this).isPlayer()) {
            if (((LivingEntityAccessor)this).isJumping() && this.jumpCount < this.maxJumpAmount && ((LivingEntityAccessor)this).getJumpingCooldown() == 0) {
                jump();
                ((LivingEntityAccessor)this).setJumpingCooldown(10);
            }
        }
    }

    @Inject(method = "jump()V", at = @At("TAIL"))
    private void jumpInject(CallbackInfo ci) {
        this.jumpCount += 1;
    }

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void tickInject(CallbackInfo ci) {
        if (((LivingEntity)(Object)this).isOnGround()) {
            this.jumpCount = 0;
        }
    }
}
