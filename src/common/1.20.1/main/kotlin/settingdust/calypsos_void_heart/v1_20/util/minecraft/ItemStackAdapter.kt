package settingdust.calypsos_void_heart.v1_20.util.minecraft

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import settingdust.calypsos_void_heart.util.minecraft.ItemStackAdapter

class ItemStackAdapter : ItemStackAdapter {
    override fun ItemStack.isSameItemSameComponents(other: ItemStack) = ItemStack.isSameItemSameTags(this, other)
    override fun ItemStack.hurtNoBreak(
        user: LivingEntity,
        amount: Int
    ) : Boolean = hurt(amount, user.random, user as? ServerPlayer)
}