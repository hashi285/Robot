package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebAutoClicker {

    // 재시도 횟수 제한
    private static final int MAX_RETRIES = 3;
    private static int retryCount = 0;

    public static void main(String[] args) {
        executeTask();
    }

    public static void executeTask() {
        // ChromeDriver 경로 설정 (각자 환경에 맞게 설정)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\m5118\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Chrome 브라우저 열기
        WebDriver driver = new ChromeDriver();

        // 웹사이트로 이동
        driver.get("https://google.com");

        try {
            // 텍스트 상자 요소 찾기 (id가 "APjFqb"인 텍스트 상자)
            WebElement textBox = driver.findElement(By.id("APjFqb"));

            // 텍스트 상자에 특정 단어 입력 (예: "Selenium 테스트")
            textBox.sendKeys("fc 온라인");

            System.out.println("텍스트 상자에 단어가 입력되었습니다!");

            // 엔터 키 입력 (검색 실행)
            textBox.sendKeys(Keys.RETURN);  // 또는 Keys.ENTER

            // className을 이용해 버튼 요소 찾기 (class 속성이 'MV3Tnb'인 첫 번째 요소)
            WebElement button = driver.findElement(By.className("L9C20lb"));

            // 버튼 클릭
            button.click();

            System.out.println("버튼이 클릭되었습니다!");

        } catch (Exception e) {
            driver.quit();
            retryCount++;

            System.out.println("오류가 발생했습니다: " + e.getMessage());

            // 재시도 횟수가 제한을 넘지 않았을 때 다시 시도
            if (retryCount <= MAX_RETRIES) {
                System.out.println("다시 시도 중... " + retryCount + "/" + MAX_RETRIES);
                executeTask();  // 메서드 재호출
            } else {
                driver.quit();
                System.out.println("재시도 횟수를 초과했습니다.");
            }

        } finally {
            // 브라우저 종료
            driver.quit();
        }
    }
}
