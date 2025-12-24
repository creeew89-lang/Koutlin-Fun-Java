package dya6.kt

import java.io.File

fun main(){
    println("Student Grades")
    loadFromFile()
    menu()
}
val students = mutableMapOf<String, MutableList<Int>>()

fun menu()
{
    while (true) {
        println(
            "     1) Add student\n" +
                    "            2) Remove student\n" +
                    "            3) Add grade\n" +
                    "            4) Show averages\n" +
                    "            5) Save & exit".trimIndent()
        )
        when (readLine()) {
            "1" -> addStudent()
            "2" -> removeStudent()
            "3" -> addGrade()
            "4" -> showAverages()
            "5" -> {
                saveToFile()
                return
            }

            else -> println("Pick 1-5")
        }
    }
}
fun addStudent(){
    print("Name: ")
    val  name = readLine().orEmpty().trim()
    if (name.isEmpty())return
    students[name] = mutableListOf()
    println("Added $name")
}
fun removeStudent() {
    print("Name to remove: ")
    val name = readLine().orEmpty().trim()
    if (students.remove(name) != null) println("Removed") else println("Not found")
}
fun addGrade(){
    print("Name: ")
    val name = readLine().orEmpty().trim()
    val list = students[name] ?: return println("Not found")
    print("Grade (1-5): ")
    val g = readLine()?.toIntOrNull()
    if (g == null || g !in 1..5) return println("Bad grade")
    list += g
    println("Added $g to $name")
}
fun showAverages() {
    if (students.isEmpty()) return println("No data")
    students.forEach { (n, g) ->
        val avg = g.average()
        println("$n → ${g.joinToString()}  avg=${"%.2f".format(avg)}")
    }
    val classAvg = students.values.flatten().average()
    println("Class avg=${"%.2f".format(classAvg)}")
}

fun saveToFile() {
    File("grades.txt").writeText(students.map { (n, g) -> "$n:${g.joinToString(",")}" }.joinToString("\n"))
    println("Saved")
}
fun loadFromFile(){
    val f = File("grades.txt")
    if (!f.exists()) return
    f.readLines().forEach { line ->
        val (name, grades) = line.split(":")
        students[name] = grades.split(",").mapNotNull { it.toIntOrNull() }.toMutableList()
    }
    println("Loaded ${students.size} students")
}
