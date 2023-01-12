package com.kilchichakov.emails.parser.compound

import com.kilchichakov.emails.parser.format.FileFormat
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


class CompoundExtractorTest {


    @Test
    fun `compound extract - happy path`() {
        // given
        val input = javaClass.getResource("/compound/archive.zip")!!
        val expected1 = javaClass.getResource("/compound/Test 1.eml")!!.readText()
        val expected2 = javaClass.getResource("/compound/Test 2.eml")!!.readText()
        val expected3 = javaClass.getResource("/compound/Test 3.eml")!!.readText()

        // when
        val actual = CompoundExtractor.extract(File(input.file), FileFormat.ZIP)

        // then
        assertThat(actual).hasSize(3)
        assertThat(actual.find { it.name == "Test 1.eml" }!!.readText()).isEqualToNormalizingNewlines(expected1)
        assertThat(actual.find { it.name == "Test 2.eml" }!!.readText()).isEqualToNormalizingNewlines(expected2)
        assertThat(actual.find { it.name == "Test 3.eml" }!!.readText()).isEqualToNormalizingNewlines(expected3)
    }
}

