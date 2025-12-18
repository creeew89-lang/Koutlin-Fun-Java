fun canVote(age: Int): Boolean = age >= 18

fun main() {
    val birthYear = 2001
    val currentYear = 2025
    val myAge = currentYear - birthYear
    println("Age check: ${canVote(myAge)}")
}