package settingdust.calypsos_void_heart.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Nullable public HitResult hitResult;

    @WrapWithCondition(
            method = {"startAttack", "continueAttack"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V")
    )
    private boolean calypsos_void_heart$cancelSwing(LocalPlayer player, InteractionHand hand) {
        return !player.getMainHandItem().is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())
                || !CalypsosVoidHeartItems.INSTANCE.getMiningLaser().isUsable(player.getMainHandItem());
    }

    @Inject(
            method = "continueAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/phys/HitResult;getType()Lnet/minecraft/world/phys/HitResult$Type;"
            )
    )
    private void calypsos_void_heart$attackEntity(boolean leftClick, CallbackInfo ci) {
        if (hitResult.getType() != HitResult.Type.ENTITY) return;

    }
}
