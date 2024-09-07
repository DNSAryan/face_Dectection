import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class imagecheck {
    public static void main(String[] args) {
        String image1Path = "P.jpeg"; // Replace with the path to the first image
        String image2Path = "P2.jpeg"; // Replace with the path to the second image

        try {
            BufferedImage image1 = ImageIO.read(new File(image1Path));
            BufferedImage image2 = ImageIO.read(new File(image2Path));

            double similarity = calculateSimilarity(image1, image2);
            System.out.println("Similarity between the two images: " + similarity);
        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }

    public static double calculateSimilarity(BufferedImage image1, BufferedImage image2) {
        int width = image1.getWidth();
        int height = image1.getHeight();

        // Calculate the section width and height for dividing the images into 9 sections
        int sectionWidth = width / 3;
        int sectionHeight = height / 3;

        // Keep track of the accumulated similarity between corresponding sections
        double totalSimilarity = 0.0;

        // Iterate through the sections and calculate their similarity
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                BufferedImage section1 = image1.getSubimage(j * sectionWidth, i * sectionHeight, sectionWidth, sectionHeight);
                BufferedImage section2 = image2.getSubimage(j * sectionWidth, i * sectionHeight, sectionWidth, sectionHeight);

                double sectionSimilarity = calculateSectionSimilarity(section1, section2);
                totalSimilarity += sectionSimilarity;
            }
        }

        // Calculate the average similarity of all sections
        double averageSimilarity = totalSimilarity / 9.0;
        return averageSimilarity;
    }

    public static double calculateSectionSimilarity(BufferedImage section1, BufferedImage section2) {
        // Compare corresponding pixels in the sections and calculate similarity
        int width = section1.getWidth();
        int height = section1.getHeight();
        double totalPixelDifference = 0.0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = section1.getRGB(x, y);
                int rgb2 = section2.getRGB(x, y);

                int redDiff = (rgb1 >> 16) & 0xFF - (rgb2 >> 16) & 0xFF;
                int greenDiff = (rgb1 >> 8) & 0xFF - (rgb2 >> 8) & 0xFF;
                int blueDiff = (rgb1 & 0xFF) - (rgb2 & 0xFF);

                totalPixelDifference += Math.sqrt(redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff);
            }
        }

        // Calculate the average pixel difference in the section
        double averagePixelDifference = totalPixelDifference / (width * height);
        // Calculate and return the section similarity (1 - average pixel difference)
        return 1.0 - averagePixelDifference;
    }
}