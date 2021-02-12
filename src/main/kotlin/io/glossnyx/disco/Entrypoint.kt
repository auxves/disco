package io.glossnyx.disco

import io.glossnyx.disco.mixin.LootTableAccessor
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry

fun init() {
	addons.flatMap { it.discs }.forEach {
		Registry.register(Registry.ITEM, it.id, it)
	}

	LootTableLoadingCallback.EVENT.register { _, lootManager, id, supplier, _ ->
		val tables = addons
			.flatMap { it.tables.entries }
			.filter { it.value.contains(id) }
			.map { it.key }
			.distinct()

		tables.forEach {
			val table = lootManager.getTable(it) as LootTableAccessor
			table.pools.forEach(supplier::withPool)
		}
	}
}

private val settings = Item.Settings()
	.group(ItemGroup.MISC)
	.rarity(Rarity.RARE)
	.maxCount(1)

class Disc(val id: Identifier) : MusicDiscItem(15, SoundEvent(id), settings) {
	override fun getName(stack: ItemStack?): Text {
		return TranslatableText("item.disco.all.name")
	}
}
