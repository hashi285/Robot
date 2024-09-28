package 연습;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.CvType;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class 이미지_특정위치_숫자_추출 {
    static {
        // OpenCV 라이브러리 로드
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("OpenCV 라이브러리를 로드할 수 없습니다: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        // Tesseract 인스턴스 생성
        ITesseract tesseract = new Tesseract();

        // Tesseract 데이터 경로 설정 (traineddata 파일이 있는 폴더 경로)
        tesseract.setDatapath("C:\\Users\\m5118\\IdeaProjects\\Robot\\tessdata");
        tesseract.setLanguage("eng"); // OCR에 사용할 언어 설정 (영어)

        // 원본 이미지 파일 경로 설정
        String originalImagePath = "C:\\Users\\m5118\\Videos\\Captures\\111.png";
        File originalImageFile = new File(originalImagePath);

        // 이미지 파일 존재 여부 확인
        if (!originalImageFile.exists()) {
            System.err.println("이미지 파일이 존재하지 않습니다: " + originalImagePath);
            return;
        }

        // 이미지 전처리 수행
        String processedImagePath = "C:\\Users\\m5118\\Videos\\Captures\\이미지 전처리.png";
        boolean preprocessingSuccess = preprocessImage(originalImagePath, processedImagePath);

        if (!preprocessingSuccess) {
            System.err.println("이미지 전처리에 실패했습니다.");
            return;
        }

        // 전처리된 이미지에서 특정 좌표의 숫자를 추출하고 메서드 실행
        extractNumberAndExecute(processedImagePath, tesseract, 453, 735, 34, 20); // 예시 좌표
    }

    /**
     * 이미지를 전처리하여 OCR의 인식률을 높입니다.
     *
     * @param inputImagePath  원본 이미지 경로
     * @param outputImagePath 전처리된 이미지 저장 경로
     * @return 전처리 성공 여부
     */
    private static boolean preprocessImage(String inputImagePath, String outputImagePath) {
        // 이미지 읽기
        Mat image = Imgcodecs.imread(inputImagePath);
        if (image.empty()) {
            System.err.println("이미지를 읽을 수 없습니다: " + inputImagePath);
            return false;
        }

        // 1. 그레이스케일 변환
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // 2. 노이즈 제거 (Gaussian Blur)
        Mat blurred = new Mat();
        Imgproc.GaussianBlur(gray, blurred, new Size(3, 3), 0);

        // 3. 이진화 (Adaptive Thresholding)
        Mat binary = new Mat();
        Imgproc.adaptiveThreshold(blurred, binary, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                Imgproc.THRESH_BINARY_INV, 11, 2);

        // 4. 이미지 확대 (해상도 향상)
        Mat resized = new Mat();
        Imgproc.resize(binary, resized, new Size(), 2, 2, Imgproc.INTER_LINEAR);

        // 5. 샤프닝 (경계 강화)
        Mat sharpened = new Mat();
        Mat kernel = new Mat(3, 3, CvType.CV_32F) {
            {
                put(0, 0,  0, -1,  0);
                put(1, 0, -1,  5, -1);
                put(2, 0,  0, -1,  0);
            }
        };
        Imgproc.filter2D(resized, sharpened, -1, kernel);

        // 전처리된 이미지 저장
        boolean success = Imgcodecs.imwrite(outputImagePath, sharpened);
        if (!success) {
            System.err.println("전처리된 이미지를 저장할 수 없습니다: " + outputImagePath);
        }
        return success;
    }

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
    private static void extractNumberAndExecute(String imagePath, ITesseract tesseract, int x, int y, int width, int height) {
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
        String roiImagePath = "C:\\Users\\m5118\\Videos\\Captures\\이미지 특정 영역 강화 등급.png";
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

                // 숫자에 따라 특정 메서드 실행
                executeMethodByNumber(number);
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

    /**
     * 숫자에 따라 특정 메서드를 실행합니다.
     *
     * @param number 추출된 숫자
     */
    private static void executeMethodByNumber(int number) {
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
        // 메서드 1의 로직 구현
    }

    private static void method2() {
        System.out.println("메서드 2 실행");
        // 메서드 2의 로직 구현
    }

    private static void method3() {
        System.out.println("메서드 3 실행");
        // 메서드 3의 로직 구현
    }
}
