package settingdust.calypsos_void_heart.mixin.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.calypsos_void_heart.util.events.ClientAttackEvents;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "stopDestroyBlock", at = @At("HEAD"))
    private void calypsos_void_heart$stopAttack(CallbackInfo ci) {
        ClientAttackEvents.STOP.getInvoker().stop(minecraft, minecraft.player);
    }
}
