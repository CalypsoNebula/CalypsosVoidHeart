package settingdust.calypsos_void_heart.v1_20.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @WrapOperation(
            method = "sameDestroyTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isSameItemSameTags(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private boolean calypsos_void_heart$ignoreDamage(
            ItemStack first,
            ItemStack second,
            Operation<Boolean> original
    ) {
        if (!first.is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())
                || !second.is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())) {
            return original.call(first, second);
        }
        var firstTag = first.getTag();
        var secondTag = second.getTag();
        if (firstTag == null || secondTag == null) {
            return original.call(first, second);
        }
        firstTag = firstTag.copy();
        secondTag = secondTag.copy();
        firstTag.remove("Damage");
        secondTag.remove("Damage");
        return firstTag.equals(secondTag);
    }
}
