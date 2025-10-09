package settingdust.calypsos_void_heart.v1_20.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;
import settingdust.calypsos_void_heart.item.mining_laser.MiningLaserAttribute;
import settingdust.calypsos_void_heart.item.mining_laser.MiningLaserBehaviour;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @WrapMethod(method = "getMaxDamage")
    private int calypsos_void_heart$handleItem(Operation<Integer> original) {
        if (getItem() == CalypsosVoidHeartItems.INSTANCE.getMINING_LASER()) {
            return (int) MiningLaserBehaviour.Companion.getAttributes((ItemStack) (Object) this)
                    .getValue(MiningLaserAttribute.Energy);
        }
        return original.call();
    }
}
