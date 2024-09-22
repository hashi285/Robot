package 리라;

import java.awt.*;

public class 마우스_휠 {

    public static void main(String[] args) {
        try {
            while (true) {
                Robot robot = new Robot();

                // 마우스의 현재 좌표를 가져옴
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                int x = (int) mouseLocation.getX();
                int y = (int) mouseLocation.getY();
                for (int a = 0; a < 100; a++) {
                    robot.mouseWheel(100);
                }
                // 좌표를 출력
                System.out.println("현재 마우스 위치: X=" + x + " Y=" + y);


                // 1초에 한 번씩 좌표를 갱신
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
