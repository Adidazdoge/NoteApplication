package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class GameView extends JFrame {
    private final JTextArea mapPanel;
    private final JTextArea infoBox;
    private boolean isMapVisible = true;

    public GameView() {
        super("Game");
        final int day = 60;
        final int food = 50;
        final int water = 30;
        final int people = 20;
        final int weapon = 15;
        final int action = 3;

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        final JLabel dayLabel = new JLabel("Day: " + day);
        dayLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(dayLabel);

        final JLabel foodLabel = new JLabel("Food:" + food);
        final JLabel waterLabel = new JLabel("Water: " + water);
        final JLabel peopleLabel = new JLabel("People: " + people);
        final JLabel weaponLabel = new JLabel("Weapon:" + weapon);
        final JLabel actionAvailableLabel = new JLabel("Action Available:" + action);
        container.add(foodLabel);
        container.add(waterLabel);
        container.add(peopleLabel);
        container.add(weaponLabel);
        container.add(actionAvailableLabel);

        // MiniMap Panel
        mapPanel = new JTextArea("Mini Map\nabcdefghijklm\nnuvwxyz");
        mapPanel.setEditable(false); // Set to non-editable
        mapPanel.setLineWrap(true); // Enable line wrap
        mapPanel.setWrapStyleWord(true); // Wrap by words
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setBackground(Color.LIGHT_GRAY);
        mapPanel.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.TWO_HUNDRED));
        container.add(mapPanel);

        // InfoBox TextArea
        infoBox = new JTextArea("Information Box");
        infoBox.setEditable(false);
        infoBox.setBackground(Color.LIGHT_GRAY);
        infoBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoBox.setVisible(false);
        container.add(infoBox);

        final JButton broadcastButton = new JButton("Broadcast");
        final JButton gatherButton = new JButton("Gather");
        final JButton upButton = new JButton("Up");
        final JButton downButton = new JButton("Down");
        final JButton leftButton = new JButton("Left");
        final JButton rightButton = new JButton("Right");
        final JButton eventButton = new JButton("Event");
        final JButton nextDayButton = new JButton("Next Day");
        final JButton infoButton = new JButton("log");
        container.add(broadcastButton);
        container.add(gatherButton);
        container.add(upButton);
        container.add(downButton);
        container.add(leftButton);
        container.add(rightButton);
        container.add(eventButton);
        container.add(nextDayButton);
        container.add(infoButton);

        // Layout Constraints
        extracted(layout, dayLabel, container, foodLabel, waterLabel, peopleLabel, weaponLabel, actionAvailableLabel
                , broadcastButton, gatherButton, eventButton, nextDayButton, upButton, downButton,
                leftButton, rightButton, infoButton);

        // Add ActionListeners
        infoButton.addActionListener(e -> toggleInfoBox());

        // Window settings
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    private void extracted(SpringLayout layout, JLabel dayLabel, Container container, JLabel foodLabel,
                           JLabel waterLabel, JLabel peopleLabel, JLabel weaponLabel, JLabel actionAvailableLabel,
                           JButton broadcastButton, JButton gatherButton,
                           JButton eventButton, JButton nextDayButton, JButton upButton, JButton downButton,
                           JButton leftButton, JButton rightButton, JButton infoButton) {
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
        layout.putConstraint(SpringLayout.NORTH, actionAvailableLabel, Constants.TWENTY, SpringLayout.SOUTH,
                weaponLabel);

        // MiniMap and InfoBox Layout
        layout.putConstraint(SpringLayout.EAST, mapPanel, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, mapPanel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.EAST, infoBox, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, infoBox, Constants.TWENTY, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.SOUTH, infoBox, 0, SpringLayout.SOUTH, mapPanel);
        layout.putConstraint(SpringLayout.WEST, infoBox, 0, SpringLayout.WEST, mapPanel);

        layout.putConstraint(SpringLayout.WEST, broadcastButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, broadcastButton, Constants.TWENTY, SpringLayout.SOUTH,
                actionAvailableLabel);

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
    }

    private void toggleInfoBox() {
        isMapVisible = !isMapVisible;
        mapPanel.setVisible(isMapVisible);
        infoBox.setVisible(!isMapVisible);
    }

    public void render() {
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
        setVisible(true);
    }

    public void disrender() {
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(false);
    }

    public static void main(String[] args) {
        new GameView();
    }
}
