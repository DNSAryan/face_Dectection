import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageSimilarity {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("shahruk.jpg"));
            BufferedImage image2 = ImageIO.read(new File("th.jpeg"));
            
            // Compare the similarity between the two images
            double similarity = compareImages(image, image2);
            System.out.println("Similarity: " + similarity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double compareImages(BufferedImage image1, BufferedImage image2) {
        // Resize the images to a fixed size for comparison
        int targetWidth = 100;
        int targetHeight = 100;

        BufferedImage resizedImage1 = resizeImage(image1, targetWidth, targetHeight);
        BufferedImage resizedImage2 = resizeImage(image2, targetWidth, targetHeight);

        // Calculate the mean squared error (MSE) between the two images
        double mse = calculateMSE(resizedImage1, resizedImage2);

        // Normalize the MSE to a similarity score (between 0 and 1)
        double similarity = 1.0 - (mse / (targetWidth * targetHeight * 3 * 255.0 * 255.0));
        return similarity;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public static double calculateMSE(BufferedImage image1, BufferedImage image2) {
        int width = image1.getWidth();
        int height = image1.getHeight();

        long sumSquaredDiff = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y);
                int rgb2 = image2.getRGB(x, y);

                int r1 = (rgb1 >> 16) & 0xFF;
                int g1 = (rgb1 >> 8) & 0xFF;
                int b1 = rgb1 & 0xFF;

                int r2 = (rgb2 >> 16) & 0xFF;
                int g2 = (rgb2 >> 8) & 0xFF;
                int b2 = rgb2 & 0xFF;

                int diffR = r1 - r2;
                int diffG = g1 - g2;
                int diffB = b1 - b2;

                sumSquaredDiff += diffR * diffR + diffG * diffG + diffB * diffB;
            }
        }

        return (double) sumSquaredDiff / (width * height);
    }
}