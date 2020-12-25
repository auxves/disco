package io.glossnyx.disco

import net.minecraft.item.Item.Settings
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry

fun registerDiscs() {
	addons.forEach { addon ->
		addon.discs.forEach {
			Registry.register(Registry.ITEM, it.id, it)
		}
	}
}

private val settings = Settings()
	.group(ItemGroup.MISC)
	.rarity(Rarity.RARE)
	.maxCount(1)

class Disc(val id: Identifier) : MusicDiscItem(15, SoundEvent(id), settings) {
	override fun getName(stack: ItemStack?): Text {
		return TranslatableText("item.disco.all.name")
	}
}
