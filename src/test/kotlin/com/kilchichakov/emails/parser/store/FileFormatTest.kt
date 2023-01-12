package com.kilchichakov.emails.parser.store

import com.kilchichakov.emails.parser.format.FileFormat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class FileFormatTest {

    @Test
    fun `should get ZIP format`() {
        // given
        val file = File("path/some.zIp")

        // when
        val actual = FileFormat.getFileFormat(file)

        // then
        assertThat(actual).isEqualTo(FileFormat.ZIP)
    }

    @Test
    fun `should get EML format`() {
        // given
        val file = File("path/some.emL")

        // when
        val actual = FileFormat.getFileFormat(file)

        // then
        assertThat(actual).isEqualTo(FileFormat.EML)
    }
}
