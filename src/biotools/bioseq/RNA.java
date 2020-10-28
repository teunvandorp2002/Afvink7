package biotools.bioseq;

import java.awt.*;

public class RNA extends Sequence {
    public RNA(String sequence) {
        super(sequence);
        //this.setColor();
    }

    public void setColor() {
        Color[] col = new Color[this.seq.length()];
        for (int i = 0; i < this.seq.length(); i++) {
            String letter = Character.toString(this.seq.charAt(i));
            if (letter.equals("C") || letter.equals("G")) {
                col[i] = Color.RED;
            } else if (letter.equals("A")) {
                col[i] = Color.YELLOW;
            } else {
                col[i] = Color.BLUE;
            }
        }
        this.color = col;
    }

}
