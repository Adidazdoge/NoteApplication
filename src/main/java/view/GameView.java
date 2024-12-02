package view;

import interface_adapters.fetchresource.FetchController;
import interface_adapters.fetchresource.FetchInterface;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameView extends JFrame implements PropertyChangeListener, FetchInterface {
    private int day;
    private int food;
    private int water;
    private int people;
    private int weapon;
    private int action;

    private final JLabel dayLabel;
    private final JLabel foodLabel;
    private final JLabel waterLabel;
    private final JLabel peopleLabel;
    private final JLabel weaponLabel;
    private final JLabel actionAvailableLabel;

    private final JTextArea mapPanel;
    private final JTextArea infoBox;
    private boolean isMapVisible = true;

    private FetchController fetchController;

    private final PropertyChangeSupport propertyChangeSupport;

    public GameView() {
        super("Game");

        // Initialize PropertyChangeSupport
        this.propertyChangeSupport = new PropertyChangeSupport(this);

        // Initialize UI components
        Container container = getContentPane();
        SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Initialize labels for resources
        dayLabel = new JLabel("Day: " + day);
        dayLabel.setFont(new Font("Serif", Font.BOLD, 20));
        foodLabel = new JLabel("Food: " + food);
        waterLabel = new JLabel("Water: " + water);
        peopleLabel = new JLabel("People: " + people);
        weaponLabel = new JLabel("Weapon: " + weapon);
        actionAvailableLabel = new JLabel("Action Available: " + action);

        container.add(dayLabel);
        container.add(foodLabel);
        container.add(waterLabel);
        container.add(peopleLabel);
        container.add(weaponLabel);
        container.add(actionAvailableLabel);

        // MiniMap Panel
        mapPanel = new JTextArea("Mini Map\nabcdefghijklm\nnuvwxyz");
        mapPanel.setEditable(false);
        mapPanel.setLineWrap(true);
        mapPanel.setWrapStyleWord(true);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setBackground(Color.LIGHT_GRAY);
        mapPanel.setPreferredSize(new Dimension(200, 200));
        container.add(mapPanel);

        // InfoBox TextArea
        infoBox = new JTextArea("Information Box");
        infoBox.setEditable(false);
        infoBox.setBackground(Color.LIGHT_GRAY);
        infoBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoBox.setVisible(false);
        container.add(infoBox);

        // Buttons
        JButton broadcastButton = new JButton("Broadcast");
        JButton gatherButton = new JButton("Gather");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton eventButton = new JButton("Event");
        JButton nextDayButton = new JButton("Next Day");
        JButton infoButton = new JButton("Log");

        container.add(broadcastButton);
        container.add(gatherButton);
        container.add(upButton);
        container.add(downButton);
        container.add(leftButton);
        container.add(rightButton);
        container.add(eventButton);
        container.add(nextDayButton);
        container.add(infoButton);

        // Add ActionListeners
        infoButton.addActionListener(e -> toggleInfoBox());
        nextDayButton.addActionListener(e -> {
            if (fetchController != null) {
                fetchController.execute();
            }
        });

        // Layout Constraints
        applyLayoutConstraints(layout, container, dayLabel, foodLabel, waterLabel, peopleLabel, weaponLabel,
                actionAvailableLabel, mapPanel, infoBox, broadcastButton, gatherButton, upButton, downButton,
                leftButton, rightButton, eventButton, nextDayButton, infoButton);

        // Set frame properties
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Register this as a listener for property changes
        propertyChangeSupport.addPropertyChangeListener(this);
    }

    public void setFetchController(FetchController fetchController) {
        this.fetchController = fetchController;
    }

    public void render() {
        fetchController.execute();
        setVisible(true);
    }

    public void disrender() {
        setVisible(false);
    }

    private void toggleInfoBox() {
        isMapVisible = !isMapVisible;
        mapPanel.setVisible(isMapVisible);
        infoBox.setVisible(!isMapVisible);
    }

    public void setDay(int day) {
        int oldDay = this.day;
        this.day = day;
        propertyChangeSupport.firePropertyChange("day", oldDay, day);
    }

    public void setFood(int food) {
        int oldFood = this.food;
        this.food = food;
        propertyChangeSupport.firePropertyChange("food", oldFood, food);
    }

    public void setWater(int water) {
        int oldWater = this.water;
        this.water = water;
        propertyChangeSupport.firePropertyChange("water", oldWater, water);
    }

    public void setPeople(int people) {
        int oldPeople = this.people;
        this.people = people;
        propertyChangeSupport.firePropertyChange("people", oldPeople, people);
    }

    public void setWeapon(int weapon) {
        int oldWeapon = this.weapon;
        this.weapon = weapon;
        propertyChangeSupport.firePropertyChange("weapon", oldWeapon, weapon);
    }

    public void setAction(int action) {
        int oldAction = this.action;
        this.action = action;
        propertyChangeSupport.firePropertyChange("action", oldAction, action);
    }

    private void applyLayoutConstraints(SpringLayout layout, Container container, JLabel dayLabel,
                                        JLabel foodLabel, JLabel waterLabel, JLabel peopleLabel,
                                        JLabel weaponLabel, JLabel actionAvailableLabel, JTextArea mapPanel,
                                        JTextArea infoBox, JButton broadcastButton, JButton gatherButton,
                                        JButton upButton, JButton downButton, JButton leftButton,
                                        JButton rightButton, JButton eventButton, JButton nextDayButton,
                                        JButton infoButton) {
        // Position resource labels
        layout.putConstraint(SpringLayout.WEST, dayLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, dayLabel, 20, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, foodLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, foodLabel, 20, SpringLayout.SOUTH, dayLabel);

        layout.putConstraint(SpringLayout.WEST, waterLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, waterLabel, 20, SpringLayout.SOUTH, foodLabel);

        layout.putConstraint(SpringLayout.WEST, peopleLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, peopleLabel, 20, SpringLayout.SOUTH, waterLabel);

        layout.putConstraint(SpringLayout.WEST, weaponLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, weaponLabel, 20, SpringLayout.SOUTH, peopleLabel);

        layout.putConstraint(SpringLayout.WEST, actionAvailableLabel, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, actionAvailableLabel, 20, SpringLayout.SOUTH, weaponLabel);

        // Position minimap
        layout.putConstraint(SpringLayout.EAST, mapPanel, -20, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, mapPanel, 20, SpringLayout.NORTH, container);

        // Position info box
        layout.putConstraint(SpringLayout.EAST, infoBox, -20, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, infoBox, 20, SpringLayout.NORTH, container);

        // Position buttons
        layout.putConstraint(SpringLayout.WEST, broadcastButton, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, broadcastButton, 20, SpringLayout.SOUTH, actionAvailableLabel);

        layout.putConstraint(SpringLayout.WEST, gatherButton, 20, SpringLayout.EAST, broadcastButton);
        layout.putConstraint(SpringLayout.NORTH, gatherButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, upButton, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, upButton, 20, SpringLayout.SOUTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, downButton, 20, SpringLayout.EAST, upButton);
        layout.putConstraint(SpringLayout.NORTH, downButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, leftButton, 20, SpringLayout.EAST, downButton);
        layout.putConstraint(SpringLayout.NORTH, leftButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, rightButton, 20, SpringLayout.EAST, leftButton);
        layout.putConstraint(SpringLayout.NORTH, rightButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, eventButton, 20, SpringLayout.EAST, gatherButton);
        layout.putConstraint(SpringLayout.NORTH, eventButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, nextDayButton, 20, SpringLayout.EAST, eventButton);
        layout.putConstraint(SpringLayout.NORTH, nextDayButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, infoButton, 20, SpringLayout.EAST, rightButton);
        layout.putConstraint(SpringLayout.NORTH, infoButton, 0, SpringLayout.NORTH, upButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        switch (propertyName) {
            case "day":
                dayLabel.setText("Day: " + evt.getNewValue());
                break;
            case "food":
                foodLabel.setText("Food: " + evt.getNewValue());
                break;
            case "water":
                waterLabel.setText("Water: " + evt.getNewValue());
                break;
            case "people":
                peopleLabel.setText("People: " + evt.getNewValue());
                break;
            case "weapon":
                weaponLabel.setText("Weapon: " + evt.getNewValue());
                break;
            case "action":
                actionAvailableLabel.setText("Action Available: " + evt.getNewValue());
                break;
            default:
                // Handle unknown properties if needed
                break;
        }
    }

    @Override
    public void updateUiResource(int food, int water, int people, int weapon) {
        setFood(food);
        setWater(water);
        setPeople(people);
        setWeapon(weapon);
    }

}
