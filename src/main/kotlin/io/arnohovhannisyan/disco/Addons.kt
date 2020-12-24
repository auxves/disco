package io.arnohovhannisyan.disco

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.util.Identifier

data class Addon(
	val id: String,
	val discs: List<Disc>,
	val tables: List<Identifier>
)

fun ModMetadata.toAddon(): Addon {
	val custom = getCustomValue("disco").asObject

	val discs = custom.get("discs").asArray.toList().map { Disc(Identifier(id, it.asString)) }
	val tables = custom.get("tables").asArray.toList().map { Identifier(it.asString) }

	return Addon(id, discs, tables)
}

val addons = FabricLoader.getInstance().allMods
	.filter { it.metadata.customValues.containsKey("disco") }
	.map { it.metadata.toAddon() }
