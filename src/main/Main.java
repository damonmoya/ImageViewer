package main;

import control.*;
import model.Image;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame{
    private Map<String, Command> commands = new HashMap<>();
    private ImagePanel applicationDisplayPanel;

    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch(Exception ignored){}
        new Main().setVisible(true);
    }

    public Main() throws HeadlessException {
        deployUI();
        addCommands();
    }

    private void addCommands() {
        commands.put("Next",new NextImageCommand(applicationDisplayPanel));
        commands.put("Prev",new PrevImageCommand(applicationDisplayPanel));
    }

    private void deployUI() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imagePanel());
        this.add(toolBar(),BorderLayout.SOUTH);
    }

    private ImagePanel imagePanel() {
        ImagePanel panel = new ImagePanel(image());
        applicationDisplayPanel = panel;
        return panel;
    }

    private Image image() {
        return new FileImageReader("C:\\Users\\Asus\\Pictures").read();
    }

    private JMenuBar toolBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuBar.add(prevButton());
        menuBar.add(nextButton());
        return menuBar;
    }

    private JButton nextButton() {
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(e -> commands.get("Next").execute());
        return nextButton;
    }

    private JButton prevButton() {
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(e -> commands.get("Prev").execute());
        return prevButton;
    }
}