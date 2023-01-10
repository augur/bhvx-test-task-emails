package com.kilchichakov.emails.parser.args

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


class ArgumentParserTest {
    
    @Test
    fun `parse - happy path`() {
        // given
        val args = arrayOf("--input=in.zip", "--format=ZIP", "--output=some_out/")
        
        // when
        val actual = ArgumentParser.parse(args)
        
        // then
        assertThat(actual.inputFile).isEqualTo(File("in.zip"))
        assertThat(actual.format).isEqualTo(FileFormat.ZIP)
        assertThat(actual.output).isEqualTo(File("some_out/"))
    }

    @Test
    fun `parse - with default output`() {
        // given
        val args = arrayOf("--input=in.zip", "--format=EML")

        // when
        val actual = ArgumentParser.parse(args)

        // then
        assertThat(actual.inputFile).isEqualTo(File("in.zip"))
        assertThat(actual.format).isEqualTo(FileFormat.EML)
        assertThat(actual.output).isEqualTo(File("output/"))
    }
}