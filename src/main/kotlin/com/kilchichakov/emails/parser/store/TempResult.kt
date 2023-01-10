package com.kilchichakov.emails.parser.store

import java.io.File

/**
 * Intermediate result file, stored in temporary folder
 */
data class TempResult(
    val file: File,
    val format: FileFormat,
)
