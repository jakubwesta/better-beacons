package com.betbea.mixin;

import com.betbea.Mod;
import com.betbea.ModRegistry;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.ItemStack;
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
        int dropChance = Math.round(Mod.CONFIG.dropChances.exquisiteEnderShard * 100);
        boolean shouldDrop = (random.nextInt(100) + 1 <= dropChance);
        Mod.sendDebugLog(String.valueOf(random.nextInt(100) + 1));
        Mod.sendDebugLog(String.valueOf(dropChance));
        if (shouldDrop) {
            EndCrystalEntity entity = ((EndCrystalEntity)(Object)this);
            ItemStack exquisiteIngredient = new ItemStack(ModRegistry.EXQUISITE_ENDER_SHARD);
            ItemEntity itemEntity = new ItemEntity(entity.world, entity.getBlockX(), entity.getBlockY(), entity.getBlockZ(), exquisiteIngredient);
            entity.world.spawnEntity(itemEntity);
        }
    }
}
