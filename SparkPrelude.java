import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SparkPrelude extends JFrame {

    public SparkPrelude() {
        setTitle("Spark Sched");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        ImageIcon logo = new ImageIcon("src/SparkSchedLogo.png");
        setIconImage(logo.getImage());

        JPanel namePanel = new JPanel();
        namePanel.setBounds(400, 200, 480, 80);
        //namePanel.setBackground(Color.PINK); // To show the bounds only
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(namePanel);

        // Load the custom font
        Font customFont = loadFont("src/HeyGotcha-Regular.ttf", 80);

        // JLabels for each letter in "SPARK"
        JLabel[] letterLabels = new JLabel[5];
        Color[] colors = {Color.decode("#545454"), Color.decode("#BEB6A8"),
                Color.decode("#D1CBBC"), Color.decode("#8E8374"), Color.decode("#5C5246")};
        String spark = "SPARK";

        for (int i = 0; i < spark.length(); i++) {
            letterLabels[i] = new JLabel(String.valueOf(spark.charAt(i)));
            letterLabels[i].setFont(customFont);
            letterLabels[i].setForeground(colors[i]);
            namePanel.add(letterLabels[i]);
        }

        // Add "SCHED" label
        JLabel schedLabel = new JLabel(" SCHED");
        schedLabel.setFont(customFont); // Use the same custom font
        schedLabel.setForeground(Color.decode("#545454")); // Set color to black
        namePanel.add(schedLabel);

        // Shape
        JPanel shapePanel = new JPanel();
        shapePanel.setBounds(400, 280, 250, 10);
        shapePanel.setBackground(Color.decode("#545454"));
        //shapePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(shapePanel);

        JLabel textPanel = new JLabel("<html>Turning plans into the spark<br>of success.</html>");
        textPanel.setBounds(660, 280, 250, 50);
        textPanel.setFont(new Font("Canva Sans", Font.PLAIN, 16));
        add(textPanel);

        RoundedButtonPanel createSched = new RoundedButtonPanel("Create Schedule");
        createSched.setFont(new Font("Arial", Font.PLAIN, 14));
        createSched.setBounds(710, 370, 150, 40);
        add(createSched);
        }



    private Font loadFont(String fontPath, float fontSize) {
        try {
            // Load the font file
            File fontFile = new File(fontPath);

            // Register the font with the Graphics Environment
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            // Create a font object from the font file
            return customFont.deriveFont(fontSize);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null; // Return a fallback font if loading fails
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SparkPrelude::new);
    }
}
