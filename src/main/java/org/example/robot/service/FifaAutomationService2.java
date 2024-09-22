package org.example.robot.service;



import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.IOException;

public class FifaAutomationService2 {
    public void automatic() throws Exception {

        // 잠시 대기하여 앱이 열리도록 함
        Thread.sleep(5000);  // 5초 대기 (앱이 로드될 시간을 확보)

        try {
            // Sikuli로 버튼을 찾고 클릭
            Screen screen = new Screen();

            // 버튼 클릭 시도
            Pattern buttonImage = new Pattern("C:/Users/m5118/IdeaProjects/Robot/pig.png").similar(0.6); // 버튼 이미지 경로
            screen.click(buttonImage);
            screen.click(buttonImage);
            System.out.println("버튼이 클릭되었습니다.");
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
