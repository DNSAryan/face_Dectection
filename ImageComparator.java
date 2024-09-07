import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
public class ImageComparator {
// Method to divide an image into 9 equal sections
    public static BufferedImage[] divideImage(BufferedImage image) {
        BufferedImage[] sections = new BufferedImage[9];
        int sectionWidth = image.getWidth() / 3;
        int sectionHeight = image.getHeight() / 3;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Rectangle sectionRect = new Rectangle(col * sectionWidth, row * sectionHeight, sectionWidth, sectionHeight);
                PlanarImage sectionImage = PlanarImage.wrapRenderedImage(image.getSubimage(sectionRect.x, sectionRect.y, sectionRect.width, sectionRect.height));
                sections[row * 3 + col] = sectionImage.getAsBufferedImage();
            }
        }
        return sections;
    }// Method to compare two images for similarity
    public static boolean areImagesSimilar(BufferedImage image1, BufferedImage image2) {// Assuming both images have the same dimensions
        int width = image1.getWidth();
        int height = image1.getHeight();// Compare pixel-by-pixel
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String[] args) {// Replace these file paths with your input images
        String imageFilePath1 = "P.jpeg";
        String imageFilePath2 = "shahrukh1.jpeg";
        PlanarImage image1 = JAI.create("fileload", imageFilePath1);
        PlanarImage image2 = JAI.create("fileload", imageFilePath2);

        BufferedImage[] sections1 = divideImage(image1.getAsBufferedImage());
        BufferedImage[] sections2 = divideImage(image2.getAsBufferedImage());

        boolean areSimilar = true;
        for (int i = 0; i < 9; i++) {
            if (!areImagesSimilar(sections1[i], sections2[i])) {
                areSimilar = false;
                break;
            }
        }

        System.out.println("The two images are " + (areSimilar ? "similar" : "different") + " in their 9 sections.");
    }
}
