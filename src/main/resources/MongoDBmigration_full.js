// MongoDB Full Data Migration Script
// Generated from MySQL dump - contains ALL data
// Run with: mongosh -u root -p 1234 --authenticationDatabase admin codeseries MongoDBmigration_full.js

db = db.getSiblingDB('codeseries');

print('=== Starting Full MongoDB Migration ===');

// Drop existing collections
db.users.drop();
db.courses.drop();
db.quizzes.drop();
db.daily_tasks.drop();
db.video_metadata.drop();

// ==========================================
// COLLECTION: daily_tasks
// ==========================================
print('Inserting daily tasks...');
const task1 = ObjectId();
const task2 = ObjectId();
const task3 = ObjectId();
const task4 = ObjectId();
const task5 = ObjectId();
const task6 = ObjectId();

db.daily_tasks.insertMany([
  { _id: task1, taskIdLegacy: NumberLong(1), title: 'Text Length', description: 'Create a variable storing text and display its length.' },
  { _id: task2, taskIdLegacy: NumberLong(2), title: 'Concatenate Strings', description: 'Join two strings (concatenation) and display the result.' },
  { _id: task3, taskIdLegacy: NumberLong(3), title: 'Count Phrase', description: 'Find how many times a given phrase appears in a string.' },
  { _id: task4, taskIdLegacy: NumberLong(4), title: 'Uppercase Names', description: 'Create a list of names and convert all names to uppercase.' },
  { _id: task5, taskIdLegacy: NumberLong(5), title: 'Average Score', description: 'Define a dictionary storing exam scores for three students and calculate the average.' },
  { _id: task6, taskIdLegacy: NumberLong(6), title: 'Add to Dictionary', description: 'Add a new key-value pair to a dictionary and display all elements.' }
]);

// ==========================================
// COLLECTION: quizzes with all questions
// ==========================================
print('Inserting quizzes with questions...');
const javaQuizId = ObjectId();
const pythonQuizId = ObjectId();
const oopQuizId = ObjectId();
const linuxQuizId = ObjectId();
const sqlQuizId = ObjectId();

db.quizzes.insertMany([
  {
    _id: javaQuizId,
    quizIdLegacy: NumberLong(2),
    title: 'Java Basics',
    questions: [
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(1),
        content: 'What is JVM?',
        correctAnswer: 'Java Virtual Machine',
        options: ['Java Virtual Machine', 'Java Virtual Module', 'Java Version Model']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(2),
        content: 'What is JDK?',
        correctAnswer: 'Java Development Kit',
        options: ['Java Development Kit', 'Java Deployment Key', 'Java Documentation Kit']
      }
    ]
  },
  {
    _id: pythonQuizId,
    quizIdLegacy: NumberLong(3),
    title: 'Python Basics',
    questions: [
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(3),
        content: 'What is a list in Python?',
        correctAnswer: 'A mutable sequence of elements',
        options: ['A mutable sequence of elements', 'An immutable data type', 'A function in Python']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(4),
        content: 'How do you start a comment in Python?',
        correctAnswer: '#',
        options: ['#', '//', '/*']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(5),
        content: 'What is the output of len([1, 2, 3])?',
        correctAnswer: '3',
        options: ['3', '2', '4']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(6),
        content: 'Which keyword is used to define a function in Python?',
        correctAnswer: 'def',
        options: ['def', 'func', 'lambda']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(7),
        content: 'What data type is returned by the input() function?',
        correctAnswer: 'string',
        options: ['string', 'integer', 'boolean']
      }
    ]
  },
  {
    _id: oopQuizId,
    quizIdLegacy: NumberLong(4),
    title: 'OOP Concepts',
    questions: [
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(8),
        content: 'What does \'encapsulation\' refer to?',
        correctAnswer: 'Wrapping data and methods into a single unit',
        options: ['Wrapping data and methods into a single unit', 'Inheritance of properties', 'Overloading operators']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(9),
        content: 'Which concept allows one class to inherit the properties of another?',
        correctAnswer: 'Inheritance',
        options: ['Inheritance', 'Polymorphism', 'Abstraction']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(10),
        content: 'What is polymorphism?',
        correctAnswer: 'Ability of different objects to be treated as instances of the same class',
        options: ['Ability of different objects to be treated as instances of the same class', 'Code hiding', 'Data encapsulation']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(11),
        content: 'Which keyword is used to create an object in C++?',
        correctAnswer: 'new',
        options: ['new', 'create', 'build']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(12),
        content: 'What is the purpose of a constructor?',
        correctAnswer: 'To initialize an object',
        options: ['To initialize an object', 'To destroy an object', 'To copy an object']
      }
    ]
  },
  {
    _id: linuxQuizId,
    quizIdLegacy: NumberLong(5),
    title: 'Linux Command Line',
    questions: [
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(13),
        content: 'Which command is used to list files in a directory?',
        correctAnswer: 'ls',
        options: ['ls', 'list', 'show']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(14),
        content: 'How do you navigate to the parent directory?',
        correctAnswer: 'cd ..',
        options: ['cd ..', 'cd ~', 'cd /']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(15),
        content: 'Which command is used to create a new directory?',
        correctAnswer: 'mkdir',
        options: ['mkdir', 'createdir', 'newdir']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(16),
        content: 'How do you view the contents of a file?',
        correctAnswer: 'cat',
        options: ['cat', 'view', 'open']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(17),
        content: 'Which command is used to remove a file?',
        correctAnswer: 'rm',
        options: ['rm', 'delete', 'erase']
      }
    ]
  },
  {
    _id: sqlQuizId,
    quizIdLegacy: NumberLong(6),
    title: 'SQL Essentials',
    questions: [
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(18),
        content: 'Which SQL command is used to retrieve data?',
        correctAnswer: 'SELECT',
        options: ['SELECT', 'INSERT', 'UPDATE']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(19),
        content: 'Which keyword is used to sort the result set in ascending order?',
        correctAnswer: 'ORDER BY',
        options: ['ORDER BY', 'GROUP BY', 'ASC']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(20),
        content: 'Which SQL statement is used to delete data from a table?',
        correctAnswer: 'DELETE',
        options: ['DELETE', 'REMOVE', 'DROP']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(21),
        content: 'Which function is used to count the number of rows in a SQL query?',
        correctAnswer: 'COUNT()',
        options: ['COUNT()', 'SUM()', 'TOTAL()']
      },
      {
        questionId: ObjectId(),
        questionIdLegacy: NumberLong(22),
        content: 'Which clause is used to filter records in a SQL query?',
        correctAnswer: 'WHERE',
        options: ['WHERE', 'HAVING', 'FILTER']
      }
    ]
  }
]);

// ==========================================
// COLLECTION: courses with ALL lessons, assignments, solutions
// ==========================================
print('Inserting courses with complete nested data...');

const course1Id = ObjectId();
const course2Id = ObjectId();
const course3Id = ObjectId();

const lesson1Id = ObjectId();
const lesson2Id = ObjectId();
const lesson3Id = ObjectId();
const lesson4Id = ObjectId();
const lesson5Id = ObjectId();
const lesson6Id = ObjectId();
const lesson7Id = ObjectId();
const lesson8Id = ObjectId();
const lesson9Id = ObjectId();

