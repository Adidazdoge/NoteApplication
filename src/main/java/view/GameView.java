package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * Game view.
 */

public class GameView extends JFrame {
    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings",
                       "checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS"})
    public GameView() {
        super("Game");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        final JLabel dayLabel = new JLabel("Day: 50");
        dayLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(dayLabel);

        final JLabel foodLabel = new JLabel("Food: 50");
        final JLabel waterLabel = new JLabel("Water: 30");
        final JLabel peopleLabel = new JLabel("People: 20");
        final JLabel weaponLabel = new JLabel("Weapon: 15");
        final JLabel actionAvailableLabel = new JLabel("Action Available: 3");
        container.add(foodLabel);
        container.add(waterLabel);
        container.add(peopleLabel);
        container.add(weaponLabel);
        container.add(actionAvailableLabel);

        final JPanel mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.TWO_HUNDRED));
        container.add(mapPanel);
        final JLabel mapLabel = new JLabel("Mini Map");
        mapPanel.add(mapLabel);

        final JLabel infoLabel = new JLabel("Information:");
        final JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setBackground(Color.LIGHT_GRAY);
        infoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(infoLabel);
        container.add(infoArea);

        final JButton broadcastButton = new JButton("Broadcast");
        final JButton gatherButton = new JButton("Gather");
        final JButton upButton = new JButton("Up");
        final JButton downButton = new JButton("Down");
        final JButton leftButton = new JButton("Left");
        final JButton rightButton = new JButton("Right");
        final JButton eventButton = new JButton("Event");
        final JButton nextDayButton = new JButton("Next Day");
        final JButton infoButton = new JButton("information (only brief info)");
        container.add(broadcastButton);
        container.add(gatherButton);
        container.add(upButton);
        container.add(downButton);
        container.add(leftButton);
        container.add(rightButton);
        container.add(eventButton);
        container.add(nextDayButton);
        container.add(infoButton);

        layout.putConstraint(SpringLayout.WEST, dayLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, dayLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, foodLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, foodLabel, Constants.TWENTY, SpringLayout.SOUTH, dayLabel);

        layout.putConstraint(SpringLayout.WEST, waterLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, waterLabel, Constants.TWENTY, SpringLayout.SOUTH, foodLabel);

        layout.putConstraint(SpringLayout.WEST, peopleLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, peopleLabel, Constants.TWENTY, SpringLayout.SOUTH, waterLabel);

        layout.putConstraint(SpringLayout.WEST, weaponLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, weaponLabel, Constants.TWENTY, SpringLayout.SOUTH, peopleLabel);

        layout.putConstraint(SpringLayout.WEST, actionAvailableLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, actionAvailableLabel, Constants.TWENTY, SpringLayout.SOUTH, weaponLabel);

        layout.putConstraint(SpringLayout.EAST, mapPanel, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, mapPanel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, infoLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, infoLabel, Constants.TWENTY, SpringLayout.SOUTH, actionAvailableLabel);

        layout.putConstraint(SpringLayout.NORTH, infoArea, Constants.TEN, SpringLayout.SOUTH, infoLabel);
        layout.putConstraint(SpringLayout.SOUTH, infoArea, -Constants.ONE_HUNDRED, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.WEST, broadcastButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, broadcastButton, Constants.TWENTY, SpringLayout.SOUTH, infoArea);

        layout.putConstraint(SpringLayout.WEST, gatherButton, Constants.TWENTY, SpringLayout.EAST, broadcastButton);
        layout.putConstraint(SpringLayout.NORTH, gatherButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, eventButton, Constants.TWENTY, SpringLayout.EAST, gatherButton);
        layout.putConstraint(SpringLayout.NORTH, eventButton, 0, SpringLayout.NORTH, gatherButton);

        layout.putConstraint(SpringLayout.WEST, nextDayButton, Constants.TWENTY, SpringLayout.EAST, eventButton);
        layout.putConstraint(SpringLayout.NORTH, nextDayButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.NORTH, upButton, Constants.TWENTY, SpringLayout.SOUTH, broadcastButton);
        layout.putConstraint(SpringLayout.WEST, upButton, Constants.TWENTY, SpringLayout.WEST, container);

        layout.putConstraint(SpringLayout.WEST, downButton, Constants.TWENTY, SpringLayout.EAST, upButton);
        layout.putConstraint(SpringLayout.NORTH, downButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, leftButton, Constants.TWENTY, SpringLayout.EAST, downButton);
        layout.putConstraint(SpringLayout.NORTH, leftButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, rightButton, Constants.TWENTY, SpringLayout.EAST, leftButton);
        layout.putConstraint(SpringLayout.NORTH, rightButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, infoButton, Constants.TWENTY, SpringLayout.EAST, rightButton);
        layout.putConstraint(SpringLayout.NORTH, infoButton, 0, SpringLayout.NORTH, upButton);

        // Add ActionListeners to Buttons
        final ActionListener openGameViewListener = e -> {
            dispose();
            new GameView();
        };

        final ActionListener openEventViewListener = e -> {
            dispose();
            new EventView();
        };
        eventButton.addActionListener(openEventViewListener);
        broadcastButton.addActionListener(openGameViewListener);
        gatherButton.addActionListener(openGameViewListener);
        nextDayButton.addActionListener(openGameViewListener);
        upButton.addActionListener(openGameViewListener);
        downButton.addActionListener(openGameViewListener);
        leftButton.addActionListener(openGameViewListener);
        rightButton.addActionListener(openGameViewListener);

        // Window settings
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameView();
    }
}
