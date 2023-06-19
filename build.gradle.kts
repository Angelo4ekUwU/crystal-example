import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.0.1"
    id("net.minecrell.plugin-yml.paper") version "0.6.0-SNAPSHOT"
}

group = "me.example"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://the-planet.fun/repo/snapshots/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")

    val crystalVersion = "2.0.0-SNAPSHOT"
    library("me.denarydev.crystal.paper:utils:$crystalVersion")
    library("me.denarydev.crystal.shared:config:$crystalVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

paper {
    // Default values can be overridden if needed
    // name = "TestPlugin"
    // version = "1.0"
    // description = "This is a test plugin"
    // website = "https://example.com"
    // author = "Notch"

    // Plugin main class (required)
    main = "me.example.testplugin.TestPlugin"

    // Plugin bootstrapper/loader (optional)
    bootstrapper = "me.example.testplugin.bootstrap.TestPluginBootstrap"
    loader = "me.example.testplugin.loader.PluginLibrariesLoader"
    hasOpenClassloader = false

    // generate paper-libraries.json?
    generateLibrariesJson = true

    // Mark plugin for supporting Folia
    foliaSupported = true

    // API version (Needs to be 1.19 or higher)
    apiVersion = "1.19"

    // Other possible properties from plugin.yml (optional)
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP // or POSTWORLD
    authors = listOf("Notch", "Notch2")

    prefix = "TEST"
    defaultPermission = BukkitPluginDescription.Permission.Default.OP // TRUE, FALSE, OP or NOT_OP
    provides = listOf("TestPluginOldName", "TestPlug")

    depends {
        // Required dependency
        register("WorldEdit") {
            required = true
            bootstrap = true
        }
        // Optional dependency
        register("Essentials") {
        }
    }
    loadBefore {
        register("BeforePlugin") {
            bootstrap = true
        }
    }
    loadAfter {
        register("AfterPlugin") {
            bootstrap = true
        }
    }

    permissions {
        register("testplugin.*") {
            children = listOf("testplugin.test") // Defaults permissions to true
            // You can also specify the values of the permissions
            childrenMap = mapOf("testplugin.test" to true)
        }
        register("testplugin.test") {
            description = "Allows you to run the test command"
            default = BukkitPluginDescription.Permission.Default.OP // TRUE, FALSE, OP or NOT_OP
        }
    }
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.compilerArgs.addAll(
            listOf(
                "-parameters",
                "-nowarn",
                "-Xlint:-unchecked",
                "-Xlint:-deprecation",
                "-Xlint:-processing"
            )
        )
        options.isFork = true
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    runServer {
        minecraftVersion("1.19.4")
        runDirectory.set(project.projectDir.resolve("run/"))
    }
}
