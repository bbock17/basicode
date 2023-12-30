package de.haupz.basicode;

/**
 * basicode parameters; may be read from the commandline.
 * 
 * @author Bernd Bock <chef@bernd-bock.de>
 */
public class Parameters {
    private boolean nowait   = false;
    private boolean nosound  = false;
    private boolean hold     = true;
    private String  filename = "";

    public boolean isNoWait() {
        return nowait;
    }

    public void setNoWait(boolean nowait) {
        this.nowait = nowait;
    }

    public boolean isNoSound() {
        return nosound;
    }

    public void setNoSound(boolean nosound) {
        this.nosound = nosound;
    }

    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
