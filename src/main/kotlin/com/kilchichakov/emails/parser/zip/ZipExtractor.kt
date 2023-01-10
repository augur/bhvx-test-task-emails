package com.kilchichakov.emails.parser.zip

import com.kilchichakov.emails.parser.store.FileFormat
import com.kilchichakov.emails.parser.store.TempResult
import com.kilchichakov.emails.parser.store.TempStorage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.ZipInputStream


object ZipExtractor {

    /**
     * Returns contents of the archive, or empty list, if archive doesn't contain any permitted file formats
     */
    fun extract(fis: InputStream): List<TempResult> {
        val zis = ZipInputStream(fis)
        val result = mutableListOf<TempResult>()
        var entry = zis.nextEntry
        val dir = TempStorage.provideTempDir()
        val buffer = ByteArray(65536)
        while (entry != null) {
            val entryFileName = entry.name
            val entryFile = File(dir.toString() + File.separator + entryFileName)
            val fos = FileOutputStream(entryFile)

            var len: Int
            while (zis.read(buffer).also { len = it } > 0) {
                fos.write(buffer, 0, len)
            }

            fos.close()
            result.add(
                TempResult(
                    file = entryFile,
                    format = FileFormat.getFileFormat(entryFile),
                    isFinal = false
                )
            )
            entry = zis.nextEntry
        }
        zis.close()
        return result
    }
}
