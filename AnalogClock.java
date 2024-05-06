import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class AnalogClock extends JPanel {
    private int hours;
    private int minutes;
    private int seconds;

    public AnalogClock() {
        // Set initial time
        updateTime();

        // Update time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
        Calendar now = Calendar.getInstance();
        hours = now.get(Calendar.HOUR);
        minutes = now.get(Calendar.MINUTE);
        seconds = now.get(Calendar.SECOND);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get panel dimensions
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        // Calculate clock size
        int clockSize = (width < height) ? width : height;

        // Draw clock outline
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Draw clock border
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(centerX - clockSize / 2, centerY - clockSize / 2, clockSize, clockSize);

        // Draw hours
        double hourAngle = Math.toRadians((hours % 12) * 30 + (minutes / 2));
        int hourHandLength = (int) (clockSize * 0.4);
        int hourHandX = (int) (centerX + hourHandLength * Math.sin(hourAngle));
        int hourHandY = (int) (centerY - hourHandLength * Math.cos(hourAngle));
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(centerX, centerY, hourHandX, hourHandY);

        // Draw minutes
        double minuteAngle = Math.toRadians(minutes * 6 + (seconds / 10));
        int minuteHandLength = (int) (clockSize * 0.45);
        int minuteHandX = (int) (centerX + minuteHandLength * Math.sin(minuteAngle));
        int minuteHandY = (int) (centerY - minuteHandLength * Math.cos(minuteAngle));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(centerX, centerY, minuteHandX, minuteHandY);

        // Draw seconds
        double secondAngle = Math.toRadians(seconds * 6);
        int secondHandLength = (int) (clockSize * 0.47);
        int secondHandX = (int) (centerX + secondHandLength * Math.sin(secondAngle));
        int secondHandY = (int) (centerY - secondHandLength * Math.cos(secondAngle));
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(centerX, centerY, secondHandX, secondHandY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        AnalogClock clock = new AnalogClock();
        frame.add(clock);

        frame.setVisible(true);
    }
}
