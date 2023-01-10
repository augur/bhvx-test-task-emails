package com.kilchichakov.emails.parser

import com.kilchichakov.emails.parser.args.ArgumentParser

fun main(args: Array<String>) {
    println("behavox-test-task-emails main() started")
    println("Input args: ${args.joinToString()}")

    val arguments = ArgumentParser.parse(
        if (args.isNotEmpty()) args else arrayOf("--input=in.zip", "--format=ZIP") //TODO remove on release
    )

    println("Arguments: $arguments")

    // create dir in temp
    // return extraction result
    // save result in output dir

    println("behavox-test-task-emails main() finished")

}