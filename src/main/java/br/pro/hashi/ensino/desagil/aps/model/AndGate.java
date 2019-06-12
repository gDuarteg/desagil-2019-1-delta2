package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandRight;


    public AndGate() {
        super("AND", 2, 1);
        nandLeft = new NandGate();
        nandRight = new NandGate();
        nandRight.connect(0, nandLeft);
        nandRight.connect(1, nandLeft);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        nandLeft.connect(inputPin, emitter);
    }
}
