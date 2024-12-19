import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runigram {

    public static Color[][] read(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        scanner.next(); // Skip the "P3" header
        int cols = scanner.nextInt();
        int rows = scanner.nextInt();
        scanner.nextInt(); // Skip the maximum color value (255)

        Color[][] image = new Color[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int r = scanner.nextInt();
                int g = scanner.nextInt();
                int b = scanner.nextInt();
                image[i][j] = new Color(r, g, b);
            }
        }
        scanner.close();
        return image;
    }

    public static void print(Color[][] image) {
        for (Color[] row : image) {
            for (Color pixel : row) {
                System.out.printf("(%3d, %3d, %3d) ", pixel.getRed(), pixel.getGreen(), pixel.getBlue());
            }
            System.out.println();
        }
    }

    public static Color[][] flippedHorizontally(Color[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        Color[][] flipped = new Color[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flipped[i][cols - 1 - j] = image[i][j];
            }
        }
        return flipped;
    }

    public static Color[][] flippedVertically(Color[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        Color[][] flipped = new Color[rows][cols];
        for (int i = 0; i < rows; i++) {
            flipped[rows - 1 - i] = image[i];
        }
        return flipped;
    }

    public static Color[][] greyScaled(Color[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        Color[][] grey = new Color[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Color pixel = image[i][j];
                int luminance = (int) (0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());
                grey[i][j] = new Color(luminance, luminance, luminance);
            }
        }
        return grey;
    }

    public static Color[][] scaled(Color[][] image, int newWidth, int newHeight) {
        int rows = image.length;
        int cols = image[0].length;
        Color[][] scaled = new Color[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                int srcRow = i * rows / newHeight;
                int srcCol = j * cols / newWidth;
                scaled[i][j] = image[srcRow][srcCol];
            }
        }
        return scaled;
    }

    public static void morph(Color[][] source, Color[][] target, int steps) {
        int rows = source.length;
        int cols = source[0].length;
        for (int step = 0; step <= steps; step++) {
            double alpha = (double) (steps - step) / steps;
            Color[][] blended = new Color[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    blended[i][j] = blend(source[i][j], target[i][j], alpha);
                }
            }
            StdDraw.clear();
            StdDraw.setCanvasSize(512, 512);
            display(blended);
            StdDraw.pause(500);
        }
    }

    private static Color blend(Color c1, Color c2, double alpha) {
        int r = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());
        int g = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
        int b = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());
        return new Color(r, g, b);
    }

    public static void display(Color[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        StdDraw.setCanvasSize(cols, rows);
        StdDraw.setXscale(0, cols);
        StdDraw.setYscale(0, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Color pixel = image[i][j];
                StdDraw.setPenColor(pixel);
                StdDraw.filledRectangle(j + 0.5, rows - i - 0.5, 0.5, 0.5);
            }
        }
    }
}