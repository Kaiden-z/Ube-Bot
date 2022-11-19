package kdn.kazabot.commands;

import kdn.kazabot.commands.slashcommands.Cmd8Ball;
import kdn.kazabot.commands.slashcommands.CmdHelp;
import kdn.kazabot.commands.slashcommands.CmdPing;
import kdn.kazabot.commands.slashcommands.CmdPlay;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SlashCommandManager extends ListenerAdapter
{
    /**
     * Class constants
     * @var {List<CommandData>} commandData - list of available slash commands
     */
    public List<CommandData> commandData;

    /**
     * Register commands to bot
     */
    private void registerGuildCommands()
    {
        int maxOptions = 2;
        List<OptionData> optionList = new ArrayList<>();
        for (int i = 0; i < maxOptions; i++) {
            optionList.add(null);
        }
        commandData = new ArrayList<>();

        // Help
        commandData.add(Commands.slash("help", "See a list of available commands"));

        // Ping
        commandData.add(Commands.slash("ping", "See bot response time"));

        // 8ball
        optionList.set(0, new OptionData(OptionType.STRING, "question", "What would you like to ask?", true));
        commandData.add(Commands.slash("8ball", "Ask a yes/no question to the bot").addOptions(optionList.get(0)));

        // Play
        optionList.set(0, new OptionData(OptionType.STRING, "link", "Enter the url of the song you'd like to play", true));
        commandData.add(Commands.slash("play", "Add a song to the queue").addOptions(optionList.get(0)));
    }

    /**
     * Intialize commands for current guilds
     * @param {GuildReadyEvent} event - user's command
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event)
    {
        registerGuildCommands();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    /**
     * Intialize commands for recently joined guilds
     * @param {GuildReadyEvent} event - user's command
     */
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        registerGuildCommands();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    /**
     * Initialize commands for global usage
     * @param {ReadyEvent} event - user's command
     */
    @Override
    public void onReady(ReadyEvent event) {
        // Implement commands after finalized on guild
        // use: event.getJDA().updateCommands().addCommands(commandData).queue();
    }

    /**
     * Define bot behaviors for commands
     * @param {SlashCommandInteractionEvent} event - user's command
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // Proper user check
        if (event.getMember().getUser().isBot()) return;

        String command = event.getName();

        // Help
        if (command.equalsIgnoreCase("help"))
        {
            new CmdHelp(event, commandData);
        }

        // Ping
        else if (command.equalsIgnoreCase("ping"))
        {
            new CmdPing(event);
        }

        // 8Ball
        else if (command.equalsIgnoreCase("8ball"))
        {
            new Cmd8Ball(event);
        }

        // Play
        else if (command.equalsIgnoreCase("play"))
        {
            new CmdPlay(event);
        }
    }
}
