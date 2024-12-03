package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import interface_adapters.EventManager;
import interface_adapters.eventinitializer.EventInitializerController;
import interface_adapters.eventinitializer.EventInitializerInterface;
import interface_adapters.eventrespond.ambush.AmbushResponseInterface;
import interface_adapters.eventrespond.blizzard.BlizzardResponseInterface;
import interface_adapters.eventrespond.flood.FloodResponseInterface;
import interface_adapters.eventrespond.survivor.SurvivorResponseInterface;
import interface_adapters.eventrespond.trader.TraderResponseInterface;
import interface_adapters.fetchcurrentevent.FetchEventController;
import interface_adapters.fetchcurrentevent.FetchEventInterface;
import interface_adapters.nevagategame.NevagateGameController;
import interface_adapters.nevagategame.NevagateGameInterface;

/**
 * Represents the view for displaying and interacting with events in the game.
 */
public class EventView extends JFrame implements EventInitializerInterface,
        AmbushResponseInterface, BlizzardResponseInterface, TraderResponseInterface,
        FloodResponseInterface, SurvivorResponseInterface, FetchEventInterface, NevagateGameInterface {

    // UI components as instance variables
    private final JLabel eventLabel;
    private final JTextArea descriptionArea;
    private final JScrollPane descriptionScrollPane;
    private final JButton fightButton;
    private final JButton negotiateButton;
    private final JButton fleeButton;
    private final JButton backButton;
    private final SpringLayout layout;

    private EventInitializerController eventInitializerController;
    private EventManager eventManager;
    private FetchEventController fetchEventController;
    private NevagateGameController nevagateGameController;

    /**
     * Constructs the EventView UI and initializes components.
     */
    public EventView() {
        super("Event");

        // Initialize the container and layout
        final Container container = getContentPane();
        layout = new SpringLayout();
        container.setLayout(layout);

        // Initialize UI components
        eventLabel = new JLabel("Event:");
        eventLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));

        // Initialize the description area
        descriptionArea = new JTextArea("Event description goes here...");
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Wrap the description area in a scroll pane
        // Now it's an instance variable
        descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setPreferredSize(new Dimension(Constants.FOUR_HUNDRED, Constants.ONE_HUNDRED));

        // Buttons
        fightButton = new JButton("Fight");
        negotiateButton = new JButton("Negotiate");
        fleeButton = new JButton("Flee");
        backButton = new JButton("Back");

        // Add components to container
        containerContent(container);

        // Call applyLayoutConstraints to position components
        applyLayoutConstraints(container);

        // Add button listeners
        fightButton.addActionListener(event -> {
            fetchEventController.execute();
            // also reset event manager event string.
            eventManager.execute(1);
        });

        negotiateButton.addActionListener(event -> {
            fetchEventController.execute();
            // also reset event manager event string.
            eventManager.execute(2);
        });

        fleeButton.addActionListener(event -> {
            fetchEventController.execute();
            // also reset event manager event string.
            eventManager.execute(Constants.THREE);
        });

        backButton.addActionListener(event -> {
            nevagateGameController.execute();
        });

        // Window settings
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    /**
     * Sets the controllers required for handling events and navigation.
     *
     * @param inieventInitializerController Controller for event initialization.
     * @param inifetchEventController       Controller for fetching event details.
     * @param ininevagateGameController     Controller for game navigation.
     */
    public void setController(EventInitializerController inieventInitializerController,
                              FetchEventController inifetchEventController,
                              NevagateGameController ininevagateGameController) {
        this.eventInitializerController = inieventInitializerController;
        this.fetchEventController = inifetchEventController;
        this.nevagateGameController = ininevagateGameController;
    }

    /**
     * Sets the event manager responsible for handling event-related data.
     *
     * @param inieventManager The event manager instance.
     */
    public void setManager(EventManager inieventManager) {
        this.eventManager = inieventManager;
    }

    private void containerContent(Container container) {
        container.add(eventLabel);
        // Add the scroll pane, not just the text area
        container.add(descriptionScrollPane);
        container.add(fightButton);
        container.add(negotiateButton);
        container.add(fleeButton);
        container.add(backButton);
    }

    /**
     * Applies layout constraints to the UI components.
     *
     * @param container The parent container.
     */
    private void applyLayoutConstraints(Container container) {
        // Event Label Constraints
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, eventLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, eventLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        // Description Scroll Pane Constraints
        layout.putConstraint(SpringLayout.WEST, descriptionScrollPane, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, descriptionScrollPane, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, descriptionScrollPane, Constants.TWENTY,
                SpringLayout.SOUTH, eventLabel);
        layout.putConstraint(SpringLayout.SOUTH, descriptionScrollPane, -Constants.ONE_HUNDRED,
                SpringLayout.SOUTH, container);

        // Fight Button Constraints
        layout.putConstraint(SpringLayout.WEST, fightButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, fightButton, Constants.TWENTY, SpringLayout.SOUTH,
                descriptionScrollPane);

        // Negotiate Button Constraints
        layout.putConstraint(SpringLayout.WEST, negotiateButton, Constants.TWENTY, SpringLayout.EAST, fightButton);
        layout.putConstraint(SpringLayout.NORTH, negotiateButton, 0, SpringLayout.NORTH, fightButton);

        // Flee Button Constraints
        layout.putConstraint(SpringLayout.WEST, fleeButton, Constants.TWENTY, SpringLayout.EAST, negotiateButton);
        layout.putConstraint(SpringLayout.NORTH, fleeButton, 0, SpringLayout.NORTH, fightButton);

        // Back Button Constraints
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, backButton, Constants.TWENTY, SpringLayout.SOUTH, fightButton);
    }

    /**
     * Adds default action listeners to buttons.
     *
     * @param returnToGameViewListener The listener to return to the GameView.
     */
    private void addListeners(ActionListener returnToGameViewListener) {
        fightButton.addActionListener(returnToGameViewListener);
        negotiateButton.addActionListener(returnToGameViewListener);
        fleeButton.addActionListener(returnToGameViewListener);
        backButton.addActionListener(returnToGameViewListener);
    }

    /**
     * Renders the component by initializing required events, setting its size,
     * and making it visible.
     *
     * <p>This method triggers the execution of the eventInitializerController
     * and adjusts the component's size to a predefined value. Finally, it makes
     * the component visible to the user.</p>
     */
    public void render() {
        eventInitializerController.execute();
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(true);
    }

    /**
     * Disables the rendering of the component by maintaining its size but
     * making it invisible.
     *
     * <p>This method adjusts the component's size to a predefined value but
     * ensures that it is not visible to the user.</p>
     */
    public void disrender() {
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(false);
    }

    @Override
    public void updateUiEventInitializer(String eventdescription, String option1, String option2, String option3) {
        descriptionArea.setText(eventdescription);
        fightButton.setText(option1);
        negotiateButton.setText(option2);
        fleeButton.setText(option3);
    }

    @Override
    public void failureEventInitializer(String failmessage) {
        JOptionPane.showMessageDialog(this, failmessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void updateUiResponse(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Notification",
                JOptionPane.INFORMATION_MESSAGE
        );
        nevagateGameController.execute();
        // return to game.
    }

    @Override
    public void failureResponse(String errorMessage) {
        JOptionPane.showMessageDialog(
                this,
                errorMessage,
                "Invaild choice",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public void setEventName(String event) {
        eventManager.setActiveEvent(event);
    }
}
