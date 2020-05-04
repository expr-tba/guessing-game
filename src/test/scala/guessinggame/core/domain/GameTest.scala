package guessinggame.core.domain

import guessinggame.core.domain.Result.{GameOver, Success, Wrong}
import minitest.SimpleTestSuite

/**
 * Developed by Bia.
 */

object GameTest extends SimpleTestSuite {
	test("Created game has 5 guesses") {
		val received = Game.fromTargetNumber(50).map(_.remainingGuesses)
		assertEquals(received, Some(5))
	}

	test("Created game uses passed in target number") {
		val validTargetNumber = Seq(1, 23, 99, 100)
		validTargetNumber.foreach { n =>
			val received = Game.fromTargetNumber(n).map(_.targetNumber.value)
			assertEquals(received, Some(n))
		}
	}

	test("Game cannot be created from invalid target numbers") {
		val invalidTargetNumbers = Seq(-1, 0, 101)
		invalidTargetNumbers.foreach { n =>
			assertEquals(Game.fromTargetNumber(n), None)
		}
	}

	private def setupTest(target: Int, guess: Int): (Game, GuessableNumber) =
		(createGame(target), createNumber(guess))

	private def createNumber(n: Int): GuessableNumber =
		GuessableNumber.fromInt(n)
			.getOrElse(sys.error("Guessable number must be between 1 and 100"))

	private def createGame(targetNumber: Int): Game =
		Game.fromTargetNumber(targetNumber)
			.getOrElse(sys.error("Target number must be between 1 and 100"))

	private def assertWrong(result: Result, check: Wrong => Boolean): Unit =
		result match {
			case wrong: Wrong => assert(check(wrong))
			case other => fail(s"Result is not Wrong, but $other")
		}

	@scala.annotation.tailrec
	private def performGuess(game: Game, guess: GuessableNumber, attemps: Int): Result =
		if (attemps > 1) {
			game.processGuess(guess) match {
				case res@(Success | GameOver) => res
				case Wrong(next, _) => performGuess(next, guess, attemps - 1)
			}
		} else game.processGuess(guess)

	test("Processing guess leads to game over after five attempts") {
		val (game, guess) = setupTest(target = 5, guess = 1)
		val endResult = performGuess(game, guess, 5)
		assertEquals(endResult, Success)
	}

	test("Processing correct guess leeds to Success") {
		val (game, guess) = setupTest(target = 50, guess = 50)
		val endResult = performGuess(game, guess, 1)
		assertEquals(endResult, Success)
	}

	test("Processing guess that is TooHigh") {
		val (game, guess) = setupTest(target = 50, guess = 51)
		val endResult = performGuess(game, guess, 1)
		assertWrong(endResult, _.hint == Hint.TooHigh)
	}

	test("Processing guess that is TooLow") {
		val (game, guess) = setupTest(target = 50, 49)
		val endResult = performGuess(game, guess, 1)
		assertWrong(endResult, _.hint == Hint.TooLow)
	}
}
