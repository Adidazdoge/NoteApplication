package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import interface_adapters.NavigationManager;
import interface_adapters.broadcast.BroadcastController;
import interface_adapters.broadcast.BroadcastInterface;
import interface_adapters.dailygather.DailyGatherController;
import interface_adapters.dailygather.DailyGatherInterface;
import interface_adapters.dailymove.DailyMoveController;
import interface_adapters.dailymove.DailyMoveInterface;
import interface_adapters.endprocesshorde.HordeController;
import interface_adapters.endprocesshorde.HordeInterfaceNavigate;
import interface_adapters.eventdecide.EventDecideController;
import interface_adapters.eventdecide.EventDecideInterface;
import interface_adapters.fetchresource.FetchController;
import interface_adapters.fetchresource.FetchInterface;
import interface_adapters.gamelosedetecter.LoseController;
import interface_adapters.gamelosedetecter.LoseInterfaceNavigate;
import interface_adapters.gameminimap.MinimapController;
import interface_adapters.gameminimap.MinimapInterface;
import interface_adapters.gamenewday.NewdayController;
import interface_adapters.gamenewday.NewdayInterface;
import interface_adapters.gameplacedescription.PlaceDescriptionController;
import interface_adapters.gameplacedescription.PlaceDescriptionInterface;
import interface_adapters.nevagateevent.NevagateEventController;
import interface_adapters.nevagateevent.NevagateEventInterface;
import interface_adapters.nevagategameover.NevagateGameOverController;
import interface_adapters.nevagategameover.NevagateGameOverInterface;

/**
 * Represents the Game view in the application.
 * Provides UI components for displaying the game result and returning to the main menu.
 */
