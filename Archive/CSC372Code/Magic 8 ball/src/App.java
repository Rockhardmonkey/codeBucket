import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
    }
}

class Magic8Ball {
    private String[] responses = {
        "It is certain",
        "Ask again later",
        "Don't count on it",
        "Yes",
        "No",
        "Outlook good",
        "Signs point to yes",
        "Cannot predict now",
        "Very doubtful",
        "Absolutely"
    };

    public String getResponse() {
        int index = (int) (Math.random() * responses.length);
        return responses[index];
    }
}
class Magic8BallGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Magic 8-Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Magic 8-Ball");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        titleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JTextArea responseArea = new JTextArea(5, 30);
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        responseArea.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        responseArea.setHorizontalAlignment(SwingConstants.CENTER);
        responseArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(responseArea);

        JButton askButton = new JButton("Ask Magic 8-Ball");
        askButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        askButton.addActionListener(e -> {
            Magic8Ball magic8Ball = new Magic8Ball();
            responseArea.setText(magic8Ball.getResponse());
        });

        panel.add(Box.createVerticalStrut(20));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(10));
        panel.add(askButton);
        panel.add(Box.createVerticalStrut(20));

        frame.add(panel);
        frame.setVisible(true);
    }
}