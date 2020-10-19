package biotools.bioseq;

public class Protein extends Sequence {
    public Protein(String sequence) {
        super(sequence);
        this.setColor();
    }

    private void setColor(){
        String polar = "QNHSTYC";
        String apolar = "AVLIPFWM";
        Colors[] col = new Colors[this.seq.length()];
        for (int i = 0; i < this.seq.length(); i++) {
            String letter = Character.toString(this.seq.charAt(i));
            if (polar.contains(letter)) {
                col[i] = Colors.BLUE;
            } else if (apolar.contains(letter)) {
                col[i] = Colors.RED;
            } else {
                col[i] = Colors.GREY;
            }
        }
        this.color = col;
    }
}
