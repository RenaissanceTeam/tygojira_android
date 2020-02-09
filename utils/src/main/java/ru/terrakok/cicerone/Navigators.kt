package ru.terrakok.cicerone

import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace

/**
 * Created by 23alot on 02.02.2020.
 * @see https://gitlab.com/terrakok/gitlab-client/blob/develop/app/src/main/java/ru/terrakok/gitlabclient/util/Extensions.kt
 * by terrakok
 */
fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(
        arrayOf(
            BackTo(null),
            Replace(screen)
        )
    )
}

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"
