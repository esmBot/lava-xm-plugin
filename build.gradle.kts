plugins {
    java
    `maven-publish`
    alias(libs.plugins.lavalink)
}

group = "net.esmbot"
version = "0.2.4"

lavalinkPlugin {
    name = "lava-xm-plugin"
    apiVersion = libs.versions.lavalink.api
    serverVersion = libs.versions.lavalink.server
}

repositories {
    maven(url = "https://repo.projectlounge.pw/maven/releases")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    implementation(libs.libxmp)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "projectlounge"
            url = uri("https://repo.projectlounge.pw/maven/releases")
            credentials {
                username = System.getenv("MAVEN_NAME")
                password = System.getenv("MAVEN_SECRET")
            }
        }
    }
}