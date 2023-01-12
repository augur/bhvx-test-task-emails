package com.kilchichakov.emails.parser

import com.kilchichakov.emails.parser.args.ArgumentParser
import com.kilchichakov.emails.parser.format.FormatStructureParser
import com.kilchichakov.emails.parser.format.StructuredExtractor
import com.kilchichakov.emails.parser.store.TempStorage
import java.io.FileInputStream

fun main(args: Array<String>) {
    println("behavox-test-task-emails main() started")
    println("Input args: ${args.joinToString()}")

    val arguments = ArgumentParser.parse(args)

    println("Arguments: $arguments")

    val structure = FileInputStream(arguments.formatFile).use { FormatStructureParser.parse(it) }
    println("Parsed format structure: $structure")
    val result = StructuredExtractor.extract(arguments.inputFile, structure)
    println("Final extraction result: ${result.joinToString(separator = " ") { it.name }}")

    result.forEach {
        TempStorage.moveFile(it, arguments.output)
    }

    println("behavox-test-task-emails main() finished")
}