public class GameView extends JFrame implements PropertyChangeListener, FetchInterface, BroadcastInterface,
        PlaceDescriptionInterface, DailyGatherInterface, DailyMoveInterface,
        EventDecideInterface, NevagateEventInterface, NewdayInterface, MinimapInterface,
        LoseInterfaceNavigate, HordeInterfaceNavigate,
        NevagateGameOverInterface {
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
    private NewdayController newdayController;
    private MinimapController minimapController;
    private LoseController loseController;
    private HordeController hordeController;
    private NevagateGameOverController nevagateGameOverController;

    private final PropertyChangeSupport propertyChangeSupport;
    private final String changeline = new String("\n\n");

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

        containerContent(container);

        // MiniMap Panel
        mapPanel = new JTextArea("Mini Map\nabcdefghijklm\nnuvwxyz");
        mapPanelSetteings();
        container.add(mapPanel);

        // Wrap infoBox in a JScrollPane (top-left position, smaller width)
        final JScrollPane infoScrollPane = new JScrollPane(infoBox);
        infoScrollPaneSettings(infoScrollPane, container, layout);

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

        containerContent(container, broadcastButton, gatherButton, upButton, downButton, leftButton,
                rightButton, eventButton, nextDayButton, infoButton);

        broadcastButtonListener(broadcastButton);

        gatherButtonListener(gatherButton);

        upButtonListener(upButton);

        downButtonListener(downButton);

        leftButtonListener(leftButton);

        rightButtonListener(rightButton);

        eventButtonListener(eventButton);

        nextdayButtonListener(nextDayButton);

        // Add ActionListeners
        infoButtonListener(infoButton, nextDayButton);

        // Layout Constraints
        applyLayoutConstraints(layout, container, dayLabel, foodLabel, waterLabel, peopleLabel, weaponLabel,
                actionAvailableLabel, mapPanel, infoBox, broadcastButton, gatherButton, upButton, downButton,
                leftButton, rightButton, eventButton, nextDayButton, infoButton);

        // Set frame properties
        frameSettings();

        // Register this as a listener for property changes
        propertyChangeSupport.addPropertyChangeListener(this);
    }

    private void frameSettings() {
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void infoButtonListener(JButton infoButton, JButton nextDayButton) {
        infoButton.addActionListener(event -> toggleInfoBox());
        nextDayButton.addActionListener(event -> {
            if (fetchController != null) {
                fetchController.execute();
            }
        });
    }

    private void nextdayButtonListener(JButton nextDayButton) {
        nextDayButton.addActionListener(event -> {
            newdayController.execute();
            fetchController.execute();
        });
    }

    private void eventButtonListener(JButton eventButton) {
        eventButton.addActionListener(event -> {
            nevagateEventController.execute();
        });
    }

    private void rightButtonListener(JButton rightButton) {
        rightButton.addActionListener(event -> {
            dailyMoveController.execute("right");
            fetchController.execute();
            minimapController.execute();
        });
    }

    private void leftButtonListener(JButton leftButton) {
        leftButton.addActionListener(event -> {
            dailyMoveController.execute("left");
            fetchController.execute();
            minimapController.execute();
        });
    }

    private void downButtonListener(JButton downButton) {
        downButton.addActionListener(event -> {
            dailyMoveController.execute("down");
            fetchController.execute();
            minimapController.execute();
        });
    }

    private void upButtonListener(JButton upButton) {
        upButton.addActionListener(event -> {
            dailyMoveController.execute("up");
            fetchController.execute();
            minimapController.execute();
        });
    }

    private void gatherButtonListener(JButton gatherButton) {
        gatherButton.addActionListener(event -> {
            dailyGatherController.execute();
            fetchController.execute();
        });
    }

    private void broadcastButtonListener(JButton broadcastButton) {
        broadcastButton.addActionListener(event -> {
            broadcastController.execute();
            fetchController.execute();
        });
    }

    private static void containerContent(Container container, JButton broadcastButton, JButton gatherButton,
                                         JButton upButton, JButton downButton, JButton leftButton, JButton rightButton,
                                         JButton eventButton, JButton nextDayButton, JButton infoButton) {
        container.add(broadcastButton);
        container.add(gatherButton);
        container.add(upButton);
        container.add(downButton);
        container.add(leftButton);
        container.add(rightButton);
        container.add(eventButton);
        container.add(nextDayButton);
        container.add(infoButton);
    }

    private void containerContent(Container container) {
        container.add(dayLabel);
        container.add(foodLabel);
        container.add(waterLabel);
        container.add(peopleLabel);
        container.add(weaponLabel);
        container.add(actionAvailableLabel);
    }

    private void infoScrollPaneSettings(JScrollPane infoScrollPane, Container container, SpringLayout layout) {
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
    }

    private void mapPanelSetteings() {
        mapPanel.setEditable(false);
        mapPanel.setLineWrap(true);
        mapPanel.setWrapStyleWord(true);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setBackground(Color.LIGHT_GRAY);
        mapPanel.setPreferredSize(new Dimension(Constants.TWO_HUNDRED, Constants.TWO_HUNDRED));
    }

    /**
     * Sets the controllers for managing various aspects of the application.
     *
     * <p>This method initializes the required controllers by associating the provided
     * controller instances with the corresponding fields in this class. Each controller
     * is responsible for handling specific functionality within the application.</p>
     *
     * @param inifetchController the controller responsible for fetching data
     * @param inibroadcastController the controller responsible for broadcasting updates
     * @param iniplaceDescriptionController the controller responsible for handling place descriptions
     * @param inidailyGatherController the controller for managing daily gathering operations
     * @param inidailyMoveController the controller for managing daily movement operations
     * @param ininevagateEventController the controller for navigating events
     * @param inieventDecideController the controller for handling event decision logic
     * @param ininewdayController the controller responsible for handling new day transitions
     * @param iniminimapController the controller for managing the minimap
     * @param iniloseController the controller responsible for handling game loss conditions
     * @param inihordeController the controller for managing hordes in the application
     * @param ininevagateGameOverController the controller for managing the game-over navigation process
     */
    public void setController(FetchController inifetchController, BroadcastController inibroadcastController,
                              PlaceDescriptionController iniplaceDescriptionController,
                              DailyGatherController inidailyGatherController, DailyMoveController inidailyMoveController,
                              NevagateEventController ininevagateEventController,
                              EventDecideController inieventDecideController, NewdayController ininewdayController,
                              MinimapController iniminimapController,
                              LoseController iniloseController, HordeController inihordeController,
                              NevagateGameOverController ininevagateGameOverController) {
        this.fetchController = inifetchController;
        this.broadcastController = inibroadcastController;
        this.placeDescriptionController = iniplaceDescriptionController;
        this.dailyGatherController = inidailyGatherController;
        this.dailyMoveController = inidailyMoveController;
        this.nevagateEventController = ininevagateEventController;
        this.eventDecideController = inieventDecideController;
        this.newdayController = ininewdayController;
        this.minimapController = iniminimapController;
        this.loseController = iniloseController;
        this.hordeController = inihordeController;
        this.nevagateGameOverController = ininevagateGameOverController;
    }

    /**
     * Renders the UI by executing controllers and making the panel visible.
     */
    public void render() {
        placeDescriptionController.execute();
        fetchController.execute();
        minimapController.execute();
        loseController.execute();
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
    public void updateUiMinimap(ArrayList<ArrayList<String>> grid) {
        // Validate input
        if (grid == null || grid.isEmpty() || grid.get(0).isEmpty()) {
            mapPanel.setText("Invalid map data.");
            return;
        }

        final int rows = grid.size();
        final int cols = grid.get(0).size();

        // Find the center and set a marker (e.g., @) for the player's position
        final int centerRow = rows / 2;
        final int centerCol = cols / 2;

        final StringBuilder mapBuilder = new StringBuilder();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == centerRow && c == centerCol) {
                    mapBuilder.append("@  ");
                }
                else {
                    mapBuilder.append(grid.get(r).get(c)).append("  ");
                }
            }
            mapBuilder.append("\n");
        }

        // Set text to mapPanel with fixed font and centered scroll
        mapPanel.setFont(new Font("Monospaced", Font.PLAIN, Constants.FIFTEEN));
        mapPanel.setText(mapBuilder.toString());
        mapPanel.setCaretPosition(0);
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
    public void updateUiResource(int inifood, int iniwater, int inipeople, int iniweapon, int iniday,
                                 int iniactionpoint) {
        loseController.execute();
        // check if player lose on update.
        setFood(inifood);
        setWater(iniwater);
        setPeople(inipeople);
        setWeapon(iniweapon);
        setDay(iniday);
        setAction(iniactionpoint);
    }

    @Override
    public void updateUiBroadcast(String message) {
        if (infoBox != null) {
            infoBox.append(message + changeline);
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
            infoBox.append(message + changeline);
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
            infoBox.append(message + changeline);
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
            infoBox.append(placeDescription + changeline);
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
                infoBox.append("No events happened today.\n\n");
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

    @Override
    public void updateUiNewday(String message) {
        // only message is needed.
        if (infoBox != null) {
            infoBox.append(message + "\n\n");
            System.out.println("newday message prointed");
            infoBox.setCaretPosition(infoBox.getDocument().getLength());
        }
        eventDecideController.execute();
    }

    @Override
    public void failureNewday(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Unable to go to next day!",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void navigateGameOver(NavigationManager navigationManager) {
        navigationManager.showGameOverView();
    }

    @Override
    public void NavigateHordeGameOver(NavigationManager manager) {
        manager.showGameOverView();
    }
}
