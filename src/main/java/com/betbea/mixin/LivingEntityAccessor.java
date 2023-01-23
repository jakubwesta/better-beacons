package com.betbea.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    /*
        Usage: ((LivingEntityAccessor) instance).accessor_method()
     */

    @Accessor
    int getJumpingCooldown();
    @Accessor("jumpingCooldown")
    public void setJumpingCooldown(int jumpingCooldown);
    @Accessor
    boolean isJumping();
}
