package com.kilchichakov.emails.parser.format

data class FormatStructure(
    val format: FileFormat,
    val sub: List<FormatStructure>
)
