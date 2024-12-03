package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 * Information view.
 */
public class InformationView extends JFrame {
    @SuppressWarnings({"checkstyle:LambdaParameterName", "checkstyle:SuppressWarnings"})
    public InformationView(String information) {
        super("Information");

        // Set up the container
        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // Create JTextArea to display information
        final JTextArea infoArea = new JTextArea(information);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setBackground(Color.LIGHT_GRAY);
        infoArea.setFont(new Font("Serif", Font.PLAIN, Constants.TWENTY));
        infoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add a scroll pane for JTextArea
        final JScrollPane scrollPane = new JScrollPane(infoArea);
        container.add(scrollPane);

        // Create the back button
        final JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, Constants.TWENTY));
        container.add(backButton);

        // Layout constraints
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, Constants.TWENTY, SpringLayout.NORTH, container);
        layout.putConstraint(SpringLayout.WEST, scrollPane, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, scrollPane, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.SOUTH, scrollPane, -Constants.SIXTY, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, backButton, Constants.TWENTY, SpringLayout.SOUTH, scrollPane);

        // Add action listener to the back button
        backButton.addActionListener(e -> {
            dispose();
            new GameView();
        });

        // Set window properties
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
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
        setSize(Constants.FOUR_HUNDRED, Constants.SIX_HUNDRED);
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

    public static void main(String[] args) {
        // Sample information to display
        final String sampleInformation = "This is the system-generated information. "
                + "It can include logs, updates, or any other details about the backend process.";
        new InformationView(sampleInformation);
    }
}
