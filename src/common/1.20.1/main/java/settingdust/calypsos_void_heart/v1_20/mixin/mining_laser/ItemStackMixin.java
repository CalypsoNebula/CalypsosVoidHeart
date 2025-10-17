package settingdust.calypsos_void_heart.v1_20.mixin.mining_laser;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.calypsos_void_heart.CalypsosVoidHeartItems;
import settingdust.calypsos_void_heart.mining_laser.MiningLaserDataManager;
import settingdust.calypsos_void_heart.mining_laser.data.MiningLaserAttributes;
import settingdust.calypsos_void_heart.util.minecraft.AttributeAdapter;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean is(Item item);

    @WrapMethod(method = "getMaxDamage")
    private int calypsos_void_heart$handleItem(Operation<Integer> original) {
        if (is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())) {
            return calypsos_void_heart$getMaxDamage();
        }
        return original.call();
    }

    @WrapOperation(method = "isDamageableItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;getMaxDamage()I"))
    private int calypsos_void_heart$handleItem(Item item, Operation<Integer> original) {
        if (is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())) {
            return calypsos_void_heart$getMaxDamage();
        }
        return original.call(item);
    }

    @Unique
    private int calypsos_void_heart$getMaxDamage() {
        return (int) AttributeAdapter.Companion.getValue(
                MiningLaserDataManager.Companion.getAttributes((ItemStack) (Object) this),
                MiningLaserAttributes.INSTANCE.getEnergy());
    }

    @WrapMethod(method = "isCorrectToolForDrops")
    private boolean calypsos_void_heart$handleItem(BlockState state, Operation<Boolean> original) {
        if (is(CalypsosVoidHeartItems.INSTANCE.getMiningLaser())) {
            return CalypsosVoidHeartItems.INSTANCE.getMiningLaser()
                    .isCorrectToolForDrops((ItemStack) (Object) this, state);
        }
        return original.call(state);
    }
}
