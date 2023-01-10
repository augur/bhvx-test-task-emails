package com.kilchichakov.emails.parser.store

import java.io.File

data class TempResult(
    val file: File,
    val format: FileFormat,
    val isFinal: Boolean // true if file doesn't contain any nested files
)
