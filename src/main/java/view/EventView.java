package view;

import interface_adapters.eventrespond.EventResponseController;
import interface_adapters.eventrespond.EventResponseInterface;
import interface_adapters.eventrespond.EventResponsePresenter;
import usecases.eventrespond.shared.RespondDataAccessInterface;
import usecases.eventrespond.shared.RespondInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Event view.
 */
public class EventView extends JFrame implements EventResponseInterface {
    private final EventResponseController controller;

    public EventView(RespondDataAccessInterface dataAccess) {
        super("Event");

        // Set up the presenter and controller
        EventResponsePresenter presenter = new EventResponsePresenter(this);
        // this.controller = new EventResponseController(dataAccess, presenter);

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Event Label
        final JLabel eventLabel = new JLabel("Event:");
        eventLabel.setFont(new Font("Serif", Font.BOLD, 20));
        container.add(eventLabel);

        // Description Area
        final JTextArea descriptionArea = new JTextArea("Event description goes here...");
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(descriptionArea);

        // Buttons
        final JButton fightButton = new JButton("Fight");
        final JButton negotiateButton = new JButton("Negotiate");
        final JButton fleeButton = new JButton("Flee");
        final JButton backButton = new JButton("Back");
        container.add(fightButton);
        container.add(negotiateButton);
        container.add(fleeButton);
        container.add(backButton);

        // Layout Constraints
        setupLayout(layout, eventLabel, container, descriptionArea, fightButton, negotiateButton, fleeButton, backButton);

        // Add ActionListeners to Buttons
        // fightButton.addActionListener(e -> controller.execute(1));
        // negotiateButton.addActionListener(e -> controller.execute(2));
        // fleeButton.addActionListener(e -> controller.execute(3));
        backButton.addActionListener(e -> {
            dispose();
            new GameView();
        });

        // Window Settings
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void setupLayout(SpringLayout layout, JLabel eventLabel, Container container,
                             JTextArea descriptionArea, JButton fightButton, JButton negotiateButton, JButton fleeButton, JButton backButton) {
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, eventLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, eventLabel, 20, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, descriptionArea, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, descriptionArea, -20, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, descriptionArea, 20, SpringLayout.SOUTH, eventLabel);
        layout.putConstraint(SpringLayout.SOUTH, descriptionArea, -100, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.WEST, fightButton, 20, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, fightButton, 20, SpringLayout.SOUTH, descriptionArea);

        layout.putConstraint(SpringLayout.WEST, negotiateButton, 20, SpringLayout.EAST, fightButton);
        layout.putConstraint(SpringLayout.NORTH, negotiateButton, 0, SpringLayout.NORTH, fightButton);

        layout.putConstraint(SpringLayout.WEST, fleeButton, 20, SpringLayout.EAST, negotiateButton);
        layout.putConstraint(SpringLayout.NORTH, fleeButton, 0, SpringLayout.NORTH, fightButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, backButton, 20, SpringLayout.SOUTH, fightButton);
    }

    @Override
    public void updateUiResponse(String message) {
        JOptionPane.showMessageDialog(this, "Success: " + message, "Event Response", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void failureResponse(String errorMessage) {
        JOptionPane.showMessageDialog(this, "Error: " + errorMessage, "Event Response", JOptionPane.ERROR_MESSAGE);
    }
}
