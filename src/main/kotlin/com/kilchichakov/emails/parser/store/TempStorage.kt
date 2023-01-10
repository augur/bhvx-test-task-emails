package com.kilchichakov.emails.parser.store

import java.io.File
import java.nio.file.Files
import java.util.UUID

object TempStorage {

    fun provideTempDir(): File {
        val result = Files.createTempDirectory(UUID.randomUUID().toString())
        return result.toFile()
    }
}