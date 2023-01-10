package com.kilchichakov.emails.parser.args

import com.kilchichakov.emails.parser.store.FileFormat
import java.io.File

object ArgumentParser {

    fun parse(args: Array<String>): Arguments {
        lateinit var inputFile: String
        lateinit var inputFormat: String
        var output = "output/"

        for (arg in args) {
            when {
                arg.startsWith("--input=") -> inputFile = arg.substringAfter("=")
                arg.startsWith("--output=") -> output = arg.substringAfter("=")
                arg.startsWith("--format=") -> inputFormat = arg.substringAfter("=")
            }
        }

        return Arguments(
            inputFile = File(inputFile),
            format = FileFormat.valueOf(inputFormat.uppercase()),
            output = File(output)
        )
    }
}
