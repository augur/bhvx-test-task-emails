package com.kilchichakov.emails.parser.format

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


class StructuredExtractorTest {

    @Test
    fun `structured extract - level 1`() {
        // given
        val input = javaClass.getResource("/compound/archive.zip")!!
        val structure = FormatStructure(FileFormat.ZIP, emptyList())

        // when
        val actual = StructuredExtractor.extract(File(input.file), structure)

        // then
        assertThat(actual).hasSize(2)
        assertThat(actual.first()).exists()
        assertThat(actual.first().name).isEqualTo("Email 1.eml")
        assertThat(actual.last()).exists()
        assertThat(actual.last().name).isEqualTo("Email 2.eml")
        //println(actual)
    }

    @Test
    fun `structured extract - level 2`() {
        // given
        val input = javaClass.getResource("/compound/archive.zip")!!
        val structure = FormatStructure(FileFormat.ZIP, listOf(
                FormatStructure(FileFormat.EML, emptyList())
            ))

        // when
        val actual = StructuredExtractor.extract(File(input.file), structure)

        // then
        assertThat(actual).hasSize(2)
        assertThat(actual.first()).exists()
        assertThat(actual.first().name).isEqualTo("archive-1.zip")
        assertThat(actual.last()).exists()
        assertThat(actual.last().name).isEqualTo("archive-2.zip")
    }

    @Test
    fun `structured extract - level 3`() {
        // given
        val input = javaClass.getResource("/compound/archive.zip")!!
        val structure = FormatStructure(FileFormat.ZIP, listOf(
                FormatStructure(FileFormat.EML, listOf(
                        FormatStructure(FileFormat.ZIP, emptyList()))
                )))

        // when
        val actual = StructuredExtractor.extract(File(input.file), structure)

        // then
        assertThat(actual).hasSize(3)
        assertThat(actual.first()).exists()
        assertThat(actual.first().name).isEqualTo("Fwd  Test 2.eml")
        assertThat(actual.last()).exists()
        assertThat(actual.last().name).isEqualTo("email-3-a.eml")
        //println(actual)
    }

    @Test
    fun `structured extract - level 4`() {
        // given
        val input = javaClass.getResource("/compound/archive.zip")!!
        val structure = FormatStructure(FileFormat.ZIP, listOf(
            FormatStructure(FileFormat.EML, listOf(
                FormatStructure(FileFormat.ZIP, listOf(
                    FormatStructure(FileFormat.EML, emptyList())
                )))
            )))

        // when
        val actual = StructuredExtractor.extract(File(input.file), structure)

        // then
        assertThat(actual).hasSize(3)

        assertThat(actual.all { it.exists() }).isTrue()
        assertThat(actual.map { it.name }).containsExactlyInAnyOrder("Test 1.eml", "Test 2.eml", "Test 3.eml")
    }
}