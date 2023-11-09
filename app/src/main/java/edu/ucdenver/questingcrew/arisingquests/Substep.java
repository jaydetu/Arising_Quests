package edu.ucdenver.questingcrew.arisingquests;

public class Substep {
    private int id;
    private String step;

    public Substep(String step){
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
