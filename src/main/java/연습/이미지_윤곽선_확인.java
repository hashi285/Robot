package 연습;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class 이미지_윤곽선_확인 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Select Rectangle");
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\m5118\\Videos\\Captures\\111.png");
        JLabel label = new JLabel(imageIcon);
        frame.add(label);

        label.addMouseListener(new MouseAdapter() {
            Point point1 = null;
            Point point2 = null;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (point1 == null) {
                    point1 = e.getPoint();
                    System.out.println("첫 번째 클릭: " + point1);
                } else {
                    point2 = e.getPoint();
                    System.out.println("두 번째 클릭: " + point2);

                    // width와 height 계산
                    int width = Math.abs(point2.x - point1.x);
                    int height = Math.abs(point2.y - point1.y);

                    System.out.println("계산된 width: " + width);
                    System.out.println("계산된 height: " + height);

                    // 관심 영역 표시
                    Graphics g = label.getGraphics();
                    g.setColor(Color.RED);
                    g.drawRect(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y), width, height);

                    // 초기화
                    point1 = null;
                }
            }
        });

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}