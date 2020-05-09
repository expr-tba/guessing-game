package guessinggame.core.presentation

import guessinggame.core.domain.{GuessableNumber, Hint}

/**
  * Developed by Bia.
  */
object Presentation {
  val introduction: String =
    """
		| #####                                                  #####
		|#     # #    # ######  ####   ####  # #    #  ####     #     #   ##   #    # ######
		|#       #    # #      #      #      # ##   # #    #    #        #  #  ##  ## #
		|#  #### #    # #####   ####   ####  # # #  # #         #  #### #    # # ## # #####
		|#     # #    # #           #      # # #  # # #  ###    #     # ###### #    # #
		|#     # #    # #      #    # #    # # #   ## #    #    #     # #    # #    # #
		| #####   ####  ######  ####   ####  # #    #  ####      #####  #    # #    # ######
		|
		|# I just came up with a number between 1 and 100 (inclusive)!
		|# If you guess it, you'll win!
		|# If you're wrong, I will tell if it's higher or lower.
		|# You have five attempts. Good Luck!
		|""".stripMargin('#')

  val prompt: String = "\n> "

  val invalidNumber: String =
    "\nPlease enter a number between 1 and 100."

  def successMessage(number: GuessableNumber): String =
    s"\nThat's damn right! ${number.value} it is!"

  def hint(guess: GuessableNumber, hint: Hint): String = {
    val comparator = hint match {
      case Hint.TooHigh => "lower"
      case Hint.TooLow  => "higher"
    }
    s"""
			 |Your guess ${guess.value}, is wrong.
			 |The target number is $comparator than that!
			 |""".stripMargin
  }

  val gameOver: String =
    """
			| #####                          #######
			|#     #   ##   #    # ######    #     # #    # ###### #####
			|#        #  #  ##  ## #         #     # #    # #      #    #
			|#  #### #    # # ## # #####     #     # #    # #####  #    #
			|#     # ###### #    # #         #     # #    # #      #####
			|#     # #    # #    # #         #     #  #  #  #      #   #
			| #####  #    # #    # ######    #######   ##   ###### #    #
			|""".stripMargin

  val problemWithSetup: String =
    "There was a problem setting up the game! The game will exit..."
}
