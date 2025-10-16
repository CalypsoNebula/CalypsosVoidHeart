package settingdust.calypsos_void_heart.fabric.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.calypsos_void_heart.util.events.PlayerTickEvents;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void calypsos_void_heart$tick(CallbackInfo ci) {
        PlayerTickEvents.POST.getInvoker().onPost((Player) (Object) this);
    }
}
