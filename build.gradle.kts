plugins {
    java
    `maven-publish`
    alias(libs.plugins.lavalink)
}

group = "space.essem"
version = "0.2.3"

lavalinkPlugin {
    name = "lava-xm-plugin"
    apiVersion = libs.versions.lavalink.api
    serverVersion = libs.versions.lavalink.server
}

repositories {
    maven(url = "https://dankmemeitthefrog.github.io/maven-repo")
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
}