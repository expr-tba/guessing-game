package guessinggame.shell

import guessinggame.core.domain._
import guessinggame.core.presentation.Presentation
import scala.io.StdIn
import scala.util.Random
import scala.annotation.tailrec

/**
 * Developed by Bia.
 */

private[shell] class Application (
	messagePrinter: MessagePrinter = new MessagePrinter,
	inputReader: InputReader = prompt => StdIn.readLine(prompt),
	randomGenerator: () => Int = () => Random.nextInt(99) + 1
) {

	def run(): Unit = {
		messagePrinter.showIntro()
		loop(launchOrExit())
	}

	@tailrec
	private def loop(game: Game): Unit = {
		import messagePrinter._
		readGuess(Presentation.prompt) match {
			case None => showErrorMessage()
				loop(game)
			case Some(guess) => game.processGuess(guess) match {
				case Result.GameOver => showGameOverMessage()
				case Result.Success => showSuccessMessage(game.targetNumber)
				case Result.Wrong(nextState, hint) =>
					showHint(guess, hint)
					loop(nextState)
			}
		}
	}

	private def readGuess(promt: String): Option[GuessableNumber] =
		GuessableNumber.fromString(inputReader(promt))

	private def launchOrExit(): Game =
		Game.fromTargetNumber(randomGenerator()).getOrElse {
			messagePrinter.showProblemWithSetup()
			sys.exit(-1)
		}

}

object Application {
	def main(args: Array[String]): Unit = {
		val app = new Application()
		app.run()
	}
}
