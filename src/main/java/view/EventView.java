package view;

import entities.Event;
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
import interface_adapters.nevagatemainview.NevagateMainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Event view.
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

    public void setController(EventInitializerController eventInitializerController,
                              FetchEventController fetchEventController,
                              NevagateGameController nevagateGameController) {
        this.eventInitializerController = eventInitializerController;
        this.fetchEventController = fetchEventController;
        this.nevagateGameController = nevagateGameController;
    }

    public void setManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventView() {
        super("Event");

        // Initialize the container and layout
        Container container = getContentPane();
        layout = new SpringLayout();
        container.setLayout(layout);

        // Initialize UI components
        eventLabel = new JLabel("Event:");
        eventLabel.setFont(new Font("Serif", Font.BOLD, 20));

        // Initialize the description area
        descriptionArea = new JTextArea("Event description goes here...");
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Wrap the description area in a scroll pane
        descriptionScrollPane = new JScrollPane(descriptionArea); // Now it's an instance variable
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descriptionScrollPane.setPreferredSize(new Dimension(400, 100));

        // Buttons
        fightButton = new JButton("Fight");
        negotiateButton = new JButton("Negotiate");
        fleeButton = new JButton("Flee");
        backButton = new JButton("Back");

        // Add components to container
        container.add(eventLabel);
        container.add(descriptionScrollPane); // Add the scroll pane, not just the text area
        container.add(fightButton);
        container.add(negotiateButton);
        container.add(fleeButton);
        container.add(backButton);

        // Call applyLayoutConstraints to position components
        applyLayoutConstraints(container);

        // Add button listeners
        fightButton.addActionListener(e -> {
            fetchEventController.execute();
            // also reset event manager event string.
            eventManager.execute(1);
        });

        negotiateButton.addActionListener(e -> {
            fetchEventController.execute();
            // also reset event manager event string.
            eventManager.execute(2);
        });

        fleeButton.addActionListener(e -> {
            fetchEventController.execute();
            // also reset event manager event string.
            eventManager.execute(3);
        });

        backButton.addActionListener(e -> {
            nevagateGameController.execute();
        });

        // Window settings
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
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
        layout.putConstraint(SpringLayout.NORTH, fightButton, Constants.TWENTY, SpringLayout.SOUTH, descriptionScrollPane);

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

    public void render() {
        eventInitializerController.execute();
        setSize(Constants.FOUR_HUNDRED, Constants.FOUR_HUNDRED);
        setVisible(true);
    }

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
