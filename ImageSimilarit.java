import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_imgcodecs.*;
import org.bytedeco.javacpp.opencv_imgproc.*;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgproc.CvScalar;

public class ImageSimilarit {
    public static void main(String[] args) {
        try {
            Mat image1 = imread("shahruk.jpg");
            Mat image2 = imread("th.jpg");

            // Compare the similarity between the two images using SSIM
            double similarity = calculateSSIM(image1, image2);
            System.out.println("Similarity: " + similarity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double calculateSSIM(Mat image1, Mat image2) {
        Mat grayImage1 = new Mat();
        Mat grayImage2 = new Mat();
        Mat diffImage = new Mat();
        Mat ssimMap = new Mat();

        // Convert images to grayscale
        cvtColor(image1, grayImage1, COLOR_BGR2GRAY);
        cvtColor(image2, grayImage2, COLOR_BGR2GRAY);

        // Calculate SSIM
        SSIM(grayImage1, grayImage2, ssimMap, diffImage);

        // Extract the mean SSIM value from the map
        CvScalar meanSSIM = cvAvg(ssimMap);

        return meanSSIM.val(0); // Return the mean SSIM value
    }
}
