package 연습;

import java.awt.*;
import java.awt.event.InputEvent;

public class 선수팩_구매 {

    public static void main(String[] args) {
        try {

            // Robot 객체 생성
            Robot robot = new Robot();
            // 클릭 반복 시간 설정 (10초 = 10000밀리초)
            long duration = 1000000000;  // 10초
            long startTime = System.currentTimeMillis();


            while (true) {
                // 마우스 이동 (상점 버튼)
                int x = 598;  // 원하는 x 좌표
                int y = 590;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (상점 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);


                x = 384;  // 원하는 x 좌표
                y = 118;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (선수팩 버튼)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);
                x = 542;  // 원하는 x 좌표
                y = 326;  // 원하는 y 좌표
                robot.mouseMove(x, y);
                Thread.sleep(1000);

                // 마우스 클릭 (구매 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);
                x = 686;  // 원하는 x 좌표
                y = 398;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (수량 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);
                x = 686;  // 원하는 x 좌표
                y = 374;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                //마우스 휠
                for (int a = 0; a < 10000; a++) {
                    robot.mouseWheel(100);
                }

                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);
                x = 548;  // 원하는 x 좌표
                y = 471;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (왼쪽 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);
                x = 644;  // 원하는 x 좌표
                y = 394;  // 원하는 y 좌표
                robot.mouseMove(x, y);

                // 마우스 클릭 (왼쪽 버튼 클릭)
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                System.out.println("버튼이 클릭되었습니다!");
                Thread.sleep(3000);

                while (System.currentTimeMillis() - startTime < duration) {
                    while (System.currentTimeMillis() - startTime < duration) {
                        x = 542;  // 원하는 x 좌표
                        y = 326;  // 원하는 y 좌표
                        robot.mouseMove(x, y);

                        // 마우스 클릭 (구매 버튼 클릭)
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                        System.out.println("버튼이 클릭되었습니다!");
                        Thread.sleep(3000);
                        x = 686;  // 원하는 x 좌표
                        y = 398;  // 원하는 y 좌표
                        robot.mouseMove(x, y);

                        // 마우스 클릭 (수량 버튼 클릭)
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                        System.out.println("버튼이 클릭되었습니다!");
                        Thread.sleep(3000);
                        x = 686;  // 원하는 x 좌표
                        y = 374;  // 원하는 y 좌표
                        robot.mouseMove(x, y);

                        //마우스 휠
                        for (int a = 0; a < 10000; a++) {
                            robot.mouseWheel(100);
                        }

                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                        System.out.println("버튼이 클릭되었습니다!");
                        Thread.sleep(3000);
                        x = 548;  // 원하는 x 좌표
                        y = 471;  // 원하는 y 좌표
                        robot.mouseMove(x, y);

                        // 마우스 클릭 (왼쪽 버튼 클릭)
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                        System.out.println("버튼이 클릭되었습니다!");
                        Thread.sleep(3000);
                        x = 644;  // 원하는 x 좌표
                        y = 394;  // 원하는 y 좌표
                        robot.mouseMove(x, y);

                        // 마우스 클릭 (왼쪽 버튼 클릭)
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


                        System.out.println("버튼이 클릭되었습니다!");
                        Thread.sleep(3000);

                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }}