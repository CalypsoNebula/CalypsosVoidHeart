package settingdust.calypsos_void_heart.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import settingdust.calypsos_void_heart.mining_laser.PlayerMiningLaserExtension;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @WrapMethod(method = "swing")
    private void calypsos_void_heart$cancelSwing(InteractionHand hand, Operation<Void> original) {
        if (!PlayerMiningLaserExtension.INSTANCE.isUsingMiningLaser((Player) (Object) this)) {
            original.call(hand);
        }
    }
}
