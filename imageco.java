import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class imageco {
    public static void main(String[] args) {
        String image1Path = "prabhas.jpg";
        String image2Path = "prabhas.jpg";                                                           

        try {
            BufferedImage image1 = ImageIO.read(new File(image1Path));
            BufferedImage image2 = ImageIO.read(new File(image2Path));

            int totalPixelsImage1 = image1.getWidth() * image1.getHeight();
            int totalPixelsImage2 = image2.getWidth() * image2.getHeight();

            int totalPixels = totalPixelsImage1 + totalPixelsImage2;

            System.out.println("Total pixels in image1: " + totalPixelsImage1);
            System.out.println("Total pixels in image2: " + totalPixelsImage2);
            System.out.println("Total pixels in both images: " + totalPixels);

            double similarity = calculateSimilarity(image1, image2);
            System.out.println("Similarity: " + similarity);

            int totalSimilarPixels = (int) (similarity * totalPixels);
            int totalDissimilarPixels = totalPixels - totalSimilarPixels;

            System.out.println("Total similar pixels: " + totalSimilarPixels);
            System.out.println("Total dissimilar pixels: " + totalDissimilarPixels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public static double calculateSimilarity(BufferedImage image1, BufferedImage image2) {
    int width = image1.getWidth();
    int height = image1.getHeight();
    int regionWidth = width / 3;
    int regionHeight = height / 3;
    double totalSimilarity = 0.0;
    int totalSimilarPixels = 0;
    int totalPixels = 0;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            BufferedImage region1 = image1.getSubimage(j * regionWidth, i * regionHeight, regionWidth, regionHeight);
            BufferedImage region2 = image2.getSubimage(j * regionWidth, i * regionHeight, regionWidth, regionHeight);

            int[] regionSimilarity = calculateRegionSimilarity(region1, region2);
            totalSimilarity += (double) regionSimilarity[0] / regionSimilarity[1];
            totalSimilarPixels += regionSimilarity[0];
            totalPixels += regionSimilarity[1];
        }
    }
    return totalSimilarity / 9.0;
}
public static int[] calculateRegionSimilarity(BufferedImage region1, BufferedImage region2) {
    int width = region1.getWidth();
    int height = region1.getHeight();
    int totalPixels = width * height;
    int similarPixels = 0;
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            int pixel1 = region1.getRGB(i, j);
            int pixel2 = region2.getRGB(i, j);
            if (pixel1 == pixel2) {
                similarPixels++;
            }
        }
    }
    return new int[]{similarPixels, totalPixels};
}
}