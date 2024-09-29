package org.example.robot.enhanceService;


import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class 이미지_특정위치_숫자_추출 {
    private final 이미지_전처리 A;
    private final 전처리이미지_특정좌표_숫자추출 B;
    static {
        // OpenCV 라이브러리 로드
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("OpenCV 라이브러리를 로드할 수 없습니다: " + e.getMessage());
            System.exit(1);
        }
    }

    public void A() {
        // Tesseract 인스턴스 생성
        ITesseract tesseract = new Tesseract();

        // Tesseract 데이터 경로 설정 (traineddata 파일이 있는 폴더 경로)
        tesseract.setDatapath("C:\\Users\\m5118\\IdeaProjects\\Robot\\tessdata");
        tesseract.setLanguage("eng"); // OCR에 사용할 언어 설정 (영어)

        // 원본 이미지 파일 경로 설정
        String originalImagePath = "강화 전체 화면.png";
        File originalImageFile = new File(originalImagePath);

        // 이미지 파일 존재 여부 확인
        if (!originalImageFile.exists()) {
            System.err.println("이미지 파일이 존재하지 않습니다: " + originalImagePath);
            return;
        }

        // 이미지 전처리 수행
        String processedImagePath = "강화 전체 화면 전처리.png";
        boolean preprocessingSuccess = A.preprocessImage(originalImagePath, processedImagePath);

        if (!preprocessingSuccess) {
            System.err.println("이미지 전처리에 실패했습니다.");
            A();
            return;
        }

        // 전처리된 이미지에서 특정 좌표의 숫자를 추출하고 메서드 실행
        B.extractNumberAndExecute(processedImagePath, tesseract, 453, 735, 34, 20); // 예시 좌표
    }

}
