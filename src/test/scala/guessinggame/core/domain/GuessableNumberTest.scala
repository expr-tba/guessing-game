package guessinggame.core.domain

import minitest.SimpleTestSuite

/**
 * Developed by Bia.
 */

object GuessableNumberTest extends SimpleTestSuite {
	test("can't create a GuessableNumber from -1") {
		assertEquals(GuessableNumber.fromInt(-1), None)
	}

	test("can't create a GuessableNumber from 0") {
		assertEquals(GuessableNumber.fromInt(0), None)
	}

	test("Can create a GuessableNumber from 1") {
		val received = GuessableNumber.fromInt(1).map(_.value)
		assertEquals(received, Some(1))
	}

	test("Can create a GuessableNumber from 99") {
		val received = GuessableNumber.fromInt(99).map(_.value)
		assertEquals(received, Some(99))
	}

	test("Can create a GuessableNumber from 100") {
		val received = GuessableNumber.fromInt(100).map(_.value)
		assertEquals(received, Some(100))
	}

	test("Can't create a GuessableNumber from 101") {
		assertEquals(GuessableNumber.fromInt(101), None)
	}

	test("Can't create GuessableNumber from non-number string") {
		assertEquals(GuessableNumber.fromString(""), None)
	}

	test("Can create a GuessableNumber from a valid String") {
		val received = GuessableNumber.fromString("42").map(_.value)
		assertEquals(received, Some(42))
	}
}
