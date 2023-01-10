package com.kilchichakov.emails.parser.email

import com.kilchichakov.emails.parser.store.FileFormat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class EmailExtractorTest {

    @Test
    fun `extract - happy path`() {
        // given
        val input = javaClass.getResourceAsStream("/emails/test.eml")!!

        // when
        val actual = EmailExtractor.extract(input)

        // then
        assertThat(actual).hasSize(1)
        assertThat(actual.first().file).exists()
        assertThat(actual.first().format).isEqualTo(FileFormat.ZIP)
        assertThat(actual.first().file.name).isEqualTo("archive-2.zip")
    }
}
