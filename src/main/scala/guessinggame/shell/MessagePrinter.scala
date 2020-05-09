package guessinggame.shell

import guessinggame.core.domain.{GuessableNumber, Hint}
import guessinggame.core.presentation.Presentation

import java.io.PrintStream

/**
  * Developed by Bia.
  */
private[shell] class MessagePrinter(out: PrintStream = System.out) {
  def showIntro(): Unit = out.println(Presentation.introduction)

  def showErrorMessage(): Unit = out.println(Presentation.invalidNumber)

  def showSuccessMessage(targetNumber: GuessableNumber): Unit =
    out.println(Presentation.successMessage(targetNumber))

  def showGameOverMessage(): Unit = out.println(Presentation.gameOver)

  def showHint(guess: GuessableNumber, hint: Hint): Unit =
    out.println(Presentation.hint(guess, hint))

  def showProblemWithSetup(): Unit = out.println(Presentation.problemWithSetup)
}
