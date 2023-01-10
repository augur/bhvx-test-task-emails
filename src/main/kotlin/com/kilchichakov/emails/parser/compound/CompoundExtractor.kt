package com.kilchichakov.emails.parser.compound

import com.kilchichakov.emails.parser.email.EmailExtractor
import com.kilchichakov.emails.parser.store.FileFormat
import com.kilchichakov.emails.parser.store.TempResult
import com.kilchichakov.emails.parser.zip.ZipExtractor
import java.io.File
import java.io.FileInputStream
import java.util.LinkedList
import java.util.Queue

object CompoundExtractor {

    /**
     * Returns list of resulting files (stored in system temp dir)
     */
    fun extract(file: File, format: FileFormat): List<File> {
        val intermediateResults: Queue<TempResult> = LinkedList()
        val result = mutableListOf<File>()

        var nextResult: TempResult? = TempResult(file = file, format = format)
        while (nextResult != null) {
            FileInputStream(nextResult.file).use { fis ->
                when (nextResult!!.format) {
                    FileFormat.ZIP -> {
                        val contents = ZipExtractor.extract(fis)
                        intermediateResults.addAll(contents)
                    }
                    FileFormat.EML -> {
                        val attachments = EmailExtractor.extract(fis)
                        if (attachments.isEmpty()) {
                            result.add(nextResult!!.file)
                        } else {
                            intermediateResults.addAll(attachments)
                        }
                    }
                }
            }
            nextResult = intermediateResults.poll()
        }

        return result
    }
}