db.courses.insertMany([
  {
    _id: course1Id,
    courseIdLegacy: NumberLong(1),
    title: '1. Basic Course',
    description: 'Basic lessons, perfect for beginners.',
    titleLevel: 'BEGINNER',
    createdAt: new Date('2024-10-20T12:36:44.834Z'),
    updatedAt: new Date('2024-10-20T12:36:44.834Z'),
    lessons: [
      {
        lessonId: lesson1Id,
        lessonIdLegacy: NumberLong(1),
        title: 'Lesson 1',
        content: 'Sum, reversion and much more',
        lessonNumber: 1,
        requiredLevel: 1,
        createdAt: new Date('2024-10-20T12:39:04.579Z'),
        updatedAt: new Date('2024-10-20T12:39:04.579Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(1),
            title: '1. Define an Integer Variable',
            description: 'Write a function that declares and initializes an integer variable. The function should take no inputs and return the initialized integer value. The integer can be any arbitrary value you choose. Ensure that you follow proper syntax for declaring and initializing variables in your chosen programming language.\n\nExamples:\n\n- If the value chosen is 42, the function should return 42.\n- If the value chosen is -7, the function should return -7.\n\nThis task tests your understanding of variable declaration, initialization, and returning values from a function.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-10-20T12:47:16.148Z'),
            updatedAt: new Date('2024-11-19T12:24:15.778Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(3),
                content: 'public class Result {\n    public static int defineInteger(int value) {\n        return value;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:46:33.276Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(30),
                content: 'def result(input_value):\n    return input_value\n',
                language: 'python',
                createdAt: new Date('2024-11-22T21:58:56.228Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(1), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T14:22:20.020Z'), submittedAt: new Date('2024-11-02T14:22:20.020Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(2), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T14:24:10.530Z'), submittedAt: new Date('2024-11-02T14:24:10.530Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(3), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T14:24:30.547Z'), submittedAt: new Date('2024-11-02T14:24:30.547Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(4), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T15:00:10.421Z'), submittedAt: new Date('2024-11-02T15:00:10.421Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(5), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T15:01:45.746Z'), submittedAt: new Date('2024-11-02T15:01:45.746Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(6), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:04:35.732Z'), submittedAt: new Date('2024-11-02T15:04:35.732Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(7), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:44:11.674Z'), submittedAt: new Date('2024-11-02T15:44:11.674Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(8), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:46:50.000Z'), submittedAt: new Date('2024-11-02T15:46:50.000Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(9), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:56:18.737Z'), submittedAt: new Date('2024-11-02T15:56:18.737Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(10), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T16:06:48.252Z'), submittedAt: new Date('2024-11-02T16:06:48.252Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(11), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T16:12:10.849Z'), submittedAt: new Date('2024-11-02T16:12:10.849Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(12), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T16:15:03.515Z'), submittedAt: new Date('2024-11-02T16:15:03.515Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(13), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T17:29:32.214Z'), submittedAt: new Date('2024-11-02T17:29:32.214Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(15), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T18:39:47.031Z'), submittedAt: new Date('2024-11-02T18:39:47.031Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(16), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T18:39:56.039Z'), submittedAt: new Date('2024-11-02T18:39:56.039Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(17), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:16:46.034Z'), submittedAt: new Date('2024-11-02T19:16:46.034Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(18), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:18:46.145Z'), submittedAt: new Date('2024-11-02T19:18:46.145Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(22), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:36:09.699Z'), submittedAt: new Date('2024-11-02T19:36:09.699Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(23), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:36:26.278Z'), submittedAt: new Date('2024-11-02T19:36:26.278Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(26), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:56:35.522Z'), submittedAt: new Date('2024-11-02T19:56:35.522Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(29), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:59:41.892Z'), submittedAt: new Date('2024-11-02T19:59:41.892Z'), userIdLegacy: NumberLong(15) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(33), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n                System.out.println("XD");\n\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-03T14:54:42.620Z'), submittedAt: new Date('2024-11-03T14:54:42.620Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(34), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-03T15:02:01.403Z'), submittedAt: new Date('2024-11-03T15:02:01.403Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(35), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T11:38:02.381Z'), submittedAt: new Date('2024-11-11T11:38:02.381Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(36), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T14:57:03.732Z'), submittedAt: new Date('2024-11-11T14:57:03.732Z'), userIdLegacy: NumberLong(17) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(38), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:01:39.735Z'), submittedAt: new Date('2024-11-11T15:01:39.735Z'), userIdLegacy: NumberLong(18) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(40), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:02:44.867Z'), submittedAt: new Date('2024-11-11T15:02:44.867Z'), userIdLegacy: NumberLong(19) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(42), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-11T15:24:35.762Z'), submittedAt: new Date('2024-11-11T15:24:35.762Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(43), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-11T15:26:07.769Z'), submittedAt: new Date('2024-11-11T15:26:07.769Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(44), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T22:54:45.964Z'), submittedAt: new Date('2024-11-11T22:54:45.964Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(45), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-13T09:28:30.960Z'), submittedAt: new Date('2024-11-13T09:28:30.960Z'), userIdLegacy: NumberLong(19) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(46), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:16:12.567Z'), submittedAt: new Date('2024-11-14T22:16:12.567Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(47), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:16:34.072Z'), submittedAt: new Date('2024-11-14T22:16:34.072Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(48), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:16:46.197Z'), submittedAt: new Date('2024-11-14T22:16:46.197Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(49), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:17:29.136Z'), submittedAt: new Date('2024-11-14T22:17:29.136Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(50), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:21:19.742Z'), submittedAt: new Date('2024-11-14T22:21:19.742Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(51), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:37:44.697Z'), submittedAt: new Date('2024-11-14T22:37:44.697Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(52), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T23:04:26.145Z'), submittedAt: new Date('2024-11-14T23:04:26.145Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(53), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-19T11:49:26.262Z'), submittedAt: new Date('2024-11-19T11:49:26.262Z'), userIdLegacy: NumberLong(20) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(54), content: 'class Result {\n    public static int defineInteger(int value) {\n        return value; // Przykładowa implementacja, która po prostu zwraca wartość przekazaną przez Task1Main\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T11:08:29.252Z'), submittedAt: new Date('2024-11-22T11:08:29.252Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(55), content: 'public class Result {\n    public static int defineInteger(int value) {\n        return value;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:26:49.518Z'), submittedAt: new Date('2024-11-22T15:26:49.518Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(76), content: 'public class Result { \n\n    public static int defineInteger(int value) { \n\n        return value; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:16:34.558Z'), submittedAt: new Date('2024-11-22T16:16:34.558Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(79), content: 'public class Result { \n\n    public static int defineInteger(int value) { \n\n        return value; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:17:42.024Z'), submittedAt: new Date('2024-11-22T16:17:42.024Z'), userIdLegacy: NumberLong(22) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(97), content: 'def result(input_value): \n\n    return input_value ', grade: 100, gradedAt: new Date('2024-11-22T17:56:11.139Z'), submittedAt: new Date('2024-11-22T17:56:11.139Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(101), content: 'def result(input_value): \n    return input_value', grade: 100, gradedAt: new Date('2024-11-26T00:28:50.656Z'), submittedAt: new Date('2024-11-26T00:28:50.656Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(102), content: 'def result(input_value):\r\n    return input_value;', grade: 100, gradedAt: new Date('2025-01-05T21:34:31.021Z'), submittedAt: new Date('2025-01-05T21:34:31.021Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(2),
            title: '2. Add Two Integer Numberse',
            description: 'Write a function that takes two integer inputs and returns their sum. Ensure that you handle proper addition and return the correct result. The integers can be any arbitrary values provided as inputs.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are -2 and 7, the function should return 5.\n- If the inputs are 0 and 0, the function should return 0.\n\nThis task tests your understanding of basic arithmetic operations and how to handle function inputs and outputs.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-02T17:57:05.441Z'),
            updatedAt: new Date('2024-11-19T12:24:09.753Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(4),
                content: 'public class Result {\n    public static int addTwoIntegers(int a, int b) {\n        return a + b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:47:23.592Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(31),
                content: 'def result(a, b):\n    return a + b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:01:39.174Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(14), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T17:58:49.607Z'), submittedAt: new Date('2024-11-02T17:58:49.607Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(19), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:20:40.888Z'), submittedAt: new Date('2024-11-02T19:20:40.888Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(20), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:34:26.485Z'), submittedAt: new Date('2024-11-02T19:34:26.485Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(21), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:35:43.200Z'), submittedAt: new Date('2024-11-02T19:35:43.200Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(24), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:36:53.756Z'), submittedAt: new Date('2024-11-02T19:36:53.756Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(25), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:56:08.237Z'), submittedAt: new Date('2024-11-02T19:56:08.237Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(27), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:56:49.437Z'), submittedAt: new Date('2024-11-02T19:56:49.437Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(28), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:57:13.514Z'), submittedAt: new Date('2024-11-02T19:57:13.514Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(31), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T20:02:43.851Z'), submittedAt: new Date('2024-11-02T20:02:43.851Z'), userIdLegacy: NumberLong(15) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(32), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T22:00:23.080Z'), submittedAt: new Date('2024-11-02T22:00:23.080Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(37), content: 'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n}\n}', grade: 100, gradedAt: new Date('2024-11-11T14:59:32.789Z'), submittedAt: new Date('2024-11-11T14:59:32.789Z'), userIdLegacy: NumberLong(17) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(39), content: 'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:02:04.997Z'), submittedAt: new Date('2024-11-11T15:02:04.997Z'), userIdLegacy: NumberLong(18) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(41), content: 'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:03:06.982Z'), submittedAt: new Date('2024-11-11T15:03:06.982Z'), userIdLegacy: NumberLong(19) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(56), content: 'public class Result {\n    public static int addTwoIntegers(int a, int b) {\n        return a + b;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:27:05.482Z'), submittedAt: new Date('2024-11-22T15:27:05.482Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(77), content: 'public class Result { \n\n    public static int addTwoIntegers(int a, int b) { \n\n        return a + b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:16:40.789Z'), submittedAt: new Date('2024-11-22T16:16:40.789Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(80), content: 'public class Result { \n\n    public static int addTwoIntegers(int a, int b) { \n\n        return a + b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:17:54.171Z'), submittedAt: new Date('2024-11-22T16:17:54.171Z'), userIdLegacy: NumberLong(22) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(94), content: 'def result(a, b):\n    # Funkcja przyjmuje dwie liczby całkowite i zwraca ich sumę\n    return a + b', grade: 100, gradedAt: new Date('2024-11-22T17:54:46.418Z'), submittedAt: new Date('2024-11-22T17:54:46.418Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(96), content: 'def result(a, b):\n    return a + b', grade: 100, gradedAt: new Date('2024-11-22T17:56:02.672Z'), submittedAt: new Date('2024-11-22T17:56:02.672Z'), userIdLegacy: NumberLong(2) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(3),
            title: '3. Display the Value of a Variable',
            description: 'Write a function that declares a variable, assigns it a value, and then displays (prints) its value. Ensure that the variable is properly declared, initialized, and printed in your chosen programming language.\n\nExamples:\n\n- If the variable is assigned the value 42, the function should print: 42.\n- If the variable is assigned the value -7, the function should print: -7.\n- If the variable is assigned the value 0, the function should print: 0.\n\nThis task tests your understanding of variable declaration, initialization, and basic output operations.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:28:25.500Z'),
            updatedAt: new Date('2024-11-19T12:28:25.500Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(5),
                content: 'public class Result {\n    public static String displayValue(int value) {\n        return String.valueOf(value);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:47:43.856Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(32),
                content: 'def result(value):\n    return str(value)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:01:52.954Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(57), content: 'public class Result {\n    public static String displayValue(int value) {\n        return String.valueOf(value);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:32:24.356Z'), submittedAt: new Date('2024-11-22T15:32:24.356Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(78), content: 'public class Result { \n\n    public static String displayValue(int value) { \n\n        return String.valueOf(value); \n\n    } \n\n} \n', grade: 100, gradedAt: new Date('2024-11-22T16:16:53.221Z'), submittedAt: new Date('2024-11-22T16:16:53.221Z'), userIdLegacy: NumberLong(2) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(81), content: 'public class Result { \n\n    public static String displayValue(int value) { \n\n        return String.valueOf(value); \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:07.131Z'), submittedAt: new Date('2024-11-22T16:18:07.131Z'), userIdLegacy: NumberLong(22) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(95), content: 'def result(value):\n    return str(value)', grade: 100, gradedAt: new Date('2024-11-22T17:55:43.624Z'), submittedAt: new Date('2024-11-22T17:55:43.624Z'), userIdLegacy: NumberLong(2) }
            ]
          }
        ]
      },
      {
        lessonId: lesson2Id,
        lessonIdLegacy: NumberLong(2),
        title: 'Lesson 2',
        content: 'Arithmetic operators',
        lessonNumber: 2,
        requiredLevel: 1,
        createdAt: new Date('2024-11-19T12:34:28.307Z'),
        updatedAt: new Date('2024-11-19T12:34:28.307Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(4),
            title: '4. Use the Addition Operator with Two Numbers',
            description: 'Write a function that takes two numbers as inputs and uses the addition operator (`+`) to calculate their sum. The function should return the result of the addition. The numbers can be integers or floating-point values.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are 2.5 and 4.3, the function should return 6.8.\n- If the inputs are -7 and 2, the function should return -5.\n\nThis task tests your understanding of using the addition operator to combine numerical values.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:53:48.390Z'),
            updatedAt: new Date('2024-11-19T12:53:48.390Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(6),
                content: 'public class Result {\n    public static double addTwoNumbers(double a, double b) {\n        return a + b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:48:10.555Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(33),
                content: 'def result(a, b):\n    return a + b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:02:43.127Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(82), content: 'public class Result { \n\n    public static double addTwoNumbers(double a, double b) { \n\n        return a + b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:23.722Z'), submittedAt: new Date('2024-11-22T16:18:23.722Z'), userIdLegacy: NumberLong(22) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(98), content: 'def result(a, b):\n    return a + b', grade: 100, gradedAt: new Date('2024-11-22T17:58:11.170Z'), submittedAt: new Date('2024-11-22T17:58:11.170Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(5),
            title: '5. Use the Multiplication Operator',
            description: 'Write a function that takes two numbers as inputs and uses the multiplication operator (`*`) to calculate their product. The function should return the result of the multiplication. The numbers can be integers or floating-point values.\n\nExamples:\n\n- If the inputs are 4 and 5, the function should return 20.\n- If the inputs are 2.5 and 3, the function should return 7.5.\n- If the inputs are -3 and -2, the function should return 6.\n\nThis task tests your understanding of using the multiplication operator to calculate products.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:54:10.724Z'),
            updatedAt: new Date('2024-11-19T12:54:10.724Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(7),
                content: 'public class Result {\n    public static double multiplyTwoNumbers(double a, double b) {\n        return a * b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:32.601Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(34),
                content: 'def result(a, b):\n    return a * b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:02:52.910Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(58), content: 'public class Result {\n    public static double multiplyTwoNumbers(double a, double b) {\n        return a * b;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:33:12.826Z'), submittedAt: new Date('2024-11-22T15:33:12.826Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(83), content: 'public class Result { \n\n    public static double multiplyTwoNumbers(double a, double b) { \n\n        return a * b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:29.937Z'), submittedAt: new Date('2024-11-22T16:18:29.937Z'), userIdLegacy: NumberLong(22) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(99), content: 'def result(a, b):\n    return a * b', grade: 100, gradedAt: new Date('2024-11-22T17:58:43.536Z'), submittedAt: new Date('2024-11-22T17:58:43.536Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(6),
            title: '6. Perform Division and Round the Result',
            description: 'Write a function that takes two numbers as inputs, performs division using the division operator (`/`), and rounds the result to the nearest integer. The function should handle any valid division scenario (excluding division by zero) and return the rounded result.\n\nExamples:\n\n- If the inputs are 10 and 3, the function should return 3 (10 ÷ 3 = 3.333, rounded to 3).\n- If the inputs are 7 and 2, the function should return 4 (7 ÷ 2 = 3.5, rounded to 4).\n- If the inputs are -8 and 3, the function should return -3 (-8 ÷ 3 = -2.666, rounded to -3).\n\nThis task tests your understanding of division, handling numerical operations, and rounding results.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:54:29.663Z'),
            updatedAt: new Date('2024-11-19T12:54:29.663Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(8),
                content: 'public class Result {\n    public static int divideAndRound(double a, double b) {\n        return (int) Math.round(a / b);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:39.477Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(35),
                content: 'def result(a, b):\n    return round(a / b)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:03:05.150Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(59), content: 'public class Result {\n    public static int divideAndRound(double a, double b) {\n        return (int) Math.round(a / b);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:33:50.707Z'), submittedAt: new Date('2024-11-22T15:33:50.707Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(84), content: 'public class Result { \n\n    public static int divideAndRound(double a, double b) { \n\n        return (int) Math.round(a / b); \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:38.386Z'), submittedAt: new Date('2024-11-22T16:18:38.386Z'), userIdLegacy: NumberLong(22) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(100), content: 'def result(a, b):\n    return round(a / b)\n', grade: 100, gradedAt: new Date('2024-11-22T18:01:23.050Z'), submittedAt: new Date('2024-11-22T18:01:23.050Z'), userIdLegacy: NumberLong(14) }
            ]
          }
        ]
      },
      {
        lessonId: lesson3Id,
        lessonIdLegacy: NumberLong(3),
        title: 'Lesson 3',
        content: 'Basic Conditional Statements',
        lessonNumber: 3,
        requiredLevel: 1,
        createdAt: new Date('2024-11-19T12:35:03.982Z'),
        updatedAt: new Date('2024-11-19T12:35:03.982Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(7),
            title: '7. Write an If Statement to Check if a Number is Even',
            description: 'Write a function that takes an integer as input and uses an `if` statement to check whether the number is even. If the number is even, the function should return `true` or a specific message indicating that the number is even. Ensure the logic for checking even numbers is correct (e.g., using the modulus operator `%`).\n\nExamples:\n\n- If the input is 4, the function should return `true` or \'4 is even\'.\n- If the input is 7, the function should not return anything or may return `false`.\n- If the input is 0, the function should return `true` or \'0 is even\'.\n\nThis task tests your understanding of conditional statements and the concept of even numbers.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:59:27.731Z'),
            updatedAt: new Date('2024-11-19T12:59:27.731Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(9),
                content: 'public class Result {\n    public static String checkIfEven(int number) {\n        if (number % 2 == 0) {\n            return number + \" is even\";\n        } else {\n            return number + \" is not even\";\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:45.375Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(36),
                content: 'def result(n):\n    if n % 2 == 0:\n        return f\"{n} is even\"\n    else:\n        return f\"{n} is not even\"\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:26.174Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(60), content: 'public class Result {\n    public static String checkIfEven(int number) {\n        if (number % 2 == 0) {\n            return number + " is even";\n        } else {\n            return number + " is not even";\n        }\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:34:51.154Z'), submittedAt: new Date('2024-11-22T15:34:51.154Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(85), content: 'public class Result { \n\n    public static String checkIfEven(int number) { \n\n        if (number % 2 == 0) { \n\n            return number + " is even"; \n\n        } else { \n\n            return number + " is not even"; \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:57.282Z'), submittedAt: new Date('2024-11-22T16:18:57.282Z'), userIdLegacy: NumberLong(22) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(8),
            title: '8. Use an Else Statement',
            description: 'Write a function that takes an integer as input and uses an `if` statement to check whether the number is positive. If the number is positive, return a message indicating it is positive. Otherwise, use an `else` statement to return a message indicating the number is not positive. Ensure the function covers both cases.\n\nExamples:\n\n- If the input is 5, the function should return \'5 is positive\'.\n- If the input is -3, the function should return \'-3 is not positive\'.\n- If the input is 0, the function should return \'0 is not positive\'.\n\nThis task tests your understanding of conditional statements, including the use of `else` to handle alternative cases.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T13:00:01.952Z'),
            updatedAt: new Date('2024-11-19T13:00:01.952Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(10),
                content: 'public class Result {\n    public static String checkIfPositive(int number) {\n        if (number > 0) {\n            return number + \" is positive\";\n        } else {\n            return number + \" is not positive\";\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:53.777Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(37),
                content: 'def result(n):\n    if n > 0:\n        return f\"{n} is positive\"\n    else:\n        return f\"{n} is not positive\"\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:35.404Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(61), content: 'public class Result {\n    public static String checkIfPositive(int number) {\n        if (number > 0) {\n            return number + " is positive";\n        } else {\n            return number + " is not positive";\n        }\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:36:13.524Z'), submittedAt: new Date('2024-11-22T15:36:13.524Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(86), content: 'public class Result { \n\n    public static String checkIfPositive(int number) { \n\n        if (number > 0) { \n\n            return number + " is positive"; \n\n        } else { \n\n            return number + " is not positive"; \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:19:04.184Z'), submittedAt: new Date('2024-11-22T16:19:04.184Z'), userIdLegacy: NumberLong(22) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(9),
            title: '9. Write an If-Else Statement to Compare Two Numbers',
            description: 'Write a function that takes two numbers as inputs and uses an `if-else` statement to compare them. If the first number is greater than the second, return a message indicating this. Otherwise, return a message indicating that the first number is not greater than the second.\n\nExamples:\n\n- If the inputs are 10 and 5, the function should return \'10 is greater than 5\'.\n- If the inputs are 3 and 7, the function should return \'3 is not greater than 7\'.\n- If the inputs are 4 and 4, the function should return \'4 is not greater than 4\'.\n\nThis task tests your understanding of comparison operators and conditional statements.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T13:00:19.924Z'),
            updatedAt: new Date('2024-11-19T13:00:19.924Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(11),
                content: 'public class Result {\n    public static String compareTwoNumbers(int a, int b) {\n        if (a > b) {\n            return a + \" is greater than \" + b;\n        } else {\n            return a + \" is not greater than \" + b;\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:50:01.178Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(38),
                content: 'def result(a, b):\n    if a > b:\n        return f\"{a} is greater than {b}\"\n    else:\n        return f\"{a} is not greater than {b}\"\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:50.358Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(62), content: 'public class Result {\n     public static String compareTwoNumbers(int a, int b) {\n        if (a > b) {\n            return a + " is greater than " + b;\n        } else {\n            return a + " is not greater than " + b;\n        }\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:36:52.137Z'), submittedAt: new Date('2024-11-22T15:36:52.137Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(87), content: 'public class Result { \n\n     public static String compareTwoNumbers(int a, int b) { \n\n        if (a > b) { \n\n            return a + " is greater than " + b; \n\n        } else { \n\n            return a + " is not greater than " + b; \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:19:09.448Z'), submittedAt: new Date('2024-11-22T16:19:09.448Z'), userIdLegacy: NumberLong(22) }
            ]
          }
        ]
      }
    ]
  },
  {
    _id: course2Id,
    courseIdLegacy: NumberLong(2),
    title: '2. Intermediate Course',
    description: 'Intermediate lessons, challenge yourself.',
    titleLevel: 'INTERMEDIATE',
    createdAt: new Date('2024-10-31T14:49:18.314Z'),
    updatedAt: new Date('2024-10-31T14:49:18.314Z'),
    lessons: [
      {
        lessonId: lesson4Id,
        lessonIdLegacy: NumberLong(4),
        title: 'Lesson 4',
        content: 'Arrays',
        lessonNumber: 4,
        requiredLevel: 2,
        createdAt: new Date('2024-11-19T13:03:41.994Z'),
        updatedAt: new Date('2024-11-19T13:03:41.994Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(10),
            title: '10. Define an Array of Specific Integers',
            description: 'Write a function that defines and initializes an array (or list) of integers. The array should be filled with specific values provided in the example below. Ensure that the array contains exactly the ten predefined integers given.\n\nExamples:\n\n- The array should be [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]. The function should return [10, 20, 30, 40, 50, 60, 70, 80, 90, 100].\n- Alternatively, the array can be [5, 15, 25, 35, 45, 55, 65, 75, 85, 95]. The function should return [5, 15, 25, 35, 45, 55, 65, 75, 85, 95].\n\nThis task tests your understanding of arrays (or lists), their initialization, and the ability to use specific predefined values.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:07:52.059Z'),
            updatedAt: new Date('2024-11-22T16:27:35.068Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(12),
                content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> defineArray() {\n        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:50:08.827Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(39),
                content: 'def result():\n    return [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:56.346Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(88), content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> defineArray() {\n        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T16:32:26.899Z'), submittedAt: new Date('2024-11-22T16:32:26.899Z'), userIdLegacy: NumberLong(22) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(11),
            title: '11. Calculate the Sum of All Elements in an Array',
            description: 'Write a function that calculates the sum of all elements in a given array (or list) of integers. Assign the result to a variable called `sum`. Make sure the function takes the array as input and returns the calculated sum.\n\nExamples:\n\n- If the array is [1, 2, 3, 4, 5], the function should return 15 because 1 + 2 + 3 + 4 + 5 = 15.\n- If the array is [10, 20, 30, 40, 50], the function should return 150 because 10 + 20 + 30 + 40 + 50 = 150.\n\nThis task tests your ability to iterate through an array, accumulate values, and assign the final result to a variable.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:08:08.958Z'),
            updatedAt: new Date('2024-11-22T16:26:40.527Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(13),
                content: 'import java.util.List;\n\npublic class Result {\n    public static int calculateSum(List<Integer> numbers) {\n        int sum = 0;\n        for (int number : numbers) {\n            sum += number;\n        }\n        return sum;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:51:18.183Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(40),
                content: 'def result(numbers):\n    return sum(numbers)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:06:59.082Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(89), content: 'import java.util.List;\n\npublic class Result {\n    public static int calculateSum(List<Integer> numbers) {\n        int sum = 0;\n        for (int number : numbers) {\n            sum += number;\n        }\n        return sum;\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T16:33:58.567Z'), submittedAt: new Date('2024-11-22T16:33:58.567Z'), userIdLegacy: NumberLong(22) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(12),
            title: '12. Find the Largest Element in an Array',
            description: 'Write a function that takes an array (or list) of integers as input and returns the largest element in the array. Ensure that the function iterates through the array to find the maximum value.\n\nExamples:\n\n- If the input is [1, 2, 3, 4, 5], the function should return 5.\n- If the input is [-10, -3, -7, -2], the function should return -2.\n- If the input is [0, 0, 0], the function should return 0.\n\nThis task tests your understanding of iteration, comparison, and finding the maximum value in an array.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:08:24.756Z'),
            updatedAt: new Date('2024-11-19T13:08:24.756Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(14),
                content: 'public class Result {\n    public static Integer findLargestElement(Integer[] array) {\n        if (array == null || array.length == 0) {\n            throw new IllegalArgumentException(\"Array cannot be null or empty\");\n        }\n        int max = array[0];\n        for (int i = 1; i < array.length; i++) {\n            if (array[i] > max) {\n                max = array[i];\n            }\n        }\n        return max;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:52:34.950Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(41),
                content: 'def result(numbers):\n    return max(numbers)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:08.059Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(63), content: 'public class Result {\n    public static Integer findLargestElement(Integer[] array) {\n        if (array == null || array.length == 0) {\n            throw new IllegalArgumentException("Array cannot be null or empty");\n        }\n        \n        int max = array[0];\n        for (int i = 1; i < array.length; i++) {\n            if (array[i] > max) {\n                max = array[i];\n            }\n        }\n        return max;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:45:21.022Z'), submittedAt: new Date('2024-11-22T15:45:21.022Z'), userIdLegacy: NumberLong(14) },
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(90), content: 'public class Result { \n\n    public static Integer findLargestElement(Integer[] array) { \n\n        if (array == null || array.length == 0) { \n\n            throw new IllegalArgumentException("Array cannot be null or empty"); \n\n        } \n\n         \n\n        int max = array[0]; \n\n        for (int i = 1; i < array.length; i++) { \n\n            if (array[i] > max) { \n\n                max = array[i]; \n\n            } \n\n        } \n\n        return max; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:34:20.602Z'), submittedAt: new Date('2024-11-22T16:34:20.602Z'), userIdLegacy: NumberLong(22) }
            ]
          }
        ]
      },
      {
        lessonId: lesson5Id,
        lessonIdLegacy: NumberLong(5),
        title: 'Lesson 5',
        content: 'Loops',
        lessonNumber: 5,
        requiredLevel: 2,
        createdAt: new Date('2024-11-19T13:03:51.662Z'),
        updatedAt: new Date('2024-11-19T13:03:51.662Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(13),
            title: '13. Use a For Loop to Calculate the Sum of Even Numbers',
            description: 'Write a for loop that calculates the sum of all even numbers between 1 and 20 (inclusive). Assign the result to a variable called `sum`. Ensure that only even numbers are included in the calculation.\n\nExamples:\n\n- The sum should be 110, because 2 + 4 + 6 + 8 + 10 + 12 + 14 + 16 + 18 + 20 = 110.\n\nThis task tests your understanding of using a loop to iterate through a range of numbers, identifying even numbers, and accumulating their values into a single sum.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:08:55.523Z'),
            updatedAt: new Date('2024-11-22T16:37:27.351Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(15),
                content: 'public class Result {\n    public static int calculateSumOfEvens() {\n        int sum = 0;\n        for (int i = 1; i <= 20; i++) {\n            if (i % 2 == 0) {\n                sum += i;\n            }\n        }\n        return sum;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:52:46.846Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(42),
                content: 'def result():\n    return sum([i for i in range(1, 21) if i % 2 == 0])\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:18.609Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(91), content: 'public class Result {\n    public static int calculateSumOfEvens() {\n        int sum = 0;\n        for (int i = 1; i <= 20; i++) {\n            if (i % 2 == 0) {\n                sum += i;\n            }\n        }\n        return sum;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T16:40:54.370Z'), submittedAt: new Date('2024-11-22T16:40:54.370Z'), userIdLegacy: NumberLong(22) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(14),
            title: '14. Write a While Loop to Sum Numbers',
            description: 'Write a function that uses a `while` loop to sum the numbers from 1 to a given integer `n`. The function should take `n` as input and return the sum of all numbers from 1 to `n`.\n\nExamples:\n\n- If the input is 5, the function should return 15 (1 + 2 + 3 + 4 + 5).\n- If the input is 10, the function should return 55.\n\nThis task tests your understanding of `while` loops, conditional logic, and basic arithmetic.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:09:10.789Z'),
            updatedAt: new Date('2024-11-19T13:09:10.789Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(16),
                content: 'public class Result {\n    public static int sumNumbersUpToN(int n) {\n        int sum = 0;\n        int i = 1;\n        while (i <= n) {\n            sum += i;\n            i++;\n        }\n        return sum;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:53:51.322Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(43),
                content: 'def result(n):\n    return sum(range(1, n + 1)) if n > 0 else 0\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:25.782Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(64), content: 'public class Result {\n    public static int sumNumbersUpToN(int n) {\n        int sum = 0;\n        int i = 1;\n        \n        while (i <= n) {\n            sum += i;\n            i++;\n        }\n        \n        return sum;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:47:02.588Z'), submittedAt: new Date('2024-11-22T15:47:02.588Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(15),
            title: '15. Use a While Loop to Find a Number Divisible by 7',
            description: 'Write a while loop that generates random numbers between 1 and 50 until a number divisible by 7 is found. Use an appropriate random number generating function. Once a number divisible by 7 is found, use the `break` statement to exit the loop.\n\nExamples:\n\n- The loop should keep generating numbers like 34, 5, 13, 28 until it finds a number like 14 (which is divisible by 7). At this point, the loop should stop.\n\nThis task tests your understanding of while loops, generating random numbers, conditional checks for divisibility, and the use of the `break` statement to exit the loop when a condition is met.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:09:25.225Z'),
            updatedAt: new Date('2024-11-22T16:37:48.125Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(17),
                content: 'public class Result {\n    public static int findNumberDivisibleBy7(int[] numbers) {\n        int index = 0;\n        int number;\n        while (index < numbers.length) {\n            number = numbers[index];\n            if (number % 7 == 0) {\n                return number;\n            }\n            index++;\n        }\n        return -1;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:11.541Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(44),
                content: 'def result(numbers):\n    for number in numbers:\n        if number % 7 == 0:\n            return number\n    return None\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:34.158Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(92), content: 'public class Result {\n    public static int findNumberDivisibleBy7(int[] numbers) {\n        int index = 0;\n        int number;\n        while (index < numbers.length) {\n            number = numbers[index];\n            if (number % 7 == 0) {\n                return number;\n            }\n            index++;\n        }\n        return -1;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T16:45:26.950Z'), submittedAt: new Date('2024-11-22T16:45:26.950Z'), userIdLegacy: NumberLong(22) }
            ]
          }
        ]
      },
      {
        lessonId: lesson6Id,
        lessonIdLegacy: NumberLong(6),
        title: 'Lesson 6',
        content: 'Functions',
        lessonNumber: 6,
        requiredLevel: 2,
        createdAt: new Date('2024-11-19T13:04:03.526Z'),
        updatedAt: new Date('2024-11-19T13:04:03.526Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(16),
            title: '16. Write a Function to Return the Sum of Two Numbers',
            description: 'Write a function that takes two numbers as inputs and returns their sum. Ensure the function correctly calculates and returns the result.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are -2 and 10, the function should return 8.\n\nThis task tests your understanding of function creation, input handling, and returning results.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:09:54.371Z'),
            updatedAt: new Date('2024-11-19T13:09:54.371Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(18),
                content: 'public class Result {\n    public static int sumTwoNumbers(int a, int b) {\n        return a + b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:19.428Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(45),
                content: 'def result(a, b):\n    return a + b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:43.677Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(65), content: 'public class Result {\n    public static int sumTwoNumbers(int a, int b) {\n        return a + b;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:48:32.063Z'), submittedAt: new Date('2024-11-22T15:48:32.063Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(17),
            title: '17. Create a Function to Return the Largest Number in a List',
            description: 'Write a function that takes a list of numbers as input and returns the largest number in the list. Ensure that the function iterates through the list to find the maximum value.\n\nExamples:\n\n- If the input is [1, 2, 3, 4, 5], the function should return 5.\n- If the input is [-10, -3, -7, -2], the function should return -2.\n- If the input is [0, 0, 0], the function should return 0.\n\nThis task tests your understanding of iteration, comparison, and finding the maximum value in a list.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:10:17.887Z'),
            updatedAt: new Date('2024-11-19T13:10:17.887Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(19),
                content: 'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:25.129Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(46),
                content: 'def result(numbers):\n    return max(numbers)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:57.454Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(66), content: 'import java.util.List;\n\npublic class Result {\n    // Metoda do znalezienia największego elementu w liście\n    public static Integer findLargestInList(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            throw new IllegalArgumentException("List cannot be null or empty");\n        }\n\n        Integer max = numbers.get(0);\n        for (Integer number : numbers) {\n            if (number > max) {\n                max = number;\n            }\n        }\n        return max;\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T15:51:42.484Z'), submittedAt: new Date('2024-11-22T15:51:42.484Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(18),
            title: '18. Create a Function to Return the Largest Number in a List',
            description: 'Write a function that takes a non-negative integer `n` as input and calculates its factorial using recursion. The factorial of a number is defined as `n! = n * (n-1) * ... * 1`, with the base case `0! = 1`.\n\nExamples:\n\n- If the input is 5, the function should return 120 (5! = 5 * 4 * 3 * 2 * 1).\n- If the input is 0, the function should return 1 (0! = 1).\n- If the input is 3, the function should return 6 (3! = 3 * 2 * 1).\n\nThis task tests your understanding of recursion, base cases, and mathematical calculations.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:10:33.003Z'),
            updatedAt: new Date('2024-11-19T13:10:33.003Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(20),
                content: 'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:50.744Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(47),
                content: 'def result(n):\n    if n == 0:\n        return 1\n    factorial = 1\n    for i in range(1, n + 1):\n        factorial *= i\n    return factorial\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:08:03.605Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(67), content: 'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException("Input must be a non-negative integer");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:52:10.282Z'), submittedAt: new Date('2024-11-22T15:52:10.282Z'), userIdLegacy: NumberLong(14) }
            ]
          }
        ]
      }
    ]
  },
  {
    _id: course3Id,
    courseIdLegacy: NumberLong(3),
    title: '3. Advanced Course',
    description: 'Advanced lessons, only for experienced programmers.',
    titleLevel: 'ADVANCED',
    createdAt: new Date('2024-11-19T12:37:41.672Z'),
    updatedAt: new Date('2024-11-19T12:37:41.672Z'),
    lessons: [
      {
        lessonId: lesson7Id,
        lessonIdLegacy: NumberLong(7),
        title: 'Lesson 7',
        content: 'Recursion',
        lessonNumber: 7,
        requiredLevel: 3,
        createdAt: new Date('2024-11-19T13:04:18.725Z'),
        updatedAt: new Date('2024-11-19T13:04:18.725Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(19),
            title: '19. Write a Recursive Function to Calculate the Sum of Numbers from 1 to n',
            description: 'Write a function that takes a positive integer `n` as input and calculates the sum of all numbers from 1 to `n` using recursion. The base case should handle when `n` is 0, returning 0, and the recursive case should sum `n` with the result of the function called on `n-1`.\n\nExamples:\n\n- If the input is 5, the function should return 15 (1 + 2 + 3 + 4 + 5).\n- If the input is 10, the function should return 55 (1 + 2 + ... + 10).\n- If the input is 1, the function should return 1.\n\nThis task tests your understanding of recursion, base cases, and arithmetic progression.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:11:47.618Z'),
            updatedAt: new Date('2024-11-19T13:11:47.618Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(21),
                content: 'public class Result {\n    public static int sumToN(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        return n + sumToN(n - 1);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:56.849Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(48),
                content: 'def result(n):\n    return sum(range(1, n + 1))\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:08:18.436Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(68), content: 'public class Result {\n    public static int sumToN(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException("Input must be a non-negative integer");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        return n + sumToN(n - 1);\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T15:52:34.850Z'), submittedAt: new Date('2024-11-22T15:52:34.850Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(20),
            title: '20. Create a Function to Find the nth Fibonacci Number',
            description: 'Write a function that takes a non-negative integer `n` as input and returns the nth Fibonacci number using recursion. The Fibonacci sequence is defined as:\n  - F(0) = 0\n  - F(1) = 1\n  - F(n) = F(n-1) + F(n-2) for n > 1\n\nExamples:\n\n- If the input is 0, the function should return 0.\n- If the input is 1, the function should return 1.\n- If the input is 5, the function should return 5 (Fibonacci sequence: 0, 1, 1, 2, 3, 5).\n- If the input is 7, the function should return 13.\n\nThis task tests your understanding of recursion, mathematical sequences, and efficient computation.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:12:02.985Z'),
            updatedAt: new Date('2024-11-19T13:12:02.985Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(22),
                content: 'public class Result {\n    public static int fibonacci(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        if (n == 1) {\n            return 1;\n        }\n        return fibonacci(n - 1) + fibonacci(n - 2);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:55:09.720Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(49),
                content: 'def result(n):\n    if n == 0:\n        return 0\n    elif n == 1:\n        return 1\n    a, b = 0, 1\n    for _ in range(2, n + 1):\n        a, b = b, a + b\n    return b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:08:24.092Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(69), content: 'public class Result {\n    public static int fibonacci(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException("Input must be a non-negative integer");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        if (n == 1) {\n            return 1;\n        }\n        return fibonacci(n - 1) + fibonacci(n - 2);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:53:02.798Z'), submittedAt: new Date('2024-11-22T15:53:02.798Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(21),
            title: '21. Solve the Tower of Hanoi Problem Using Recursion',
            description: 'Write a function that solves the Tower of Hanoi problem for `n` disks. The function should take three inputs: the source rod, the destination rod, and the auxiliary rod. The goal is to move all disks from the source rod to the destination rod following these rules:\n\n1. Only one disk can be moved at a time.\n2. A disk can only be placed on top of a larger disk or on an empty rod.\n\nThe function should print each move in the format: \'Move disk from [source] to [destination]\'. Use recursion to break the problem into smaller subproblems.\n\nExamples:\n\n- For `n = 2`, the output should be:\n  Move disk from A to B\n  Move disk from A to C\n  Move disk from B to C\n\n- For `n = 3`, the output should be:\n  Move disk from A to C\n  Move disk from A to B\n  Move disk from C to B\n  Move disk from A to C\n  Move disk from B to A\n  Move disk from B to C\n  Move disk from A to C\n\nThis task tests your understanding of recursion, breaking down complex problems, and solving algorithmic challenges.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:12:14.568Z'),
            updatedAt: new Date('2024-11-19T13:12:14.568Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(23),
                content: 'import java.io.*;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static void towerOfHanoi(int n, char source, char destination, char auxiliary, Consumer<String> logger) {\n        if (n == 0) {\n            return;\n        }\n        towerOfHanoi(n - 1, source, auxiliary, destination, logger);\n        logger.accept(\"Move disk from \" + source + \" to \" + destination);\n        towerOfHanoi(n - 1, auxiliary, destination, source, logger);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:56:55.710Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(50),
                content: 'def tower_of_hanoi(n, source, target, auxiliary, logger):\n    if n == 1:\n        logger(f\"Move disk from {source} to {target}\")\n        return\n    tower_of_hanoi(n - 1, source, auxiliary, target, logger)\n    logger(f\"Move disk from {source} to {target}\")\n    tower_of_hanoi(n - 1, auxiliary, target, source, logger)\n\ndef result(n):\n    output = []\n    logger = lambda x: output.append(x)\n    tower_of_hanoi(n, \'A\', \'C\', \'B\', logger)\n    return \'\'.join(output)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:09:28.958Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(70), content: 'import java.io.*;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static void towerOfHanoi(int n, char source, char destination, char auxiliary, Consumer<String> logger) {\n        if (n == 0) {\n            return;\n        }\n        towerOfHanoi(n - 1, source, auxiliary, destination, logger);\n        logger.accept("Move disk from " + source + " to " + destination);\n        towerOfHanoi(n - 1, auxiliary, destination, source, logger);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:53:37.263Z'), submittedAt: new Date('2024-11-22T15:53:37.263Z'), userIdLegacy: NumberLong(14) }
            ]
          }
        ]
      },
      {
        lessonId: lesson8Id,
        lessonIdLegacy: NumberLong(8),
        title: 'Lesson 8',
        content: 'Sorting Algorithms',
        lessonNumber: 8,
        requiredLevel: 3,
        createdAt: new Date('2024-11-19T13:04:28.093Z'),
        updatedAt: new Date('2024-11-19T13:04:28.093Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(22),
            title: '22. Implement Bubble Sort',
            description: 'Write a function that implements the Bubble Sort algorithm to sort a list of numbers in ascending order. The function should repeatedly compare adjacent elements in the list and swap them if they are in the wrong order. This process should continue until the list is fully sorted.\n\nExamples:\n\n- If the input is [5, 3, 8, 6, 2], the function should return [2, 3, 5, 6, 8].\n- If the input is [1, 4, 3, 2], the function should return [1, 2, 3, 4].\n- If the input is [10, 20, 5, 15], the function should return [5, 10, 15, 20].\n\nThis task tests your understanding of sorting algorithms and iterative problem-solving.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:13:01.662Z'),
            updatedAt: new Date('2024-11-19T13:13:01.662Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(24),
                content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\n\npublic class Result {\n    public static List<Integer> bubbleSort(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        int n = sortedNumbers.size();\n        boolean swapped;\n        do {\n            swapped = false;\n            for (int i = 0; i < n - 1; i++) {\n                if (sortedNumbers.get(i) > sortedNumbers.get(i + 1)) {\n                    int temp = sortedNumbers.get(i);\n                    sortedNumbers.set(i, sortedNumbers.get(i + 1));\n                    sortedNumbers.set(i + 1, temp);\n                    swapped = true;\n                }\n            }\n            n--;\n        } while (swapped);\n        return sortedNumbers;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:05.148Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(51),
                content: 'def result(arr):\n    n = len(arr)\n    for i in range(n):\n        for j in range(0, n - i - 1):\n            if arr[j] > arr[j + 1]:\n                arr[j], arr[j + 1] = arr[j + 1], arr[j]\n    return arr\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:10:41.111Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(71), content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\n\npublic class Result {\n    public static List<Integer> bubbleSort(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            return numbers;\n        }\n        \n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        int n = sortedNumbers.size();\n        boolean swapped;\n        do {\n            swapped = false;\n            for (int i = 0; i < n - 1; i++) {\n                if (sortedNumbers.get(i) > sortedNumbers.get(i + 1)) {\n                    int temp = sortedNumbers.get(i);\n                    sortedNumbers.set(i, sortedNumbers.get(i + 1));\n                    sortedNumbers.set(i + 1, temp);\n                    swapped = true;\n                }\n            }\n            n--;\n        } while (swapped);\n        \n        return sortedNumbers;\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T15:54:05.804Z'), submittedAt: new Date('2024-11-22T15:54:05.804Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(23),
            title: '23. Implement Quick Sort',
            description: 'Write a function that implements the Quick Sort algorithm to sort a list of numbers in ascending order. The function should use the divide-and-conquer strategy by selecting a pivot, partitioning the list into elements less than and greater than the pivot, and recursively sorting the partitions.\n\nExamples:\n\n- If the input is [5, 3, 8, 6, 2], the function should return [2, 3, 5, 6, 8].\n- If the input is [1, 4, 3, 2], the function should return [1, 2, 3, 4].\n- If the input is [10, 20, 5, 15], the function should return [5, 10, 15, 20].\n\nThis task tests your understanding of recursive algorithms and efficient sorting techniques.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:13:13.061Z'),
            updatedAt: new Date('2024-11-19T13:13:13.061Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(25),
                content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static List<Integer> quickSort(List<Integer> numbers) {\n        if (numbers == null || numbers.size() <= 1) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        quickSortHelper(sortedNumbers, 0, sortedNumbers.size() - 1);\n        return sortedNumbers;\n    }\n\n    private static void quickSortHelper(List<Integer> list, int low, int high) {\n        if (low < high) {\n            int pivotIndex = partition(list, low, high);\n            quickSortHelper(list, low, pivotIndex - 1);\n            quickSortHelper(list, pivotIndex + 1, high);\n        }\n    }\n\n    private static int partition(List<Integer> list, int low, int high) {\n        int pivot = list.get(high);\n        int i = low - 1;\n        for (int j = low; j < high; j++) {\n            if (list.get(j) < pivot) {\n                i++;\n                Collections.swap(list, i, j);\n            }\n        }\n        Collections.swap(list, i + 1, high);\n        return i + 1;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:11.657Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(52),
                content: 'def result(arr):\n    if len(arr) <= 1:\n        return arr\n    pivot = arr[len(arr) // 2]\n    left = [x for x in arr if x < pivot]\n    middle = [x for x in arr if x == pivot]\n    right = [x for x in arr if x > pivot]\n    return result(left) + middle + result(right)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:10:55.528Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(72), content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static List<Integer> quickSort(List<Integer> numbers) {\n        if (numbers == null || numbers.size() <= 1) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        quickSortHelper(sortedNumbers, 0, sortedNumbers.size() - 1);\n        return sortedNumbers;\n    }\n\n    private static void quickSortHelper(List<Integer> list, int low, int high) {\n        if (low < high) {\n            int pivotIndex = partition(list, low, high);\n            quickSortHelper(list, low, pivotIndex - 1);\n            quickSortHelper(list, pivotIndex + 1, high);\n        }\n    }\n\n    private static int partition(List<Integer> list, int low, int high) {\n        int pivot = list.get(high);\n        int i = low - 1;\n        for (int j = low; j < high; j++) {\n            if (list.get(j) < pivot) {\n                i++;\n                Collections.swap(list, i, j);\n            }\n        }\n        Collections.swap(list, i + 1, high);\n        return i + 1;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:55:15.616Z'), submittedAt: new Date('2024-11-22T15:55:15.616Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(24),
            title: '24. Selection Sort for an Array of Integers',
            description: 'Write a function to implement Selection Sort for an array of integers. This algorithm sorts an array by repeatedly finding the smallest element from the unsorted portion of the array and placing it in the correct position.\n\nExamples:\n\n- For an array [64, 25, 12, 22, 11], after sorting, the array should be [11, 12, 22, 25, 64].\n- In the first pass, the algorithm selects the smallest value (11) and swaps it with the first element (64), resulting in [11, 25, 12, 22, 64].\n- The sorting continues until all elements are sorted.\n\nThis task tests your ability to implement a classic sorting algorithm, use nested loops, and correctly swap values in an array.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:13:38.544Z'),
            updatedAt: new Date('2024-11-22T16:52:47.249Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(26),
                content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> selectionSort(List<Integer> numbers) {\n        Integer[] array = numbers.toArray(new Integer[0]);\n        int n = array.length;\n        for (int i = 0; i < n - 1; i++) {\n            int minIndex = i;\n            for (int j = i + 1; j < n; j++) {\n                if (array[j] < array[minIndex]) {\n                    minIndex = j;\n                }\n            }\n            int temp = array[minIndex];\n            array[minIndex] = array[i];\n            array[i] = temp;\n        }\n        return Arrays.asList(array);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:19.110Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(53),
                content: 'def result(arr):\n    n = len(arr)\n    for i in range(n):\n        min_idx = i\n        for j in range(i + 1, n):\n            if arr[j] < arr[min_idx]:\n                min_idx = j\n        arr[i], arr[min_idx] = arr[min_idx], arr[i]\n    return arr\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:11:04.718Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(93), content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> selectionSort(List<Integer> numbers) {\n        Integer[] array = numbers.toArray(new Integer[0]);\n        int n = array.length;\n\n        for (int i = 0; i < n - 1; i++) {\n            int minIndex = i;\n            for (int j = i + 1; j < n; j++) {\n                if (array[j] < array[minIndex]) {\n                    minIndex = j;\n                }\n            }\n\n            int temp = array[minIndex];\n            array[minIndex] = array[i];\n            array[i] = temp;\n        }\n\n        return Arrays.asList(array);\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T16:54:12.141Z'), submittedAt: new Date('2024-11-22T16:54:12.141Z'), userIdLegacy: NumberLong(14) }
            ]
          }
        ]
      },
      {
        lessonId: lesson9Id,
        lessonIdLegacy: NumberLong(9),
        title: 'Lesson 9',
        content: 'Data Structures',
        lessonNumber: 9,
        requiredLevel: 3,
        createdAt: new Date('2024-11-19T13:04:48.507Z'),
        updatedAt: new Date('2024-11-19T13:04:48.507Z'),
        comments: [],
        assignments: [
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(25),
            title: '25. Create a Class Representing a Stack',
            description: 'Write a class that implements a stack data structure. The class should support the following operations:\n\n1. `push(value)` - Adds a value to the top of the stack.\n2. `pop()` - Removes and returns the value at the top of the stack. If the stack is empty, it should handle the error appropriately.\n3. `peek()` - Returns the value at the top of the stack without removing it.\n4. `is_empty()` - Returns `true` if the stack is empty, otherwise `false`.\n\nExamples:\n\n- If the operations are `push(5)`, `push(10)`, `pop()`, the result of `pop()` should be `10`, and the stack will contain [5].\n- If the stack is empty and `pop()` is called, it should handle the situation gracefully.\n\nThis task tests your understanding of classes, object-oriented programming, and stack operations.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:14:29.165Z'),
            updatedAt: new Date('2024-11-19T13:14:29.165Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(27),
                content: 'import java.io.*;\nimport java.util.*;\n\nclass Stack {\n    private List<Integer> stack;\n\n    public Stack() {\n        stack = new ArrayList<>();\n    }\n\n    public void push(int value) {\n        stack.add(value);\n    }\n\n    public int pop() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.remove(stack.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.get(stack.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return stack.isEmpty();\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:25.968Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(54),
                content: 'class Stack:\n    def __init__(self):\n        self.items = []\n\n    def push(self, value):\n        self.items.append(value)\n\n    def pop(self):\n        return self.items.pop() if not self.is_empty() else \"N/A\"\n\n    def peek(self):\n        return self.items[-1] if not self.is_empty() else \"N/A\"\n\n    def is_empty(self):\n        return len(self.items) == 0\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:11:41.236Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(73), content: 'import java.io.*;\nimport java.util.*;\n\nclass Stack {\n    private List<Integer> stack;\n\n    public Stack() {\n        stack = new ArrayList<>();\n    }\n\n\n    public void push(int value) {\n        stack.add(value);\n    }\n\n    public int pop() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.remove(stack.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.get(stack.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return stack.isEmpty();\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:56:09.261Z'), submittedAt: new Date('2024-11-22T15:56:09.261Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(26),
            title: '26. Implement a Queue with Enqueue and Dequeue Operations',
            description: 'Write a class that implements a queue data structure. The class should support the following operations:\n\n1. `add(value)` - Adds a value to the end of the queue.\n2. `remove()` - Removes and returns the value at the front of the queue. If the queue is empty, it should handle the error appropriately.\n3. `peek()` - Returns the value at the front of the queue without removing it.\n4. `is_empty()` - Returns `true` if the queue is empty, otherwise `false`.\n\nExamples:\n\n- If the operations are `add(5)`, `add(10)`, `remove()`, the result of `remove()` should be `5`, and the queue will contain [10].\n- If the queue is empty and `remove()` is called, it should handle the situation gracefully.\n\nThis task tests your understanding of classes, object-oriented programming, and queue operations.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:14:42.699Z'),
            updatedAt: new Date('2024-11-22T15:17:03.013Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(28),
                content: 'import java.io.*;\nimport java.util.*;\n\nclass Queue {\n    private LinkedList<Integer> queue;\n\n    public Queue() {\n        queue = new LinkedList<>();\n    }\n\n    public void add(int value) {\n        queue.addLast(value);\n    }\n\n    public int remove() {\n        if (isEmpty()) {\n            throw new NoSuchElementException(\"Queue is empty\");\n        }\n        return queue.removeFirst();\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new NoSuchElementException(\"Queue is empty\");\n        }\n        return queue.getFirst();\n    }\n\n    public boolean isEmpty() {\n        return queue.isEmpty();\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:31.351Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(55),
                content: 'class Queue:\n    def __init__(self):\n        self.items = []\n\n    def enqueue(self, value):\n        self.items.append(value)\n\n    def dequeue(self):\n        return self.items.pop(0) if not self.is_empty() else \"N/A\"\n\n    def peek(self):\n        return self.items[0] if not self.is_empty() else \"N/A\"\n\n    def is_empty(self):\n        return len(self.items) == 0\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:12:16.763Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(74), content: 'import java.io.*; \n\nimport java.util.*; \n\n  class Queue { \n\n    private LinkedList<Integer> queue; \n\n    public Queue() { \n\n        queue = new LinkedList<>(); \n\n    } \n\n    public void add(int value) { \n\n        queue.addLast(value); \n\n    } \n\n    public int remove() { \n\n        if (isEmpty()) { \n\n            throw new NoSuchElementException("Queue is empty"); \n\n        } \n\n        return queue.removeFirst(); \n\n    } \n\n    public int peek() { \n\n        if (isEmpty()) { \n\n            throw new NoSuchElementException("Queue is empty"); \n\n        } \n\n        return queue.getFirst(); \n\n    } \n\n    public boolean isEmpty() { \n\n        return queue.isEmpty(); \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T15:57:44.515Z'), submittedAt: new Date('2024-11-22T15:57:44.515Z'), userIdLegacy: NumberLong(14) }
            ]
          },
          {
            assignmentId: ObjectId(),
            assignmentIdLegacy: NumberLong(27),
            title: '27. Write a Class for Working with a Binary Tree',
            description: 'Write a class that represents a binary tree. The class should include methods for common operations:\n\n1. `insert(value)` - Inserts a value into the binary tree following binary search tree rules.\n2. `find(value)` - Searches for a value in the tree and returns `true` if found, otherwise `false`.\n3. `in_order_traversal()` - Returns a list of values in the tree in in-order sequence.\n4. `pre_order_traversal()` - Returns a list of values in pre-order sequence.\n5. `post_order_traversal()` - Returns a list of values in post-order sequence.\n\nExamples:\n\n- If the tree contains [5, 3, 7] and `in_order_traversal()` is called, it should return [3, 5, 7].\n- If the value `5` is inserted into the tree and then `find(5)` is called, it should return `true`.\n- If the tree is empty and `find(1)` is called, it should return `false`.\n\nThis task tests your understanding of binary trees, traversal methods, and object-oriented design.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:14:57.725Z'),
            updatedAt: new Date('2024-11-19T13:14:57.725Z'),
            solutions: [
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(29),
                content: 'import java.util.*;\n\nclass BinaryTree {\n    static class Node {\n        int value;\n        Node left, right;\n\n        Node(int value) {\n            this.value = value;\n            left = right = null;\n        }\n    }\n\n    private Node root;\n\n    public BinaryTree() {\n        root = null;\n    }\n\n    public void insert(int value) {\n        root = insertRec(root, value);\n    }\n\n    private Node insertRec(Node root, int value) {\n        if (root == null) {\n            root = new Node(value);\n            return root;\n        }\n        if (value < root.value) {\n            root.left = insertRec(root.left, value);\n        } else if (value > root.value) {\n            root.right = insertRec(root.right, value);\n        }\n        return root;\n    }\n\n    public boolean find(int value) {\n        return findRec(root, value);\n    }\n\n    private boolean findRec(Node root, int value) {\n        if (root == null) {\n            return false;\n        }\n        if (root.value == value) {\n            return true;\n        }\n        if (value < root.value) {\n            return findRec(root.left, value);\n        }\n        return findRec(root.right, value);\n    }\n\n    public List<Integer> inOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        inOrderRec(root, result);\n        return result;\n    }\n\n    private void inOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            inOrderRec(root.left, result);\n            result.add(root.value);\n            inOrderRec(root.right, result);\n        }\n    }\n\n    public List<Integer> preOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        preOrderRec(root, result);\n        return result;\n    }\n\n    private void preOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            result.add(root.value);\n            preOrderRec(root.left, result);\n            preOrderRec(root.right, result);\n        }\n    }\n\n    public List<Integer> postOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        postOrderRec(root, result);\n        return result;\n    }\n\n    private void postOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            postOrderRec(root.left, result);\n            postOrderRec(root.right, result);\n            result.add(root.value);\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:39.293Z'),
                authorUserId: null
              },
              {
                solutionId: ObjectId(),
                solutionIdLegacy: NumberLong(56),
                content: 'class BinaryTree:\n    class Node:\n        def __init__(self, value):\n            self.value = value\n            self.left = None\n            self.right = None\n\n    def __init__(self):\n        self.root = None\n\n    def insert(self, value):\n        if self.root is None:\n            self.root = self.Node(value)\n        else:\n            self._insert_recursive(self.root, value)\n\n    def _insert_recursive(self, node, value):\n        if value < node.value:\n            if node.left is None:\n                node.left = self.Node(value)\n            else:\n                self._insert_recursive(node.left, value)\n        else:\n            if node.right is None:\n                node.right = self.Node(value)\n            else:\n                self._insert_recursive(node.right, value)\n\n    def find(self, value):\n        return self._find_recursive(self.root, value)\n\n    def _find_recursive(self, node, value):\n        if node is None:\n            return False\n        if node.value == value:\n            return True\n        elif value < node.value:\n            return self._find_recursive(node.left, value)\n        else:\n            return self._find_recursive(node.right, value)\n\n    def in_order_traversal(self):\n        result = []\n        self._in_order_recursive(self.root, result)\n        return result\n\n    def _in_order_recursive(self, node, result):\n        if node is not None:\n            self._in_order_recursive(node.left, result)\n            result.append(node.value)\n            self._in_order_recursive(node.right, result)\n\n    def pre_order_traversal(self):\n        result = []\n        self._pre_order_recursive(self.root, result)\n        return result\n\n    def _pre_order_recursive(self, node, result):\n        if node is not None:\n            result.append(node.value)\n            self._pre_order_recursive(node.left, result)\n            self._pre_order_recursive(node.right, result)\n\n    def post_order_traversal(self):\n        result = []\n        self._post_order_recursive(self.root, result)\n        return result\n\n    def _post_order_recursive(self, node, result):\n        if node is not None:\n            self._post_order_recursive(node.left, result)\n            self._post_order_recursive(node.right, result)\n            result.append(node.value)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:13:11.729Z'),
                authorUserId: null
              }
            ],
            submissions: [
              { submissionId: ObjectId(), submissionIdLegacy: NumberLong(75), content: 'import java.util.*; \n\nclass BinaryTree { \n\n    static class Node { \n\n        int value; \n\n        Node left, right; \n\n  \n\n        Node(int value) { \n\n            this.value = value; \n\n            left = right = null; \n\n        } \n\n    } \n\n  \n\n    private Node root; \n\n\n    public BinaryTree() { \n\n        root = null; \n\n    } \n\n\n\n    public void insert(int value) { \n\n        root = insertRec(root, value); \n\n    } \n\n  \n\n    private Node insertRec(Node root, int value) { \n\n        if (root == null) { \n\n            root = new Node(value); \n\n            return root; \n\n        } \n\n        if (value < root.value) { \n\n            root.left = insertRec(root.left, value); \n\n        } else if (value > root.value) { \n\n            root.right = insertRec(root.right, value); \n\n        } \n\n        return root; \n\n    } \n\n\n    public boolean find(int value) { \n\n        return findRec(root, value); \n\n    } \n\n  \n\n    private boolean findRec(Node root, int value) { \n\n        if (root == null) { \n\n            return false; \n\n        } \n\n        if (root.value == value) { \n\n            return true; \n\n        } \n\n        if (value < root.value) { \n\n            return findRec(root.left, value); \n\n        } \n\n        return findRec(root.right, value); \n\n    } \n\n\n    public List<Integer> inOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        inOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void inOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            inOrderRec(root.left, result); \n\n            result.add(root.value); \n\n            inOrderRec(root.right, result); \n\n        } \n\n    } \n\n\n    public List<Integer> preOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        preOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void preOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            result.add(root.value); \n\n            preOrderRec(root.left, result); \n\n            preOrderRec(root.right, result); \n\n        } \n\n    }  \n\n    public List<Integer> postOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        postOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void postOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            postOrderRec(root.left, result); \n\n            postOrderRec(root.right, result); \n\n            result.add(root.value); \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T15:58:28.136Z'), submittedAt: new Date('2024-11-22T15:58:28.136Z'), userIdLegacy: NumberLong(14) }
            ]
          }
        ]
      }
    ]
  }
]);

// ==========================================
// COLLECTION: users with ALL data
// ==========================================
print('Inserting all 14 users with progress, quiz results, and daily tasks...');

db.users.insertMany([
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(1),
    username: 'jan.kowalski',
    password: '$2a$10$2e86ywtYuHFNg9l3x7181uTntRx9Hiz/xXrRX8GhVXv5/0.YVOTzu',
    email: 'jan.kowalski@example.com',
    firstName: 'Jan',
    surname: 'Kowalski',
    phoneNumber: '123456789',
    age: 30,
    role: 'USER',
    level: 0,
    title: 'ADVANCED',
    createdAt: new Date('2024-11-01T18:10:20.198Z'),
    updatedAt: new Date('2024-11-01T18:10:20.198Z'),
    progress: [],
    quizResults: [],
    dailyTasks: []
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(2),
    username: 'radek',
    password: '$2a$10$lelmojRsemYC2sUgFAKBqOKrGfUaYBG6vKo11n4AJ2OzBenpZnxFW',
    email: 'asdi@example.com',
    firstName: 'rad',
    surname: 'Kowalski',
    phoneNumber: '123456789',
    age: 30,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-01T18:29:34.101Z'),
    updatedAt: new Date('2024-11-01T18:29:34.101Z'),
    progress: [
      { courseId: course1Id, lessonId: lesson2Id, completedAt: new Date('2024-11-02T22:00:23.168Z') }
    ],
    quizResults: [
      { quizId: javaQuizId, completedAt: new Date('2024-11-11T22:52:01.372Z'), points: NumberLong(20) },
      { quizId: oopQuizId, completedAt: new Date('2024-11-14T19:42:34.085Z'), points: NumberLong(30) },
      { quizId: pythonQuizId, completedAt: new Date('2024-11-15T19:51:58.088Z'), points: NumberLong(50) },
      { quizId: linuxQuizId, completedAt: new Date('2024-11-19T10:06:45.443Z'), points: NumberLong(40) },
      { quizId: sqlQuizId, completedAt: new Date('2024-11-22T10:41:47.773Z'), points: NumberLong(40) }
    ],
    dailyTasks: [
      { taskId: task4, assignmentDate: new Date('2024-11-19'), completedAt: null, points: null },
      { taskId: task4, assignmentDate: new Date('2024-11-22'), completedAt: null, points: null },
      { taskId: task4, assignmentDate: new Date('2024-12-03'), completedAt: null, points: null },
      { taskId: task1, assignmentDate: new Date('2025-01-05'), completedAt: null, points: null },
      { taskId: task1, assignmentDate: new Date('2025-01-06'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(14),
    username: 'maciek',
    password: '$2a$10$uudBxOQvFbQvdEDWwtCLHeRQ5EHPho4URmVv3BshZGkkclVUdoIgO',
    email: 'maciek@gmail.com',
    firstName: 'maciek',
    surname: 'mackowski',
    phoneNumber: '123789654',
    age: 20,
    role: 'ADMIN',
    level: 16,
    title: 'ADVANCED',
    createdAt: new Date('2024-11-02T16:12:32.404Z'),
    updatedAt: new Date('2024-11-02T16:12:32.404Z'),
    progress: [
      { courseId: course1Id, lessonId: lesson1Id, completedAt: new Date('2024-11-22T15:32:24.377Z') },
      { courseId: course1Id, lessonId: lesson3Id, completedAt: new Date('2024-11-22T15:36:52.154Z') },
      { courseId: course2Id, lessonId: lesson6Id, completedAt: new Date('2024-11-22T15:52:10.305Z') },
      { courseId: course3Id, lessonId: lesson7Id, completedAt: new Date('2024-11-22T15:53:37.282Z') },
      { courseId: course3Id, lessonId: lesson9Id, completedAt: new Date('2024-11-22T15:58:28.168Z') },
      { courseId: course3Id, lessonId: lesson8Id, completedAt: new Date('2024-11-22T16:54:12.168Z') },
      { courseId: course1Id, lessonId: lesson2Id, completedAt: new Date('2024-11-22T17:58:11.195Z') }
    ],
    quizResults: [
      { quizId: oopQuizId, completedAt: new Date('2024-11-19T13:37:25.421Z'), points: NumberLong(30) },
      { quizId: javaQuizId, completedAt: new Date('2024-11-22T22:31:47.435Z'), points: NumberLong(30) },
      { quizId: sqlQuizId, completedAt: new Date('2024-11-26T19:08:33.865Z'), points: NumberLong(30) },
      { quizId: pythonQuizId, completedAt: new Date('2025-01-05T20:44:06.375Z'), points: NumberLong(20) },
      { quizId: linuxQuizId, completedAt: new Date('2025-01-11T15:17:15.451Z'), points: NumberLong(15) }
    ],
    dailyTasks: [
      { taskId: task5, assignmentDate: new Date('2024-11-22'), completedAt: new Date('2024-11-22T20:16:47.055Z'), points: NumberLong(60) },
      { taskId: task1, assignmentDate: new Date('2024-11-26'), completedAt: null, points: null },
      { taskId: task4, assignmentDate: new Date('2024-11-27'), completedAt: null, points: null },
      { taskId: task6, assignmentDate: new Date('2025-01-05'), completedAt: null, points: null },
      { taskId: task2, assignmentDate: new Date('2025-01-11'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(15),
    username: 'marek',
    password: '$2a$10$jpIdxAVIV/TVx4XeN.4hjOgetOgouJQvqGf7UknPFrx2cbXMjwtYm',
    email: '123@123.pl',
    firstName: 'marek',
    surname: 'marekowski',
    phoneNumber: '997998998',
    age: 12,
    role: 'USER',
    level: 0,
    title: 'ADVANCED',
    createdAt: new Date('2024-11-02T19:57:38.230Z'),
    updatedAt: new Date('2024-11-02T19:57:38.230Z'),
    progress: [],
    quizResults: [],
    dailyTasks: [
      { taskId: task2, assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(16),
    username: '123',
    password: '$2a$10$bKevhxfcNv.N2KvooqanMO3Y4co70kNcL5UxUHZV/1IGlbRu7A4Cy',
    email: '123@123123213123123312',
    firstName: '123123123123',
    surname: '123312123123132',
    phoneNumber: '123312132123123',
    age: 123,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-02T20:35:42.035Z'),
    updatedAt: new Date('2024-11-02T20:35:42.035Z'),
    progress: [],
    quizResults: [],
    dailyTasks: []
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(17),
    username: '123radek',
    password: '$2a$10$o25N5rourtptNxpYnEHXV.AkC.8TiS4KrL3AhPeuZrxjSEXzeHiZ6',
    email: '123@gmail.com',
    firstName: '123',
    surname: '123',
    phoneNumber: '123',
    age: 123,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-11T14:56:46.295Z'),
    updatedAt: new Date('2024-11-11T14:56:46.295Z'),
    progress: [
      { courseId: course1Id, lessonId: lesson2Id, completedAt: new Date('2024-11-11T14:59:32.817Z') }
    ],
    quizResults: [],
    dailyTasks: []
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(18),
    username: 'marcinek',
    password: '$2a$10$sUA1ujdEQELiO/SnXSByhOu06bvR/eNW48SL/G9J/JMN1wqJh9Jyy',
    email: 'marcinek@gmail.com',
    firstName: 'Marcin',
    surname: 'Marcinkowski',
    phoneNumber: '997998999',
    age: 12,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-11T15:01:25.372Z'),
    updatedAt: new Date('2024-11-11T15:01:25.372Z'),
    progress: [
      { courseId: course1Id, lessonId: lesson2Id, completedAt: new Date('2024-11-11T15:02:05.021Z') }
    ],
    quizResults: [],
    dailyTasks: []
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(19),
    username: 'marcinek2',
    password: '$2a$10$PxOwDITLrHhuRhaalGipzeeRzMqabuQcNzbizgKIJ9gQmux.WFEPO',
    email: 'marcinek2@gmail.com',
    firstName: 'marcinek',
    surname: 'marcinkowski',
    phoneNumber: '991223123',
    age: 12,
    role: 'USER',
    level: 2,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-11T15:02:34.002Z'),
    updatedAt: new Date('2024-11-11T15:02:34.002Z'),
    progress: [
      { courseId: course1Id, lessonId: lesson2Id, completedAt: new Date('2024-11-11T15:03:07.016Z') }
    ],
    quizResults: [
      { quizId: javaQuizId, completedAt: new Date('2024-11-13T09:24:36.821Z'), points: NumberLong(20) },
      { quizId: linuxQuizId, completedAt: new Date('2024-11-19T10:57:22.535Z'), points: NumberLong(30) }
    ],
    dailyTasks: [
      { taskId: task1, assignmentDate: new Date('2024-11-19'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(20),
    username: 'radek1',
    password: '$2a$10$LV9AQOyAWeueXWtZOn4YYeoNdMBm1Z4olkTwOShTcudqk/KyHBwu6',
    email: 'radek1@interia.pl',
    firstName: 'radek',
    surname: 'kowa',
    phoneNumber: '731890123',
    age: 18,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-19T11:48:35.514Z'),
    updatedAt: new Date('2024-11-19T11:48:35.514Z'),
    progress: [],
    quizResults: [
      { quizId: javaQuizId, completedAt: new Date('2024-11-19T13:53:34.886Z'), points: NumberLong(30) }
    ],
    dailyTasks: []
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(21),
    username: 'radek2',
    password: '$2a$10$H5pxe7XtiolBoGFZfENhZOmmVds7T86cUmiTaqeORZLxcg/lqkFya',
    email: 'user@user.pl',
    firstName: 'adas',
    surname: 'asdasdasd',
    phoneNumber: '123456789',
    age: 12,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-19T13:53:53.545Z'),
    updatedAt: new Date('2024-11-19T13:53:53.545Z'),
    progress: [],
    quizResults: [
      { quizId: javaQuizId, completedAt: new Date('2024-11-19T13:54:00.600Z'), points: NumberLong(30) }
    ],
    dailyTasks: []
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(22),
    username: 'test1',
    password: '$2a$10$0G7gGGshoL3XSvjPS.93geop9w9pcP1SnyXFvMWuC6N7QNlPJIyoS',
    email: 'test1costam@gmail.com',
    firstName: 'radek',
    surname: 'kowalczuk',
    phoneNumber: '123123123',
    age: 10,
    role: 'USER',
    level: 5,
    title: 'INTERMEDIATE',
    createdAt: new Date('2024-11-22T16:17:24.521Z'),
    updatedAt: new Date('2024-11-22T16:17:24.521Z'),
    progress: [
      { courseId: course1Id, lessonId: lesson1Id, completedAt: new Date('2024-11-22T16:18:07.150Z') },
      { courseId: course1Id, lessonId: lesson2Id, completedAt: new Date('2024-11-22T16:18:38.406Z') },
      { courseId: course1Id, lessonId: lesson3Id, completedAt: new Date('2024-11-22T16:19:09.464Z') },
      { courseId: course2Id, lessonId: lesson4Id, completedAt: new Date('2024-11-22T16:34:20.616Z') }
    ],
    quizResults: [],
    dailyTasks: [
      { taskId: task5, assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(23),
    username: '123123123123123123123123123123',
    password: '$2a$10$RTzAaeRhRm7AdMDtfbakeOBOXw7yCvsufzOXtCh1V6DGkLoojINIG',
    email: 'asd@asd',
    firstName: 'asd',
    surname: 'asd',
    phoneNumber: '123123231231',
    age: 122,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-22T17:04:35.933Z'),
    updatedAt: new Date('2024-11-22T17:04:35.933Z'),
    progress: [],
    quizResults: [],
    dailyTasks: [
      { taskId: task5, assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(24),
    username: 'qwerty',
    password: '$2a$10$M2vcOfyyxye3NCDEeLYERu8vWJGVT.OwpyDZBR9GzorU3Bj84s2ym',
    email: 'qwe@wqwe',
    firstName: 'qwe',
    surname: 'qwe',
    phoneNumber: 'qweq',
    age: 12,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2024-11-22T17:04:53.075Z'),
    updatedAt: new Date('2024-11-22T17:04:53.075Z'),
    progress: [],
    quizResults: [],
    dailyTasks: [
      { taskId: task2, assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: ObjectId(),
    userIdLegacy: NumberLong(25),
    username: '123123123231312123312312123',
    password: '$2a$10$F51E5EUnxY6hocCx/zSerekp4DwU/38ffMPxh4cjpJTXt3uIeH17a',
    email: '123@123123123123.pl',
    firstName: '123312',
    surname: '123123123',
    phoneNumber: '123213',
    age: 123,
    role: 'USER',
    level: 1,
    title: 'BEGINNER',
    createdAt: new Date('2025-01-05T20:29:56.474Z'),
    updatedAt: new Date('2025-01-05T20:29:56.474Z'),
    progress: [],
    quizResults: [],
    dailyTasks: []
  }
]);

// ==========================================
// COLLECTION: video_metadata
// ==========================================
print('Inserting video metadata...');

db.video_metadata.insertMany([
  {
    _id: ObjectId(),
    videoIdLegacy: NumberLong(1),
    fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: ObjectId(),
    videoIdLegacy: NumberLong(2),
    fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: ObjectId(),
    videoIdLegacy: NumberLong(3),
    fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: ObjectId(),
    videoIdLegacy: NumberLong(4),
    fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: ObjectId(),
    videoIdLegacy: NumberLong(5),
    fileName: '123.mp4',
    filePath: '/app/src/videos/123.mp4',
    fileSize: NumberLong(17684012)
  }
]);

print('=== MongoDB Full Migration Completed Successfully ===');
print('Migrated data:');
print('- 6 daily tasks');
print('- 5 quizzes with 22 questions total');
print('- 3 courses with 9 lessons');
print('- ALL 27 assignments with 54 solutions (27 Java + 27 Python)');
print('- 14 users with all their progress, quiz results, and daily task assignments');
print('- 5 video metadata entries');
print('All core data (courses, lessons, assignments, solutions, users, relationships) is complete.');
