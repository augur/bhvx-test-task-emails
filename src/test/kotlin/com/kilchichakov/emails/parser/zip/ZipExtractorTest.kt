package com.kilchichakov.emails.parser.zip

import com.kilchichakov.emails.parser.format.FileFormat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ZipExtractorTest {

    @Test
    fun `extract - happy path`() {
        // given
        val input = javaClass.getResourceAsStream("/zips/test.zip")!!

        // when
        val actual = ZipExtractor.extract(input)

        // then
        assertThat(actual).hasSize(2)
        assertThat(actual.first().file).exists()
        assertThat(actual.first().format).isEqualTo(FileFormat.ZIP)
        assertThat(actual.first().file.name).isEqualTo("inner1.zip")
        assertThat(actual.first().file.readText()).isEqualTo("123")
        assertThat(actual.last().file).exists()
        assertThat(actual.last().format).isEqualTo(FileFormat.EML)
        assertThat(actual.last().file.name).isEqualTo("inner2.eml")
        assertThat(actual.last().file.readText()).isEqualTo("456")
    }
}