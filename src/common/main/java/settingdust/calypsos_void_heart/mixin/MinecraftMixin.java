package settingdust.calypsos_void_heart.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.mining_laser.MiningLaserBehaviour;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapWithCondition(
            method = "startAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V"
            )
    )
    private boolean calypsos_void_heart$cancelSwing(LocalPlayer instance, InteractionHand hand) {
        return !MiningLaserBehaviour.Companion.isUsingMiningLaser(instance);
    }
}
