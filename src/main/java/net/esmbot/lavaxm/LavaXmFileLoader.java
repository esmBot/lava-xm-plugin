package net.esmbot.lavaxm;

import com.sedmelluq.discord.lavaplayer.tools.io.SeekableInputStream;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioProcessingContext;
import org.helllabs.libxmp.Player;

import java.io.IOException;

public class LavaXmFileLoader {
    private final SeekableInputStream inputStream;

    public LavaXmFileLoader(SeekableInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public LavaXmTrackProvider loadTrack(AudioProcessingContext context) throws IOException {
        Player player = new Player(context.outputFormat.sampleRate);
        player.setDefaultPanning(50);
        player.loadModuleFromMemory(this.inputStream);

        return new LavaXmTrackProvider(context, player);
    }
}
