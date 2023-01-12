package com.kilchichakov.emails.parser.format

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class FormatStructureParserTest {

    @Test
    fun `parse 1-level structure`() {
        // given
        val input = javaClass.getResourceAsStream("/format/level1.yaml")!!

        // when
        val actual = FormatStructureParser.parse(input)

        // then
        assertThat(actual).isEqualTo(FormatStructure(FileFormat.ZIP, emptyList()))
    }

    @Test
    fun `parse 2-level structure`() {
        // given
        val input = javaClass.getResourceAsStream("/format/level2.yaml")!!

        // when
        val actual = FormatStructureParser.parse(input)

        // then
        assertThat(actual).isEqualTo(
            FormatStructure(FileFormat.ZIP, listOf(FormatStructure(FileFormat.EML, emptyList())))
        )
    }

    @Test
    fun `parse 3-level structure`() {
        // given
        val input = javaClass.getResourceAsStream("/format/level3.yaml")!!

        // when
        val actual = FormatStructureParser.parse(input)

        // then
        assertThat(actual).isEqualTo(
            FormatStructure(FileFormat.ZIP, listOf(
                FormatStructure(FileFormat.EML, listOf(
                    FormatStructure(FileFormat.ZIP, emptyList())
                )))
            )
        )
    }

    @Test
    fun `parse 3-level #2 structure`() {
        // given
        val input = javaClass.getResourceAsStream("/format/level3_2.yaml")!!

        // when
        val actual = FormatStructureParser.parse(input)

        // then
        assertThat(actual).isEqualTo(
            FormatStructure(FileFormat.ZIP, listOf(
                FormatStructure(FileFormat.EML, listOf(
                    FormatStructure(FileFormat.ZIP, emptyList()),
                    FormatStructure(FileFormat.EML, emptyList())
                )))
            )
        )
    }

    @Test
    fun `parse 4-level structure`() {
        // given
        val input = javaClass.getResourceAsStream("/format/level4.yaml")!!

        // when
        val actual = FormatStructureParser.parse(input)

        // then
        assertThat(actual).isEqualTo(
            FormatStructure(FileFormat.ZIP, listOf(
                FormatStructure(FileFormat.EML, listOf(
                    FormatStructure(FileFormat.ZIP, emptyList()),
                    FormatStructure(FileFormat.EML, listOf(
                        FormatStructure(FileFormat.EML, emptyList())
                    ))
                )))
            )
        )
    }
}