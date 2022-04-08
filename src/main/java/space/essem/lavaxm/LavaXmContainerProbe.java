package space.essem.lavaxm;

import com.sedmelluq.lavaplayer.extensions.format.xm.XmContainerProbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LavaXmContainerProbe extends XmContainerProbe {
  private static final Logger log = LoggerFactory.getLogger(LavaXmContainerProbe.class);

  public LavaXmContainerProbe() {
		log.info("Loading MOD/XM playback plugin...");
	}
}
