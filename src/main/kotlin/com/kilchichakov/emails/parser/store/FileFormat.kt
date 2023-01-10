package com.kilchichakov.emails.parser.store

import java.io.File

enum class FileFormat {
    ZIP,
    EML;

    companion object {
        fun getFileFormat(file: File): FileFormat {
            return FileFormat.valueOf(file.extension.uppercase())
        }
    }
}

