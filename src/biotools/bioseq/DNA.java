package biotools.bioseq;

import java.awt.*;

public class DNA extends Sequence {
    public DNA(String sequence) {
        super(sequence);
        this.setColor();
    }

    private void setColor() {
        Colors[] col = new Colors[this.seq.length()];
        for (int i = 0; i < this.seq.length(); i++) {
            String letter = Character.toString(this.seq.charAt(i));
            if (letter.equals("C") || letter.equals("G")) {
                col[i] = Colors.RED;
            } else {
                col[i] = Colors.YELLOW;
            }
        }
        this.color = col;
    }

    public float getGcPerc() {
        float g = (float) this.seq.chars().filter(ch -> ch == 'G').count();
        float c = (float) this.seq.chars().filter(ch -> ch == 'C').count();
        float l = (float) this.getLength();
        return (g + c) / l * 100;
    }

}
