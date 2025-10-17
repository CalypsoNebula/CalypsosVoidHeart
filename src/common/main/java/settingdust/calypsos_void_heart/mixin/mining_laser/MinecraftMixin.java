package settingdust.calypsos_void_heart.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapWithCondition(
            method = {"startAttack", "continueAttack"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;swing(Lnet/minecraft/world/InteractionHand;)V")
    )
    private boolean calypsos_void_heart$cancelSwing(LocalPlayer player, InteractionHand hand) {
        return !player.getMainHandItem().is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())
                || !CalypsosVoidHeartItems.INSTANCE.getMiningLaser().isUsable(player.getMainHandItem());
    }
}
