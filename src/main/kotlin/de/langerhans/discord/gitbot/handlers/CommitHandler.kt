package de.langerhans.discord.gitbot.handlers

import de.langerhans.discord.gitbot.models.PullRequestEvent
import de.langerhans.discord.gitbot.models.PushEvent
import org.springframework.stereotype.Component

/**
 * Created by maxke on 07.08.2016.
 * handles new/closed PRs
 */

@Component
open class CommitHandler: AbstractHandler() {

    override fun handle(payload: String) {
        val event = gson.fromJson(payload, PushEvent::class.java)


        val message = "[${event.repository}]Push by ${event.pusher}:"
        client.getChannelByID(config.targetChannel).sendMessage(message)
        for (commit in event.commits)
        {
            client.getChannelByID(config.targetChannel).sendMessage("```${commit.message}")
        }


    }
}