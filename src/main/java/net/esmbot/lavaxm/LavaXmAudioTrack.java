package net.esmbot.lavaxm;

import com.sedmelluq.discord.lavaplayer.container.wav.WavAudioTrack;
import com.sedmelluq.discord.lavaplayer.tools.io.SeekableInputStream;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.BaseAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioProcessingContext;
import com.sedmelluq.discord.lavaplayer.track.playback.LocalAudioTrackExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.helllabs.libxmp.Player;

public class LavaXmAudioTrack extends BaseAudioTrack {
    private static final Logger log = LoggerFactory.getLogger(WavAudioTrack.class);
    private final SeekableInputStream inputStream;
    private final LavaXmConfig config;

    public LavaXmAudioTrack(AudioTrackInfo trackInfo, SeekableInputStream inputStream, LavaXmConfig config) {
        super(trackInfo);
        this.inputStream = inputStream;
        this.config = config;
    }

    @Override
    public void process(LocalAudioTrackExecutor executor) throws Exception {
        AudioProcessingContext context = executor.getProcessingContext();
        Player player = new Player(context.outputFormat.sampleRate);
        player.setDefaultPanning(this.config.getDefaultPan());
        player.loadModuleFromMemory(this.inputStream);
        LavaXmTrackProvider trackProvider = new LavaXmTrackProvider(context, player, this.config);

        try {
            log.debug("Starting to play module {}", this.getIdentifier());
            executor.executeProcessingLoop(trackProvider::provideFrames, null);
        } finally {
            trackProvider.close();
        }
    }
}
