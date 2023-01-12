package com.kilchichakov.emails.parser.format

import org.yaml.snakeyaml.Yaml
import java.io.InputStream
import java.lang.IllegalArgumentException

object FormatStructureParser {

    fun parse(inputStream: InputStream): FormatStructure {
        val yaml = Yaml()
        val data: Any = yaml.load(inputStream)
        return parseStructure(data)
    }

    private fun parseStructure(data: Any): FormatStructure {
        return when (data) {
            is String -> FormatStructure(FileFormat.valueOf(data.uppercase()), emptyList())
            is Map<*, *> -> {
                data as Map<String, Any>
                val entry = data.entries.first()
                val format = FileFormat.valueOf(entry.key.uppercase())
                val subs = (entry.value as List<Any>?)?.map { parseStructure(it) } ?: emptyList()
                FormatStructure(format, subs)
            }
            else -> throw IllegalArgumentException()
        }
    }
}