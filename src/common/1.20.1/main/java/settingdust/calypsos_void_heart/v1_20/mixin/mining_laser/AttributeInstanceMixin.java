package settingdust.calypsos_void_heart.v1_20.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartRegistries;

@Mixin(AttributeInstance.class)
public class AttributeInstanceMixin {
    @ModifyReceiver(
            method = "save",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/Registry;getKey(Ljava/lang/Object;)Lnet/minecraft/resources/ResourceLocation;"
            )
    )
    private Registry<Attribute> calypsos_void_heart$useCorrectAttributeRegistry(
            Registry<Attribute> registry,
            Object attribute
    ) {
        if (CalypsosVoidHeartRegistries.INSTANCE.getMiningLaserAttribute().getKey((Attribute) attribute) != null) {
            return CalypsosVoidHeartRegistries.INSTANCE.getMiningLaserAttribute();
        }
        return registry;
    }
}
