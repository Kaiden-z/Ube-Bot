package kdn.kazabot.commands.slashcommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;

public class CmdHelp
{
    public CmdHelp(SlashCommandInteractionEvent event, List<CommandData> commandData)
    {
        StringBuilder sb = new StringBuilder();
        CommandData cur;

        sb.append("List of available commands:\n");
        for (int i = 0; i < commandData.size(); i++) {
            cur = commandData.get(i);
            sb.append("\t/" + cur.getName());

            sb.append("\n");
        }

        event.reply(sb.toString()).queue();
    }
}
