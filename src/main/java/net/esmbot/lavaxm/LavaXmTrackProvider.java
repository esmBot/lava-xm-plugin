package net.esmbot.lavaxm;

import com.sedmelluq.discord.lavaplayer.filter.AudioPipeline;
import com.sedmelluq.discord.lavaplayer.filter.AudioPipelineFactory;
import com.sedmelluq.discord.lavaplayer.filter.PcmFormat;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioProcessingContext;
import org.helllabs.libxmp.FrameInfo;
import org.helllabs.libxmp.Player;

public class LavaXmTrackProvider {
    private final Player player;
    private final AudioPipeline downstream;

    public LavaXmTrackProvider(AudioProcessingContext context, Player player) {
        this.player = player;
        this.downstream = AudioPipelineFactory.create(context, new PcmFormat(2, player.getSamplingRate()));
    }

    public void provideFrames() {
        Player.Callback callback = new Player.Callback() {
            @Override
            public boolean callback(final FrameInfo info, final Object args) {
                int bufSize = info.getBufferSize();
                short[] shortBuffer = new short[bufSize / 2];
                byte[] buffer = info.getBufferArray();
                for (int i = 0; i < bufSize; i += 2) {
                    shortBuffer[i / 2] = (short)(((buffer[i + 1] & 0xFF) << 8) | (buffer[i] & 0xFF));
                }
                try {
                    downstream.process(shortBuffer, 0, bufSize / 2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        };

        this.player.play(callback, false);
    }

    /*public void seekToTimecode(long timecode) {
        //long fileOffset = (timecode * info.sampleRate / 1000L) * info.blockAlign + info.startOffset;
        System.out.println(timecode);
        int seekedTo = player.seekTime((int) timecode);
        System.out.println(seekedTo);
        downstream.seekPerformed(timecode, timecode);
    }*/

    public void close() {
        this.player.stop();
        this.downstream.close();
    }
}
