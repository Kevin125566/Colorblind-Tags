package com.colorblindtags;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

@Slf4j
@PluginDescriptor(
	name = "Colorblind Tags"
)
public class ColorblindTagsPlugin extends Plugin
{
	private static final String ICON_FILE = "/icons/invisible_icon.png";

	@Inject
	private Client client;

	@Inject
	private ColorblindTagsConfig config;

	@Inject
	private ClientToolbar clientToolbar;

	private ColorblindTagsPluginPanel pluginPanel;
	private NavigationButton navButton;

	@Override
	protected void startUp() throws Exception
	{
		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), ICON_FILE);

		pluginPanel = new ColorblindTagsPluginPanel(client, this, config);
		navButton = NavigationButton.builder()
				.tooltip("Colorblind Tags")
				.icon(icon)
				.priority(70)
				.panel(pluginPanel)
				.build();

		clientToolbar.addNavigation(navButton);
//		pluginPanel.rebuild();


	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(navButton);
//		pluginPanel.update(state);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	ColorblindTagsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ColorblindTagsConfig.class);
	}
}
