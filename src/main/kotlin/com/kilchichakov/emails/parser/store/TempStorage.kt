package com.kilchichakov.emails.parser.store

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID
import kotlin.io.path.Path

object TempStorage {

    // TODO consider removing created temp directories on shutdown
    fun provideTempDir(): File {
        val result = Files.createTempDirectory(UUID.randomUUID().toString())
        return result.toFile()
    }

    fun moveFile(file: File, directory: File) {
        directory.mkdir()
        val target = File(directory, file.name)
        Files.move(file.toPath(), target.toPath())
    }
}
