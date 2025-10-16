package settingdust.calypsos_void_heart.v1_20.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.util.AddServerResourcesListenerCallback;

@Mixin(ReloadableServerResources.class)
public class ReloadableServerResourcesMixin {
    @ModifyExpressionValue(
            method = "loadResources",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/ReloadableServerResources;listeners()Ljava/util/List;"
            )
    )
    private static List<PreparableReloadListener> calypsos_void_heart$addListener(
            List<PreparableReloadListener> original,
            @Local(argsOnly = true) RegistryAccess.Frozen registryAccess,
            @Local ReloadableServerResources resources
    ) {
        var mutable = new ArrayList<>(original);
        mutable.addAll(AddServerResourcesListenerCallback.CALLBACK.getInvoker().callback(resources, registryAccess));
        return mutable;
    }
}
