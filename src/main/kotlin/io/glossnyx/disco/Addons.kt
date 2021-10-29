package io.glossnyx.disco

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.metadata.CustomValue
import net.fabricmc.loader.api.metadata.ModMetadata
import net.minecraft.util.Identifier

data class Addon(
	val id: String,
	val discs: List<Disc>,
	val tables: Map<Identifier, List<Identifier>>
)

fun CustomValue.CvObject.toIdentifierMap() = associate { (key, value) ->
	Identifier(key) to value.asArray.toList().map { Identifier(it.asString) }
}

fun ModMetadata.toAddon(): Addon {
	val custom = getCustomValue(modName).asObject

	val discs = custom.get("discs").asArray.map { Disc(Identifier(id, it.asString)) }

	val tables = custom.get("tables").let {
		when (it.type) {
			CustomValue.CvType.OBJECT -> it.asObject.toIdentifierMap()
			else -> mapOf()
		}
	}

	return Addon(id, discs, tables)
}

val addons = FabricLoader.getInstance().allMods
	.filter { it.metadata.customValues.containsKey(modName) }
	.map { it.metadata.toAddon() }
