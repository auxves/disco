package dev.auxves.disco

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ItemStack
import net.minecraft.item.MusicDiscItem
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

private val settings = FabricItemSettings()
	.rarity(Rarity.RARE)
	.maxCount(1)

class Disc(val id: Identifier, duration: Int) : MusicDiscItem(15, SoundEvent.of(id), settings, duration) {
	override fun getName(stack: ItemStack?): Text {
		return Text.translatable("item.minecraft.music_disc_cat")
	}
}
