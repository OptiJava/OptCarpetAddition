import java.text.SimpleDateFormat
import java.util.Date

plugins {
    id("net.fabricmc.fabric-loom")
    id("maven-publish")
}


val minecraftVer = stonecutter.current.version
val modver = "${property("mod_version")}"
val mod = "${modver}+${minecraftVer}+build.${SimpleDateFormat("yyMMddHHmm").format(Date())}"
val archivesBaseName = project.findProperty("archives_base_name")

base {
    archivesName.set("${archivesBaseName}+${mod}")
}

repositories {
    mavenCentral()
    // 阿里云镜像
    maven {
        url = uri("https://maven.aliyun.com/repository/public/")
    }
    // CurseMaven
    maven {
        url = uri("https://www.cursemaven.com")
    }
    // Modrinth
    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
    }
    // JitPack
    maven {
        url = uri("https://jitpack.io")
    }
}

tasks.withType<Test> {
    enabled = false
}

//https://github.com/FabricMC/fabric-loader/issues/783
//configurations {
//	modRuntimeOnly.exclude group: 'net.fabricmc', module: 'fabric-loader'
//}


dependencies {
    "minecraft"("com.mojang:minecraft:${minecraftVer}")
    "implementation"("net.fabricmc:fabric-loader:${property("loader_version")}")
    "implementation"("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
    "implementation"("com.github.gnembon:fabric-carpet:${property("carpet_core_version")}")
}


tasks.processResources {
    from("opt-carpet-addition.accesswidener")

    inputs.property("version", modver)
    inputs.property("minecraft_requirement_version", minecraftVer)

    filesMatching("fabric.mod.json") {
        val valueMap = mapOf(
            "version" to  modver,
            "minecraft_requirement_version" to minecraftVer,
        )
		expand(valueMap)
    }
}

loom {
    accessWidenerPath.set(file("opt-carpet-addition.accesswidener"))

    runConfigs.all {
		ideConfigGenerated(true)
        vmArgs("-Dmixin.debug.export=true")
		runDir("../../run")
	}
}

tasks.jar {
    inputs.property("archivesName", base.archivesName)
    from("LICENSE") {
        rename { fileName ->
            "${fileName}_${base.archivesName.get()}"
        }
    }
}
