# Lavalink XM Plugin
This is a plugin for [Lavalink](https://github.com/freyacodes/Lavalink) that adds support for various [tracker module](https://en.wikipedia.org/wiki/Module_file) formats via the [libxmp](https://github.com/libxmp/libxmp) library.

## Usage
This plugin requires Lavalink version 3.5-rc1 or later.

You have two options: either download the latest JAR file and put it in your `plugins` folder, or add this to your `application.yml` file:
```yaml
lavalink:
  plugins:
    - dependency: "net.esmbot:lava-xm-plugin:vx.x.x" # replace vx.x.x with the latest release tag
      repository: "https://repo.projectlounge.pw/maven/releases"
```

## Build
Building is simple provided you have a working JDK:
```sh
./gradlew build
```