package kdn.kazabot.commands.slashcommands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.concurrent.ThreadLocalRandom;

public class Cmd8Ball
{
    public Cmd8Ball(SlashCommandInteractionEvent event)
    {
        String[] answers = {
                "For sure :100:",
                "Without a doubt",
                "*sigh* Unfortunately, yes",
                "My algorithms tell me theres a good chance :grin:",
                "Well duh",
                "Yes, if you leave me alone",
                "Come on, lets be real here",
                "Yeah definitely not :thumbsdown:",
                "You wouldn't like the answer to that :grimacing:",
        };
        int randomNum = ThreadLocalRandom.current().nextInt(0, answers.length);
        String question = event.getOption("question").getAsString();

        event.reply(event.getUser().getName() + "'s question:\n\t\t" + question + "\n\n" + answers[randomNum]).queue();
    }
}
