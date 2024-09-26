package 연습;

public class 선수_강화 {
    public static void main(String[] args) {

        int 카드  = 1;
        int 재료 = 1;
        // 미등록 버튼 클릭
        // 능력치 버튼 클릭

        /**
         * 강화할 카드 확인
         */
        if(true){ // 카드가 없는 경우
            // 선수팩_구매 / 선수팩_열기 매서드 이용
        }
        // 강화 등급 확인

            if (카드 > 1) {
                System.out.println("2강 이상인 경우 pass");
                // pass 이후 다음 카드 확인

            } else {
                System.out.println("1강인 경우 클릭"); // 클릭

        }

        /**
         * 강화할 재료 확인
         */

        // 재료 리스트 확인
        for (int i = 0; i < 5; i++) { // 5회 반복
            if (재료 > 2) {
                System.out.println("재료가 2강 이상인 경우 pass");
            } else {
                System.out.println("재료가 1강인 경우 클릭 ");
            }
        }
        // 강화 실행 버튼 클릭
        // 확인 버튼 이후 본 화면 복귀

    }
}
