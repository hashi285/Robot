package org.example.robot.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service

public class AdService {
    public void A(){
        try {
            // Robot 객체 생성
            Robot robot = new Robot();

            // 캡처할 화면의 크기 설정
            Rectangle captureRect = new Rectangle(136, 109, 760, 450);


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
