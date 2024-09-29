package org.example.robot.enhanceService;

import lombok.RequiredArgsConstructor;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class 이미지_전처리 {
    /**
     * 이미지를 전처리하여 OCR의 인식률을 높입니다.
     *
     * @param inputImagePath  원본 이미지 경로
     * @param outputImagePath 전처리된 이미지 저장 경로
     * @return 전처리 성공 여부
     */
    public boolean preprocessImage(String inputImagePath, String outputImagePath) {
        // 이미지 읽기
        Mat image = Imgcodecs.imread(inputImagePath);
        if (image.empty()) {
            System.err.println("이미지를 읽을 수 없습니다: " + inputImagePath);


            // 이미지 캡쳐 매서드 재시작 ----------------------------------------------------------------------------


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
}
