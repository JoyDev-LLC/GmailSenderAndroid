package gmail

import android.util.Log
import di.GmailDi
import utils.isMainThread

/**
 * Класс обёртка для public api
 */
class GmailSender private constructor(
    private val title: String,
    private val text: String,
    private val emailsTo: List<String>
) {

    /**
     * @see sendMessage метод для отправки текстовых сообщений
     */
    suspend fun sendMessage(): Boolean {
        if (isMainThread()) {
            Log.e("GmailSender", "sendMessage can not be called from Main thread")
            return false
        }
        if (listOf(title, text).any { value -> value.isBlank() } or emailsTo.isEmpty()) {
            Log.e("GmailSender", "Specify title, text and email(s) to send")
            return false
        }
        return GmailDi.getGmailServer().sendMessage(title, text, emailsTo)
    }

    /**
     * Builder для конфигурирования сообщения
     */
    class GmailSenderBuilder {

        private var title: String = ""
        private var text: String = ""
        private var emailsTo: List<String> = emptyList()

        fun setTitle(title: String) = apply { this.title = title }
        fun setText(text: String) = apply { this.text = text }
        fun setEmailsTo(emailsTo: List<String>) = apply { this.emailsTo = emailsTo }

        fun build() = GmailSender(title, text, emailsTo)
    }
}
