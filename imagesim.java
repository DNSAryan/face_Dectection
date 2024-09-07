import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class imagesim {
    public static void main(String[] args) {
        // Path to the folder containing images
        String folderPath = "C:\\Users\\ARYAN\\OneDrive\\Desktop\\Java Project\\shahruk.jpg";

        // Get the query image from the user (you can use a GUI to get the path or any other method)
        String queryImagePath ="C:\\Users\\ARYAN\\OneDrive\\Desktop\\Java Project\\shahruk.jpg" ;

        try {
            BufferedImage queryImage = ImageIO.read(new File(queryImagePath));
            double[] queryHistogram = computeHistogram(queryImage);

            File folder = new File(folderPath);
            File[] imageFiles = folder.listFiles();

            if (imageFiles != null) {
                for (File imageFile : imageFiles) {
                    BufferedImage image = ImageIO.read(imageFile);
                    double[] histogram = computeHistogram(image);
                    double similarity = histogramIntersection(queryHistogram, histogram);
                    System.out.println(imageFile.getName() + " - Similarity: " + similarity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] computeHistogram(BufferedImage image) {
        // Here, you need to implement your own histogram computation method.
        // You can divide the image into bins based on the color range and count
        // the number of pixels in each bin to create the histogram.

        // For simplicity, let's assume you have a method that returns a double array
        // representing the color histogram of the input image.
        // The array should have the same length for all images in the folder.

        // Replace the code below with your histogram computation method.
        int histogramLength = 256; // Assuming 256 bins for each color channel (grayscale)
        double[] histogram = new double[histogramLength];
        // Your histogram computation logic here...
        return histogram;
    }

    private static double histogramIntersection(double[] histA, double[] histB) {
        // Computes the Histogram Intersection similarity between two histograms
        double similarity = 0.0;
        for (int i = 0; i < histA.length; i++) {
            similarity += Math.min(histA[i], histB[i]);
        }
        return similarity;
    }
}