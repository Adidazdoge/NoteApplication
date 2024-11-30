package frameworks.view;

import javax.swing.*;
import java.awt.*;

public class EventView extends JFrame {
    public EventView() {
        super("Event");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        final JLabel eventLabel = new JLabel("Event:");
        eventLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(eventLabel);

        final JTextArea descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.LIGHT_GRAY);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(descriptionArea);

        final JButton fightButton = new JButton("Fight");
        final JButton negotiateButton = new JButton("Negotiate");
        final JButton fleeButton = new JButton("Flee");
        final JButton backButton = new JButton("Back");
        container.add(fightButton);
        container.add(negotiateButton);
        container.add(fleeButton);
        container.add(backButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, eventLabel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, eventLabel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, descriptionArea, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, descriptionArea, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, descriptionArea, Constants.TWENTY, SpringLayout.SOUTH, eventLabel);
        layout.putConstraint(SpringLayout.SOUTH, descriptionArea, -Constants.ONE_HUNDRED, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.WEST, fightButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, fightButton, Constants.TWENTY, SpringLayout.SOUTH, descriptionArea);

        layout.putConstraint(SpringLayout.WEST, negotiateButton, Constants.TWENTY, SpringLayout.EAST, fightButton);
        layout.putConstraint(SpringLayout.NORTH, negotiateButton, 0, SpringLayout.NORTH, fightButton);

        layout.putConstraint(SpringLayout.WEST, fleeButton, Constants.TWENTY, SpringLayout.EAST, negotiateButton);
        layout.putConstraint(SpringLayout.NORTH, fleeButton, 0, SpringLayout.NORTH, fightButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, backButton, Constants.TWENTY, SpringLayout.SOUTH, fightButton);

        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EventView();
    }
}
