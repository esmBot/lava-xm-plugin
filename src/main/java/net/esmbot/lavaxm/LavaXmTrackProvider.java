package net.esmbot.lavaxm;

import com.sedmelluq.discord.lavaplayer.filter.AudioPipeline;
import com.sedmelluq.discord.lavaplayer.filter.AudioPipelineFactory;
import com.sedmelluq.discord.lavaplayer.filter.PcmFormat;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioProcessingContext;
import org.helllabs.libxmp.FrameInfo;
import org.helllabs.libxmp.Player;
import org.helllabs.libxmp.Xmp;

public class LavaXmTrackProvider {
    private final Player player;
    private final AudioPipeline downstream;
    private final LavaXmConfig config;
    private final FrameInfo info = new FrameInfo();
    //private long seeked = 0;

    public LavaXmTrackProvider(AudioProcessingContext context, Player player, LavaXmConfig config) {
        this.player = player;
        this.config = config;
        this.downstream = AudioPipelineFactory.create(context, new PcmFormat(1, player.getSamplingRate()));
    }

    public void provideFrames() {
        this.player.start();
        this.player.setAmplificationFactor(this.config.getAmpFactor());
        this.player.setInterpolation(this.config.getInterpolation());
        int playerFlags = (this.config.getVblank() ? Xmp.FLAGS_VBLANK : 0)
                          | (this.config.getFx9Bug() ? Xmp.FLAGS_FX9BUG : 0)
                          | (this.config.getFixSampleLoop() ? Xmp.FLAGS_FIXLOOP : 0)
                          | (this.config.getAmigaMixer() ? Xmp.FLAGS_A500 : 0);
        this.player.setFlags(playerFlags);

        while (this.player.playFrame(this.info)) {
			if (this.info.getLoopCount() > 0) {
				break;
			}

            /*if (this.seeked > 0) {
                this.downstream.seekPerformed(this.seeked, this.info.getTime());
                this.seeked = 0;
            }*/

			int bufSize = this.info.getBufferSize();
            short[] shortBuffer = new short[bufSize / 2];
            byte[] buffer = this.info.getBufferArray();
            for (int i = 0; i < bufSize; i += 2) {
                shortBuffer[i / 2] = (short)(((buffer[i + 1] & 0xFF) << 8) | (buffer[i] & 0xFF));
            }
            try {
                downstream.process(shortBuffer, 0, bufSize / 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
		}
		this.player.end();
    }

    /*public void seekToTimecode(long timecode) {
        //long fileOffset = (timecode * info.sampleRate / 1000L) * info.blockAlign + info.startOffset;
        System.out.println(timecode);
        int seekedTo = this.player.seekTime((int)timecode);
        System.out.println(seekedTo);
        //this.player.playFrame(this.info);
        //int time = this.info.getTime();
        //System.out.println(time);
        this.seeked = timecode;
        //this.downstream.seekPerformed(timecode, time);
    }*/

    public void close() {
        this.player.end();
        this.downstream.close();
    }
}
