package ru.joydev.gmail

import ru.joydev.di.GmailDi

/**
 * @see setUpServerCredentials
 * Метод для задания сервера-отправки сообщений
 *
 * @param internalEmail для внутренней почты, с которой будет производиться отправка сообщений
 * @param password пароль для внутренней почты
 */
object GmailServerSetUp {

    fun setUpServerCredentials(internalEmail: String, password: String) {
        GmailDi.getGmailServer().setUpServer(internalEmail, password)
    }
}
