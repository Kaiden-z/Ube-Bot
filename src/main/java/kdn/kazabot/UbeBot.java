package kdn.kazabot;

import io.github.cdimascio.dotenv.Dotenv;
import kdn.kazabot.commands.SlashCommandManager;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

/**
 * Main class of UbeBot. Bot is initialized here.
 *
 * @author Kaiden Zapanta
 */
public class UbeBot
{
    /**
     * Class constants
     * @var {ShardManager} shardManager
     * @var {Dotenv} config
     */
    private final ShardManager shardManager;
    private final Dotenv config;

    /**
     * Initialize bot defaults
     */
    public UbeBot() throws LoginException
    {
        config = Dotenv.configure().load();

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault
            (
                config.get("TOKEN"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES
            );

        builder.enableCache(CacheFlag.VOICE_STATE);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("/help"));
        builder.addEventListeners(new SlashCommandManager());

        shardManager = builder.build();
    }

    /**
     * ShardManager getter
     */
    private ShardManager getShardManager()
    {
        return shardManager;
    }

    /**
     * Config getter
     */
    public Dotenv getConfig()
    {
        return config;
    }

    /**
     * Start bot
     */
    public static void main (String[] args)
    {
        try
        {
            UbeBot bot = new UbeBot();
            System.out.println("Bot initiated.");

//            Guild guild = getShardManager()
        }
        catch (LoginException e)
        {
            System.out.println("ERROR: Token is invalid");
        }
    }
}
