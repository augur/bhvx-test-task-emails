package com.kilchichakov.emails.parser.args

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


class ArgumentParserTest {
    
    @Test
    fun `parse - happy path`() {
        // given
        val args = arrayOf("--input=in.zip", "--format=fmt.yaml", "--output=some_out/")
        
        // when
        val actual = ArgumentParser.parse(args)
        
        // then
        assertThat(actual.inputFile).isEqualTo(File("in.zip"))
        assertThat(actual.formatFile).isEqualTo(File("fmt.yaml"))
        assertThat(actual.output).isEqualTo(File("some_out/"))
    }

    @Test
    fun `parse - with default output`() {
        // given
        val args = arrayOf("--input=in.zip", "--format=fmt.yaml")

        // when
        val actual = ArgumentParser.parse(args)

        // then
        assertThat(actual.inputFile).isEqualTo(File("in.zip"))
        assertThat(actual.formatFile).isEqualTo(File("fmt.yaml"))
        assertThat(actual.output).isEqualTo(File("output/"))
    }
}