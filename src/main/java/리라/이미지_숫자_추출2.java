package 리라;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class 이미지_숫자_추출2 {
    public static void main(String[] args) {
        // Tesseract 인스턴스 생성
        ITesseract tesseract = new Tesseract();

        // Tesseract 데이터 경로 설정 (traineddata 파일이 있는 폴더 경로)
        tesseract.setDatapath("C:\\Users\\m5118\\IdeaProjects\\Robot\\tessdata");
        tesseract.setLanguage("eng"); // OCR에 사용할 언어 설정 (영어)

        // 원본 이미지 파일 경로 설정
        String originalImagePath = "C:\\Users\\m5118\\Videos\\Captures\\3강.png";
        File originalImageFile = new File(originalImagePath);

}
}