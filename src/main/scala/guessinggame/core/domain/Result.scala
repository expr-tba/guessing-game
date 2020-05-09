package guessinggame.core.domain

/**
  * Developed by Bia.
  */
sealed trait Result

object Result {
  final case class Wrong(nextState: Game, hint: Hint) extends Result

  case object Success  extends Result
  case object GameOver extends Result
}
