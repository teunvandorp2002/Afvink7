package biotools.bioseq;

import java.awt.*;

public class Protein extends Sequence {
    public Protein(String sequence) {
        super(sequence);
        this.setColor();
    }

    private void setColor(){
        String polar = "QNHSTYC";
        String apolar = "AVLIPFWM";
        Color[] col = new Color[this.seq.length()];
        for (int i = 0; i < this.seq.length(); i++) {
            String letter = Character.toString(this.seq.charAt(i));
            if (polar.contains(letter)) {
                col[i] = Color.BLUE;
            } else if (apolar.contains(letter)) {
                col[i] = Color.RED;
            } else {
                col[i] = Color.GRAY;
            }
        }
        this.color = col;
    }
}
