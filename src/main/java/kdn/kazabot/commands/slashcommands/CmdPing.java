package kdn.kazabot.commands.slashcommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CmdPing
{
    public CmdPing(SlashCommandInteractionEvent event) {
        long time = System.currentTimeMillis();
        event.reply("Pong!").queue();
        event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
    }
}
