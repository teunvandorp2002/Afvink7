package biotools.bioseq;

import java.awt.*;

abstract class Sequence {
    public String seq;
    public Color[] color;

    public Sequence(String sequence) {
        this.seq = sequence;
    }

    /*public enum Colors {
        RED,
        BLUE,
        GREY,
        YELLOW;
        RED("255, 0, 0"),
        BLUE("0, 0, 255"),
        GREY("136, 136, 136"),
        YELLOW("255, 255, 0");

        Colors(final String string) {

        }
    }*/

    public void setSeq(String sequence) {
        this.seq = sequence;
    }

    public String getSeq() {
        return this.seq;
    }

    public int getLength() {
        return this.seq.length();
    }

    public Color[] getColor() {
        return this.color;
    }
}

