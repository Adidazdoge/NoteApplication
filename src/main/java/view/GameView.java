package view;

import interface_adapters.broadcast.BroadcastController;
import interface_adapters.broadcast.BroadcastInterface;
import interface_adapters.dailygather.DailyGatherController;
import interface_adapters.dailygather.DailyGatherInterface;
import interface_adapters.dailymove.DailyMoveController;
import interface_adapters.dailymove.DailyMoveInterface;
import interface_adapters.eventdecide.EventDecideController;
import interface_adapters.eventdecide.EventDecideInterface;
import interface_adapters.fetchresource.FetchController;
import interface_adapters.fetchresource.FetchInterface;
import interface_adapters.gameplacedescription.PlaceDescriptionController;
import interface_adapters.gameplacedescription.PlaceDescriptionInterface;
import interface_adapters.nevagateevent.NevagateEventController;
import interface_adapters.nevagateevent.NevagateEventInterface;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class GameView extends JFrame implements PropertyChangeListener, FetchInterface, BroadcastInterface,
        PlaceDescriptionInterface, DailyGatherInterface, DailyMoveInterface,
        EventDecideInterface, NevagateEventInterface {
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
    private final JTextArea infoBox = new JTextArea();
    private boolean isMapVisible = true;

    private FetchController fetchController;
    private BroadcastController broadcastController;
    private PlaceDescriptionController placeDescriptionController;
    private DailyGatherController dailyGatherController;
    private DailyMoveController dailyMoveController;
    private EventDecideController eventDecideController;
    private NevagateEventController nevagateEventController;

    private final PropertyChangeSupport propertyChangeSupport;

    public GameView() {
        super("Game");

        // Initialize PropertyChangeSupport
        this.propertyChangeSupport = new PropertyChangeSupport(this);

        // Initialize UI components
        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Initialize labels for resources
        dayLabel = new JLabel("Day: " + day);
        dayLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
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
        mapPanel.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.TWO_HUNDRED));
        container.add(mapPanel);

        // Wrap infoBox in a JScrollPane (top-left position, smaller width)
        final JScrollPane infoScrollPane = new JScrollPane(infoBox);
        infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // Adjusted smaller width and fixed height
        infoScrollPane.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.TWO_HUNDRED));
        container.add(infoScrollPane);
        infoBox.setLineWrap(true);
        infoBox.setWrapStyleWord(true);
        infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Update Layout Constraints for infoScrollPane (top-left, same as minimap)
        layout.putConstraint(SpringLayout.EAST, infoScrollPane, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, infoScrollPane, Constants.TWENTY, SpringLayout.NORTH, container);

        // Buttons
        final JButton broadcastButton = new JButton("Broadcast");
        final JButton gatherButton = new JButton("Gather");
        final JButton upButton = new JButton("Up");
        final JButton downButton = new JButton("Down");
        final JButton leftButton = new JButton("Left");
        final JButton rightButton = new JButton("Right");
        final JButton eventButton = new JButton("Event");
        final JButton nextDayButton = new JButton("Next Day");
        final JButton infoButton = new JButton("Log");

        container.add(broadcastButton);
        container.add(gatherButton);
        container.add(upButton);
        container.add(downButton);
        container.add(leftButton);
        container.add(rightButton);
        container.add(eventButton);
        container.add(nextDayButton);
        container.add(infoButton);

        broadcastButton.addActionListener(e -> {
            broadcastController.execute();
            fetchController.execute();
        });

        gatherButton.addActionListener(e -> {
            dailyGatherController.execute();
            fetchController.execute();
        });

        upButton.addActionListener(e -> {
            dailyMoveController.execute("up");
            fetchController.execute();
        });

        downButton.addActionListener(e -> {
            dailyMoveController.execute("down");
            fetchController.execute();
        });

        leftButton.addActionListener(e -> {
            dailyMoveController.execute("left");
            fetchController.execute();
        });

        rightButton.addActionListener(e -> {
            dailyMoveController.execute("right");
            fetchController.execute();
        });

        eventButton.addActionListener(e -> {
            nevagateEventController.execute();
        });

        nextDayButton.addActionListener(e -> {
            eventDecideController.execute();
        });

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
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Register this as a listener for property changes
        propertyChangeSupport.addPropertyChangeListener(this);
    }

    public void setController(FetchController fetchController, BroadcastController broadcastController,
                              PlaceDescriptionController placeDescriptionController,
                              DailyGatherController dailyGatherController, DailyMoveController dailyMoveController,
                              NevagateEventController nevagateEventController,
                              EventDecideController eventDecideController) {
        this.fetchController = fetchController;
        this.broadcastController = broadcastController;
        this.placeDescriptionController = placeDescriptionController;
        this.dailyGatherController = dailyGatherController;
        this.dailyMoveController = dailyMoveController;
        this.nevagateEventController = nevagateEventController;
        this.eventDecideController = eventDecideController;
    }

    /**
     * Renders the UI by executing controllers and making the panel visible.
     */
    public void render() {
        placeDescriptionController.execute();
        fetchController.execute();
        setVisible(true);
    }

    /**
     * Hides the UI by making the panel invisible.
     */
    public void disrender() {
        setVisible(false);
    }

    /**
     * Toggles the visibility of the map and information box.
     * If the map is currently visible, it hides the map and shows the info box, and vice versa.
     */
    private void toggleInfoBox() {
        isMapVisible = !isMapVisible;
        mapPanel.setVisible(isMapVisible);
        infoBox.setVisible(!isMapVisible);
    }

    /**
     * Sets the current day and notifies listeners of the property change.
     *
     * @param day the new day value
     */
    public void setDay(int day) {
        final int oldDay = this.day;
        this.day = day;
        propertyChangeSupport.firePropertyChange("day", oldDay, day);
    }

    /**
     * Sets the current food level and notifies listeners of the property change.
     *
     * @param food the new food value
     */
    public void setFood(int food) {
        final int oldFood = this.food;
        this.food = food;
        propertyChangeSupport.firePropertyChange("food", oldFood, food);
    }

    /**
     * Sets the current water level and notifies listeners of the property change.
     *
     * @param water the new water value
     */
    public void setWater(int water) {
        final int oldWater = this.water;
        this.water = water;
        propertyChangeSupport.firePropertyChange("water", oldWater, water);
    }

    /**
     * Sets the current population count and notifies listeners of the property change.
     *
     * @param people the new people count
     */
    public void setPeople(int people) {
        final int oldPeople = this.people;
        this.people = people;
        propertyChangeSupport.firePropertyChange("people", oldPeople, people);
    }

    /**
     * Sets the current weapon count and notifies listeners of the property change.
     *
     * @param weapon the new weapon value
     */
    public void setWeapon(int weapon) {
        final int oldWeapon = this.weapon;
        this.weapon = weapon;
        propertyChangeSupport.firePropertyChange("weapon", oldWeapon, weapon);
    }

    /**
     * Sets the current action count and notifies listeners of the property change.
     *
     * @param action the new action value
     */
    public void setAction(int action) {
        final int oldAction = this.action;
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

        // Position minimap
        layout.putConstraint(SpringLayout.EAST, mapPanel, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, mapPanel, Constants.TWENTY, SpringLayout.NORTH, container);

        // Position info box
        layout.putConstraint(SpringLayout.EAST, infoBox, Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, infoBox, Constants.TWENTY, SpringLayout.NORTH, container);

        // Position buttons
        layout.putConstraint(SpringLayout.WEST, broadcastButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, broadcastButton, Constants.TWENTY, SpringLayout.SOUTH,
                actionAvailableLabel);

        layout.putConstraint(SpringLayout.WEST, gatherButton, Constants.TWENTY, SpringLayout.EAST, broadcastButton);
        layout.putConstraint(SpringLayout.NORTH, gatherButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, upButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, upButton, Constants.TWENTY, SpringLayout.SOUTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, downButton, Constants.TWENTY, SpringLayout.EAST, upButton);
        layout.putConstraint(SpringLayout.NORTH, downButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, leftButton, Constants.TWENTY, SpringLayout.EAST, downButton);
        layout.putConstraint(SpringLayout.NORTH, leftButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, rightButton, Constants.TWENTY, SpringLayout.EAST, leftButton);
        layout.putConstraint(SpringLayout.NORTH, rightButton, 0, SpringLayout.NORTH, upButton);

        layout.putConstraint(SpringLayout.WEST, eventButton, Constants.TWENTY, SpringLayout.EAST, gatherButton);
        layout.putConstraint(SpringLayout.NORTH, eventButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, nextDayButton, Constants.TWENTY, SpringLayout.EAST, eventButton);
        layout.putConstraint(SpringLayout.NORTH, nextDayButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, infoButton, Constants.TWENTY, SpringLayout.EAST, rightButton);
        layout.putConstraint(SpringLayout.NORTH, infoButton, 0, SpringLayout.NORTH, upButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();

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
    public void updateUiResource(int food, int water, int people, int weapon, int day, int actionpoint) {
        setFood(food);
        setWater(water);
        setPeople(people);
        setWeapon(weapon);
        setDay(day);
        setAction(actionpoint);
    }

    @Override
    public void updateUiBroadcast(String message) {
        if (infoBox != null) {
            infoBox.append(message + "\n");
            infoBox.setCaretPosition(infoBox.getDocument().getLength());
        }
    }

    @Override
    public void failureBroadcast(String errorMessage) {
        JOptionPane.showMessageDialog(
                this,
                errorMessage,
                "Unable to Broadcast",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void updateUiGather(String message) {
        if (infoBox != null) {
            infoBox.append(message + "\n");
            infoBox.setCaretPosition(infoBox.getDocument().getLength());
        }
    }

    @Override
    public void failureGather(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Unable to Gather",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void updateUiMove(String message) {
        if (infoBox != null) {
            infoBox.append(message + "\n");
            infoBox.setCaretPosition(infoBox.getDocument().getLength());
        }
    }

    @Override
    public void failureMove(String errormessage) {
        JOptionPane.showMessageDialog(
                this,
                errormessage,
                "Unable to Move",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void updateUiPlaceDescription(String placeDescription) {
        if (infoBox != null) {
            infoBox.append(placeDescription + "\n");
            infoBox.setCaretPosition(infoBox.getDocument().getLength());
        }
    }

    @Override
    public void failurePlaceDescription(String failmessage) {
        JOptionPane.showMessageDialog(
                this,
                failmessage,
                "Unable to Get Place Description",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void updateUiEventDecide(ArrayList<String> eventNames) {
        if (infoBox != null) {
            if (eventNames.isEmpty()) {
                // No events
                infoBox.append("No events happened today.\n");
            }
            else {
                // Display the events
                infoBox.append("Today's events:\n");
                for (String event : eventNames) {
                    infoBox.append("- " + event + "\n");
                }
            }
            infoBox.setCaretPosition(infoBox.getDocument().getLength());
        }
    }

    @Override
    public void failureEventDecide(String errorMessage) {
        JOptionPane.showMessageDialog(
                this,
                errorMessage,
                "Unable to decide event.",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void failureNevagateEvent(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Unable to go to event page!",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
