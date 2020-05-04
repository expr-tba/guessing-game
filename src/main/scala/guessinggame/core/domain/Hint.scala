package guessinggame.core.domain

/**
 * Developed by Bia.
 */

sealed trait Hint

object Hint {

	case object TooHigh extends Hint
	case object TooLow extends Hint

}


