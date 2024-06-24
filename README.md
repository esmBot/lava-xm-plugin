# Lavalink XM Plugin
This is a plugin for [Lavalink](https://github.com/freyacodes/Lavalink) that adds support for various [tracker module](https://en.wikipedia.org/wiki/Module_file) formats via the [libxmp](https://github.com/libxmp/libxmp) library.

## Usage
This plugin requires Lavalink version 3.5-rc1 or later.

You have two options: either download the latest JAR file and put it in your `plugins` folder, or add this to your `application.yml` file:
```yaml
lavalink:
  plugins:
    - dependency: "net.esmbot:lava-xm-plugin:x.x.x" # replace x.x.x with the latest release tag
      repository: "https://repo.projectlounge.pw/maven/releases"
```

Config options are listed below with their default values:
```yaml
plugins:
  xm:
    ampFactor: 1         # Amplification factor, can be between 0 and 3
    interpolation: 1     # Mixer interpolation type (0 = Nearest neighbor, 1 = Linear, 2 = Cublic spline)
    vblank: false        # Uses vblank timing during playback
    fx9Bug: false        # Emulates the FX9 effect bug from Protracker 2.x
    fixSampleLoop: false # Divides sample loop start values in half
    amigaMixer: false    # Emulates the Paula mixer in Amiga modules
```

## Build
Building is simple provided you have a working JDK:
```sh
./gradlew build
```