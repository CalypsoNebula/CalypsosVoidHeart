package settingdust.calypsos_void_heart.item.mining_laser

import net.minecraft.world.entity.ai.attributes.RangedAttribute

object MiningLaserAttribute {
    @JvmField
    val Energy = RangedAttribute("energy", 300.0, 1.0, Double.MAX_VALUE)
    @JvmField
    val BestRange = RangedAttribute("best_range", 6.0, 1.0, 48.0)
    @JvmField
    val MaxRange = RangedAttribute("max_range", 8.0, 1.0, 48.0)
    @JvmField
    val Damage = RangedAttribute("damage", 0.5, 0.0, 100.0)
    @JvmField
    val Speed = RangedAttribute("speed", 0.6, 0.0, 32.0)
    @JvmField
    val ModuleSlot = RangedAttribute("module_slot", 1.0, 1.0, 8.0)
}