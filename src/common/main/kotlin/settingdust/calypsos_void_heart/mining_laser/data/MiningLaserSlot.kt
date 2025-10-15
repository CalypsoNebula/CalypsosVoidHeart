package settingdust.calypsos_void_heart.mining_laser.data

import settingdust.calypsos_void_heart.util.serialization.CalypsosVoidHeartCodecs

enum class MiningLaserSlot {
    Tool, Crystal, Generator, Module;

    companion object {
        val nameToSlot = entries.associateBy { it.name.lowercase() }

        val CODEC = CalypsosVoidHeartCodecs.stringResolver(
            { it.name },
            { nameToSlot[it.lowercase()] }
        )
    }
}