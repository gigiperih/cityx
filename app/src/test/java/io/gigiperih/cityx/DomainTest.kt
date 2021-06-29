package io.gigiperih.cityx

import org.junit.Test
import kotlin.system.measureTimeMillis

class DomainTest {
    @Test
    fun `verify search function is consume better than linear time`() {
        // https://stackoverflow.com/questions/44099480/how-can-i-get-the-time-it-takes-a-function-to-test-the-performance-of-functions
        val time = measureTimeMillis {
            doNothing()
        }

        TODO("Not yet implemented")
    }

    private fun doNothing() {
        // do nothing
    }
}