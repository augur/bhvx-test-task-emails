package com.kilchichakov.emails.parser.store

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
}