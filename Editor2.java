public class Editor2 {
    public static void main(String[] args) throws Exception {
        String filename = args[0];
        int newWidth = Integer.parseInt(args[1]);
        int newHeight = Integer.parseInt(args[2]);

        Color[][] image = Runigram.read(filename);
        Runigram.display(image);

        Color[][] scaled = Runigram.scaled(image, newWidth, newHeight);

        StdDraw.pause(2000);
        Runigram.display(scaled);
    }
}