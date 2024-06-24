package net.esmbot.lavaxm;

import com.sedmelluq.discord.lavaplayer.container.MediaContainerDetectionResult;
import com.sedmelluq.discord.lavaplayer.container.MediaContainerHints;
import com.sedmelluq.discord.lavaplayer.container.MediaContainerProbe;
import com.sedmelluq.discord.lavaplayer.tools.io.SeekableInputStream;
import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import org.helllabs.libxmp.FrameInfo;
import org.helllabs.libxmp.Player;
import org.helllabs.libxmp.Module;

import java.io.IOException;

import org.helllabs.libxmp.Xmp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LavaXmContainerProbe implements MediaContainerProbe {
  private static final Logger log = LoggerFactory.getLogger(LavaXmContainerProbe.class);

  private final LavaXmConfig config;

  public LavaXmContainerProbe(LavaXmConfig config) {
		log.info("Loading MOD/XM playback plugin...");

    this.config = config;

    int ampFactor = config.getAmpFactor();
    if (ampFactor > 3 || ampFactor < 0) {
      log.error("The ampFactor config value is invalid, must be between 0 and 3");
      return;
    }

    int interpolation = config.getInterpolation();
    if (interpolation > 2 || interpolation < 0) {
      log.error("The interpolation config value is invalid, must be 0 (nearest), 1 (linear), or 2 (cubic spline)");
      return;
    }
	}

  @Override
  public String getName() {
    return "xm";
  }

  @Override
  public boolean matchesHints(MediaContainerHints hints) {
    return false;
  }

  @Override
  public MediaContainerDetectionResult probe(AudioReference reference, SeekableInputStream inputStream)
      throws IOException {
    Player player = new Player();
    player.setSampleControlFlags(Xmp.SMPCTL_SKIP);
    try {
      player.loadModuleFromMemory(inputStream);
    } catch (IOException e) {
      return null;
    }

    log.debug("Track {} is a module.", reference.identifier);

    Module module = player.getModule();
    FrameInfo info = new FrameInfo();
    player.getFrameInfo(info);

    inputStream.seek(0);
    return MediaContainerDetectionResult.supportedFormat(this, null, new AudioTrackInfo(module.getName(), "Unknown artist", info.getTotalTime(), reference.identifier, false, reference.identifier));
  }

  @Override
  public AudioTrack createTrack(String parameters, AudioTrackInfo trackInfo, SeekableInputStream inputStream) {
    return new LavaXmAudioTrack(trackInfo, inputStream, config);
  }
}
