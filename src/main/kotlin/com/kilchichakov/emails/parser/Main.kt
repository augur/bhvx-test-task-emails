package com.kilchichakov.emails.parser

import com.kilchichakov.emails.parser.args.ArgumentParser
import com.kilchichakov.emails.parser.compound.CompoundExtractor
import com.kilchichakov.emails.parser.store.TempStorage

fun main(args: Array<String>) {
    println("behavox-test-task-emails main() started")
    println("Input args: ${args.joinToString()}")

    val arguments = ArgumentParser.parse(args)

    println("Arguments: $arguments")

    val result = CompoundExtractor.extract(arguments.inputFile, arguments.format)
    println("Final extraction result: ${result.joinToString(separator = " ") { it.name }}")

    result.forEach {
        TempStorage.moveFile(it, arguments.output)
    }

    println("behavox-test-task-emails main() finished")
}
