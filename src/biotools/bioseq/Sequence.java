package biotools.bioseq;

import java.awt.*;

abstract class Sequence {
    public String seq;
    public Color[] color;

    public Sequence(String sequence) {
        this.seq = sequence;
    }

    public Sequence() {

    }

    public void setSeq(String sequence) {
        this.seq = sequence;
    }

    public String getSeq() {
        return this.seq;
    }

    public int getLength() {
        return this.seq.length();
    }
}

