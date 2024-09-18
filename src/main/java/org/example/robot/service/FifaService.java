package org.example.robot.service;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FifaService {

    public String executeTask() {
        // ChromeDriver 경로 설정 (각자 환경에 맞게 설정)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\m5118\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Chrome 브라우저 열기
        WebDriver driver = new ChromeDriver();

        String result = "작업이 완료되었습니다!";  // 기본 성공 메시지

        try {
            // 웹사이트로 이동
            driver.get("https://google.com");

            // 텍스트 상자 요소 찾기
            WebElement textBox = driver.findElement(By.id("APjFqb"));

            // 텍스트 상자에 특정 단어 입력
            textBox.sendKeys("fc 온라인");
            textBox.sendKeys(Keys.RETURN);

            // 검색 결과 첫 번째 링크 클릭
            WebElement button = driver.findElement(By.className("L9C20lb"));
            button.click();

            System.out.println("작업이 성공적으로 완료되었습니다.");

        } catch (Exception e) {
            System.out.println("오류가 발생했습니다: " + e.getMessage());
            result = "작업 중 오류가 발생했습니다: " + e.getMessage();  // 오류 메시지를 반환
        } finally {
            // 브라우저 종료
            driver.quit();
        }

        return result;  // 결과 메시지 반환
    }
}
