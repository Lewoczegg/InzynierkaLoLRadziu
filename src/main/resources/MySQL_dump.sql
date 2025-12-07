-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: codeseries
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
                               `assignment_id` bigint NOT NULL AUTO_INCREMENT,
                               `created_at` datetime(6) NOT NULL,
                               `description` text NOT NULL,
                               `title` varchar(255) NOT NULL,
                               `updated_at` datetime(6) NOT NULL,
                               `lesson_id` bigint NOT NULL,
                               `title_lvl` enum('ADVANCED','BEGINNER','INTERMEDIATE') NOT NULL,
                               PRIMARY KEY (`assignment_id`),
                               KEY `FKj6l7dcv3gjrabhqjqsdolg78l` (`lesson_id`),
                               CONSTRAINT `FKj6l7dcv3gjrabhqjqsdolg78l` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (1,'2024-10-20 12:47:16.148000','Write a function that declares and initializes an integer variable. The function should take no inputs and return the initialized integer value. The integer can be any arbitrary value you choose. Ensure that you follow proper syntax for declaring and initializing variables in your chosen programming language.\n\nExamples:\n\n- If the value chosen is 42, the function should return 42.\n- If the value chosen is -7, the function should return -7.\n\nThis task tests your understanding of variable declaration, initialization, and returning values from a function.','1. Define an Integer Variable','2024-11-19 12:24:15.778000',1,'BEGINNER'),(2,'2024-11-02 17:57:05.441000','Write a function that takes two integer inputs and returns their sum. Ensure that you handle proper addition and return the correct result. The integers can be any arbitrary values provided as inputs.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are -2 and 7, the function should return 5.\n- If the inputs are 0 and 0, the function should return 0.\n\nThis task tests your understanding of basic arithmetic operations and how to handle function inputs and outputs.','2. Add Two Integer Numberse','2024-11-19 12:24:09.753000',1,'BEGINNER'),(3,'2024-11-19 12:28:25.500000','Write a function that declares a variable, assigns it a value, and then displays (prints) its value. Ensure that the variable is properly declared, initialized, and printed in your chosen programming language.\n\nExamples:\n\n- If the variable is assigned the value 42, the function should print: 42.\n- If the variable is assigned the value -7, the function should print: -7.\n- If the variable is assigned the value 0, the function should print: 0.\n\nThis task tests your understanding of variable declaration, initialization, and basic output operations.','3. Display the Value of a Variable','2024-11-19 12:28:25.500000',1,'BEGINNER'),(4,'2024-11-19 12:53:48.390000','Write a function that takes two numbers as inputs and uses the addition operator (`+`) to calculate their sum. The function should return the result of the addition. The numbers can be integers or floating-point values.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are 2.5 and 4.3, the function should return 6.8.\n- If the inputs are -7 and 2, the function should return -5.\n\nThis task tests your understanding of using the addition operator to combine numerical values.','4. Use the Addition Operator with Two Numbers','2024-11-19 12:53:48.390000',2,'BEGINNER'),(5,'2024-11-19 12:54:10.724000','Write a function that takes two numbers as inputs and uses the multiplication operator (`*`) to calculate their product. The function should return the result of the multiplication. The numbers can be integers or floating-point values.\n\nExamples:\n\n- If the inputs are 4 and 5, the function should return 20.\n- If the inputs are 2.5 and 3, the function should return 7.5.\n- If the inputs are -3 and -2, the function should return 6.\n\nThis task tests your understanding of using the multiplication operator to calculate products.','5. Use the Multiplication Operator','2024-11-19 12:54:10.724000',2,'BEGINNER'),(6,'2024-11-19 12:54:29.663000','Write a function that takes two numbers as inputs, performs division using the division operator (`/`), and rounds the result to the nearest integer. The function should handle any valid division scenario (excluding division by zero) and return the rounded result.\n\nExamples:\n\n- If the inputs are 10 and 3, the function should return 3 (10 ÷ 3 = 3.333, rounded to 3).\n- If the inputs are 7 and 2, the function should return 4 (7 ÷ 2 = 3.5, rounded to 4).\n- If the inputs are -8 and 3, the function should return -3 (-8 ÷ 3 = -2.666, rounded to -3).\n\nThis task tests your understanding of division, handling numerical operations, and rounding results.','6. Perform Division and Round the Result','2024-11-19 12:54:29.663000',2,'BEGINNER'),(7,'2024-11-19 12:59:27.731000','Write a function that takes an integer as input and uses an `if` statement to check whether the number is even. If the number is even, the function should return `true` or a specific message indicating that the number is even. Ensure the logic for checking even numbers is correct (e.g., using the modulus operator `%`).\n\nExamples:\n\n- If the input is 4, the function should return `true` or \'4 is even\'.\n- If the input is 7, the function should not return anything or may return `false`.\n- If the input is 0, the function should return `true` or \'0 is even\'.\n\nThis task tests your understanding of conditional statements and the concept of even numbers.','7. Write an If Statement to Check if a Number is Even','2024-11-19 12:59:27.731000',3,'BEGINNER'),(8,'2024-11-19 13:00:01.952000','Write a function that takes an integer as input and uses an `if` statement to check whether the number is positive. If the number is positive, return a message indicating it is positive. Otherwise, use an `else` statement to return a message indicating the number is not positive. Ensure the function covers both cases.\n\nExamples:\n\n- If the input is 5, the function should return \'5 is positive\'.\n- If the input is -3, the function should return \'-3 is not positive\'.\n- If the input is 0, the function should return \'0 is not positive\'.\n\nThis task tests your understanding of conditional statements, including the use of `else` to handle alternative cases.','8. Use an Else Statement','2024-11-19 13:00:01.952000',3,'BEGINNER'),(9,'2024-11-19 13:00:19.924000','Write a function that takes two numbers as inputs and uses an `if-else` statement to compare them. If the first number is greater than the second, return a message indicating this. Otherwise, return a message indicating that the first number is not greater than the second.\n\nExamples:\n\n- If the inputs are 10 and 5, the function should return \'10 is greater than 5\'.\n- If the inputs are 3 and 7, the function should return \'3 is not greater than 7\'.\n- If the inputs are 4 and 4, the function should return \'4 is not greater than 4\'.\n\nThis task tests your understanding of comparison operators and conditional statements.','9. Write an If-Else Statement to Compare Two Numbers','2024-11-19 13:00:19.924000',3,'BEGINNER'),(10,'2024-11-19 13:07:52.059000','Write a function that defines and initializes an array (or list) of integers. The array should be filled with specific values provided in the example below. Ensure that the array contains exactly the ten predefined integers given.\n\nExamples:\n\n- The array should be [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]. The function should return [10, 20, 30, 40, 50, 60, 70, 80, 90, 100].\n- Alternatively, the array can be [5, 15, 25, 35, 45, 55, 65, 75, 85, 95]. The function should return [5, 15, 25, 35, 45, 55, 65, 75, 85, 95].\n\nThis task tests your understanding of arrays (or lists), their initialization, and the ability to use specific predefined values.','10. Define an Array of Specific Integers','2024-11-22 16:27:35.068000',4,'INTERMEDIATE'),(11,'2024-11-19 13:08:08.958000','Write a function that calculates the sum of all elements in a given array (or list) of integers. Assign the result to a variable called `sum`. Make sure the function takes the array as input and returns the calculated sum.\n\nExamples:\n\n- If the array is [1, 2, 3, 4, 5], the function should return 15 because 1 + 2 + 3 + 4 + 5 = 15.\n- If the array is [10, 20, 30, 40, 50], the function should return 150 because 10 + 20 + 30 + 40 + 50 = 150.\n\nThis task tests your ability to iterate through an array, accumulate values, and assign the final result to a variable.','11. Calculate the Sum of All Elements in an Array','2024-11-22 16:26:40.527000',4,'INTERMEDIATE'),(12,'2024-11-19 13:08:24.756000','Write a function that takes an array (or list) of integers as input and returns the largest element in the array. Ensure that the function iterates through the array to find the maximum value.\n\nExamples:\n\n- If the input is [1, 2, 3, 4, 5], the function should return 5.\n- If the input is [-10, -3, -7, -2], the function should return -2.\n- If the input is [0, 0, 0], the function should return 0.\n\nThis task tests your understanding of iteration, comparison, and finding the maximum value in an array.','12. Find the Largest Element in an Array','2024-11-19 13:08:24.756000',4,'INTERMEDIATE'),(13,'2024-11-19 13:08:55.523000','Write a for loop that calculates the sum of all even numbers between 1 and 20 (inclusive). Assign the result to a variable called `sum`. Ensure that only even numbers are included in the calculation.\n\nExamples:\n\n- The sum should be 110, because 2 + 4 + 6 + 8 + 10 + 12 + 14 + 16 + 18 + 20 = 110.\n\nThis task tests your understanding of using a loop to iterate through a range of numbers, identifying even numbers, and accumulating their values into a single sum.','13. Use a For Loop to Calculate the Sum of Even Numbers','2024-11-22 16:37:27.351000',5,'INTERMEDIATE'),(14,'2024-11-19 13:09:10.789000','Write a function that uses a `while` loop to sum the numbers from 1 to a given integer `n`. The function should take `n` as input and return the sum of all numbers from 1 to `n`.\n\nExamples:\n\n- If the input is 5, the function should return 15 (1 + 2 + 3 + 4 + 5).\n- If the input is 10, the function should return 55.\n\nThis task tests your understanding of `while` loops, conditional logic, and basic arithmetic.','14. Write a While Loop to Sum Numbers','2024-11-19 13:09:10.789000',5,'INTERMEDIATE'),(15,'2024-11-19 13:09:25.225000','Write a while loop that generates random numbers between 1 and 50 until a number divisible by 7 is found. Use an appropriate random number generating function. Once a number divisible by 7 is found, use the `break` statement to exit the loop.\n\nExamples:\n\n- The loop should keep generating numbers like 34, 5, 13, 28 until it finds a number like 14 (which is divisible by 7). At this point, the loop should stop.\n\nThis task tests your understanding of while loops, generating random numbers, conditional checks for divisibility, and the use of the `break` statement to exit the loop when a condition is met.','15. Use a While Loop to Find a Number Divisible by 7','2024-11-22 16:37:48.125000',5,'INTERMEDIATE'),(16,'2024-11-19 13:09:54.371000','Write a function that takes two numbers as inputs and returns their sum. Ensure the function correctly calculates and returns the result.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are -2 and 10, the function should return 8.\n\nThis task tests your understanding of function creation, input handling, and returning results.','16. Write a Function to Return the Sum of Two Numbers','2024-11-19 13:09:54.371000',6,'INTERMEDIATE'),(17,'2024-11-19 13:10:17.887000','Write a function that takes a list of numbers as input and returns the largest number in the list. Ensure that the function iterates through the list to find the maximum value.\n\nExamples:\n\n- If the input is [1, 2, 3, 4, 5], the function should return 5.\n- If the input is [-10, -3, -7, -2], the function should return -2.\n- If the input is [0, 0, 0], the function should return 0.\n\nThis task tests your understanding of iteration, comparison, and finding the maximum value in a list.','17. Create a Function to Return the Largest Number in a List','2024-11-19 13:10:17.887000',6,'INTERMEDIATE'),(18,'2024-11-19 13:10:33.003000','Write a function that takes a non-negative integer `n` as input and calculates its factorial using recursion. The factorial of a number is defined as `n! = n * (n-1) * ... * 1`, with the base case `0! = 1`.\n\nExamples:\n\n- If the input is 5, the function should return 120 (5! = 5 * 4 * 3 * 2 * 1).\n- If the input is 0, the function should return 1 (0! = 1).\n- If the input is 3, the function should return 6 (3! = 3 * 2 * 1).\n\nThis task tests your understanding of recursion, base cases, and mathematical calculations.','18. Create a Function to Return the Largest Number in a List','2024-11-19 13:10:33.003000',6,'INTERMEDIATE'),(19,'2024-11-19 13:11:47.618000','Write a function that takes a positive integer `n` as input and calculates the sum of all numbers from 1 to `n` using recursion. The base case should handle when `n` is 0, returning 0, and the recursive case should sum `n` with the result of the function called on `n-1`.\n\nExamples:\n\n- If the input is 5, the function should return 15 (1 + 2 + 3 + 4 + 5).\n- If the input is 10, the function should return 55 (1 + 2 + ... + 10).\n- If the input is 1, the function should return 1.\n\nThis task tests your understanding of recursion, base cases, and arithmetic progression.','19. Write a Recursive Function to Calculate the Sum of Numbers from 1 to n','2024-11-19 13:11:47.618000',7,'ADVANCED'),(20,'2024-11-19 13:12:02.985000','Write a function that takes a non-negative integer `n` as input and returns the nth Fibonacci number using recursion. The Fibonacci sequence is defined as:\n  - F(0) = 0\n  - F(1) = 1\n  - F(n) = F(n-1) + F(n-2) for n > 1\n\nExamples:\n\n- If the input is 0, the function should return 0.\n- If the input is 1, the function should return 1.\n- If the input is 5, the function should return 5 (Fibonacci sequence: 0, 1, 1, 2, 3, 5).\n- If the input is 7, the function should return 13.\n\nThis task tests your understanding of recursion, mathematical sequences, and efficient computation.','20. Create a Function to Find the nth Fibonacci Number','2024-11-19 13:12:02.985000',7,'ADVANCED'),(21,'2024-11-19 13:12:14.568000','Write a function that solves the Tower of Hanoi problem for `n` disks. The function should take three inputs: the source rod, the destination rod, and the auxiliary rod. The goal is to move all disks from the source rod to the destination rod following these rules:\n\n1. Only one disk can be moved at a time.\n2. A disk can only be placed on top of a larger disk or on an empty rod.\n\nThe function should print each move in the format: \'Move disk from [source] to [destination]\'. Use recursion to break the problem into smaller subproblems.\n\nExamples:\n\n- For `n = 2`, the output should be:\n  Move disk from A to B\n  Move disk from A to C\n  Move disk from B to C\n\n- For `n = 3`, the output should be:\n  Move disk from A to C\n  Move disk from A to B\n  Move disk from C to B\n  Move disk from A to C\n  Move disk from B to A\n  Move disk from B to C\n  Move disk from A to C\n\nThis task tests your understanding of recursion, breaking down complex problems, and solving algorithmic challenges.','21. Solve the Tower of Hanoi Problem Using Recursion','2024-11-19 13:12:14.568000',7,'ADVANCED'),(22,'2024-11-19 13:13:01.662000','Write a function that implements the Bubble Sort algorithm to sort a list of numbers in ascending order. The function should repeatedly compare adjacent elements in the list and swap them if they are in the wrong order. This process should continue until the list is fully sorted.\n\nExamples:\n\n- If the input is [5, 3, 8, 6, 2], the function should return [2, 3, 5, 6, 8].\n- If the input is [1, 4, 3, 2], the function should return [1, 2, 3, 4].\n- If the input is [10, 20, 5, 15], the function should return [5, 10, 15, 20].\n\nThis task tests your understanding of sorting algorithms and iterative problem-solving.','22. Implement Bubble Sort','2024-11-19 13:13:01.662000',8,'ADVANCED'),(23,'2024-11-19 13:13:13.061000','Write a function that implements the Quick Sort algorithm to sort a list of numbers in ascending order. The function should use the divide-and-conquer strategy by selecting a pivot, partitioning the list into elements less than and greater than the pivot, and recursively sorting the partitions.\n\nExamples:\n\n- If the input is [5, 3, 8, 6, 2], the function should return [2, 3, 5, 6, 8].\n- If the input is [1, 4, 3, 2], the function should return [1, 2, 3, 4].\n- If the input is [10, 20, 5, 15], the function should return [5, 10, 15, 20].\n\nThis task tests your understanding of recursive algorithms and efficient sorting techniques.','23. Implement Quick Sort','2024-11-19 13:13:13.061000',8,'ADVANCED'),(24,'2024-11-19 13:13:38.544000','Write a function to implement Selection Sort for an array of integers. This algorithm sorts an array by repeatedly finding the smallest element from the unsorted portion of the array and placing it in the correct position.\n\nExamples:\n\n- For an array [64, 25, 12, 22, 11], after sorting, the array should be [11, 12, 22, 25, 64].\n- In the first pass, the algorithm selects the smallest value (11) and swaps it with the first element (64), resulting in [11, 25, 12, 22, 64].\n- The sorting continues until all elements are sorted.\n\nThis task tests your ability to implement a classic sorting algorithm, use nested loops, and correctly swap values in an array.','24. Selection Sort for an Array of Integers','2024-11-22 16:52:47.249000',8,'ADVANCED'),(25,'2024-11-19 13:14:29.165000','Write a class that implements a stack data structure. The class should support the following operations:\n\n1. `push(value)` - Adds a value to the top of the stack.\n2. `pop()` - Removes and returns the value at the top of the stack. If the stack is empty, it should handle the error appropriately.\n3. `peek()` - Returns the value at the top of the stack without removing it.\n4. `is_empty()` - Returns `true` if the stack is empty, otherwise `false`.\n\nExamples:\n\n- If the operations are `push(5)`, `push(10)`, `pop()`, the result of `pop()` should be `10`, and the stack will contain [5].\n- If the stack is empty and `pop()` is called, it should handle the situation gracefully.\n\nThis task tests your understanding of classes, object-oriented programming, and stack operations.','25. Create a Class Representing a Stack','2024-11-19 13:14:29.165000',9,'ADVANCED'),(26,'2024-11-19 13:14:42.699000','Write a class that implements a queue data structure. The class should support the following operations:\n\n1. `add(value)` - Adds a value to the end of the queue.\n2. `remove()` - Removes and returns the value at the front of the queue. If the queue is empty, it should handle the error appropriately.\n3. `peek()` - Returns the value at the front of the queue without removing it.\n4. `is_empty()` - Returns `true` if the queue is empty, otherwise `false`.\n\nExamples:\n\n- If the operations are `add(5)`, `add(10)`, `remove()`, the result of `remove()` should be `5`, and the queue will contain [10].\n- If the queue is empty and `remove()` is called, it should handle the situation gracefully.\n\nThis task tests your understanding of classes, object-oriented programming, and queue operations.','26. Implement a Queue with Enqueue and Dequeue Operations','2024-11-22 15:17:03.013000',9,'ADVANCED'),(27,'2024-11-19 13:14:57.725000','Write a class that represents a binary tree. The class should include methods for common operations:\n\n1. `insert(value)` - Inserts a value into the binary tree following binary search tree rules.\n2. `find(value)` - Searches for a value in the tree and returns `true` if found, otherwise `false`.\n3. `in_order_traversal()` - Returns a list of values in the tree in in-order sequence.\n4. `pre_order_traversal()` - Returns a list of values in pre-order sequence.\n5. `post_order_traversal()` - Returns a list of values in post-order sequence.\n\nExamples:\n\n- If the tree contains [5, 3, 7] and `in_order_traversal()` is called, it should return [3, 5, 7].\n- If the value `5` is inserted into the tree and then `find(5)` is called, it should return `true`.\n- If the tree is empty and `find(1)` is called, it should return `false`.\n\nThis task tests your understanding of binary trees, traversal methods, and object-oriented design.','27. Write a Class for Working with a Binary Tree','2024-11-19 13:14:57.725000',9,'ADVANCED');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
                            `comment_id` bigint NOT NULL AUTO_INCREMENT,
                            `content` varchar(255) NOT NULL,
                            `created_at` datetime(6) NOT NULL,
                            `rating` int DEFAULT NULL,
                            `lesson_id` bigint NOT NULL,
                            `user_id` bigint NOT NULL,
                            PRIMARY KEY (`comment_id`),
                            KEY `FK37jam8u2nwqw9enhv7nqn52e4` (`lesson_id`),
                            KEY `FKqi14bvepnwtjbbaxm7m4v44yg` (`user_id`),
                            CONSTRAINT `FK37jam8u2nwqw9enhv7nqn52e4` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`),
                            CONSTRAINT `FKqi14bvepnwtjbbaxm7m4v44yg` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
                           `course_id` bigint NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) NOT NULL,
                           `description` varchar(255) NOT NULL,
                           `title` varchar(255) NOT NULL,
                           `updated_at` datetime(6) NOT NULL,
                           `title_lvl` enum('ADVANCED','BEGINNER','INTERMEDIATE') NOT NULL,
                           PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'2024-10-20 12:36:44.834000','Basic lessons, perfect for beginners.','1. Basic Course','2024-10-20 12:36:44.834000','BEGINNER'),(2,'2024-10-31 14:49:18.314000','Intermediate lessons, challenge yourself.','2. Intermediate Course','2024-10-31 14:49:18.314000','INTERMEDIATE'),(3,'2024-11-19 12:37:41.672000','Advanced lessons, only for experienced programmers.','3. Advanced Course','2024-11-19 12:37:41.672000','ADVANCED');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_task`
