# Lavalink XM Plugin
This is a plugin for [Lavalink](https://github.com/freyacodes/Lavalink) that adds support for the MOD, XM, and S3M music tracker module formats via the [libxmp](https://github.com/libxmp/libxmp) library.

## Usage
This plugin requires Lavalink version 3.5-rc1 or later.

You have two options: either download the latest JAR file and put it in your `plugins` folder, or add this to your `application.yml` file:
```yaml
lavalink:
  plugins:
    - dependency: "com.github.esmBot:lava-xm-plugin:vx.x.x" # replace vx.x.x with the latest release tag
      repository: "https://jitpack.io"
```

## Build
Building is simple provided you have a working JDK:
```sh
./gradlew build
```