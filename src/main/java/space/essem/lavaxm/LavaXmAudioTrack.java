package space.essem.lavaxm;

import com.sedmelluq.discord.lavaplayer.container.wav.WavAudioTrack;
import com.sedmelluq.discord.lavaplayer.tools.io.SeekableInputStream;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.BaseAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.LocalAudioTrackExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LavaXmAudioTrack extends BaseAudioTrack {
    private static final Logger log = LoggerFactory.getLogger(WavAudioTrack.class);
    private final SeekableInputStream inputStream;

    public LavaXmAudioTrack(AudioTrackInfo trackInfo, SeekableInputStream inputStream) {
        super(trackInfo);
        this.inputStream = inputStream;
    }

    @Override
    public void process(LocalAudioTrackExecutor executor) throws Exception {
        LavaXmTrackProvider trackProvider = (new LavaXmFileLoader(this.inputStream)).loadTrack(executor.getProcessingContext());

        try {
            log.debug("Starting to play module {}", this.getIdentifier());
            executor.executeProcessingLoop(trackProvider::provideFrames, null);
        } finally {
            trackProvider.close();
        }
    }
}
