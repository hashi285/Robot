package org.example.robot.enhanceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class 강화등급_특정_메서드 {
    /**
     * 숫자에 따라 특정 메서드를 실행합니다.
     *
     * @param number 추출된 숫자
     */
    public void executeMethodByNumber(int number) {
        switch (number) {
            case 1:
                method1();
                break;
            case 2:
                method2();
                break;
            case 3:
                method3();
                break;
            // 추가로 필요한 숫자와 메서드를 정의하세요.
            default:
                System.out.println("해당 숫자에 대한 메서드가 없습니다: " + number);
                break;
        }
    }

    // 메서드 예시
    private static void method1() {
        System.out.println("메서드 1 실행");
        // 메서드 1의 로직 구현 ( 현재 카드로 강화 실행 )
    }

    private static void method2() {
        System.out.println("메서드 2 실행");
        // 메서드 2의 로직 구현 ( 다음 카드 확인 )
    }

    private static void method3() {
        System.out.println("메서드 3 실행");
        // 메서드 3의 로직 구현 ( 다음 카드 확인 )
    }
}
