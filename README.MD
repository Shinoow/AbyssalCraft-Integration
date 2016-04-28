# AbyssalCraft Integration
Integration module for AbyssalCraft

### Main Information

AbyssalCraft Integration is a module (previously a part of AbyssalCraft) which holds cross-mod integrations. Turning this into a separate mod was done in order to make the process of adding/updating integrations faster.
Any other mod author who wishes to add support for their own mod can file a pull request with their stuff (and documentation for the things they want to add).
You can find the project at the following URL:
http://minecraft.curseforge.com/mc-mods/234851-abyssalcraft-integration

### Licensing

Right now the project is licensed as "All Rights Reserved", but that will either end up as LGPL or MIT (depending on things like CLA's for contributors)

###Support Development

[![](https://s3.amazonaws.com/patreon_public_assets/kaGh5_patreon_name_and_message.png)](https://www.patreon.com/Shinoow)

### Compiling and running

Since this isn't a complete environment, the last remaining pieces will need to be added to a folder (along with the build.gradle, build.properties and the src and libs folders).
After that, simply open the fodler in a command prompt and type `gradle setupDecompWorkspace` followed by either `gradle eclipse` or `gradle idea` (if you don't have gradle installed, use `gradlew` or `gradlew.bat` instead of `gradle`)

### Contributing

If you're a mod user, you can file issues with suggestions or bugs you've discovered while using this mod.
If you're a mod author, you can expand the project by adding support for your mod (or help improve other integrations, or even suggest integrations). There will be a Contributor License Agreement (CLA) to sign in order to prevent any legal mishaps.
