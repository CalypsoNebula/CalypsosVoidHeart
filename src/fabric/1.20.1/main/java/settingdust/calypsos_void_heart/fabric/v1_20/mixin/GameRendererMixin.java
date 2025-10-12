package settingdust.calypsos_void_heart.fabric.v1_20.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes;
import settingdust.calypsos_void_heart.mining_laser.item.MiningLaserBehaviour;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final Minecraft minecraft;

    @WrapOperation(method = "pick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;getPickRange()F"))
    private float calypsos_void_heart$largerPickRangeWithMiningLaser$getPickRange(
            MultiPlayerGameMode instance,
            Operation<Float> original,
            @Share("isUsingMiningLaser") LocalBooleanRef isUsingMiningLaser,
            @Share("maxReachRange") LocalDoubleRef maxReachRange) {
        var itemInHand = minecraft.player.getMainHandItem();
        isUsingMiningLaser.set(itemInHand.is(CalypsosVoidHeartItems.INSTANCE.getMINING_LASER()));
        if (isUsingMiningLaser.get()) {
            CalypsosVoidHeartItems.INSTANCE.getMINING_LASER().getUsingPlayers().add(minecraft.player.getUUID());
            maxReachRange.set(MiningLaserBehaviour.Companion.getAttributes(itemInHand)
                    .getValue(MiningLaserAttributes.MaxRange));
            return (float) maxReachRange.get() - 1f;
        } else {
            CalypsosVoidHeartItems.INSTANCE.getMINING_LASER().getUsingPlayers().remove(minecraft.player.getUUID());
            return original.call(instance);
        }
    }

    @WrapOperation(method = "pick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;hasFarPickRange()Z"))
    private boolean calypsos_void_heart$largerPickRangeWithMiningLaser$hasFarPickRange(
            MultiPlayerGameMode instance,
            Operation<Boolean> original,
            @Share("isUsingMiningLaser") LocalBooleanRef isUsingMiningLaser) {
        if (isUsingMiningLaser.get()) {
            return false;
        }
        return original.call(instance);
    }

    @ModifyExpressionValue(method = "pick", at = @At(value = "CONSTANT", args = "doubleValue=9.0"))
    private double calypsos_void_heart$largerPickRangeWithMiningLaser$correctEntityMaxRange(
            double original,
            @Share("isUsingMiningLaser") LocalBooleanRef isUsingMiningLaser,
            @Share("maxReachRange") LocalDoubleRef maxReachRange) {
        if (isUsingMiningLaser.get()) {
            return maxReachRange.get() * maxReachRange.get();
        }
        return original;
    }
}
