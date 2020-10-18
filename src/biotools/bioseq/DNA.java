package biotools.bioseq;

public class DNA extends Sequence {
    public DNA(String sequence) {
        super(sequence);
    }

    public float getGcPerc() {
        float g = (float) this.seq.chars().filter(ch -> ch == 'G').count();
        float c = (float) this.seq.chars().filter(ch -> ch == 'C').count();
        float l = (float) this.getLength();
        return (g + c) / l * 100;
    }

}
