package com.kilchichakov.emails.parser.args

import com.kilchichakov.emails.parser.store.FileFormat
import java.io.File

data class Arguments(
    val inputFile: File,
    val format: FileFormat,
    val output: File
)