--

DROP TABLE IF EXISTS `daily_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_task` (
                              `task_id` bigint NOT NULL AUTO_INCREMENT,
                              `description` varchar(255) DEFAULT NULL,
                              `title` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_task`
--

LOCK TABLES `daily_task` WRITE;
/*!40000 ALTER TABLE `daily_task` DISABLE KEYS */;
INSERT INTO `daily_task` VALUES (1,'Create a variable storing text and display its length.','Text Length'),(2,'Join two strings (concatenation) and display the result.','Concatenate Strings'),(3,'Find how many times a given phrase appears in a string.','Count Phrase'),(4,'Create a list of names and convert all names to uppercase.','Uppercase Names'),(5,'Define a dictionary storing exam scores for three students and calculate the average.','Average Score'),(6,'Add a new key-value pair to a dictionary and display all elements.','Add to Dictionary');
/*!40000 ALTER TABLE `daily_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_task_assignment`
--

DROP TABLE IF EXISTS `daily_task_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_task_assignment` (
                                         `id` bigint NOT NULL AUTO_INCREMENT,
                                         `assignment_date` date DEFAULT NULL,
                                         `daily_task_task_id` bigint DEFAULT NULL,
                                         `user_user_id` bigint DEFAULT NULL,
                                         PRIMARY KEY (`id`),
                                         KEY `FKr52lbee974r0bv8bkhme7fc7l` (`daily_task_task_id`),
                                         KEY `FKhx2mejauhm451twb01mrfhn86` (`user_user_id`),
                                         CONSTRAINT `FKhx2mejauhm451twb01mrfhn86` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
                                         CONSTRAINT `FKr52lbee974r0bv8bkhme7fc7l` FOREIGN KEY (`daily_task_task_id`) REFERENCES `daily_task` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_task_assignment`
--

LOCK TABLES `daily_task_assignment` WRITE;
/*!40000 ALTER TABLE `daily_task_assignment` DISABLE KEYS */;
INSERT INTO `daily_task_assignment` VALUES (1,'2024-11-19',4,2),(7,'2024-11-19',1,19),(8,'2024-11-22',4,2),(9,'2024-11-22',5,22),(10,'2024-11-22',5,14),(11,'2024-11-22',2,15),(12,'2024-11-22',5,23),(13,'2024-11-22',2,24),(14,'2024-11-26',1,14),(15,'2024-11-27',4,14),(16,'2024-12-03',4,2),(17,'2025-01-05',1,2),(18,'2025-01-05',6,14),(19,'2025-01-06',1,2),(20,'2025-01-11',2,14);
/*!40000 ALTER TABLE `daily_task_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_task_result`
--

DROP TABLE IF EXISTS `daily_task_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_task_result` (
                                     `daily_task_result_id` bigint NOT NULL AUTO_INCREMENT,
                                     `completed_at` datetime(6) DEFAULT NULL,
                                     `points` bigint DEFAULT NULL,
                                     `task_id` bigint NOT NULL,
                                     `user_id` bigint NOT NULL,
                                     PRIMARY KEY (`daily_task_result_id`),
                                     KEY `FK4xrquk555pkx8hcuvr8c37lpe` (`task_id`),
                                     KEY `FK1g8fo7r96dd02amdyftptdewe` (`user_id`),
                                     CONSTRAINT `FK1g8fo7r96dd02amdyftptdewe` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                                     CONSTRAINT `FK4xrquk555pkx8hcuvr8c37lpe` FOREIGN KEY (`task_id`) REFERENCES `daily_task` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_task_result`
--

LOCK TABLES `daily_task_result` WRITE;
/*!40000 ALTER TABLE `daily_task_result` DISABLE KEYS */;
INSERT INTO `daily_task_result` VALUES (32,'2024-11-22 20:16:47.054679',60,5,14);
/*!40000 ALTER TABLE `daily_task_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
                           `lesson_id` bigint NOT NULL AUTO_INCREMENT,
                           `content` varchar(255) NOT NULL,
                           `created_at` datetime(6) NOT NULL,
                           `title` varchar(255) NOT NULL,
                           `updated_at` datetime(6) NOT NULL,
                           `course_id` bigint NOT NULL,
                           `lesson_number` int NOT NULL,
                           `required_level` int NOT NULL,
                           PRIMARY KEY (`lesson_id`),
                           KEY `FK17ucc7gjfjddsyi0gvstkqeat` (`course_id`),
                           CONSTRAINT `FK17ucc7gjfjddsyi0gvstkqeat` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (1,'Sum, reversion and much more','2024-10-20 12:39:04.579000','Lesson 1','2024-10-20 12:39:04.579000',1,1,1),(2,'Arithmetic operators','2024-11-19 12:34:28.307000','Lesson 2','2024-11-19 12:34:28.307000',1,2,2),(3,'Basic Conditional Statements','2024-11-19 12:35:03.982000','Lesson 3','2024-11-19 12:35:03.982000',1,3,3),(4,'Arrays','2024-11-19 13:03:41.994000','Lesson 4','2024-11-19 13:03:41.994000',2,4,4),(5,'Loops','2024-11-19 13:03:51.662000','Lesson 5','2024-11-19 13:03:51.662000',2,5,5),(6,'Functions','2024-11-19 13:04:03.526000','Lesson 6','2024-11-19 13:04:03.526000',2,6,6),(7,'Recursion','2024-11-19 13:04:18.725000','Lesson 7','2024-11-19 13:04:18.725000',3,7,7),(8,'Sorting Algorithms','2024-11-19 13:04:28.093000','Lesson 8','2024-11-19 13:04:28.093000',3,8,8),(9,'Data Structures','2024-11-19 13:04:48.507000','Lesson 9','2024-11-19 13:04:48.507000',3,9,9);
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `progress`
--

DROP TABLE IF EXISTS `progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progress` (
                            `progress_id` bigint NOT NULL AUTO_INCREMENT,
                            `completed_at` datetime(6) DEFAULT NULL,
                            `course_id` bigint NOT NULL,
                            `lesson_id` bigint NOT NULL,
                            `user_id` bigint NOT NULL,
                            PRIMARY KEY (`progress_id`),
                            KEY `FK1j6c6ve6bm0t822a2kqdnt6ce` (`course_id`),
                            KEY `FKl0j4exxbrn12496b20t6o1kb3` (`lesson_id`),
                            KEY `FK1tqjxovekjvbd5oa1ohreqqk4` (`user_id`),
                            CONSTRAINT `FK1j6c6ve6bm0t822a2kqdnt6ce` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
                            CONSTRAINT `FK1tqjxovekjvbd5oa1ohreqqk4` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                            CONSTRAINT `FKl0j4exxbrn12496b20t6o1kb3` FOREIGN KEY (`lesson_id`) REFERENCES `lessons` (`lesson_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `progress`
--

LOCK TABLES `progress` WRITE;
/*!40000 ALTER TABLE `progress` DISABLE KEYS */;
INSERT INTO `progress` VALUES (6,'2024-11-02 22:00:23.168000',2,1,2),(7,'2024-11-11 14:59:32.817000',2,1,17),(8,'2024-11-11 15:02:05.021000',2,1,18),(9,'2024-11-11 15:03:07.016000',2,1,19),(10,'2024-11-22 15:32:24.377000',1,1,14),(11,'2024-11-22 15:36:52.154000',1,3,14),(12,'2024-11-22 15:52:10.305000',2,6,14),(13,'2024-11-22 15:53:37.282000',3,7,14),(14,'2024-11-22 15:58:28.168000',3,9,14),(15,'2024-11-22 16:18:07.150000',1,1,22),(16,'2024-11-22 16:18:38.406000',1,2,22),(17,'2024-11-22 16:19:09.464000',1,3,22),(18,'2024-11-22 16:34:20.616000',2,4,22),(19,'2024-11-22 16:54:12.168000',3,8,14),(20,'2024-11-22 17:58:11.195000',1,2,14);
/*!40000 ALTER TABLE `progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
                            `question_id` bigint NOT NULL AUTO_INCREMENT,
                            `content` varchar(255) NOT NULL,
                            `correct_answer` varchar(255) NOT NULL,
                            `quiz_id` bigint NOT NULL,
                            PRIMARY KEY (`question_id`),
                            KEY `FKb0yh0c1qaxfwlcnwo9dms2txf` (`quiz_id`),
                            CONSTRAINT `FKb0yh0c1qaxfwlcnwo9dms2txf` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'What is JVM?','Java Virtual Machine',2),(2,'What is JDK?','Java Development Kit',2),(3,'What is a list in Python?','A mutable sequence of elements',3),(4,'How do you start a comment in Python?','#',3),(5,'What is the output of len([1, 2, 3])?','3',3),(6,'Which keyword is used to define a function in Python?','def',3),(7,'What data type is returned by the input() function?','string',3),(8,'What does \'encapsulation\' refer to?','Wrapping data and methods into a single unit',4),(9,'Which concept allows one class to inherit the properties of another?','Inheritance',4),(10,'What is polymorphism?','Ability of different objects to be treated as instances of the same class',4),(11,'Which keyword is used to create an object in C++?','new',4),(12,'What is the purpose of a constructor?','To initialize an object',4),(13,'Which command is used to list files in a directory?','ls',5),(14,'How do you navigate to the parent directory?','cd ..',5),(15,'Which command is used to create a new directory?','mkdir',5),(16,'How do you view the contents of a file?','cat',5),(17,'Which command is used to remove a file?','rm',5),(18,'Which SQL command is used to retrieve data?','SELECT',6),(19,'Which keyword is used to sort the result set in ascending order?','ORDER BY',6),(20,'Which SQL statement is used to delete data from a table?','DELETE',6),(21,'Which function is used to count the number of rows in a SQL query?','COUNT()',6),(22,'Which clause is used to filter records in a SQL query?','WHERE',6);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_options`
--

DROP TABLE IF EXISTS `question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_options` (
                                    `question_question_id` bigint NOT NULL,
                                    `options` varchar(255) DEFAULT NULL,
                                    KEY `FKpe5m5pktlr3jq7n2u98inq4o1` (`question_question_id`),
                                    CONSTRAINT `FKpe5m5pktlr3jq7n2u98inq4o1` FOREIGN KEY (`question_question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_options`
--

LOCK TABLES `question_options` WRITE;
/*!40000 ALTER TABLE `question_options` DISABLE KEYS */;
INSERT INTO `question_options` VALUES (1,'Java Virtual Machine'),(1,'Java Virtual Module'),(1,'Java Version Model'),(2,'Java Development Kit'),(2,'Java Deployment Key'),(2,'Java Documentation Kit'),(3,'A mutable sequence of elements'),(3,'An immutable data type'),(3,'A function in Python'),(4,'#'),(4,'//'),(4,'/*'),(5,'3'),(5,'2'),(5,'4'),(6,'def'),(6,'func'),(6,'lambda'),(7,'string'),(7,'integer'),(7,'boolean'),(8,'Wrapping data and methods into a single unit'),(8,'Inheritance of properties'),(8,'Overloading operators'),(9,'Inheritance'),(9,'Polymorphism'),(9,'Abstraction'),(10,'Ability of different objects to be treated as instances of the same class'),(10,'Code hiding'),(10,'Data encapsulation'),(11,'new'),(11,'create'),(11,'build'),(12,'To initialize an object'),(12,'To destroy an object'),(12,'To copy an object'),(13,'ls'),(13,'list'),(13,'show'),(14,'cd ..'),(14,'cd ~'),(14,'cd /'),(15,'mkdir'),(15,'createdir'),(15,'newdir'),(16,'cat'),(16,'view'),(16,'open'),(17,'rm'),(17,'delete'),(17,'erase'),(18,'SELECT'),(18,'INSERT'),(18,'UPDATE'),(19,'ORDER BY'),(19,'GROUP BY'),(19,'ASC'),(20,'DELETE'),(20,'REMOVE'),(20,'DROP'),(21,'COUNT()'),(21,'SUM()'),(21,'TOTAL()'),(22,'WHERE'),(22,'HAVING'),(22,'FILTER');
/*!40000 ALTER TABLE `question_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
                        `quiz_id` bigint NOT NULL AUTO_INCREMENT,
                        `title` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (2,'Java Basics'),(3,'Python Basics'),(4,'OOP Concepts'),(5,'Linux Command Line'),(6,'SQL Essentials');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_result`
--

DROP TABLE IF EXISTS `quiz_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_result` (
                               `quiz_result_id` bigint NOT NULL AUTO_INCREMENT,
                               `completed_at` datetime(6) DEFAULT NULL,
                               `points` bigint DEFAULT NULL,
                               `quiz_id` bigint NOT NULL,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`quiz_result_id`),
                               KEY `FKd49de4d3rwgtndq0n51w1isbx` (`quiz_id`),
                               KEY `FK724trb6ambqx6fd1sqpp9rfxg` (`user_id`),
                               CONSTRAINT `FK724trb6ambqx6fd1sqpp9rfxg` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                               CONSTRAINT `FKd49de4d3rwgtndq0n51w1isbx` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_result`
--

LOCK TABLES `quiz_result` WRITE;
/*!40000 ALTER TABLE `quiz_result` DISABLE KEYS */;
INSERT INTO `quiz_result` VALUES (6,'2024-11-11 22:52:01.372196',20,2,2),(7,'2024-11-13 09:24:36.820688',20,2,19),(8,'2024-11-14 19:42:34.085236',30,4,2),(9,'2024-11-15 19:51:58.087998',50,3,2),(10,'2024-11-19 10:06:45.442770',40,5,2),(11,'2024-11-19 10:57:22.534953',30,5,19),(12,'2024-11-19 13:37:25.420573',30,4,14),(13,'2024-11-19 13:53:34.886213',30,2,20),(14,'2024-11-19 13:54:00.600267',30,2,21),(15,'2024-11-22 10:41:47.773035',40,6,2),(16,'2024-11-22 22:31:47.434804',30,2,14),(17,'2024-11-26 19:08:33.865018',30,6,14),(20,'2025-01-05 20:44:06.374940',20,3,14),(21,'2025-01-11 15:17:15.451084',15,5,14);
/*!40000 ALTER TABLE `quiz_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` bigint NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) NOT NULL,
                        PRIMARY KEY (`role_id`),
                        UNIQUE KEY `UKiubw515ff0ugtm28p8g3myt0h` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solutions`
--

DROP TABLE IF EXISTS `solutions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solutions` (
                             `solution_id` bigint NOT NULL AUTO_INCREMENT,
                             `content` text NOT NULL,
                             `created_at` datetime(6) NOT NULL,
                             `assignment_id` bigint NOT NULL,
                             `language` varchar(255) NOT NULL,
                             PRIMARY KEY (`solution_id`),
                             KEY `FK7ivp28jovxj29dq4p3ebjqru1` (`assignment_id`),
                             CONSTRAINT `FK7ivp28jovxj29dq4p3ebjqru1` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`assignment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solutions`
--

LOCK TABLES `solutions` WRITE;
/*!40000 ALTER TABLE `solutions` DISABLE KEYS */;
INSERT INTO `solutions` VALUES (3,'public class Result {\n    public static int defineInteger(int value) {\n        return value;\n    }\n}','2024-11-22 21:46:33.276000',1,'java'),(4,'public class Result {\n    public static int addTwoIntegers(int a, int b) {\n        return a + b;\n    }\n}','2024-11-22 21:47:23.592000',2,'java'),(5,'public class Result {\n    public static String displayValue(int value) {\n        return String.valueOf(value);\n    }\n}','2024-11-22 21:47:43.856000',3,'java'),(6,'public class Result {\n    public static double addTwoNumbers(double a, double b) {\n        return a + b;\n    }\n}','2024-11-22 21:48:10.555000',4,'java'),(7,'public class Result {\n    public static double multiplyTwoNumbers(double a, double b) {\n        return a * b;\n    }\n}','2024-11-22 21:49:32.601000',5,'java'),(8,'public class Result {\n    public static int divideAndRound(double a, double b) {\n        return (int) Math.round(a / b);\n    }\n}','2024-11-22 21:49:39.477000',6,'java'),(9,'public class Result {\n    public static String checkIfEven(int number) {\n        if (number % 2 == 0) {\n            return number + \" is even\";\n        } else {\n            return number + \" is not even\";\n        }\n    }\n}','2024-11-22 21:49:45.375000',7,'java'),(10,'public class Result {\n    public static String checkIfPositive(int number) {\n        if (number > 0) {\n            return number + \" is positive\";\n        } else {\n            return number + \" is not positive\";\n        }\n    }\n}','2024-11-22 21:49:53.777000',8,'java'),(11,'public class Result {\n    public static String compareTwoNumbers(int a, int b) {\n        if (a > b) {\n            return a + \" is greater than \" + b;\n        } else {\n            return a + \" is not greater than \" + b;\n        }\n    }\n}','2024-11-22 21:50:01.178000',9,'java'),(12,'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> defineArray() {\n        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);\n    }\n}','2024-11-22 21:50:08.827000',10,'java'),(13,'import java.util.List;\n\npublic class Result {\n    public static int calculateSum(List<Integer> numbers) {\n        int sum = 0;\n        for (int number : numbers) {\n            sum += number;\n        }\n        return sum;\n    }\n}','2024-11-22 21:51:18.183000',11,'java'),(14,'public class Result {\n    public static Integer findLargestElement(Integer[] array) {\n        if (array == null || array.length == 0) {\n            throw new IllegalArgumentException(\"Array cannot be null or empty\");\n        }\n        int max = array[0];\n        for (int i = 1; i < array.length; i++) {\n            if (array[i] > max) {\n                max = array[i];\n            }\n        }\n        return max;\n    }\n}','2024-11-22 21:52:34.950000',12,'java'),(15,'public class Result {\n    public static int calculateSumOfEvens() {\n        int sum = 0;\n        for (int i = 1; i <= 20; i++) {\n            if (i % 2 == 0) {\n                sum += i;\n            }\n        }\n        return sum;\n    }\n}','2024-11-22 21:52:46.846000',13,'java'),(16,'public class Result {\n    public static int sumNumbersUpToN(int n) {\n        int sum = 0;\n        int i = 1;\n        while (i <= n) {\n            sum += i;\n            i++;\n        }\n        return sum;\n    }\n}','2024-11-22 21:53:51.322000',14,'java'),(17,'public class Result {\n    public static int findNumberDivisibleBy7(int[] numbers) {\n        int index = 0;\n        int number;\n        while (index < numbers.length) {\n            number = numbers[index];\n            if (number % 7 == 0) {\n                return number;\n            }\n            index++;\n        }\n        return -1;\n    }\n}','2024-11-22 21:54:11.541000',15,'java'),(18,'public class Result {\n    public static int sumTwoNumbers(int a, int b) {\n        return a + b;\n    }\n}','2024-11-22 21:54:19.428000',16,'java'),(19,'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}','2024-11-22 21:54:25.129000',17,'java'),(20,'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}','2024-11-22 21:54:50.744000',18,'java'),(21,'public class Result {\n    public static int sumToN(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        return n + sumToN(n - 1);\n    }\n}','2024-11-22 21:54:56.849000',19,'java'),(22,'public class Result {\n    public static int fibonacci(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        if (n == 1) {\n            return 1;\n        }\n        return fibonacci(n - 1) + fibonacci(n - 2);\n    }\n}','2024-11-22 21:55:09.720000',20,'java'),(23,'import java.io.*;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static void towerOfHanoi(int n, char source, char destination, char auxiliary, Consumer<String> logger) {\n        if (n == 0) {\n            return;\n        }\n        towerOfHanoi(n - 1, source, auxiliary, destination, logger);\n        logger.accept(\"Move disk from \" + source + \" to \" + destination);\n        towerOfHanoi(n - 1, auxiliary, destination, source, logger);\n    }\n}','2024-11-22 21:56:55.710000',21,'java'),(24,'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\n\npublic class Result {\n    public static List<Integer> bubbleSort(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        int n = sortedNumbers.size();\n        boolean swapped;\n        do {\n            swapped = false;\n            for (int i = 0; i < n - 1; i++) {\n                if (sortedNumbers.get(i) > sortedNumbers.get(i + 1)) {\n                    int temp = sortedNumbers.get(i);\n                    sortedNumbers.set(i, sortedNumbers.get(i + 1));\n                    sortedNumbers.set(i + 1, temp);\n                    swapped = true;\n                }\n            }\n            n--;\n        } while (swapped);\n        return sortedNumbers;\n    }\n}','2024-11-22 21:57:05.148000',22,'java'),(25,'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static List<Integer> quickSort(List<Integer> numbers) {\n        if (numbers == null || numbers.size() <= 1) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        quickSortHelper(sortedNumbers, 0, sortedNumbers.size() - 1);\n        return sortedNumbers;\n    }\n\n    private static void quickSortHelper(List<Integer> list, int low, int high) {\n        if (low < high) {\n            int pivotIndex = partition(list, low, high);\n            quickSortHelper(list, low, pivotIndex - 1);\n            quickSortHelper(list, pivotIndex + 1, high);\n        }\n    }\n\n    private static int partition(List<Integer> list, int low, int high) {\n        int pivot = list.get(high);\n        int i = low - 1;\n        for (int j = low; j < high; j++) {\n            if (list.get(j) < pivot) {\n                i++;\n                Collections.swap(list, i, j);\n            }\n        }\n        Collections.swap(list, i + 1, high);\n        return i + 1;\n    }\n}','2024-11-22 21:57:11.657000',23,'java'),(26,'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> selectionSort(List<Integer> numbers) {\n        Integer[] array = numbers.toArray(new Integer[0]);\n        int n = array.length;\n        for (int i = 0; i < n - 1; i++) {\n            int minIndex = i;\n            for (int j = i + 1; j < n; j++) {\n                if (array[j] < array[minIndex]) {\n                    minIndex = j;\n                }\n            }\n            int temp = array[minIndex];\n            array[minIndex] = array[i];\n            array[i] = temp;\n        }\n        return Arrays.asList(array);\n    }\n}','2024-11-22 21:57:19.110000',24,'java'),(27,'import java.io.*;\nimport java.util.*;\n\nclass Stack {\n    private List<Integer> stack;\n\n    public Stack() {\n        stack = new ArrayList<>();\n    }\n\n    public void push(int value) {\n        stack.add(value);\n    }\n\n    public int pop() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.remove(stack.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.get(stack.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return stack.isEmpty();\n    }\n}','2024-11-22 21:57:25.968000',25,'java'),(28,'import java.io.*;\nimport java.util.*;\n\nclass Queue {\n    private LinkedList<Integer> queue;\n\n    public Queue() {\n        queue = new LinkedList<>();\n    }\n\n    public void add(int value) {\n        queue.addLast(value);\n    }\n\n    public int remove() {\n        if (isEmpty()) {\n            throw new NoSuchElementException(\"Queue is empty\");\n        }\n        return queue.removeFirst();\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new NoSuchElementException(\"Queue is empty\");\n        }\n        return queue.getFirst();\n    }\n\n    public boolean isEmpty() {\n        return queue.isEmpty();\n    }\n}','2024-11-22 21:57:31.351000',26,'java'),(29,'import java.util.*;\n\nclass BinaryTree {\n    static class Node {\n        int value;\n        Node left, right;\n\n        Node(int value) {\n            this.value = value;\n            left = right = null;\n        }\n    }\n\n    private Node root;\n\n    public BinaryTree() {\n        root = null;\n    }\n\n    public void insert(int value) {\n        root = insertRec(root, value);\n    }\n\n    private Node insertRec(Node root, int value) {\n        if (root == null) {\n            root = new Node(value);\n            return root;\n        }\n        if (value < root.value) {\n            root.left = insertRec(root.left, value);\n        } else if (value > root.value) {\n            root.right = insertRec(root.right, value);\n        }\n        return root;\n    }\n\n    public boolean find(int value) {\n        return findRec(root, value);\n    }\n\n    private boolean findRec(Node root, int value) {\n        if (root == null) {\n            return false;\n        }\n        if (root.value == value) {\n            return true;\n        }\n        if (value < root.value) {\n            return findRec(root.left, value);\n        }\n        return findRec(root.right, value);\n    }\n\n    public List<Integer> inOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        inOrderRec(root, result);\n        return result;\n    }\n\n    private void inOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            inOrderRec(root.left, result);\n            result.add(root.value);\n            inOrderRec(root.right, result);\n        }\n    }\n\n    public List<Integer> preOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        preOrderRec(root, result);\n        return result;\n    }\n\n    private void preOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            result.add(root.value);\n            preOrderRec(root.left, result);\n            preOrderRec(root.right, result);\n        }\n    }\n\n    public List<Integer> postOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        postOrderRec(root, result);\n        return result;\n    }\n\n    private void postOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            postOrderRec(root.left, result);\n            postOrderRec(root.right, result);\n            result.add(root.value);\n        }\n    }\n}','2024-11-22 21:57:39.293000',27,'java'),(30,'def result(input_value):\n    return input_value\n','2024-11-22 21:58:56.228000',1,'python'),(31,'def result(a, b):\n    return a + b\n','2024-11-22 22:01:39.174000',2,'python'),(32,'def result(value):\n    return str(value)\n','2024-11-22 22:01:52.954000',3,'python'),(33,'def result(a, b):\n    return a + b\n','2024-11-22 22:02:43.127000',4,'python'),(34,'def result(a, b):\n    return a * b\n','2024-11-22 22:02:52.910000',5,'python'),(35,'def result(a, b):\n    return round(a / b)\n','2024-11-22 22:03:05.150000',6,'python'),(36,'def result(n):\n    if n % 2 == 0:\n        return f\"{n} is even\"\n    else:\n        return f\"{n} is not even\"\n','2024-11-22 22:04:26.174000',7,'python'),(37,'def result(n):\n    if n > 0:\n        return f\"{n} is positive\"\n    else:\n        return f\"{n} is not positive\"\n','2024-11-22 22:04:35.404000',8,'python'),(38,'def result(a, b):\n    if a > b:\n        return f\"{a} is greater than {b}\"\n    else:\n        return f\"{a} is not greater than {b}\"\n','2024-11-22 22:04:50.358000',9,'python'),(39,'def result():\n    return [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]\n','2024-11-22 22:04:56.346000',10,'python'),(40,'def result(numbers):\n    return sum(numbers)\n','2024-11-22 22:06:59.082000',11,'python'),(41,'def result(numbers):\n    return max(numbers)\n','2024-11-22 22:07:08.059000',12,'python'),(42,'def result():\n    return sum([i for i in range(1, 21) if i % 2 == 0])\n','2024-11-22 22:07:18.609000',13,'python'),(43,'def result(n):\n    return sum(range(1, n + 1)) if n > 0 else 0\n','2024-11-22 22:07:25.782000',14,'python'),(44,'def result(numbers):\n    for number in numbers:\n        if number % 7 == 0:\n            return number\n    return None\n','2024-11-22 22:07:34.158000',15,'python'),(45,'def result(a, b):\n    return a + b\n','2024-11-22 22:07:43.677000',16,'python'),(46,'def result(numbers):\n    return max(numbers)\n','2024-11-22 22:07:57.454000',17,'python'),(47,'def result(n):\n    if n == 0:\n        return 1\n    factorial = 1\n    for i in range(1, n + 1):\n        factorial *= i\n    return factorial\n','2024-11-22 22:08:03.605000',18,'python'),(48,'def result(n):\n    return sum(range(1, n + 1))\n','2024-11-22 22:08:18.436000',19,'python'),(49,'def result(n):\n    if n == 0:\n        return 0\n    elif n == 1:\n        return 1\n    a, b = 0, 1\n    for _ in range(2, n + 1):\n        a, b = b, a + b\n    return b\n','2024-11-22 22:08:24.092000',20,'python'),(50,'def tower_of_hanoi(n, source, target, auxiliary, logger):\n    if n == 1:\n        logger(f\"Move disk from {source} to {target}\")\n        return\n    tower_of_hanoi(n - 1, source, auxiliary, target, logger)\n    logger(f\"Move disk from {source} to {target}\")\n    tower_of_hanoi(n - 1, auxiliary, target, source, logger)\n\ndef result(n):\n    output = []\n    logger = lambda x: output.append(x)\n    tower_of_hanoi(n, \'A\', \'C\', \'B\', logger)\n    return \'\'.join(output)\n','2024-11-22 22:09:28.958000',21,'python'),(51,'def result(arr):\n    n = len(arr)\n    for i in range(n):\n        for j in range(0, n - i - 1):\n            if arr[j] > arr[j + 1]:\n                arr[j], arr[j + 1] = arr[j + 1], arr[j]\n    return arr\n','2024-11-22 22:10:41.111000',22,'python'),(52,'def result(arr):\n    if len(arr) <= 1:\n        return arr\n    pivot = arr[len(arr) // 2]\n    left = [x for x in arr if x < pivot]\n    middle = [x for x in arr if x == pivot]\n    right = [x for x in arr if x > pivot]\n    return result(left) + middle + result(right)\n','2024-11-22 22:10:55.528000',23,'python'),(53,'def result(arr):\n    n = len(arr)\n    for i in range(n):\n        min_idx = i\n        for j in range(i + 1, n):\n            if arr[j] < arr[min_idx]:\n                min_idx = j\n        arr[i], arr[min_idx] = arr[min_idx], arr[i]\n    return arr\n','2024-11-22 22:11:04.718000',24,'python'),(54,'class Stack:\n    def __init__(self):\n        self.items = []\n\n    def push(self, value):\n        self.items.append(value)\n\n    def pop(self):\n        return self.items.pop() if not self.is_empty() else \"N/A\"\n\n    def peek(self):\n        return self.items[-1] if not self.is_empty() else \"N/A\"\n\n    def is_empty(self):\n        return len(self.items) == 0\n','2024-11-22 22:11:41.236000',25,'python'),(55,'class Queue:\n    def __init__(self):\n        self.items = []\n\n    def enqueue(self, value):\n        self.items.append(value)\n\n    def dequeue(self):\n        return self.items.pop(0) if not self.is_empty() else \"N/A\"\n\n    def peek(self):\n        return self.items[0] if not self.is_empty() else \"N/A\"\n\n    def is_empty(self):\n        return len(self.items) == 0\n','2024-11-22 22:12:16.763000',26,'python'),(56,'class BinaryTree:\n    class Node:\n        def __init__(self, value):\n            self.value = value\n            self.left = None\n            self.right = None\n\n    def __init__(self):\n        self.root = None\n\n    def insert(self, value):\n        if self.root is None:\n            self.root = self.Node(value)\n        else:\n            self._insert_recursive(self.root, value)\n\n    def _insert_recursive(self, node, value):\n        if value < node.value:\n            if node.left is None:\n                node.left = self.Node(value)\n            else:\n                self._insert_recursive(node.left, value)\n        else:\n            if node.right is None:\n                node.right = self.Node(value)\n            else:\n                self._insert_recursive(node.right, value)\n\n    def find(self, value):\n        return self._find_recursive(self.root, value)\n\n    def _find_recursive(self, node, value):\n        if node is None:\n            return False\n        if node.value == value:\n            return True\n        elif value < node.value:\n            return self._find_recursive(node.left, value)\n        else:\n            return self._find_recursive(node.right, value)\n\n    def in_order_traversal(self):\n        result = []\n        self._in_order_recursive(self.root, result)\n        return result\n\n    def _in_order_recursive(self, node, result):\n        if node is not None:\n            self._in_order_recursive(node.left, result)\n            result.append(node.value)\n            self._in_order_recursive(node.right, result)\n\n    def pre_order_traversal(self):\n        result = []\n        self._pre_order_recursive(self.root, result)\n        return result\n\n    def _pre_order_recursive(self, node, result):\n        if node is not None:\n            result.append(node.value)\n            self._pre_order_recursive(node.left, result)\n            self._pre_order_recursive(node.right, result)\n\n    def post_order_traversal(self):\n        result = []\n        self._post_order_recursive(self.root, result)\n        return result\n\n    def _post_order_recursive(self, node, result):\n        if node is not None:\n            self._post_order_recursive(node.left, result)\n            self._post_order_recursive(node.right, result)\n            result.append(node.value)\n','2024-11-22 22:13:11.729000',27,'python');
/*!40000 ALTER TABLE `solutions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submissions` (
                               `submission_id` bigint NOT NULL AUTO_INCREMENT,
                               `content` text NOT NULL,
                               `grade` float DEFAULT NULL,
                               `graded_at` datetime(6) DEFAULT NULL,
                               `submitted_at` datetime(6) NOT NULL,
                               `assignment_id` bigint NOT NULL,
                               `user_id` bigint NOT NULL,
                               PRIMARY KEY (`submission_id`),
                               KEY `FKrirbb44savy2g7nws0hoxs949` (`assignment_id`),
                               KEY `FKqb10pqq7p90asugtf30yqxfk3` (`user_id`),
                               CONSTRAINT `FKqb10pqq7p90asugtf30yqxfk3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                               CONSTRAINT `FKrirbb44savy2g7nws0hoxs949` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`assignment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submissions`
--

LOCK TABLES `submissions` WRITE;
/*!40000 ALTER TABLE `submissions` DISABLE KEYS */;
INSERT INTO `submissions` VALUES (1,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-02 14:22:20.020000','2024-11-02 14:22:20.020000',1,2),(2,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-02 14:24:10.530000','2024-11-02 14:24:10.530000',1,2),(3,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-02 14:24:30.547000','2024-11-02 14:24:30.547000',1,2),(4,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-02 15:00:10.421000','2024-11-02 15:00:10.421000',1,2),(5,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-02 15:01:45.746000','2024-11-02 15:01:45.746000',1,2),(6,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 15:04:35.732000','2024-11-02 15:04:35.732000',1,2),(7,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        return result;\n    }\n}',100,'2024-11-02 15:44:11.674000','2024-11-02 15:44:11.674000',1,2),(8,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 15:46:50.000000','2024-11-02 15:46:50.000000',1,2),(9,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 15:56:18.737000','2024-11-02 15:56:18.737000',1,2),(10,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 16:06:48.252000','2024-11-02 16:06:48.252000',1,2),(11,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 16:12:10.849000','2024-11-02 16:12:10.849000',1,2),(12,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 16:15:03.515000','2024-11-02 16:15:03.515000',1,14),(13,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 17:29:32.214000','2024-11-02 17:29:32.214000',1,2),(14,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 17:58:49.607000','2024-11-02 17:58:49.607000',2,2),(15,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 18:39:47.031000','2024-11-02 18:39:47.031000',1,2),(16,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 18:39:56.039000','2024-11-02 18:39:56.039000',1,2),(17,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 19:16:46.034000','2024-11-02 19:16:46.034000',1,2),(18,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 19:18:46.145000','2024-11-02 19:18:46.145000',1,2),(19,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:20:40.888000','2024-11-02 19:20:40.888000',2,2),(20,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:34:26.485000','2024-11-02 19:34:26.485000',2,2),(21,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:35:43.200000','2024-11-02 19:35:43.200000',2,14),(22,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 19:36:09.699000','2024-11-02 19:36:09.699000',1,14),(23,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 19:36:26.278000','2024-11-02 19:36:26.278000',1,14),(24,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:36:53.756000','2024-11-02 19:36:53.756000',2,14),(25,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:56:08.237000','2024-11-02 19:56:08.237000',2,2),(26,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 19:56:35.522000','2024-11-02 19:56:35.522000',1,2),(27,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:56:49.437000','2024-11-02 19:56:49.437000',2,2),(28,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 19:57:13.514000','2024-11-02 19:57:13.514000',2,14),(29,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-02 19:59:41.892000','2024-11-02 19:59:41.892000',1,15),(31,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 20:02:43.851000','2024-11-02 20:02:43.851000',2,15),(32,'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}',100,'2024-11-02 22:00:23.080000','2024-11-02 22:00:23.080000',2,2),(33,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n                System.out.println(\"XD\");\n\n        return result;\n    }\n}',100,'2024-11-03 14:54:42.620000','2024-11-03 14:54:42.620000',1,2),(34,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-03 15:02:01.403000','2024-11-03 15:02:01.403000',1,2),(35,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-11 11:38:02.381000','2024-11-11 11:38:02.381000',1,2),(36,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-11 14:57:03.732000','2024-11-11 14:57:03.732000',1,17),(37,'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n}\n}',100,'2024-11-11 14:59:32.789000','2024-11-11 14:59:32.789000',2,17),(38,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-11 15:01:39.735000','2024-11-11 15:01:39.735000',1,18),(39,'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n    }\n}',100,'2024-11-11 15:02:04.997000','2024-11-11 15:02:04.997000',2,18),(40,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-11 15:02:44.867000','2024-11-11 15:02:44.867000',1,19),(41,'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n    }\n}',100,'2024-11-11 15:03:06.982000','2024-11-11 15:03:06.982000',2,19),(42,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-11 15:24:35.762000','2024-11-11 15:24:35.762000',1,2),(43,'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println(\"ameno \"); return result; } }',100,'2024-11-11 15:26:07.769000','2024-11-11 15:26:07.769000',1,2),(44,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-11 22:54:45.964000','2024-11-11 22:54:45.964000',1,2),(45,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        return result;\n    }\n}',100,'2024-11-13 09:28:30.960000','2024-11-13 09:28:30.960000',1,19),(46,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 22:16:12.567000','2024-11-14 22:16:12.567000',1,2),(47,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 22:16:34.072000','2024-11-14 22:16:34.072000',1,2),(48,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 22:16:46.197000','2024-11-14 22:16:46.197000',1,2),(49,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 22:17:29.136000','2024-11-14 22:17:29.136000',1,2),(50,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 22:21:19.742000','2024-11-14 22:21:19.742000',1,2),(51,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 22:37:44.697000','2024-11-14 22:37:44.697000',1,2),(52,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-14 23:04:26.145000','2024-11-14 23:04:26.145000',1,2),(53,'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println(\"ameno\");\n        return result;\n    }\n}',100,'2024-11-19 11:49:26.262000','2024-11-19 11:49:26.262000',1,20),(54,'class Result {\n    public static int defineInteger(int value) {\n        return value; // Przykładowa implementacja, która po prostu zwraca wartość przekazaną przez Task1Main\n    }\n}\n',100,'2024-11-22 11:08:29.252000','2024-11-22 11:08:29.252000',1,2),(55,'public class Result {\n    public static int defineInteger(int value) {\n        return value;\n    }\n}',100,'2024-11-22 15:26:49.518000','2024-11-22 15:26:49.518000',1,14),(56,'public class Result {\n    public static int addTwoIntegers(int a, int b) {\n        return a + b;\n    }\n}',100,'2024-11-22 15:27:05.482000','2024-11-22 15:27:05.482000',2,14),(57,'public class Result {\n    public static String displayValue(int value) {\n        return String.valueOf(value);\n    }\n}',100,'2024-11-22 15:32:24.356000','2024-11-22 15:32:24.356000',3,14),(58,'public class Result {\n    public static double multiplyTwoNumbers(double a, double b) {\n        return a * b;\n    }\n}',100,'2024-11-22 15:33:12.826000','2024-11-22 15:33:12.826000',5,14),(59,'public class Result {\n    public static int divideAndRound(double a, double b) {\n        return (int) Math.round(a / b);\n    }\n}',100,'2024-11-22 15:33:50.707000','2024-11-22 15:33:50.707000',6,14),(60,'public class Result {\n    public static String checkIfEven(int number) {\n        if (number % 2 == 0) {\n            return number + \" is even\";\n        } else {\n            return number + \" is not even\";\n        }\n    }\n}',100,'2024-11-22 15:34:51.154000','2024-11-22 15:34:51.154000',7,14),(61,'public class Result {\n    public static String checkIfPositive(int number) {\n        if (number > 0) {\n            return number + \" is positive\";\n        } else {\n            return number + \" is not positive\";\n        }\n    }\n}',100,'2024-11-22 15:36:13.524000','2024-11-22 15:36:13.524000',8,14),(62,'public class Result {\n     public static String compareTwoNumbers(int a, int b) {\n        if (a > b) {\n            return a + \" is greater than \" + b;\n        } else {\n            return a + \" is not greater than \" + b;\n        }\n    }\n}',100,'2024-11-22 15:36:52.137000','2024-11-22 15:36:52.137000',9,14),(63,'public class Result {\n    public static Integer findLargestElement(Integer[] array) {\n        if (array == null || array.length == 0) {\n            throw new IllegalArgumentException(\"Array cannot be null or empty\");\n        }\n        \n        int max = array[0];\n        for (int i = 1; i < array.length; i++) {\n            if (array[i] > max) {\n                max = array[i];\n            }\n        }\n        return max;\n    }\n}',100,'2024-11-22 15:45:21.022000','2024-11-22 15:45:21.022000',12,14),(64,'public class Result {\n    public static int sumNumbersUpToN(int n) {\n        int sum = 0;\n        int i = 1;\n        \n        while (i <= n) {\n            sum += i;\n            i++;\n        }\n        \n        return sum;\n    }\n}',100,'2024-11-22 15:47:02.588000','2024-11-22 15:47:02.588000',14,14),(65,'public class Result {\n    public static int sumTwoNumbers(int a, int b) {\n        return a + b;\n    }\n}',100,'2024-11-22 15:48:32.063000','2024-11-22 15:48:32.063000',16,14),(66,'import java.util.List;\n\npublic class Result {\n    // Metoda do znalezienia największego elementu w liście\n    public static Integer findLargestInList(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            throw new IllegalArgumentException(\"List cannot be null or empty\");\n        }\n\n        Integer max = numbers.get(0);\n        for (Integer number : numbers) {\n            if (number > max) {\n                max = number;\n            }\n        }\n        return max;\n    }\n}\n',100,'2024-11-22 15:51:42.484000','2024-11-22 15:51:42.484000',17,14),(67,'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}',100,'2024-11-22 15:52:10.282000','2024-11-22 15:52:10.282000',18,14),(68,'public class Result {\n    public static int sumToN(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        return n + sumToN(n - 1);\n    }\n}\n',100,'2024-11-22 15:52:34.850000','2024-11-22 15:52:34.850000',19,14),(69,'public class Result {\n    public static int fibonacci(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        if (n == 1) {\n            return 1;\n        }\n        return fibonacci(n - 1) + fibonacci(n - 2);\n    }\n}',100,'2024-11-22 15:53:02.798000','2024-11-22 15:53:02.798000',20,14),(70,'import java.io.*;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static void towerOfHanoi(int n, char source, char destination, char auxiliary, Consumer<String> logger) {\n        if (n == 0) {\n            return;\n        }\n        towerOfHanoi(n - 1, source, auxiliary, destination, logger);\n        logger.accept(\"Move disk from \" + source + \" to \" + destination);\n        towerOfHanoi(n - 1, auxiliary, destination, source, logger);\n    }\n}',100,'2024-11-22 15:53:37.263000','2024-11-22 15:53:37.263000',21,14),(71,'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\n\npublic class Result {\n    public static List<Integer> bubbleSort(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            return numbers;\n        }\n        \n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        int n = sortedNumbers.size();\n        boolean swapped;\n        do {\n            swapped = false;\n            for (int i = 0; i < n - 1; i++) {\n                if (sortedNumbers.get(i) > sortedNumbers.get(i + 1)) {\n                    int temp = sortedNumbers.get(i);\n                    sortedNumbers.set(i, sortedNumbers.get(i + 1));\n                    sortedNumbers.set(i + 1, temp);\n                    swapped = true;\n                }\n            }\n            n--;\n        } while (swapped);\n        \n        return sortedNumbers;\n    }\n}\n',100,'2024-11-22 15:54:05.804000','2024-11-22 15:54:05.804000',22,14),(72,'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static List<Integer> quickSort(List<Integer> numbers) {\n        if (numbers == null || numbers.size() <= 1) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        quickSortHelper(sortedNumbers, 0, sortedNumbers.size() - 1);\n        return sortedNumbers;\n    }\n\n    private static void quickSortHelper(List<Integer> list, int low, int high) {\n        if (low < high) {\n            int pivotIndex = partition(list, low, high);\n            quickSortHelper(list, low, pivotIndex - 1);\n            quickSortHelper(list, pivotIndex + 1, high);\n        }\n    }\n\n    private static int partition(List<Integer> list, int low, int high) {\n        int pivot = list.get(high);\n        int i = low - 1;\n        for (int j = low; j < high; j++) {\n            if (list.get(j) < pivot) {\n                i++;\n                Collections.swap(list, i, j);\n            }\n        }\n        Collections.swap(list, i + 1, high);\n        return i + 1;\n    }\n}',100,'2024-11-22 15:55:15.616000','2024-11-22 15:55:15.616000',23,14),(73,'import java.io.*;\nimport java.util.*;\n\nclass Stack {\n    private List<Integer> stack;\n\n    public Stack() {\n        stack = new ArrayList<>();\n    }\n\n\n    public void push(int value) {\n        stack.add(value);\n    }\n\n    public int pop() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.remove(stack.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.get(stack.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return stack.isEmpty();\n    }\n}',100,'2024-11-22 15:56:09.261000','2024-11-22 15:56:09.261000',25,14),(74,'import java.io.*; \n\nimport java.util.*; \n\n  class Queue { \n\n    private LinkedList<Integer> queue; \n\n    public Queue() { \n\n        queue = new LinkedList<>(); \n\n    } \n\n    public void add(int value) { \n\n        queue.addLast(value); \n\n    } \n\n    public int remove() { \n\n        if (isEmpty()) { \n\n            throw new NoSuchElementException(\"Queue is empty\"); \n\n        } \n\n        return queue.removeFirst(); \n\n    } \n\n    public int peek() { \n\n        if (isEmpty()) { \n\n            throw new NoSuchElementException(\"Queue is empty\"); \n\n        } \n\n        return queue.getFirst(); \n\n    } \n\n    public boolean isEmpty() { \n\n        return queue.isEmpty(); \n\n    } \n\n} ',100,'2024-11-22 15:57:44.515000','2024-11-22 15:57:44.515000',26,14),(75,'import java.util.*; \n\nclass BinaryTree { \n\n    static class Node { \n\n        int value; \n\n        Node left, right; \n\n  \n\n        Node(int value) { \n\n            this.value = value; \n\n            left = right = null; \n\n        } \n\n    } \n\n  \n\n    private Node root; \n\n\n    public BinaryTree() { \n\n        root = null; \n\n    } \n\n\n\n    public void insert(int value) { \n\n        root = insertRec(root, value); \n\n    } \n\n  \n\n    private Node insertRec(Node root, int value) { \n\n        if (root == null) { \n\n            root = new Node(value); \n\n            return root; \n\n        } \n\n        if (value < root.value) { \n\n            root.left = insertRec(root.left, value); \n\n        } else if (value > root.value) { \n\n            root.right = insertRec(root.right, value); \n\n        } \n\n        return root; \n\n    } \n\n\n    public boolean find(int value) { \n\n        return findRec(root, value); \n\n    } \n\n  \n\n    private boolean findRec(Node root, int value) { \n\n        if (root == null) { \n\n            return false; \n\n        } \n\n        if (root.value == value) { \n\n            return true; \n\n        } \n\n        if (value < root.value) { \n\n            return findRec(root.left, value); \n\n        } \n\n        return findRec(root.right, value); \n\n    } \n\n\n    public List<Integer> inOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        inOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void inOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            inOrderRec(root.left, result); \n\n            result.add(root.value); \n\n            inOrderRec(root.right, result); \n\n        } \n\n    } \n\n\n    public List<Integer> preOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        preOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void preOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            result.add(root.value); \n\n            preOrderRec(root.left, result); \n\n            preOrderRec(root.right, result); \n\n        } \n\n    }  \n\n    public List<Integer> postOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        postOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void postOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            postOrderRec(root.left, result); \n\n            postOrderRec(root.right, result); \n\n            result.add(root.value); \n\n        } \n\n    } \n\n} ',100,'2024-11-22 15:58:28.136000','2024-11-22 15:58:28.136000',27,14),(76,'public class Result { \n\n    public static int defineInteger(int value) { \n\n        return value; \n\n    } \n\n} ',100,'2024-11-22 16:16:34.558000','2024-11-22 16:16:34.558000',1,2),(77,'public class Result { \n\n    public static int addTwoIntegers(int a, int b) { \n\n        return a + b; \n\n    } \n\n} ',100,'2024-11-22 16:16:40.789000','2024-11-22 16:16:40.789000',2,2),(78,'public class Result { \n\n    public static String displayValue(int value) { \n\n        return String.valueOf(value); \n\n    } \n\n} \n',100,'2024-11-22 16:16:53.221000','2024-11-22 16:16:53.221000',3,2),(79,'public class Result { \n\n    public static int defineInteger(int value) { \n\n        return value; \n\n    } \n\n} ',100,'2024-11-22 16:17:42.024000','2024-11-22 16:17:42.024000',1,22),(80,'public class Result { \n\n    public static int addTwoIntegers(int a, int b) { \n\n        return a + b; \n\n    } \n\n} ',100,'2024-11-22 16:17:54.171000','2024-11-22 16:17:54.171000',2,22),(81,'public class Result { \n\n    public static String displayValue(int value) { \n\n        return String.valueOf(value); \n\n    } \n\n} ',100,'2024-11-22 16:18:07.131000','2024-11-22 16:18:07.131000',3,22),(82,'public class Result { \n\n    public static double addTwoNumbers(double a, double b) { \n\n        return a + b; \n\n    } \n\n} ',100,'2024-11-22 16:18:23.722000','2024-11-22 16:18:23.722000',4,22),(83,'public class Result { \n\n    public static double multiplyTwoNumbers(double a, double b) { \n\n        return a * b; \n\n    } \n\n} ',100,'2024-11-22 16:18:29.937000','2024-11-22 16:18:29.937000',5,22),(84,'public class Result { \n\n    public static int divideAndRound(double a, double b) { \n\n        return (int) Math.round(a / b); \n\n    } \n\n} ',100,'2024-11-22 16:18:38.386000','2024-11-22 16:18:38.386000',6,22),(85,'public class Result { \n\n    public static String checkIfEven(int number) { \n\n        if (number % 2 == 0) { \n\n            return number + \" is even\"; \n\n        } else { \n\n            return number + \" is not even\"; \n\n        } \n\n    } \n\n} ',100,'2024-11-22 16:18:57.282000','2024-11-22 16:18:57.282000',7,22),(86,'public class Result { \n\n    public static String checkIfPositive(int number) { \n\n        if (number > 0) { \n\n            return number + \" is positive\"; \n\n        } else { \n\n            return number + \" is not positive\"; \n\n        } \n\n    } \n\n} ',100,'2024-11-22 16:19:04.184000','2024-11-22 16:19:04.184000',8,22),(87,'public class Result { \n\n     public static String compareTwoNumbers(int a, int b) { \n\n        if (a > b) { \n\n            return a + \" is greater than \" + b; \n\n        } else { \n\n            return a + \" is not greater than \" + b; \n\n        } \n\n    } \n\n} ',100,'2024-11-22 16:19:09.448000','2024-11-22 16:19:09.448000',9,22),(88,'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> defineArray() {\n        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);\n    }\n}\n',100,'2024-11-22 16:32:26.899000','2024-11-22 16:32:26.899000',10,22),(89,'import java.util.List;\n\npublic class Result {\n    public static int calculateSum(List<Integer> numbers) {\n        int sum = 0;\n        for (int number : numbers) {\n            sum += number;\n        }\n        return sum;\n    }\n}\n',100,'2024-11-22 16:33:58.567000','2024-11-22 16:33:58.567000',11,22),(90,'public class Result { \n\n    public static Integer findLargestElement(Integer[] array) { \n\n        if (array == null || array.length == 0) { \n\n            throw new IllegalArgumentException(\"Array cannot be null or empty\"); \n\n        } \n\n         \n\n        int max = array[0]; \n\n        for (int i = 1; i < array.length; i++) { \n\n            if (array[i] > max) { \n\n                max = array[i]; \n\n            } \n\n        } \n\n        return max; \n\n    } \n\n} ',100,'2024-11-22 16:34:20.602000','2024-11-22 16:34:20.602000',12,22),(91,'public class Result {\n    public static int calculateSumOfEvens() {\n        int sum = 0;\n        for (int i = 1; i <= 20; i++) {\n            if (i % 2 == 0) {\n                sum += i;\n            }\n        }\n        return sum;\n    }\n}',100,'2024-11-22 16:40:54.370000','2024-11-22 16:40:54.370000',13,22),(92,'public class Result {\n    public static int findNumberDivisibleBy7(int[] numbers) {\n        int index = 0;\n        int number;\n        while (index < numbers.length) {\n            number = numbers[index];\n            if (number % 7 == 0) {\n                return number;\n            }\n            index++;\n        }\n        return -1;\n    }\n}',100,'2024-11-22 16:45:26.950000','2024-11-22 16:45:26.950000',15,22),(93,'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> selectionSort(List<Integer> numbers) {\n        Integer[] array = numbers.toArray(new Integer[0]);\n        int n = array.length;\n\n        for (int i = 0; i < n - 1; i++) {\n            int minIndex = i;\n            for (int j = i + 1; j < n; j++) {\n                if (array[j] < array[minIndex]) {\n                    minIndex = j;\n                }\n            }\n\n            int temp = array[minIndex];\n            array[minIndex] = array[i];\n            array[i] = temp;\n        }\n\n        return Arrays.asList(array);\n    }\n}\n',100,'2024-11-22 16:54:12.141000','2024-11-22 16:54:12.141000',24,14),(94,'def result(a, b):\n    # Funkcja przyjmuje dwie liczby całkowite i zwraca ich sumę\n    return a + b',100,'2024-11-22 17:54:46.418000','2024-11-22 17:54:46.418000',2,2),(95,'def result(value):\n    return str(value)',100,'2024-11-22 17:55:43.624000','2024-11-22 17:55:43.624000',3,2),(96,'def result(a, b):\n    return a + b',100,'2024-11-22 17:56:02.672000','2024-11-22 17:56:02.672000',2,2),(97,'def result(input_value): \n\n    return input_value ',100,'2024-11-22 17:56:11.139000','2024-11-22 17:56:11.139000',1,2),(98,'def result(a, b):\n    return a + b',100,'2024-11-22 17:58:11.170000','2024-11-22 17:58:11.170000',4,14),(99,'def result(a, b):\n    return a * b',100,'2024-11-22 17:58:43.536000','2024-11-22 17:58:43.536000',5,14),(100,'def result(a, b):\n    return round(a / b)\n',100,'2024-11-22 18:01:23.050000','2024-11-22 18:01:23.050000',6,14),(101,'def result(input_value): \n    return input_value',100,'2024-11-26 00:28:50.656000','2024-11-26 00:28:50.656000',1,2),(102,'def result(input_value):\r\n    return input_value;',100,'2025-01-05 21:34:31.021000','2025-01-05 21:34:31.021000',1,14);
/*!40000 ALTER TABLE `submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `age` int DEFAULT NULL,
                        `created_at` datetime(6) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `first_name` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `phone_number` varchar(255) NOT NULL,
                        `surname` varchar(255) NOT NULL,
                        `updated_at` datetime(6) NOT NULL,
                        `username` varchar(255) NOT NULL,
                        `role_id` bigint NOT NULL,
                        `level` int NOT NULL,
                        `title` enum('ADVANCED','BEGINNER','INTERMEDIATE') NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
                        KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
                        CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,30,'2024-11-01 18:10:20.198000','jan.kowalski@example.com','Jan','$2a$10$2e86ywtYuHFNg9l3x7181uTntRx9Hiz/xXrRX8GhVXv5/0.YVOTzu','123456789','Kowalski','2024-11-01 18:10:20.198000','jan.kowalski',2,0,'ADVANCED'),(2,30,'2024-11-01 18:29:34.101000','asdi@example.com','rad','$2a$10$lelmojRsemYC2sUgFAKBqOKrGfUaYBG6vKo11n4AJ2OzBenpZnxFW','123456789','Kowalski','2024-11-01 18:29:34.101000','radek',2,1,'BEGINNER'),(14,20,'2024-11-02 16:12:32.404000','maciek@gmail.com','maciek','$2a$10$uudBxOQvFbQvdEDWwtCLHeRQ5EHPho4URmVv3BshZGkkclVUdoIgO','123789654','mackowski','2024-11-02 16:12:32.404000','maciek',1,16,'ADVANCED'),(15,12,'2024-11-02 19:57:38.230000','123@123.pl','marek','$2a$10$jpIdxAVIV/TVx4XeN.4hjOgetOgouJQvqGf7UknPFrx2cbXMjwtYm','997998998','marekowski','2024-11-02 19:57:38.230000','marek',2,0,'ADVANCED'),(16,123,'2024-11-02 20:35:42.035000','123@123123213123123312','123123123123','$2a$10$bKevhxfcNv.N2KvooqanMO3Y4co70kNcL5UxUHZV/1IGlbRu7A4Cy','123312132123123','123312123123132','2024-11-02 20:35:42.035000','123',2,1,'BEGINNER'),(17,123,'2024-11-11 14:56:46.295000','123@gmail.com','123','$2a$10$o25N5rourtptNxpYnEHXV.AkC.8TiS4KrL3AhPeuZrxjSEXzeHiZ6','123','123','2024-11-11 14:56:46.295000','123radek',2,1,'BEGINNER'),(18,12,'2024-11-11 15:01:25.372000','marcinek@gmail.com','Marcin','$2a$10$sUA1ujdEQELiO/SnXSByhOu06bvR/eNW48SL/G9J/JMN1wqJh9Jyy','997998999','Marcinkowski','2024-11-11 15:01:25.372000','marcinek',2,1,'BEGINNER'),(19,12,'2024-11-11 15:02:34.002000','marcinek2@gmail.com','marcinek','$2a$10$PxOwDITLrHhuRhaalGipzeeRzMqabuQcNzbizgKIJ9gQmux.WFEPO','991223123','marcinkowski','2024-11-11 15:02:34.002000','marcinek2',2,2,'BEGINNER'),(20,18,'2024-11-19 11:48:35.514000','radek1@interia.pl','radek','$2a$10$LV9AQOyAWeueXWtZOn4YYeoNdMBm1Z4olkTwOShTcudqk/KyHBwu6','731890123','kowa','2024-11-19 11:48:35.514000','radek1',2,1,'BEGINNER'),(21,12,'2024-11-19 13:53:53.545000','user@user.pl','adas','$2a$10$H5pxe7XtiolBoGFZfENhZOmmVds7T86cUmiTaqeORZLxcg/lqkFya','123456789','asdasdasd','2024-11-19 13:53:53.545000','radek2',2,1,'BEGINNER'),(22,10,'2024-11-22 16:17:24.521000','test1costam@gmail.com','radek','$2a$10$0G7gGGshoL3XSvjPS.93geop9w9pcP1SnyXFvMWuC6N7QNlPJIyoS','123123123','kowalczuk','2024-11-22 16:17:24.521000','test1',2,5,'INTERMEDIATE'),(23,122,'2024-11-22 17:04:35.933000','asd@asd','asd','$2a$10$RTzAaeRhRm7AdMDtfbakeOBOXw7yCvsufzOXtCh1V6DGkLoojINIG','123123231231','asd','2024-11-22 17:04:35.933000','123123123123123123123123123123',2,1,'BEGINNER'),(24,12,'2024-11-22 17:04:53.075000','qwe@wqwe','qwe','$2a$10$M2vcOfyyxye3NCDEeLYERu8vWJGVT.OwpyDZBR9GzorU3Bj84s2ym','qweq','qwe','2024-11-22 17:04:53.075000','qwerty',2,1,'BEGINNER'),(25,123,'2025-01-05 20:29:56.474000','123@123123123123.pl','123312','$2a$10$F51E5EUnxY6hocCx/zSerekp4DwU/38ffMPxh4cjpJTXt3uIeH17a','123213','123123123','2025-01-05 20:29:56.474000','123123123231312123312312123',2,1,'BEGINNER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_metadata`
--

DROP TABLE IF EXISTS `video_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_metadata` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `file_name` varchar(255) DEFAULT NULL,
                                  `file_path` varchar(255) DEFAULT NULL,
                                  `file_size` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_metadata`
--

LOCK TABLES `video_metadata` WRITE;
/*!40000 ALTER TABLE `video_metadata` DISABLE KEYS */;
INSERT INTO `video_metadata` VALUES (1,'123.mp4','C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',17684012),(2,'123.mp4','C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',17684012),(3,'123.mp4','C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',17684012),(4,'123.mp4','C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',17684012),(5,'123.mp4','/app/src/videos/123.mp4',17684012);
/*!40000 ALTER TABLE `video_metadata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-11 16:51:13
