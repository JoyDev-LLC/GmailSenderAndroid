package ru.joydev.utils

import android.os.NetworkOnMainThreadException
import android.util.Log
import java.util.Date
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class GmailServer {

    private val props = Properties().also { props ->
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"
        props["mail.smtp.socketFactory.port"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.fallback"] = "false"
        props["mail.smtp.starttls.enable"] = "true"
    }

    private var internalEmail = ""
    private var passwordToEmail = ""

    fun setUpServer(internalEmail: String, passwordToEmail: String) {
        props["mail.smtp.user"] = internalEmail
        this.internalEmail = internalEmail
        this.passwordToEmail = passwordToEmail
    }

    suspend fun sendMessage(title: String, text: String, emailsTo: List<String>): Boolean =
        suspendCoroutine { continuation ->
            try {
                val mimeMessage = MimeMessage(getSession()).apply {
                    addRecipients(
                        Message.RecipientType.TO,
                        emailsTo.map { email -> InternetAddress(email) }.toTypedArray()
                    )
                    subject = title
                    sentDate = Date()
                    setText(text)
                }
                Transport.send(mimeMessage)
                continuation.resume(true)
            } catch (exception: MessagingException) {
                Log.e("GmailServer", "sendMessage: $exception")
                continuation.resume(false)
            } catch (exception: NetworkOnMainThreadException) {
                Log.e("GmailServer", "Can't call this method on Main Thread: $exception")
                continuation.resume(false)
            }
        }

    private fun getSession(): Session {
        return Session.getDefaultInstance(props, object : Authenticator(){
            override fun getPasswordAuthentication() = PasswordAuthentication(internalEmail, passwordToEmail)
        })
    }
}
