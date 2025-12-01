package Net1Part2_Chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalTime;

public class LoginTimerApp {
    private boolean running = false;
    private Timer timer;
    private LocalTime startTime;
    private JLabel timerLabel;
    private JButton loginButton;
    private JButton logoutButton;

    public LoginTimerApp() {
        // Create the JFrame
        JFrame frame = new JFrame("Login Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        // Create the timer label
        timerLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(timerLabel, BorderLayout.CENTER);

        // Create the buttons
        loginButton = new JButton("Login");
        logoutButton = new JButton("Logout");
        logoutButton.setEnabled(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(logoutButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Logout button action listener
        logoutButton.addActionListener(e -> stopTimer());

        // Display the frame
        frame.setVisible(true);
    }

    public void startTimer() {
        if (!running) {
            running = true;
            startTime = LocalTime.now();
            logoutButton.setEnabled(true);
            loginButton.setEnabled(false);

            // Start the timer
            timer = new Timer(1000, e -> {
                Duration elapsed = Duration.between(startTime, LocalTime.now());
                long hours = elapsed.toHours();
                long minutes = elapsed.toMinutes() % 60;
                long seconds = elapsed.getSeconds() % 60;
                timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            });
            timer.start();
        }
    }

    public void stopTimer() {
        if (running) {
            running = false;
            timer.stop();
            logoutButton.setEnabled(false);
            loginButton.setEnabled(true);
        }
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
