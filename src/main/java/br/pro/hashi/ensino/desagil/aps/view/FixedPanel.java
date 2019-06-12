// NÃO É NECESSÁRIO NEM PERMITIDO MODIFICAR ESTA CLASSE!

package br.pro.hashi.ensino.desagil.aps.view;

import javax.swing.*;
import java.awt.*;

// Componente criada especialmente para o projeto, que é
// basicamente um JPanel de tamanho fixo. Fixar tamanho
// geralmente não é considerada uma boa prática de design
// de interface, mas fazemos isso aqui por simplicidade.
public class FixedPanel extends JPanel {

    // O construtor recebe a largura e a altura do painel.
    protected FixedPanel(int height) {

        // É muito importante estabelecer que esse painel
        // não tem layout, pois caso contrário seu tamanho
        // vai ser definido pelo tamanho de seu conteúdo.
        setLayout(null);

        // Usamos esse método nos Desafios, vocês lembram?
        setPreferredSize(new Dimension(140, height));
    }

    // Sobrecarga do método add que permite definir posição
    // e tamanho de cada componente adicionada ao painel.
    // Fixar posição e tamanho também não é uma boa prática,
    // pois o normal é deixar o layout decidir. Novamente,
    // escolhemos fazer isso aqui para priorizar simplicidade.
    protected Component add(Component comp, int x, int y, int width, int height) {

        // Usa a implementação original para adicionar.
        super.add(comp);

        // Redefine posição e tamanho da componente.
        comp.setBounds(x, y, width, height);

        return comp;
    }
}
