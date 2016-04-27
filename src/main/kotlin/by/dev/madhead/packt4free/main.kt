package by.dev.madhead.packt4free

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.jsoup.Jsoup
import java.net.URL
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

fun main(args: Array<String>) {
    val doc = Jsoup.parse(
            URL("https://www.packtpub.com/packt/offers/free-learning"),
            (System.getenv("PACKT4FREE_PARSE_TIMEOUT") ?: "3000").toInt()
    )

    Transport.send(
            MimeMessage(
                    Session.getDefaultInstance(
                            Properties().apply {
                                this["mail.smtp.auth"] = "true"
                                this["mail.smtp.starttls.enable"] = "true"
                                this["mail.smtp.host"] = System.getenv("PACKT4FREE_SMTP_SERVER")
                                this["mail.smtp.port"] = System.getenv("PACKT4FREE_SMTP_PORT")
                            },
                            object : Authenticator() {
                                override fun getPasswordAuthentication(): PasswordAuthentication? {
                                    return PasswordAuthentication(System.getenv("PACKT4FREE_SMTP_USERNAME"), System.getenv("PACKT4FREE_SMTP_PASSWORD"));
                                }
                            }
                    )
            ).apply {
                this.setFrom(InternetAddress(System.getenv("PACKT4FREE_FROM")))
                this.addRecipient(Message.RecipientType.TO, InternetAddress(System.getenv("PACKT4FREE_TO")))
                this.subject = "Packt free eBook @ %1\$tm/%1\$td/%1\$tY".format(Date())
                this.setContent(createHTML(true)
                        .html {
                            head { }
                            body {
                                img(src = "https:" + doc.select("#deal-of-the-day .dotd-main-book-image img").attr("src"))
                                br
                                a(href = "https://www.packtpub.com/packt/offers/free-learning") {
                                    +doc.select("#deal-of-the-day .dotd-title h2").text().trim()
                                }
                                br
                                a(href = "https://www.packtpub.com${doc.select("#deal-of-the-day a.twelve-days-claim").attr("href")}") {
                                    +"Claim now!"
                                }
                            }
                        },
                        "text/html; charset=utf-8")
            }
    )
}
