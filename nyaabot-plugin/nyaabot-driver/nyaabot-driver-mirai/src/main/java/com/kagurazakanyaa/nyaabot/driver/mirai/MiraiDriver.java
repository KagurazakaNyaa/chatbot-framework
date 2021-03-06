package com.kagurazakanyaa.nyaabot.driver.mirai;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.kagurazakanyaa.nyaabot.api.IDriver;
import com.kagurazakanyaa.nyaabot.api.model.Configuration;
import com.kagurazakanyaa.nyaabot.common.JsonUtil;
import com.kagurazakanyaa.nyaabot.driver.mirai.model.MiraiChannel;
import com.kagurazakanyaa.nyaabot.driver.mirai.model.MiraiPluginConfiguration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.DirectoryLogger;

/**
 * @author KagurazakaNyaa
 *
 */
@Slf4j
public class MiraiDriver implements IDriver {

	private Map<String, MiraiChannel> channelMap = new HashMap<>();

	@Getter
	private Bot bot = null;

	static Configuration config = null;

	static MiraiPluginConfiguration pluginConfig = null;

	/**
	 * 获取频道
	 * 
	 * @param name 频道名
	 * @return 频道对象
	 */
	@Override
	public MiraiChannel getChannel(String name) {
		if (bot == null) {
			log.error("获取频道失败，请先登录");
			return null;
		}
		if (!channelMap.containsKey(name)) {
			updateChannelList();
		}
		return channelMap.get(name);
	}

	@Override
	public Boolean init(Configuration configuration) {
		try {
			if (Boolean.FALSE.equals(loadConfig(configuration))) {
				return false;
			}
			var botConfig = new BotConfiguration();
			botConfig.fileBasedDeviceInfo(Path.of(configuration.getConfigPath(), "deviceInfo.json").toString());
			botConfig.setBotLoggerSupplier((Bot basebot) -> new DirectoryLogger(configuration.getLogPath()));
			bot = BotFactory.INSTANCE.newBot(pluginConfig.getQqNumber(), pluginConfig.getPassword(), botConfig);
			bot.login();
			updateChannelList();
			return true;
		} catch (Exception e) {
			log.error("登录时发生错误", e);
			return false;
		}
	}

	private static Boolean loadConfig(Configuration configuration) {
		config = configuration;
		File configFile;
		configFile = Path.of(configuration.getConfigPath(), "config.json").toFile();
		if (!configFile.exists()) {
			log.error("配置文件不存在");
			return false;
		}
		pluginConfig = JsonUtil.fromFile(configFile, MiraiPluginConfiguration.class);
		return true;
	}

	private void updateChannelList() {
		if (bot == null) {
			log.error("获取频道失败，请先登录");
			return;
		}
		for (Group group : bot.getGroups()) {
			var name = String.valueOf(group.getId());
			channelMap.computeIfAbsent(name, n -> new MiraiChannel(n, this));
		}
	}

}
