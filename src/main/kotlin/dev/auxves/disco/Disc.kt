package dev.auxves.disco

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

private val settings = Item.Settings()
	.group(ItemGroup.MISC)
	.rarity(Rarity.RARE)
	.maxCount(1)

class Disc(val id: Identifier, duration: Int) : MusicDiscItem(15, SoundEvent(id), settings, duration) {
	override fun getName(stack: ItemStack?): Text {
		return Text.translatable("item.minecraft.music_disc_cat")
	}
}
