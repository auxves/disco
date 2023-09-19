package dev.auxves.disco

import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.util.Identifier

data class Addon(
	val id: String,
	val discs: List<Disc>,
)

fun ModMetadata.toAddon(): Addon {
	val custom = getCustomValue("disco").asObject

	val discs = custom.get("discs").asArray.map {
		val obj = it.asObject
		val identifier = Identifier(id, obj.get("id").asString)
		val duration = obj.get("duration").asNumber.toInt()

		Disc(identifier, duration)
	}

	return Addon(id, discs)
}

