package settingdust.calypsos_void_heart.fabric.v1_20.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.calypsos_void_heart.CalypsosVoidHeart;
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes;
import settingdust.calypsos_void_heart.mining_laser.item.MiningLaserBehaviour;
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter;

@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGameModeMixin {
    @Shadow @Final protected ServerPlayer player;

    @ModifyExpressionValue(
            method = "handleBlockBreakAction",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;MAX_INTERACTION_DISTANCE:D"
            )
    )
    private double calypsos_void_heart$largerRangeWithMiningLaser(double original) {
        var usingMiningLaser = MiningLaserBehaviour.Companion.isUsingMiningLaser(player);
        if (usingMiningLaser) {
            var maxRange = AttributeAdapter.Companion.getValue(
                    MiningLaserBehaviour.Companion.getAttributes(player.getMainHandItem()),
                    MiningLaserAttributes.INSTANCE.getMaxRange());
            return maxRange * maxRange;
        }
        return original;
    }

    @Inject(method = "debugLogging", at = @At("HEAD"))
    private void calypsos_void_heart$debugLogging(
            BlockPos pos,
            boolean $$1,
            int sequence,
            String message,
            CallbackInfo ci) {
        CalypsosVoidHeart.INSTANCE.getLOGGER().info("debugLogging: {} {}", pos, message);
    }
}
