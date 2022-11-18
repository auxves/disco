package dev.auxves.disco

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.util.Identifier

data class Addon(
	val id: String,
	val discs: List<Disc>,
)

private fun ModMetadata.toAddon(): Addon {
	val custom = getCustomValue(modName).asObject

	val discs = custom.get("discs").asArray.map {
		val obj = it.asObject
		val identifier = Identifier(id, obj.get("id").asString)
		val duration = obj.get("duration").asNumber.toInt()

		Disc(identifier, duration)
	}

	return Addon(id, discs)
}

val addons = FabricLoader.getInstance().allMods
	.filter { it.metadata.customValues.containsKey(modName) }
	.map { it.metadata.toAddon() }
