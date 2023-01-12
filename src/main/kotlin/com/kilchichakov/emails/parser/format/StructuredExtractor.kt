package com.kilchichakov.emails.parser.format

import com.kilchichakov.emails.parser.email.EmailExtractor
import com.kilchichakov.emails.parser.store.TempResult
import com.kilchichakov.emails.parser.zip.ZipExtractor
import java.io.File
import java.io.FileInputStream

object StructuredExtractor {

    /**
     * If structure is a leaf (i.e. doesn't have substructures) returns files within the given file
     * Otherwise, looks into substructure files, and returns their contents instead
     * Works recursively
     */
    fun extract(file: File, structure: FormatStructure): List<File> {
        val tempResults: List<TempResult>

        FileInputStream(file).use { fis ->
            tempResults = when (structure.format) {
                FileFormat.EML -> EmailExtractor.extract(fis)
                FileFormat.ZIP -> ZipExtractor.extract(fis)
            }
        }

        if (structure.sub.isEmpty()) {
            return tempResults.map { it.file }
        } else {
            val finalResult = mutableListOf<File>()

            structure.sub.forEach { struct ->
                tempResults.forEach { tempResult ->
                    if (struct.format == tempResult.format) {
                        finalResult.addAll(
                            extract(tempResult.file, struct)
                        )
                    }
                }
            }

            return finalResult
        }
    }
}
