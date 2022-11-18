package dev.auxves.disco

import net.minecraft.util.registry.Registry

const val modName = "disco"

@Suppress("unused")
fun init() {
	addons.flatMap { it.discs }.forEach {
		Registry.register(Registry.ITEM, it.id, it)
	}

	println("Disco loaded!")
}