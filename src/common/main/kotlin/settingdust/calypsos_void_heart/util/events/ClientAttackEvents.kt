package settingdust.calypsos_void_heart.util.events

import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import settingdust.kinecraft.event.Event

object ClientAttackEvents {
    /**
     * Triggered even the hit result is missing
     */
    @JvmField
    val START: Event<Start> =
        Event { listeners -> Start { client, player -> listeners.forEach { it.start(client, player) } } }

    /**
     * Triggered every tick while the player isn't attacking, using item or in missing cooldown (10 ticks)
     *
     * @see Minecraft.continueAttack
     */
    @JvmField
    val STOP: Event<Stop> =
        Event { listeners -> Stop { client, player -> listeners.forEach { it.stop(client, player) } } }

    fun interface Start {
        fun start(client: Minecraft, player: LocalPlayer)
    }

    fun interface Stop {
        fun stop(client: Minecraft, player: LocalPlayer)
    }
}