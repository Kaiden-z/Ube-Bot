package kdn.kazabot.commands.slashcommands;

import kdn.kazabot.lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class CmdPlay
{
    public CmdPlay(SlashCommandInteractionEvent event)
    {
        if (!event.isFromGuild()) return;

        Guild guild = event.getGuild();
        GuildVoiceState userVoiceState = event.getMember().getVoiceState();

        if (!userVoiceState.inAudioChannel())
        {
            event.reply("You need to be in a voice channel to play music").queue();
            return;
        }

        final AudioManager manager = guild.getAudioManager();
        final VoiceChannel channel = userVoiceState.getChannel().asVoiceChannel();

        manager.openAudioConnection(channel);

        String link = event.getOption("link").getAsString();
        if (!isUrl(link)) {
            link = "ytsearch:" + link + "audio";
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.getMessageChannel()
                .getJDA().getTextChannelById(event.getMessageChannel().getId()), link);

        event.reply("Playing: " + link).queue();
    }

    private boolean isUrl(String url)
    {
        try
        {
            new URI(url);
            return true;
        }
        catch (URISyntaxException e)
        {
            return false;
        }
    }
}
