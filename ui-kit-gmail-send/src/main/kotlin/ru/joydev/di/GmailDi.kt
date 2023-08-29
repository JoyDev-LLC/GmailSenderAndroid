package ru.joydev.di

import ru.joydev.utils.GmailServer

internal object GmailDi {

    private val gmailServer = GmailServer()

    fun getGmailServer() = gmailServer
}
