package com.kilchichakov.emails.parser.store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.util.UUID

class TempStorageTest {

    @Test
    fun `provide temp dirs - happy path`() {
        // given, when
        val actual1 = TempStorage.provideTempDir()
        val actual2 = TempStorage.provideTempDir()

        // then
        assertThat(actual1).exists()
        assertThat(actual2).exists()
        assertThat(actual1).isNotEqualTo(actual2)
    }

    @Test
    fun `move file - happy path`() {
        // given
        val dir = TempStorage.provideTempDir()
        val file = File(TempStorage.provideTempDir(), UUID.randomUUID().toString())
        val newLocation = File(dir, file.name)
        file.createNewFile()

        assertThat(file).exists()
        assertThat(newLocation).doesNotExist()

        // when
        TempStorage.moveFile(file, dir)

        // then
        assertThat(file).doesNotExist()
        assertThat(newLocation).exists()
    }
}