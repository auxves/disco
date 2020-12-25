# Disco

Disco is a modular music disc loader built for Fabric.

Being a **loader**, it doesn't add any music discs by default and relies on addon packs to provide the sound files, textures, and translations.

### Dependencies

- [Fabric Loader]
- [Fabric API]
- [Fabric Language Kotlin]

## Installation

You have three options for downloading Disco.

- [CurseForge]
- [Modrinth]
- [GitHub Releases]

## Usage

Disco addons are JAR files that contain a resource pack, a data pack, and a `fabric.mod.json`. An unpacked example can be found [here][example].

To create a JAR file from an unpacked addon, simply compress it into a zip file and rename the extension to `.jar`.

> IMPORTANT: Make sure the `.ogg` files only have 1 audio channel (mono)

<!-- Dependencies -->

[fabric loader]: https://fabricmc.net/use
[fabric api]: https://www.curseforge.com/minecraft/mc-mods/fabric-api
[fabric language kotlin]: https://www.curseforge.com/minecraft/mc-mods/fabric-language-kotlin

<!-- Distribution -->

[curseforge]: https://www.curseforge.com/minecraft/mc-mods/disco
[modrinth]: https://modrinth.com/mod/disco
[github releases]: https://github.com/glossnyx/disco/releases

<!-- Example -->

[example]: https://github.com/glossnyx/disco/tree/example
