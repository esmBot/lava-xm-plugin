package net.esmbot.lavaxm;

import org.helllabs.libxmp.Xmp;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "plugins.xm")
@Component
public class LavaXmConfig {
  private int ampFactor = 1;
  private int interpolation = Xmp.INTERP_LINEAR;
  private boolean vblank = false;
  private boolean fx9Bug = false;
  private boolean fixSampleLoop = false;
  private boolean amigaMixer = false;

  public int getAmpFactor() {
    return this.ampFactor;
  }

  public int getInterpolation() {
    return this.interpolation;
  }

  public boolean getVblank() {
    return this.vblank;
  }

  public boolean getFx9Bug() {
    return this.fx9Bug;
  }

  public boolean getFixSampleLoop() {
    return this.fixSampleLoop;
  }

  public boolean getAmigaMixer() {
    return this.amigaMixer;
  }

  public void setAmpFactor(int ampFactor) {
    this.ampFactor = ampFactor;
  }

  public void setInterpolation(int interpolation) {
    this.interpolation = interpolation;
  }

  public void setVblank(boolean vblank) {
    this.vblank = vblank;
  }

  public void setFx9Bug(boolean fx9Bug) {
    this.fx9Bug = fx9Bug;
  }

  public void setFixSampleLoop(boolean fixSampleLoop) {
    this.fixSampleLoop = fixSampleLoop;
  }

  public void setAmigaMixer(boolean amigaMixer) {
    this.amigaMixer = amigaMixer;
  }
}