package org.example.robot.enhanceService;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class 전처리이미지_특정좌표_숫자추출 {
    private final 강화등급_특정_메서드 A;
    /**
     * 전처리된 이미지에서 특정 좌표의 숫자를 추출하고 그 숫자에 따라 메서드를 실행합니다.
     *
     * @param imagePath 전처리된 이미지 경로
     * @param tesseract Tesseract 인스턴스
     * @param x         관심 영역의 시작 X 좌표
     * @param y         관심 영역의 시작 Y 좌표
     * @param width     관심 영역의 너비
     * @param height    관심 영역의 높이
     */
    public void extractNumberAndExecute(String imagePath, ITesseract tesseract, int x, int y, int width, int height) {
        // 이미지 읽기
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.err.println("이미지를 읽을 수 없습니다: " + imagePath);
            return;
        }

        // 관심 영역 설정
        Rect roi = new Rect(x, y, width, height);
        Mat roiImage = new Mat(image, roi);

        // 관심 영역 저장 (디버깅용)
        String roiImagePath = "이미지 특정 영역 강화 등급.png";
        Imgcodecs.imwrite(roiImagePath, roiImage);

        try {
            // 관심 영역에 대해 OCR 수행
            String text = tesseract.doOCR(new File(roiImagePath));
            System.out.println("추출된 텍스트: " + text);

            // 숫자만 추출 (공백 및 기타 문자 제거)
            text = text.replaceAll("[^0-9]", "");
            System.out.println("숫자만 추출된 텍스트: " + text);

            if (!text.isEmpty()) {
                // 숫자로 변환
                int number = Integer.parseInt(text);
                System.out.println("관심 영역에서 추출한 숫자: " + number);

                // 숫자에 따라 특정 메서드 실행 ---------------------------------------------------
                A.executeMethodByNumber(number);
            } else {
                System.out.println("관심 영역에서 숫자를 추출하지 못했습니다.");
            }

        } catch (TesseractException e) {
            System.err.println("OCR 오류: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("숫자 변환 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
