package com.kilchichakov.emails.parser.args

import java.io.File

data class Arguments(
    val inputFile: File,
    val formatFile: File,
    val output: File
)
