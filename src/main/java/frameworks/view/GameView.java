package frameworks.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    public GameView() {
        super("Game");

        final Container container = getContentPane();
        final SpringLayout layout = new SpringLayout();
        container.setLayout(layout);

        // 标题部分
        final JLabel dayLabel = new JLabel("Day: 50");
        dayLabel.setFont(new Font("Serif", Font.BOLD, Constants.TWENTY));
        container.add(dayLabel);

        // 左侧资源部分
        final JLabel foodLabel = new JLabel("Food: 50");
        final JLabel waterLabel = new JLabel("Water: 30");
        final JLabel peopleLabel = new JLabel("People: 20");
        final JLabel weaponLabel = new JLabel("Weapon: 15");
        container.add(foodLabel);
        container.add(waterLabel);
        container.add(peopleLabel);
        container.add(weaponLabel);

        // 小地图部分
        final JPanel mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPanel.setPreferredSize(new Dimension(200, 200));
        container.add(mapPanel);
        final JLabel mapLabel = new JLabel("Mini Map");
        mapPanel.add(mapLabel);

        // 信息区域
        final JLabel infoLabel = new JLabel("Information:");
        final JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setBackground(Color.LIGHT_GRAY);
        infoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(infoLabel);
        container.add(infoArea);

        // 动作按钮
        final JButton broadcastButton = new JButton("Broadcast");
        final JButton gatherButton = new JButton("Gather");
        final JButton moveButton = new JButton("Move");
        final JButton nextDayButton = new JButton("Next Day");
        container.add(broadcastButton);
        container.add(gatherButton);
        container.add(moveButton);
        container.add(nextDayButton);

        // 布局部分
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

        layout.putConstraint(SpringLayout.EAST, mapPanel, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, mapPanel, Constants.TWENTY, SpringLayout.NORTH, container);

        layout.putConstraint(SpringLayout.WEST, infoLabel, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, infoLabel, Constants.TWENTY, SpringLayout.SOUTH, weaponLabel);

        layout.putConstraint(SpringLayout.WEST, infoArea, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.EAST, infoArea, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, infoArea, Constants.TEN, SpringLayout.SOUTH, infoLabel);
        layout.putConstraint(SpringLayout.SOUTH, infoArea, -Constants.ONE_HUNDRED, SpringLayout.SOUTH, container);

        layout.putConstraint(SpringLayout.WEST, broadcastButton, Constants.TWENTY, SpringLayout.WEST, container);
        layout.putConstraint(SpringLayout.NORTH, broadcastButton, Constants.TWENTY, SpringLayout.SOUTH, infoArea);

        layout.putConstraint(SpringLayout.WEST, gatherButton, Constants.TWENTY, SpringLayout.EAST, broadcastButton);
        layout.putConstraint(SpringLayout.NORTH, gatherButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.WEST, moveButton, Constants.TWENTY, SpringLayout.EAST, gatherButton);
        layout.putConstraint(SpringLayout.NORTH, moveButton, 0, SpringLayout.NORTH, broadcastButton);

        layout.putConstraint(SpringLayout.EAST, nextDayButton, -Constants.TWENTY, SpringLayout.EAST, container);
        layout.putConstraint(SpringLayout.NORTH, nextDayButton, 0, SpringLayout.NORTH, broadcastButton);

        // 窗口设置
        setSize(Constants.SIX_HUNDRED, Constants.FOUR_HUNDRED);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameView();
    }
}
