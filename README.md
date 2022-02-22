# Lavalink XM Plugin
This is a plugin for [Lavalink](https://github.com/freyacodes/Lavalink) that adds support for the MOD, XM, and S3M music tracker module formats via [IBXM](https://github.com/martincameron/micromod) and the official [Lavaplayer](https://github.com/sedmelluq/lavaplayer) XM plugin.

## Usage
You have two options: either download the latest JAR file and put it in your `plugins` folder, or add this to your `application.yml` file:
```yaml
lavalink:
  plugins:
    - dependency: "com.github.esmBot:lava-xm-plugin:vx.x.x" # replace vx.x.x with the latest release tag
      repository: "https://jitpack.io"
```

This plugin replaces the HTTP source, so if you want to use this you'll have to disable it in your `application.yml` file.

## Build
Building is simple provided you have a working JDK:
```sh
./gradlew build
```