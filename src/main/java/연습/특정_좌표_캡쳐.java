package 연습;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class 특정_좌표_캡쳐 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Select and Capture Rectangle");

        // 원본 이미지 불러오기
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\m5118\\Videos\\Captures\\1111.png");

        // 패널에 맞게 이미지를 축소하여 보여줌
        int panelWidth = 800; // 패널 너비
        int panelHeight = 600; // 패널 높이
        Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // 이미지를 표시하는 패널을 생성
        CapturePanel imagePanel = new CapturePanel(scaledIcon, imageIcon);

        imagePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (imagePanel.getPoint1() == null) {
                    imagePanel.setPoint1(e.getPoint());
                    System.out.println("첫 번째 클릭: " + imagePanel.getPoint1());
                } else {
                    imagePanel.setPoint2(e.getPoint());
                    System.out.println("두 번째 클릭: " + imagePanel.getPoint2());

                    // 캡처 실행
                    imagePanel.captureRegion(panelWidth, panelHeight);

                    // 패널 다시 그리기
                    imagePanel.repaint();

                    // 좌표 초기화
                    imagePanel.resetPoints();
                }
            }
        });

        frame.add(imagePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

// 이미지를 표시하고 캡처할 수 있는 패널 클래스
class CapturePanel extends JPanel {
    private ImageIcon scaledIcon; // 축소된 이미지
    private ImageIcon originalIcon; // 원본 이미지
    private Point point1 = null;
    private Point point2 = null;

    public CapturePanel(ImageIcon scaledIcon, ImageIcon originalIcon) {
        this.scaledIcon = scaledIcon;
        this.originalIcon = originalIcon;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void resetPoints() {
        point1 = null;
        point2 = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledIcon.getImage(), 0, 0, this);

        if (point1 != null && point2 != null) {
            g.setColor(Color.RED);
            int x = Math.min(point1.x, point2.x);
            int y = Math.min(point1.y, point2.y);
            int width = Math.abs(point2.x - point1.x);
            int height = Math.abs(point2.y - point1.y);
            g.drawRect(x, y, width, height);
        }
    }

    // 특정 좌표 영역을 캡처하는 메서드
    public void captureRegion(int panelWidth, int panelHeight) {
        if (point1 == null || point2 == null) {
            System.out.println("좌표가 설정되지 않았습니다.");
            return;
        }

        // 좌표를 원본 이미지 비율에 맞게 변환
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        double scaleX = (double) originalWidth / panelWidth;
        double scaleY = (double) originalHeight / panelHeight;

        int x = (int) (Math.min(point1.x, point2.x) * scaleX);
        int y = (int) (Math.min(point1.y, point2.y) * scaleY);
        int width = (int) (Math.abs(point2.x - point1.x) * scaleX);
        int height = (int) (Math.abs(point2.y - point1.y) * scaleY);

        BufferedImage bufferedImage = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(originalIcon.getImage(), 0, 0, null);
        g2d.dispose();

        // 선택한 영역의 서브 이미지 생성
        BufferedImage subImage = bufferedImage.getSubimage(x, y, width, height);

        try {
            // 캡처된 이미지를 파일로 저장
            ImageIO.write(subImage, "png", new File("captured_region.png"));
            System.out.println("캡처 완료: captured_region.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
