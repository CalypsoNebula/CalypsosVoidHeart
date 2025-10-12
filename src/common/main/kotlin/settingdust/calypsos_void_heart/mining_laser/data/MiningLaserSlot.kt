package settingdust.calypsos_void_heart.mining_laser.data

import settingdust.calypsos_void_heart.util.serialization.CalypsosVoidHeartCodecs

enum class MiningLaserSlot {
    Tool, Crystal, Generator, Module;

    companion object {
        val CODEC = CalypsosVoidHeartCodecs.stringResolver(
            { it.name },
            { MiningLaserSlot.valueOf(it.uppercase()) }
        )
    }
}