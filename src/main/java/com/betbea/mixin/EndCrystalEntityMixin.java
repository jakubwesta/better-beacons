package com.betbea.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EndCrystalEntity.class)
public class EndCrystalEntityMixin {
    @Inject(method = "crystalDestroyed(Lnet/minecraft/entity/damage/DamageSource;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getEnderDragonFight()Lnet/minecraft/entity/boss/dragon/EnderDragonFight;"))
    private void crystalDestroyed(DamageSource source, CallbackInfo ci) {
        Random random = new Random();
        boolean shouldDrop = (random.nextInt(100) + 1 > 50);
        if (shouldDrop) {
            EndCrystalEntity entity = ((EndCrystalEntity)(Object)this);
            ItemStack exquisiteIngredient = new ItemStack(Items.ACACIA_LOG);
            ItemEntity itemEntity = new ItemEntity(entity.world, entity.getBlockX(), entity.getBlockY(), entity.getBlockZ(), exquisiteIngredient);
            entity.world.spawnEntity(itemEntity);
        }
    }
}
