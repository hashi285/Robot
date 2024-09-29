package 연습;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class 화면캡쳐 {
    public static void main(String[] args) {
        try {
            // Robot 객체 생성
            Robot robot = new Robot();

            // 캡처할 화면의 크기 설정
           Rectangle captureRect = new Rectangle(177, 499, 760, 450);

            // 캡처할 화면의 크기 설정 (전체 화면)
           // Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // 화면 캡처
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);

            // 파일로 저장
            ImageIO.write(screenFullImage, "png", new File("강화 전체 화면.png"));

            System.out.println("화면 캡처 완료: full_screenshot.png");
        } catch (AWTException | IOException ex) {
            System.out.println("화면 캡처 오류: " + ex.getMessage());
        }
    }
}
