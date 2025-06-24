plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta13"
}

repositories {
    mavenCentral()
    gradlePluginPortal()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.titanvale.net/releases/")
    maven("https://repo.rapture.pw/repository/maven-releases/")
    maven("https://repo.infernalsuite.com/repository/maven-snapshots/")
    maven("https://repo.triumphteam.dev/snapshots")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://repo.extendedclip.com/releases/")
    maven("https://maven.enginehub.org/repo/")

}

group = "me.halfquark.fislands"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

dependencies {
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.13")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.13")
    compileOnly(files("$rootDir/libs/Vault.jar"))

}

tasks {
    shadowJar {
        archiveBaseName.set("FIslands")
        destinationDirectory.set(file(rootDir.getAbsolutePath() + "/build/libs"))
        archiveClassifier.set("")
        archiveVersion.set("")
    }
    build {
        dependsOn(shadowJar)
    }
}