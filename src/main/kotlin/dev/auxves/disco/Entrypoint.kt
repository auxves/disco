package dev.auxves.disco

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

@Suppress("unused")
fun init() {
	val addons = FabricLoader.getInstance().allMods
		.filter { it.metadata.customValues.containsKey("disco") }
		.map { it.metadata.toAddon() }

	val discs = addons.flatMap { it.discs }

	discs.forEach {
		Registry.register(Registries.ITEM, it.id, it)
	}

	ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register { entries ->
		discs.forEach(entries::add)
	}
}