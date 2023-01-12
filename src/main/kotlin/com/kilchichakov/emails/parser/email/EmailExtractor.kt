package com.kilchichakov.emails.parser.email

import com.kilchichakov.emails.parser.format.FileFormat
import com.kilchichakov.emails.parser.store.TempResult
import com.kilchichakov.emails.parser.store.TempStorage
import java.io.File
import java.io.InputStream
import java.util.Properties
import javax.mail.Multipart
import javax.mail.Part
import javax.mail.Session
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage

object EmailExtractor {

    /**
     * Returns attachments of the particular EML, or empty list, if it doesn't have any permitted attachment types
     */
    fun extract(inputStream: InputStream): List<TempResult> {
        val result = mutableListOf<TempResult>()
        val session = Session.getDefaultInstance(Properties(), null)
        val msg = MimeMessage(session, inputStream)
        val content = msg.content
        val dir = TempStorage.provideTempDir()
        if (content is Multipart) {
            for (i in 0 until content.count) {
                val bodyPart = content.getBodyPart(i) as MimeBodyPart
                if (Part.ATTACHMENT.equals(bodyPart.disposition, ignoreCase = true)) {

                    // TODO add workaround if bodyPart.fileName is null
                    val file = File(dir.toString() + File.separator + bodyPart.fileName)
                    result.add(
                        TempResult(
                            file = file,
                            format = FileFormat.getFileFormat(file)
                        )
                    )
                    bodyPart.saveFile(file)
                }
            }
        }

        return result
    }
}
