package io.gigiperih.cityx

import org.junit.Test
import kotlin.system.measureTimeMillis

class DomainTest {
    @Test
    fun `verify search function is executed in a better than linear time-complexity`() {
        // https://stackoverflow.com/questions/44099480/how-can-i-get-the-time-it-takes-a-function-to-test-the-performance-of-functions

        // assuming data are mapped to hashmap we'll get better result than linear-time
        val time = measureTimeMillis {
            doNothing()
        }

        TODO("Not yet implemented")
    }

    private fun doNothing() {
        // do nothing
    }
}