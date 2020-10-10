package proxy;

import java.net.*;
import javax.swing.*;
import java.util.*;

public class TestDrive {
    TeaComponent teaComponent;
    JFrame frame = new JFrame("Tea Selection Viewer");
    JMenuBar menuBar;
    JMenu menu;
    Hashtable<String, String> teas = new Hashtable<String, String>();

    public static void main (String[] args) throws Exception {
        TestDrive testDrive = new TestDrive();
    }

    public TestDrive() throws Exception {
        teas.put("Earl Grey","https://images-na.ssl-images-amazon.com/images/I/619J0FZbZBL._SX679_.jpg");
        teas.put("English Breakfast","https://images-na.ssl-images-amazon.com/images/I/519mpZhG%2BTL.jpg");
        teas.put("Jasmine Green Tea","https://images-na.ssl-images-amazon.com/images/I/61TrdTwbryL.jpg");
        teas.put("White Tea","https://images-na.ssl-images-amazon.com/images/I/81RHi7uDgUL._SX679_.jpg");

        URL initialURL = new URL((String) teas.get("English Breakfast"));
        menuBar = new JMenuBar();
        menu = new JMenu("Tea Options");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        for (Enumeration<String> e = teas.keys(); e.hasMoreElements();) {
            String name = (String)e.nextElement();
            JMenuItem menuItem = new JMenuItem(name);
            menu.add(menuItem);
            menuItem.addActionListener(event -> {
                teaComponent.setTeaImage(new TeaProxy(getImageUrl(event.getActionCommand())));
                frame.repaint();
            });
        }

        Icon icon = new TeaProxy(initialURL);
        teaComponent = new TeaComponent(icon);
        frame.getContentPane().add(teaComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);

    }

    URL getImageUrl(String name) {
        try {
            return new URL((String) teas.get(name));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
