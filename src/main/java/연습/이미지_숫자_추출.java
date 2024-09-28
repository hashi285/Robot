package 연습;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class 이미지_숫자_추출 {
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
        String originalImagePath = "C:\\Users\\m5118\\Videos\\Captures\\3강1.png";
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

        File processedImageFile = new File(processedImagePath);

        try {
            // 전처리된 이미지에서 텍스트 추출
            String text = tesseract.doOCR(processedImageFile);
            System.out.println("추출된 텍스트: " + text);

            // 추출한 텍스트에서 숫자만 추출 (공백 및 기타 문자 제거)
            text = text.replaceAll("[^0-9]", "");
            System.out.println("숫자만 추출된 텍스트: " + text);

            if (!text.isEmpty()) {
                // 숫자로 변환
                int number = Integer.parseInt(text);
                System.out.println("이미지에서 추출한 숫자: " + number);
            } else {
                System.out.println("이미지에서 숫자를 추출하지 못했습니다.");
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

        // 4. 이미지의 크기를 동적으로 계산하여 비율에 맞게 조정
        Size originalSize = image.size();
        double aspectRatio = originalSize.width / originalSize.height;
        double targetWidth = 1000; // 원하는 폭에 맞추어 조정
        double targetHeight = targetWidth / aspectRatio;

        Mat resized = new Mat();
        Imgproc.resize(binary, resized, new Size(targetWidth, targetHeight), 0, 0, Imgproc.INTER_LINEAR);

        // 5. 샤프닝 (경계 강화)
        Mat sharpened = new Mat();
        Mat kernel = new Mat(3, 3, CvType.CV_32F) {
            {
                put(0, 0, 0, -1, 0);
                put(1, 0, -1, 5, -1);
                put(2, 0, 0, -1, 0);
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
}
