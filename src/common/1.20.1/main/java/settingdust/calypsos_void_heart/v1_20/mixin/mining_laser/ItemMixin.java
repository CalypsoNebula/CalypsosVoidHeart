package settingdust.calypsos_void_heart.v1_20.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;
import settingdust.calypsos_void_heart.mining_laser.MiningLaserDataManager;
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes;
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter;

@Mixin(Item.class)
public class ItemMixin {
    @ModifyExpressionValue(
            method = {"getBarColor", "getBarWidth"},
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/Item;maxDamage:I")
    )
    private int calypsos_void_heart$getMaxDamage(int original, ItemStack stack) {
        if (stack.is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())) {
            return (int) AttributeAdapter.Companion.getValue(
                    MiningLaserDataManager.Companion.getAttributes(stack),
                    MiningLaserAttributes.INSTANCE.getEnergy());
        }
        return original;
    }
}
