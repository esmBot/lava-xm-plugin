package space.essem.lavaxm;

import com.sedmelluq.discord.lavaplayer.container.MediaContainerRegistry;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.lavaplayer.extensions.format.xm.XmContainerProbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.arbjerg.lavalink.api.AudioPlayerManagerConfiguration;

@Service
public class XMPlugin implements AudioPlayerManagerConfiguration {
	private static final Logger log = LoggerFactory.getLogger(XMPlugin.class);

	public XMPlugin() {
		log.info("Loading MOD/XM playback plugin...");
	}

	@Override
	public AudioPlayerManager configure(AudioPlayerManager manager) {
		manager.registerSourceManager(new HttpAudioSourceManager(MediaContainerRegistry.extended(new XmContainerProbe())));
		return manager;
	}
}
