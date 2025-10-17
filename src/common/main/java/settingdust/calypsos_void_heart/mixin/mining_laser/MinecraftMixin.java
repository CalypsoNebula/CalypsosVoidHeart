package settingdust.calypsos_void_heart.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;
import settingdust.calypsos_void_heart.mining_laser.PlayerMiningLaserExtension;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow @Nullable public HitResult hitResult;

    @Shadow @Nullable public LocalPlayer player;

    @Shadow
    protected abstract boolean startAttack();

    @WrapWithCondition(
            method = {"startAttack", "continueAttack"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V")
    )
    private boolean calypsos_void_heart$cancelSwing(LocalPlayer player, InteractionHand hand) {
        return !player.getMainHandItem().is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())
                || !CalypsosVoidHeartItems.INSTANCE.getMiningLaser().isUsable(player.getMainHandItem());
    }

    @WrapWithCondition(
            method = "continueAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;stopDestroyBlock()V"
            )
    )
    private boolean calypsos_void_heart$attackEntity(MultiPlayerGameMode gameMode, boolean leftClick) {
        if (!leftClick
                || hitResult == null
                || hitResult.getType() != HitResult.Type.ENTITY
                || !PlayerMiningLaserExtension.INSTANCE.isUsingMiningLaser(player)) {
            return true;
        }
        gameMode.attack(this.player, ((EntityHitResult) this.hitResult).getEntity());
        return false;
    }
}
