package proxy;

import java.net.*;
import java.awt.*;
import javax.swing.*;

public class TeaProxy implements Icon {
    volatile ImageIcon teaIcon;
    final URL teaImageURL;
    Thread retrievalThread;
    boolean retrieving = false;

    public TeaProxy(URL url) { teaImageURL = url; }

    public int getIconWidth() {
        if (teaIcon != null) {
            return teaIcon.getIconWidth();
        } else {
            return 500;
        }
    }

    public int getIconHeight() {
        if (teaIcon != null) {
            return teaIcon.getIconHeight();
        } else {
            return 500;
        }
    }

    synchronized void setTeaIcon(ImageIcon teaIcon) {
        this.teaIcon = teaIcon;
    }

    public void paintIcon(final Component c, Graphics  g, int x,  int y) {
        if (teaIcon != null) {
            teaIcon.paintIcon(c, g, x, y);
        } else {
            g.drawString("Loading tea selections, please wait...", x+100, y+100);
            if (!retrieving) {
                retrieving = true;

                retrievalThread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            setTeaIcon(new ImageIcon(teaImageURL, "Tea Image"));
                            c.repaint();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                retrievalThread = new Thread(() -> {
                    try {
                        setTeaIcon(new ImageIcon(teaImageURL, "Tea Image"));
                        c.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                retrievalThread.start();

            }
        }
    }
}
