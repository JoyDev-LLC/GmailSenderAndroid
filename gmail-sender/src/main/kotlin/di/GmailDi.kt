package di

import utils.GmailServer

internal object GmailDi {

    private val gmailServer = GmailServer()

    fun getGmailServer() = gmailServer
}
