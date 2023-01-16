package com.colorblindtags;

import net.runelite.api.Client;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.IconTextField;
import net.runelite.client.ui.components.PluginErrorPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ColorblindTagsPluginPanel extends PluginPanel
{
    private final JLabel title = new JLabel();
    private final JPanel searchPanel = new JPanel(new BorderLayout());
    private final PluginErrorPanel noMarkersPanel = new PluginErrorPanel();
    private final IconTextField searchBar = new IconTextField();

    private final Client client;
    private final ColorblindTagsPlugin plugin;
    private final ColorblindTagsConfig config;

    public ColorblindTagsPluginPanel(Client client, ColorblindTagsPlugin plugin, ColorblindTagsConfig config)
    {
        this.client = client;
        this.plugin = plugin;
        this.config = config;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(new EmptyBorder(1, 0, 10, 0));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(1, 3, 10, 7));

        JPanel markerButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 7, 3));

        searchPanel.setBorder(new EmptyBorder(1, 0, 0, 0));

        title.setText("Colorblind Tags");
        title.setForeground(Color.WHITE);

        titlePanel.add(title, BorderLayout.WEST);
        titlePanel.add(markerButtons, BorderLayout.EAST);

        searchPanel.add(searchBar, BorderLayout.WEST);

        northPanel.add(titlePanel, BorderLayout.NORTH);
        northPanel.add(searchPanel, BorderLayout.CENTER);
    }
}
