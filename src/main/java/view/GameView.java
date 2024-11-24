package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{
    public GameView() {
        super("Game");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        final JLabel titleLabel = new JLabel("Game");
        titleLabel.setFont(new Font("Serif", Font.BOLD, Constants.THIRTY));
        container.add(titleLabel);

        // get from output
        final JLabel dayLabel = new JLabel("Day: 1");
        final JLabel foodLabel = new JLabel("Food: 50");
        final JLabel waterLabel = new JLabel("Water: 30");
        container.add(dayLabel);
        container.add(foodLabel);
        container.add(waterLabel);

        final JLabel infoLabel = new JLabel("Information:");
        // information
        final JTextArea infoArea = new JTextArea("System-generated information will appear here.");

        infoArea.setEditable(false);
        infoArea.setBackground(Color.LIGHT_GRAY);
        infoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(infoLabel);
        container.add(infoArea);

        final JButton action1 = new JButton("Action 1");
        final JButton action2 = new JButton("Action 2");
        final JButton action3 = new JButton("Action 3");
        final JButton action4 = new JButton("Action 4");
        container.add(action1);
        container.add(action2);
        container.add(action3);
        container.add(action4);

        final JPanel mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setPreferredSize(new Dimension(Constants.ONE_HUNDRED, Constants.ONE_HUNDRED));
        container.add(mapPanel);
        final JLabel mapLabel = new JLabel("Small Map");
        mapPanel.add(mapLabel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, dayLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, dayLabel, Constants.TWENTY, SpringLayout.SOUTH, titleLabel);

        layout.putConstraint(SpringLayout.WEST, foodLabel, Constants.TWENTY, SpringLayout.EAST, dayLabel);
        layout.putConstraint(SpringLayout.NORTH, foodLabel, 0, SpringLayout.NORTH, dayLabel);

        layout.putConstraint(SpringLayout.WEST, waterLabel, Constants.TWENTY, SpringLayout.EAST, foodLabel);
        layout.putConstraint(SpringLayout.NORTH, waterLabel, 0, SpringLayout.NORTH, dayLabel);

        layout.putConstraint(SpringLayout.WEST, infoLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, infoLabel, Constants.TWENTY, SpringLayout.SOUTH, dayLabel);

        layout.putConstraint(SpringLayout.WEST, infoArea, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, infoArea, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, infoArea, Constants.TEN, SpringLayout.SOUTH, infoLabel);
        layout.putConstraint(SpringLayout.SOUTH, infoArea, -Constants.TWO_HUNDRED, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.WEST, mapPanel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, mapPanel, Constants.TWENTY, SpringLayout.SOUTH, infoArea);

        layout.putConstraint(SpringLayout.WEST, action1, Constants.TWENTY, SpringLayout.EAST, mapPanel);
        layout.putConstraint(SpringLayout.NORTH, action1, Constants.TWENTY, SpringLayout.SOUTH, infoArea);

        layout.putConstraint(SpringLayout.WEST, action2, 0, SpringLayout.WEST, action1);
        layout.putConstraint(SpringLayout.NORTH, action2, Constants.TWENTY, SpringLayout.SOUTH, action1);

        layout.putConstraint(SpringLayout.WEST, action3, 0, SpringLayout.WEST, action1);
        layout.putConstraint(SpringLayout.NORTH, action3, Constants.TWENTY, SpringLayout.SOUTH, action2);

        layout.putConstraint(SpringLayout.WEST, action4, 0, SpringLayout.WEST, action1);
        layout.putConstraint(SpringLayout.NORTH, action4, Constants.TWENTY, SpringLayout.SOUTH, action3);

        // 窗口设置
        setSize(Constants.SIX_HUNDRED, Constants.EIGHT_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new GameView();
    }
}
