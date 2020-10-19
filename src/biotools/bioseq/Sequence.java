package biotools.bioseq;

import java.awt.*;

abstract class Sequence {
    public String seq;
    public Colors[] color;

    public Sequence(String sequence) {
        this.seq = sequence;
    }

    enum Colors {
        RED,
        BLUE,
        GREY,
        YELLOW
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

    public Colors[] getColor() {
        return this.color;
    }
}

