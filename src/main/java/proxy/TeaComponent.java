package proxy;

import java.awt.*;
import javax.swing.*;

public class TeaComponent extends JComponent {
    private static final long serialVersionUID = 1L;
    private Icon teaImage;

    public TeaComponent(Icon teaImage) {
        this.teaImage = teaImage;
    }

    public void setTeaImage(Icon teaImage) {
        this.teaImage = teaImage;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = teaImage.getIconWidth();
        int h = teaImage.getIconHeight();
        int x = (500 - w)/2;
        int y = (500 - h)/2;
        teaImage.paintIcon(this, g, x, y);
    }
}
