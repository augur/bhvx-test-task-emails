package com.kilchichakov.emails.parser.args

import java.io.File

object ArgumentParser {

    fun parse(args: Array<String>): Arguments {
        lateinit var inputFile: String
        lateinit var formatFile: String
        var output = "output/"

        for (arg in args) {
            when {
                arg.startsWith("--input=") -> inputFile = arg.substringAfter("=")
                arg.startsWith("--output=") -> output = arg.substringAfter("=")
                arg.startsWith("--format=") -> formatFile = arg.substringAfter("=")
            }
        }

        return Arguments(
            inputFile = File(inputFile),
            formatFile = File(formatFile),
            output = File(output)
        )
    }
}
