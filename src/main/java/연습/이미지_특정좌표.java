package 연습;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class 이미지_특정좌표 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Mouse Click Example");
        ImageIcon icon = new ImageIcon("이미지 전처리.png");
        JLabel label = new JLabel(icon);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("클릭된 좌표: (" + e.getX() + " , " + e.getY() + ")");
            }
        });

        frame.add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}