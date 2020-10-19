package biotools.bioseq;

public class RNA extends Sequence {
    public RNA(String sequence) {
        super(sequence);
        this.setColor();
    }

    private void setColor() {
        Colors[] col = new Colors[this.seq.length()];
        for (int i = 0; i < this.seq.length(); i++) {
            String letter = Character.toString(this.seq.charAt(i));
            if (letter.equals("C") || letter.equals("G")) {
                col[i] = Colors.RED;
            } else if (letter.equals("A")) {
                col[i] = Colors.YELLOW;
            } else {
                col[i] = Colors.BLUE;
            }
        }
        this.color = col;
    }

}
