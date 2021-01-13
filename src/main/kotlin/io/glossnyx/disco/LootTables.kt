package io.glossnyx.disco

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.ConstantLootTableRange
import net.minecraft.loot.entry.TagEntry
import net.minecraft.tag.ItemTags
import net.minecraft.util.Identifier

fun modifyLootTables() {
	LootTableLoadingCallback.EVENT.register { _, _, id, supplier, _ ->
		addons.filter { it.tables.contains(id) }.forEach {
			val pool = FabricLootPoolBuilder.builder()
				.rolls(ConstantLootTableRange(1))
				.with(TagEntry.builder(ItemTags.getTagGroup().getTag(Identifier(it.id, "discs"))))
				.build()

			supplier.withPool(pool)
		}
	}
}