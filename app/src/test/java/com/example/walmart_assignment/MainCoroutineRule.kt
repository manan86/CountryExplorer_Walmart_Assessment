package com.example.walmart_assignment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * JUnit test rule for setting a main dispatcher in coroutine-based tests.
 * This rule replaces the main dispatcher with a [TestCoroutineDispatcher] for unit tests,
 * ensuring coroutine execution is controlled and testable. It also handles cleanup after tests.
 */
class MainCoroutineRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val testScope = TestCoroutineScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }
}