package io.arnohovhannisyan.disco

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.ConstantLootTableRange
import net.minecraft.loot.entry.TagEntry
import net.minecraft.tag.ServerTagManagerHolder
import net.minecraft.util.Identifier

fun modifyLootTables() {
	LootTableLoadingCallback.EVENT.register { _, _, id, supplier, _ ->
		val tagManager = ServerTagManagerHolder.getTagManager()

		val rolls = ConstantLootTableRange(1)

		addons.filter { it.tables.contains(id) }.forEach {
			val tag = tagManager.items.getTag(Identifier(it.id, "discs"))

			val pool = FabricLootPoolBuilder.builder()
				.rolls(rolls)
				.withEntry(TagEntry.builder(tag).build())
				.build()

			supplier.withPool(pool)
		}
	}
}