package 리라;

import java.awt.Robot;
import java.awt.event.InputEvent;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClicker {

    public static void main(String[] args) {
        try {
            // Robot 객체 생성
            Robot robot = new Robot();
            // 클릭 반복 시간 설정 (10초 = 10000밀리초)
            long duration = 10000;  // 10초
            long startTime = System.currentTimeMillis();
            Thread.sleep(2000);

           // while (System.currentTimeMillis() - startTime < duration) {
                // 마우스 이동 (화면의 좌표에 맞게 조정)
                int x = 790;  // 원하는 x 좌표
                int y = 938;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (왼쪽 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(1000);

                // 마우스 이동 (화면의 좌표에 맞게 조정)
                 x = 590;  // 원하는 x 좌표
                 y = 540;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (왼쪽 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(1000);

                // 마우스 이동 (화면의 좌표에 맞게 조정)
                x = 70;  // 원하는 x 좌표
                y = 100;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (왼쪽 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(1000);


           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
