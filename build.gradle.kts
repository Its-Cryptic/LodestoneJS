plugins {
    id("java-library")
    id("maven-publish")
    id("net.neoforged.moddev") version "2.0.30-beta"
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}
group = "${property("mod_group_id")}"

version = "${property("minecraft_version")}-${property("mod_version")}"
if (System.getenv("BUILD_NUMBER") != null) {
    version = "$version.${System.getenv("BUILD_NUMBER")}"
}

repositories {
    mavenLocal()
}

base {
    archivesName.set("${property("mod_id")}")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
tasks.javadoc {
    enabled = false
}
neoForge {
    version.set(project.property("neo_version").toString())

    parchment {
        mappingsVersion.set(project.property("parchment_mappings_version").toString())
        minecraftVersion.set(project.property("parchment_minecraft_version").toString())
    }

    runs {
        register("client") {
            client()

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("data") {
            data()
            programArguments.addAll(
                    "--mod", project.property("mod_id").toString(),
                    "--all",
                    "--output", file("src/generated/resources/").absolutePath,
                    "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        create("${property("mod_id")}") {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

repositories {
    mavenCentral()
    maven {
        name = "OctoStudios"
        url = uri("https://maven.octo-studios.com/releases")
    }
    maven {
        name = "JEI maven"
        url = uri("https://dvs1.progwml6.com/files/maven")
    }
    maven {
        name = "tterrag maven"
        url = uri("https://maven.tterrag.com/")
    }
    maven {
        name = "BlameJared maven"
        url = uri("https://maven.blamejared.com/")
    }
    maven {
        name = "Curse Maven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
    maven {
        url = uri("https://maven.saps.dev/releases")
        content {
            includeGroup("dev.latvian.mods")
        }
    }
}

dependencies {

    // Curios dependency
    compileOnly("top.theillusivec4.curios:curios-neoforge:${property("curios_version")}:api")
    runtimeOnly("top.theillusivec4.curios:curios-neoforge:${property("curios_version")}")

    // Lodestone dependency
    implementation("team.lodestar.lodestone:lodestone:${property("minecraft_version")}-${property("lodestone_version")}")

    // KubeJS dependency
    implementation("dev.latvian.mods:rhino:${property("rhino_version")}")
    implementation("dev.latvian.mods:kubejs-neoforge:${property("kubejs_version")}")

    // Architechtury dependency
    //implementation("dev.architectury:architectury-forge:9.1.13")

    // ProbeJS
    //runtimeOnly("curse.maven:probejs-585406:5227399")
}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val replaceProperties = mapOf(
            "minecraft_version" to project.findProperty("minecraft_version") as String,
            "minecraft_version_range" to project.findProperty("minecraft_version_range") as String,
            "neo_version" to project.findProperty("neo_version") as String,
            "neo_version_range" to project.findProperty("neo_version_range") as String,
            "loader_version_range" to project.findProperty("loader_version_range") as String,
            "mod_id" to project.findProperty("mod_id") as String,
            "mod_name" to project.findProperty("mod_name") as String,
            "mod_license" to project.findProperty("mod_license") as String,
            "mod_version" to project.findProperty("mod_version") as String,
            "mod_authors" to project.findProperty("mod_authors") as String,
            "mod_description" to project.findProperty("mod_description") as String
    )
    inputs.properties(replaceProperties)
    expand(replaceProperties)

    // Exclude .java files or any other files that shouldn't have template expansion
    filesMatching("**/*.java") {
        exclude()
    }

    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}
// Include the output of "generateModMetadata" as an input directory for the build.
// This works with both building through Gradle and the IDE.
sourceSets["main"].resources.srcDir(generateModMetadata)
neoForge.ideSyncTask(generateModMetadata)

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            artifactId = "${property("mod_id")}"
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${System.getenv("local_maven")}")
        }
    }
}

idea {
    module {
        for (fileName in listOf("run", "out", "logs")) {
            excludeDirs.add(file(fileName))
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}