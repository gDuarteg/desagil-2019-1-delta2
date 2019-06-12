// FONTE DAS IMAGENS: https://en.wikipedia.org/wiki/Logic_gate (domínio público)

package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener {
    private static final int BORDER = 10;
    private static final int SWITCH_SIZE = 18;
    private static final int LIGHT_SIZE = 12;
    private static final int GATE_WIDTH = 90;
    private static final int GATE_HEIGHT = 60;

    private final Switch[] switches;
    private final Gate gate;
    private final JCheckBox[] inputBoxes;
    private final JCheckBox[] outputBoxes;
    private final Image image;

    public GateView(Gate gate) {
        super(GATE_HEIGHT);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        int x, y, step;

        x = BORDER;
        y = -(SWITCH_SIZE / 2);
        step = (GATE_HEIGHT / (inputSize + 1));
        for (JCheckBox inputBox : inputBoxes) {
            y += step;
            add(inputBox, x, y, SWITCH_SIZE, SWITCH_SIZE);
        }

        int outputSize = gate.getOutputSize();

        outputBoxes = new JCheckBox[outputSize];

        for (int i = 0; i < outputSize; i++) {
            outputBoxes[i] = new JCheckBox();
            outputBoxes[i].setEnabled(false);
        }
        y = -(SWITCH_SIZE / 2);
        step = (GATE_HEIGHT / (outputSize + 1));

        for (JCheckBox outputBox : outputBoxes) {
            y += step;
            add(outputBox, BORDER + SWITCH_SIZE + GATE_WIDTH, y, SWITCH_SIZE, SWITCH_SIZE);
        }


        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        int outputSize = gate.getOutputSize();
        if (outputSize == 1) {
            boolean result = gate.read(0);
            outputBoxes[0].setSelected(result);
        } else {
            boolean out1 = gate.read(0);
            boolean out2 = gate.read(1);

            outputBoxes[0].setSelected(out1);
            outputBoxes[1].setSelected(out2);
        }

        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, BORDER + SWITCH_SIZE, 0, GATE_WIDTH, GATE_HEIGHT, this);

        if (gate.read()) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillOval(BORDER + SWITCH_SIZE + GATE_WIDTH, (GATE_HEIGHT - LIGHT_SIZE) / 2, LIGHT_SIZE, LIGHT_SIZE);

        getToolkit().sync();
    }
}
