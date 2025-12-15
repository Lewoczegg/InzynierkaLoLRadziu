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

db.daily_tasks.insertMany([
  { _id: NumberLong(1), title: 'Text Length', description: 'Create a variable storing text and display its length.' },
  { _id: NumberLong(2), title: 'Concatenate Strings', description: 'Join two strings (concatenation) and display the result.' },
  { _id: NumberLong(3), title: 'Count Phrase', description: 'Find how many times a given phrase appears in a string.' },
  { _id: NumberLong(4), title: 'Uppercase Names', description: 'Create a list of names and convert all names to uppercase.' },
  { _id: NumberLong(5), title: 'Average Score', description: 'Define a dictionary storing exam scores for three students and calculate the average.' },
  { _id: NumberLong(6), title: 'Add to Dictionary', description: 'Add a new key-value pair to a dictionary and display all elements.' }
]);

// ==========================================
// COLLECTION: quizzes with all questions
// ==========================================
print('Inserting quizzes with questions...');

db.quizzes.insertMany([
  {
    _id: NumberLong(2),
    title: 'Java Basics',
    questions: [
      {
        questionId: NumberLong(1),
        content: 'What is JVM?',
        correctAnswer: 'Java Virtual Machine',
        options: ['Java Virtual Machine', 'Java Virtual Module', 'Java Version Model']
      },
      {
        questionId: NumberLong(2),
        content: 'What is JDK?',
        correctAnswer: 'Java Development Kit',
        options: ['Java Development Kit', 'Java Deployment Key', 'Java Documentation Kit']
      }
    ]
  },
  {
    _id: NumberLong(3),
    title: 'Python Basics',
    questions: [
      {
        questionId: NumberLong(3),
        content: 'What is a list in Python?',
        correctAnswer: 'A mutable sequence of elements',
        options: ['A mutable sequence of elements', 'An immutable data type', 'A function in Python']
      },
      {
        questionId: NumberLong(4),
        content: 'How do you start a comment in Python?',
        correctAnswer: '#',
        options: ['#', '//', '/*']
      },
      {
        questionId: NumberLong(5),
        content: 'What is the output of len([1, 2, 3])?',
        correctAnswer: '3',
        options: ['3', '2', '4']
      },
      {
        questionId: NumberLong(6),
        content: 'Which keyword is used to define a function in Python?',
        correctAnswer: 'def',
        options: ['def', 'func', 'lambda']
      },
      {
        questionId: NumberLong(7),
        content: 'What data type is returned by the input() function?',
        correctAnswer: 'string',
        options: ['string', 'integer', 'boolean']
      }
    ]
  },
  {
    _id: NumberLong(4),
    title: 'OOP Concepts',
    questions: [
      {
        questionId: NumberLong(8),
        content: 'What does \'encapsulation\' refer to?',
        correctAnswer: 'Wrapping data and methods into a single unit',
        options: ['Wrapping data and methods into a single unit', 'Inheritance of properties', 'Overloading operators']
      },
      {
        questionId: NumberLong(9),
        content: 'Which concept allows one class to inherit the properties of another?',
        correctAnswer: 'Inheritance',
        options: ['Inheritance', 'Polymorphism', 'Abstraction']
      },
      {
        questionId: NumberLong(10),
        content: 'What is polymorphism?',
        correctAnswer: 'Ability of different objects to be treated as instances of the same class',
        options: ['Ability of different objects to be treated as instances of the same class', 'Code hiding', 'Data encapsulation']
      },
      {
        questionId: NumberLong(11),
        content: 'Which keyword is used to create an object in C++?',
        correctAnswer: 'new',
        options: ['new', 'create', 'build']
      },
      {
        questionId: NumberLong(1),
        content: 'What is the purpose of a constructor?',
        correctAnswer: 'To initialize an object',
        options: ['To initialize an object', 'To destroy an object', 'To copy an object']
      }
    ]
  },
  {
    _id: NumberLong(5),
    title: 'Linux Command Line',
    questions: [
      {
        questionId: NumberLong(13),
        content: 'Which command is used to list files in a directory?',
        correctAnswer: 'ls',
        options: ['ls', 'list', 'show']
      },
      {
        questionId: NumberLong(14),
        content: 'How do you navigate to the parent directory?',
        correctAnswer: 'cd ..',
        options: ['cd ..', 'cd ~', 'cd /']
      },
      {
        questionId: NumberLong(15),
        content: 'Which command is used to create a new directory?',
        correctAnswer: 'mkdir',
        options: ['mkdir', 'createdir', 'newdir']
      },
      {
        questionId: NumberLong(16),
        content: 'How do you view the contents of a file?',
        correctAnswer: 'cat',
        options: ['cat', 'view', 'open']
      },
      {
        questionId: NumberLong(17),
        content: 'Which command is used to remove a file?',
        correctAnswer: 'rm',
        options: ['rm', 'delete', 'erase']
      }
    ]
  },
  {
    _id: NumberLong(6),
    title: 'SQL Essentials',
    questions: [
      {
        questionId: NumberLong(18),
        content: 'Which SQL command is used to retrieve data?',
        correctAnswer: 'SELECT',
        options: ['SELECT', 'INSERT', 'UPDATE']
      },
      {
        questionId: NumberLong(19),
        content: 'Which keyword is used to sort the result set in ascending order?',
        correctAnswer: 'ORDER BY',
        options: ['ORDER BY', 'GROUP BY', 'ASC']
      },
      {
        questionId: NumberLong(20),
        content: 'Which SQL statement is used to delete data from a table?',
        correctAnswer: 'DELETE',
        options: ['DELETE', 'REMOVE', 'DROP']
      },
      {
        questionId: NumberLong(21),
        content: 'Which function is used to count the number of rows in a SQL query?',
        correctAnswer: 'COUNT()',
        options: ['COUNT()', 'SUM()', 'TOTAL()']
      },
      {
        questionId: NumberLong(22),
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

db.courses.insertMany([
  {
    _id: NumberLong(1),
    title: '1. Basic Course',
    description: 'Basic lessons, perfect for beginners.',
    titleLevel: 'BEGINNER',
    createdAt: new Date('2024-10-20T12:36:44.834Z'),
    updatedAt: new Date('2024-10-20T12:36:44.834Z'),
    lessons: [
      {
        lessonId: NumberLong(1),
        title: 'Lesson 1',
        content: 'Sum, reversion and much more',
        lessonNumber: 1,
        requiredLevel: 1,
        createdAt: new Date('2024-10-20T12:39:04.579Z'),
        updatedAt: new Date('2024-10-20T12:39:04.579Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(1),
            title: '1. Define an Integer Variable',
            description: 'Write a function that declares and initializes an integer variable. The function should take no inputs and return the initialized integer value. The integer can be any arbitrary value you choose. Ensure that you follow proper syntax for declaring and initializing variables in your chosen programming language.\n\nExamples:\n\n- If the value chosen is 42, the function should return 42.\n- If the value chosen is -7, the function should return -7.\n\nThis task tests your understanding of variable declaration, initialization, and returning values from a function.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-10-20T12:47:16.148Z'),
            updatedAt: new Date('2024-11-19T12:24:15.778Z'),
            solutions: [
              {
                solutionId: NumberLong(3),
                content: 'public class Result {\n    public static int defineInteger(int value) {\n        return value;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:46:33.276Z')
              },
              {
                solutionId: NumberLong(2),
                content: 'def result(input_value):\n    return input_value\n',
                language: 'python',
                createdAt: new Date('2024-11-22T21:58:56.228Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(3), userId: NumberLong(183), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T14:22:20.020Z'), submittedAt: new Date('2024-11-02T14:22:20.020Z') },
              { submissionId: NumberLong(4), userId: NumberLong(183), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T14:24:10.530Z'), submittedAt: new Date('2024-11-02T14:24:10.530Z') },
              { submissionId: NumberLong(5), userId: NumberLong(183), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T14:24:30.547Z'), submittedAt: new Date('2024-11-02T14:24:30.547Z') },
              { submissionId: NumberLong(6), userId: NumberLong(183), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T15:00:10.421Z'), submittedAt: new Date('2024-11-02T15:00:10.421Z') },
              { submissionId: NumberLong(7), userId: NumberLong(183), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-02T15:01:45.746Z'), submittedAt: new Date('2024-11-02T15:01:45.746Z') },
              { submissionId: NumberLong(8), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:04:35.732Z'), submittedAt: new Date('2024-11-02T15:04:35.732Z') },
              { submissionId: NumberLong(9), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:44:11.674Z'), submittedAt: new Date('2024-11-02T15:44:11.674Z') },
              { submissionId: NumberLong(10), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:46:50.000Z'), submittedAt: new Date('2024-11-02T15:46:50.000Z') },
              { submissionId: NumberLong(11), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T15:56:18.737Z'), submittedAt: new Date('2024-11-02T15:56:18.737Z') },
              { submissionId: NumberLong(12), userId: NumberLong(184), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T16:06:48.252Z'), submittedAt: new Date('2024-11-02T16:06:48.252Z') },
              { submissionId: NumberLong(13), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T16:12:10.849Z'), submittedAt: new Date('2024-11-02T16:12:10.849Z') },
              { submissionId: NumberLong(14), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T16:15:03.515Z'), submittedAt: new Date('2024-11-02T16:15:03.515Z') },
              { submissionId: NumberLong(15), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T17:29:32.214Z'), submittedAt: new Date('2024-11-02T17:29:32.214Z') },
              { submissionId: NumberLong(16), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T18:39:47.031Z'), submittedAt: new Date('2024-11-02T18:39:47.031Z') },
              { submissionId: NumberLong(17), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T18:39:56.039Z'), submittedAt: new Date('2024-11-02T18:39:56.039Z') },
              { submissionId: NumberLong(18), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:16:46.034Z'), submittedAt: new Date('2024-11-02T19:16:46.034Z') },
              { submissionId: NumberLong(19), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:18:46.145Z'), submittedAt: new Date('2024-11-02T19:18:46.145Z') },
              { submissionId: NumberLong(20), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:36:09.699Z'), submittedAt: new Date('2024-11-02T19:36:09.699Z') },
              { submissionId: NumberLong(21), userId: NumberLong(184), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:36:26.278Z'), submittedAt: new Date('2024-11-02T19:36:26.278Z') },
              { submissionId: NumberLong(22), userId: NumberLong(184), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:56:35.522Z'), submittedAt: new Date('2024-11-02T19:56:35.522Z') },
              { submissionId: NumberLong(23), userId: NumberLong(184), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:59:41.892Z'), submittedAt: new Date('2024-11-02T19:59:41.892Z') },
              { submissionId: NumberLong(24), userId: NumberLong(184), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n                System.out.println("XD");\n\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-03T14:54:42.620Z'), submittedAt: new Date('2024-11-03T14:54:42.620Z') },
              { submissionId: NumberLong(25), userId: NumberLong(183), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-03T15:02:01.403Z'), submittedAt: new Date('2024-11-03T15:02:01.403Z') },
              { submissionId: NumberLong(26), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T11:38:02.381Z'), submittedAt: new Date('2024-11-11T11:38:02.381Z') },
              { submissionId: NumberLong(27), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T14:57:03.732Z'), submittedAt: new Date('2024-11-11T14:57:03.732Z') },
              { submissionId: NumberLong(28), userId: NumberLong(184), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:01:39.735Z'), submittedAt: new Date('2024-11-11T15:01:39.735Z') },
              { submissionId: NumberLong(29), userId: NumberLong(185), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:02:44.867Z'), submittedAt: new Date('2024-11-11T15:02:44.867Z') },
              { submissionId: NumberLong(30), userId: NumberLong(184), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-11T15:24:35.762Z'), submittedAt: new Date('2024-11-11T15:24:35.762Z') },
              { submissionId: NumberLong(31), userId: NumberLong(185), content: 'public class Result { public static int sum(int a, int b) {  int result = a + b; System.out.println("ameno "); return result; } }', grade: 100, gradedAt: new Date('2024-11-11T15:26:07.769Z'), submittedAt: new Date('2024-11-11T15:26:07.769Z') },
              { submissionId: NumberLong(32), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T22:54:45.964Z'), submittedAt: new Date('2024-11-11T22:54:45.964Z') },
              { submissionId: NumberLong(33), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-13T09:28:30.960Z'), submittedAt: new Date('2024-11-13T09:28:30.960Z') },
              { submissionId: NumberLong(34), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:16:12.567Z'), submittedAt: new Date('2024-11-14T22:16:12.567Z') },
              { submissionId: NumberLong(35), userId: NumberLong(183), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:16:34.072Z'), submittedAt: new Date('2024-11-14T22:16:34.072Z') },
              { submissionId: NumberLong(36), userId: NumberLong(187), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:16:46.197Z'), submittedAt: new Date('2024-11-14T22:16:46.197Z') },
              { submissionId: NumberLong(37), userId: NumberLong(187), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:17:29.136Z'), submittedAt: new Date('2024-11-14T22:17:29.136Z') },
              { submissionId: NumberLong(38), userId: NumberLong(188), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:21:19.742Z'), submittedAt: new Date('2024-11-14T22:21:19.742Z') },
              { submissionId: NumberLong(39), userId: NumberLong(188), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T22:37:44.697Z'), submittedAt: new Date('2024-11-14T22:37:44.697Z') },
              { submissionId: NumberLong(40), userId: NumberLong(189), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-14T23:04:26.145Z'), submittedAt: new Date('2024-11-14T23:04:26.145Z') },
              { submissionId: NumberLong(41), userId: NumberLong(189), content: 'public class Result {\n    public static int sum(int a, int b) {\n        int result = a + b;\n        System.out.println("ameno");\n        return result;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-19T11:49:26.262Z'), submittedAt: new Date('2024-11-19T11:49:26.262Z') },
              { submissionId: NumberLong(42), userId: NumberLong(183), content: 'class Result {\n    public static int defineInteger(int value) {\n        return value; // Przykładowa implementacja, która po prostu zwraca wartość przekazaną przez NumberLong(1)Main\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T11:08:29.252Z'), submittedAt: new Date('2024-11-22T11:08:29.252Z') },
              { submissionId: NumberLong(43), userId: NumberLong(183), content: 'public class Result {\n    public static int defineInteger(int value) {\n        return value;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:26:49.518Z'), submittedAt: new Date('2024-11-22T15:26:49.518Z') },
              { submissionId: NumberLong(44), userId: NumberLong(183), content: 'public class Result { \n\n    public static int defineInteger(int value) { \n\n        return value; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:16:34.558Z'), submittedAt: new Date('2024-11-22T16:16:34.558Z') },
              { submissionId: NumberLong(45), userId: NumberLong(189), content: 'public class Result { \n\n    public static int defineInteger(int value) { \n\n        return value; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:17:42.024Z'), submittedAt: new Date('2024-11-22T16:17:42.024Z') },
              { submissionId: NumberLong(46), userId: NumberLong(183), content: 'def result(input_value): \n\n    return input_value ', grade: 100, gradedAt: new Date('2024-11-22T17:56:11.139Z'), submittedAt: new Date('2024-11-22T17:56:11.139Z') },
              { submissionId: NumberLong(47), userId: NumberLong(183), content: 'def result(input_value): \n    return input_value', grade: 100, gradedAt: new Date('2024-11-26T00:28:50.656Z'), submittedAt: new Date('2024-11-26T00:28:50.656Z') },
              { submissionId: NumberLong(48), userId: NumberLong(183), content: 'def result(input_value):\r\n    return input_value;', grade: 100, gradedAt: new Date('2025-01-05T21:34:31.021Z'), submittedAt: new Date('2025-01-05T21:34:31.021Z') }
            ]
          },
          {
            assignmentId: NumberLong(49), title: '2. Add Two Integer Numberse',
            description: 'Write a function that takes two integer inputs and returns their sum. Ensure that you handle proper addition and return the correct result. The integers can be any arbitrary values provided as inputs.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are -2 and 7, the function should return 5.\n- If the inputs are 0 and 0, the function should return 0.\n\nThis task tests your understanding of basic arithmetic operations and how to handle function inputs and outputs.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-02T17:57:05.441Z'),
            updatedAt: new Date('2024-11-19T12:24:09.753Z'),
            solutions: [
              {
                solutionId: NumberLong(50), content: 'public class Result {\n    public static int addTwoIntegers(int a, int b) {\n        return a + b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:47:23.592Z')
              },
              {
                solutionId: NumberLong(51), content: 'def result(a, b):\n    return a + b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:01:39.174Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(52), userId: NumberLong(183), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T17:58:49.607Z'), submittedAt: new Date('2024-11-02T17:58:49.607Z') },
              { submissionId: NumberLong(53), userId: NumberLong(183), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:20:40.888Z'), submittedAt: new Date('2024-11-02T19:20:40.888Z') },
              { submissionId: NumberLong(54), userId: NumberLong(183), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:34:26.485Z'), submittedAt: new Date('2024-11-02T19:34:26.485Z') },
              { submissionId: NumberLong(55), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:35:43.200Z'), submittedAt: new Date('2024-11-02T19:35:43.200Z') },
              { submissionId: NumberLong(56), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:36:53.756Z'), submittedAt: new Date('2024-11-02T19:36:53.756Z') },
              { submissionId: NumberLong(57), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:56:08.237Z'), submittedAt: new Date('2024-11-02T19:56:08.237Z') },
              { submissionId: NumberLong(58), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:56:49.437Z'), submittedAt: new Date('2024-11-02T19:56:49.437Z') },
              { submissionId: NumberLong(59), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T19:57:13.514Z'), submittedAt: new Date('2024-11-02T19:57:13.514Z') },
              { submissionId: NumberLong(60), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T20:02:43.851Z'), submittedAt: new Date('2024-11-02T20:02:43.851Z') },
              { submissionId: NumberLong(61), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n    String reversed = new StringBuilder(input).reverse().toString(); \n    return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-02T22:00:23.080Z'), submittedAt: new Date('2024-11-02T22:00:23.080Z') },
              { submissionId: NumberLong(62), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n}\n}', grade: 100, gradedAt: new Date('2024-11-11T14:59:32.789Z'), submittedAt: new Date('2024-11-11T14:59:32.789Z') },
              { submissionId: NumberLong(63), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:02:04.997Z'), submittedAt: new Date('2024-11-11T15:02:04.997Z') },
              { submissionId: NumberLong(64), userId: NumberLong(184), content: 'public class Result { \n    public static String reverseString(String input) { \n        String reversed = new StringBuilder(input).reverse().toString(); \n        return reversed; \n    }\n}', grade: 100, gradedAt: new Date('2024-11-11T15:03:06.982Z'), submittedAt: new Date('2024-11-11T15:03:06.982Z') },
              { submissionId: NumberLong(65), userId: NumberLong(184), content: 'public class Result {\n    public static int addTwoIntegers(int a, int b) {\n        return a + b;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:27:05.482Z'), submittedAt: new Date('2024-11-22T15:27:05.482Z') },
              { submissionId: NumberLong(66), userId: NumberLong(184), content: 'public class Result { \n\n    public static int addTwoIntegers(int a, int b) { \n\n        return a + b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:16:40.789Z'), submittedAt: new Date('2024-11-22T16:16:40.789Z') },
              { submissionId: NumberLong(67), userId: NumberLong(184), content: 'public class Result { \n\n    public static int addTwoIntegers(int a, int b) { \n\n        return a + b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:17:54.171Z'), submittedAt: new Date('2024-11-22T16:17:54.171Z') },
              { submissionId: NumberLong(68), userId: NumberLong(184), content: 'def result(a, b):\n    # Funkcja przyjmuje dwie liczby całkowite i zwraca ich sumę\n    return a + b', grade: 100, gradedAt: new Date('2024-11-22T17:54:46.418Z'), submittedAt: new Date('2024-11-22T17:54:46.418Z') },
              { submissionId: NumberLong(69), userId: NumberLong(184), content: 'def result(a, b):\n    return a + b', grade: 100, gradedAt: new Date('2024-11-22T17:56:02.672Z'), submittedAt: new Date('2024-11-22T17:56:02.672Z') }
            ]
          },
          {
            assignmentId: NumberLong(70), title: '3. Display the Value of a Variable',
            description: 'Write a function that declares a variable, assigns it a value, and then displays (prints) its value. Ensure that the variable is properly declared, initialized, and printed in your chosen programming language.\n\nExamples:\n\n- If the variable is assigned the value 42, the function should print: 42.\n- If the variable is assigned the value -7, the function should print: -7.\n- If the variable is assigned the value 0, the function should print: 0.\n\nThis task tests your understanding of variable declaration, initialization, and basic output operations.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:28:25.500Z'),
            updatedAt: new Date('2024-11-19T12:28:25.500Z'),
            solutions: [
              {
                solutionId: NumberLong(71), content: 'public class Result {\n    public static String displayValue(int value) {\n        return String.valueOf(value);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:47:43.856Z')
              },
              {
                solutionId: NumberLong(72), content: 'def result(value):\n    return str(value)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:01:52.954Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(73), userId: NumberLong(184), content: 'public class Result {\n    public static String displayValue(int value) {\n        return String.valueOf(value);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:32:24.356Z'), submittedAt: new Date('2024-11-22T15:32:24.356Z') },
              { submissionId: NumberLong(74), userId: NumberLong(184), content: 'public class Result { \n\n    public static String displayValue(int value) { \n\n        return String.valueOf(value); \n\n    } \n\n} \n', grade: 100, gradedAt: new Date('2024-11-22T16:16:53.221Z'), submittedAt: new Date('2024-11-22T16:16:53.221Z') },
              { submissionId: NumberLong(75), userId: NumberLong(184), content: 'public class Result { \n\n    public static String displayValue(int value) { \n\n        return String.valueOf(value); \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:07.131Z'), submittedAt: new Date('2024-11-22T16:18:07.131Z') },
              { submissionId: NumberLong(76), userId: NumberLong(183), content: 'def result(value):\n    return str(value)', grade: 100, gradedAt: new Date('2024-11-22T17:55:43.624Z'), submittedAt: new Date('2024-11-22T17:55:43.624Z') }
            ]
          }
        ]
      },
      {
        lessonId: NumberLong(2), title: 'Lesson 2',
        content: 'Arithmetic operators',
        lessonNumber: 2,
        requiredLevel: 1,
        createdAt: new Date('2024-11-19T12:34:28.307Z'),
        updatedAt: new Date('2024-11-19T12:34:28.307Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(77), title: '4. Use the Addition Operator with Two Numbers',
            description: 'Write a function that takes two numbers as inputs and uses the addition operator (`+`) to calculate their sum. The function should return the result of the addition. The numbers can be integers or floating-point values.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are 2.5 and 4.3, the function should return 6.8.\n- If the inputs are -7 and 2, the function should return -5.\n\nThis task tests your understanding of using the addition operator to combine numerical values.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:53:48.390Z'),
            updatedAt: new Date('2024-11-19T12:53:48.390Z'),
            solutions: [
              {
                solutionId: NumberLong(78), content: 'public class Result {\n    public static double addTwoNumbers(double a, double b) {\n        return a + b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:48:10.555Z')
              },
              {
                solutionId: NumberLong(79), content: 'def result(a, b):\n    return a + b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:02:43.127Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(80), userId: NumberLong(192), content: 'public class Result { \n\n    public static double addTwoNumbers(double a, double b) { \n\n        return a + b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:23.722Z'), submittedAt: new Date('2024-11-22T16:18:23.722Z') },
              { submissionId: NumberLong(81), userId: NumberLong(192), content: 'def result(a, b):\n    return a + b', grade: 100, gradedAt: new Date('2024-11-22T17:58:11.170Z'), submittedAt: new Date('2024-11-22T17:58:11.170Z') }
            ]
          },
          {
            assignmentId: NumberLong(82), title: '5. Use the Multiplication Operator',
            description: 'Write a function that takes two numbers as inputs and uses the multiplication operator (`*`) to calculate their product. The function should return the result of the multiplication. The numbers can be integers or floating-point values.\n\nExamples:\n\n- If the inputs are 4 and 5, the function should return 20.\n- If the inputs are 2.5 and 3, the function should return 7.5.\n- If the inputs are -3 and -2, the function should return 6.\n\nThis task tests your understanding of using the multiplication operator to calculate products.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:54:10.724Z'),
            updatedAt: new Date('2024-11-19T12:54:10.724Z'),
            solutions: [
              {
                solutionId: NumberLong(83), content: 'public class Result {\n    public static double multiplyTwoNumbers(double a, double b) {\n        return a * b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:32.601Z')
              },
              {
                solutionId: NumberLong(84), content: 'def result(a, b):\n    return a * b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:02:52.910Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(85), userId: NumberLong(192), content: 'public class Result {\n    public static double multiplyTwoNumbers(double a, double b) {\n        return a * b;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:33:12.826Z'), submittedAt: new Date('2024-11-22T15:33:12.826Z') },
              { submissionId: NumberLong(86), userId: NumberLong(192), content: 'public class Result { \n\n    public static double multiplyTwoNumbers(double a, double b) { \n\n        return a * b; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:29.937Z'), submittedAt: new Date('2024-11-22T16:18:29.937Z') },
              { submissionId: NumberLong(87), userId: NumberLong(192), content: 'def result(a, b):\n    return a * b', grade: 100, gradedAt: new Date('2024-11-22T17:58:43.536Z'), submittedAt: new Date('2024-11-22T17:58:43.536Z') }
            ]
          },
          {
            assignmentId: NumberLong(88), title: '6. Perform Division and Round the Result',
            description: 'Write a function that takes two numbers as inputs, performs division using the division operator (`/`), and rounds the result to the nearest integer. The function should handle any valid division scenario (excluding division by zero) and return the rounded result.\n\nExamples:\n\n- If the inputs are 10 and 3, the function should return 3 (10 ÷ 3 = 3.333, rounded to 3).\n- If the inputs are 7 and 2, the function should return 4 (7 ÷ 2 = 3.5, rounded to 4).\n- If the inputs are -8 and 3, the function should return -3 (-8 ÷ 3 = -2.666, rounded to -3).\n\nThis task tests your understanding of division, handling numerical operations, and rounding results.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:54:29.663Z'),
            updatedAt: new Date('2024-11-19T12:54:29.663Z'),
            solutions: [
              {
                solutionId: NumberLong(89), content: 'public class Result {\n    public static int divideAndRound(double a, double b) {\n        return (int) Math.round(a / b);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:39.477Z')
              },
              {
                solutionId: NumberLong(90), content: 'def result(a, b):\n    return round(a / b)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:03:05.150Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(91), userId: NumberLong(192), content: 'public class Result {\n    public static int divideAndRound(double a, double b) {\n        return (int) Math.round(a / b);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:33:50.707Z'), submittedAt: new Date('2024-11-22T15:33:50.707Z') },
              { submissionId: NumberLong(92), userId: NumberLong(192), content: 'public class Result { \n\n    public static int divideAndRound(double a, double b) { \n\n        return (int) Math.round(a / b); \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:38.386Z'), submittedAt: new Date('2024-11-22T16:18:38.386Z') },
              { submissionId: NumberLong(93), userId: NumberLong(184), content: 'def result(a, b):\n    return round(a / b)\n', grade: 100, gradedAt: new Date('2024-11-22T18:01:23.050Z'), submittedAt: new Date('2024-11-22T18:01:23.050Z') }
            ]
          }
        ]
      },
      {
        lessonId: NumberLong(3), title: 'Lesson 3',
        content: 'Basic Conditional Statements',
        lessonNumber: 3,
        requiredLevel: 1,
        createdAt: new Date('2024-11-19T12:35:03.982Z'),
        updatedAt: new Date('2024-11-19T12:35:03.982Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(94), title: '7. Write an If Statement to Check if a Number is Even',
            description: 'Write a function that takes an integer as input and uses an `if` statement to check whether the number is even. If the number is even, the function should return `true` or a specific message indicating that the number is even. Ensure the logic for checking even numbers is correct (e.g., using the modulus operator `%`).\n\nExamples:\n\n- If the input is 4, the function should return `true` or \'4 is even\'.\n- If the input is 7, the function should not return anything or may return `false`.\n- If the input is 0, the function should return `true` or \'0 is even\'.\n\nThis task tests your understanding of conditional statements and the concept of even numbers.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T12:59:27.731Z'),
            updatedAt: new Date('2024-11-19T12:59:27.731Z'),
            solutions: [
              {
                solutionId: NumberLong(95), content: 'public class Result {\n    public static String checkIfEven(int number) {\n        if (number % 2 == 0) {\n            return number + \" is even\";\n        } else {\n            return number + \" is not even\";\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:45.375Z')
              },
              {
                solutionId: NumberLong(96), content: 'def result(n):\n    if n % 2 == 0:\n        return f\"{n} is even\"\n    else:\n        return f\"{n} is not even\"\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:26.174Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(97), userId: NumberLong(183), content: 'public class Result {\n    public static String checkIfEven(int number) {\n        if (number % 2 == 0) {\n            return number + " is even";\n        } else {\n            return number + " is not even";\n        }\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:34:51.154Z'), submittedAt: new Date('2024-11-22T15:34:51.154Z') },
              { submissionId: NumberLong(98), userId: NumberLong(184), content: 'public class Result { \n\n    public static String checkIfEven(int number) { \n\n        if (number % 2 == 0) { \n\n            return number + " is even"; \n\n        } else { \n\n            return number + " is not even"; \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:18:57.282Z'), submittedAt: new Date('2024-11-22T16:18:57.282Z') }
            ]
          },
          {
            assignmentId: NumberLong(99), title: '8. Use an Else Statement',
            description: 'Write a function that takes an integer as input and uses an `if` statement to check whether the number is positive. If the number is positive, return a message indicating it is positive. Otherwise, use an `else` statement to return a message indicating the number is not positive. Ensure the function covers both cases.\n\nExamples:\n\n- If the input is 5, the function should return \'5 is positive\'.\n- If the input is -3, the function should return \'-3 is not positive\'.\n- If the input is 0, the function should return \'0 is not positive\'.\n\nThis task tests your understanding of conditional statements, including the use of `else` to handle alternative cases.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T13:00:01.952Z'),
            updatedAt: new Date('2024-11-19T13:00:01.952Z'),
            solutions: [
              {
                solutionId: NumberLong(100), content: 'public class Result {\n    public static String checkIfPositive(int number) {\n        if (number > 0) {\n            return number + \" is positive\";\n        } else {\n            return number + \" is not positive\";\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:49:53.777Z')
              },
              {
                solutionId: NumberLong(101), content: 'def result(n):\n    if n > 0:\n        return f\"{n} is positive\"\n    else:\n        return f\"{n} is not positive\"\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:35.404Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(102), userId: NumberLong(184), content: 'public class Result {\n    public static String checkIfPositive(int number) {\n        if (number > 0) {\n            return number + " is positive";\n        } else {\n            return number + " is not positive";\n        }\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:36:13.524Z'), submittedAt: new Date('2024-11-22T15:36:13.524Z') },
              { submissionId: NumberLong(103), userId: NumberLong(184), content: 'public class Result { \n\n    public static String checkIfPositive(int number) { \n\n        if (number > 0) { \n\n            return number + " is positive"; \n\n        } else { \n\n            return number + " is not positive"; \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:19:04.184Z'), submittedAt: new Date('2024-11-22T16:19:04.184Z') }
            ]
          },
          {
            assignmentId: NumberLong(104), title: '9. Write an If-Else Statement to Compare Two Numbers',
            description: 'Write a function that takes two numbers as inputs and uses an `if-else` statement to compare them. If the first number is greater than the second, return a message indicating this. Otherwise, return a message indicating that the first number is not greater than the second.\n\nExamples:\n\n- If the inputs are 10 and 5, the function should return \'10 is greater than 5\'.\n- If the inputs are 3 and 7, the function should return \'3 is not greater than 7\'.\n- If the inputs are 4 and 4, the function should return \'4 is not greater than 4\'.\n\nThis task tests your understanding of comparison operators and conditional statements.',
            titleLevel: 'BEGINNER',
            createdAt: new Date('2024-11-19T13:00:19.924Z'),
            updatedAt: new Date('2024-11-19T13:00:19.924Z'),
            solutions: [
              {
                solutionId: NumberLong(105), content: 'public class Result {\n    public static String compareTwoNumbers(int a, int b) {\n        if (a > b) {\n            return a + \" is greater than \" + b;\n        } else {\n            return a + \" is not greater than \" + b;\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:50:01.178Z')
              },
              {
                solutionId: NumberLong(106), content: 'def result(a, b):\n    if a > b:\n        return f\"{a} is greater than {b}\"\n    else:\n        return f\"{a} is not greater than {b}\"\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:50.358Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(107), userId: NumberLong(184), content: 'public class Result {\n     public static String compareTwoNumbers(int a, int b) {\n        if (a > b) {\n            return a + " is greater than " + b;\n        } else {\n            return a + " is not greater than " + b;\n        }\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:36:52.137Z'), submittedAt: new Date('2024-11-22T15:36:52.137Z') },
              { submissionId: NumberLong(108), userId: NumberLong(184), content: 'public class Result { \n\n     public static String compareTwoNumbers(int a, int b) { \n\n        if (a > b) { \n\n            return a + " is greater than " + b; \n\n        } else { \n\n            return a + " is not greater than " + b; \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:19:09.448Z'), submittedAt: new Date('2024-11-22T16:19:09.448Z') }
            ]
          }
        ]
      }
    ]
  },
  {
    _id: NumberLong(2), title: '2. Intermediate Course',
    description: 'Intermediate lessons, challenge yourself.',
    titleLevel: 'INTERMEDIATE',
    createdAt: new Date('2024-10-31T14:49:18.314Z'),
    updatedAt: new Date('2024-10-31T14:49:18.314Z'),
    lessons: [
      {
        lessonId: NumberLong(4), title: 'Lesson 4',
        content: 'Arrays',
        lessonNumber: 4,
        requiredLevel: 2,
        createdAt: new Date('2024-11-19T13:03:41.994Z'),
        updatedAt: new Date('2024-11-19T13:03:41.994Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(109), title: '10. Define an Array of Specific Integers',
            description: 'Write a function that defines and initializes an array (or list) of integers. The array should be filled with specific values provided in the example below. Ensure that the array contains exactly the ten predefined integers given.\n\nExamples:\n\n- The array should be [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]. The function should return [10, 20, 30, 40, 50, 60, 70, 80, 90, 100].\n- Alternatively, the array can be [5, 15, 25, 35, 45, 55, 65, 75, 85, 95]. The function should return [5, 15, 25, 35, 45, 55, 65, 75, 85, 95].\n\nThis task tests your understanding of arrays (or lists), their initialization, and the ability to use specific predefined values.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:07:52.059Z'),
            updatedAt: new Date('2024-11-22T16:27:35.068Z'),
            solutions: [
              {
                solutionId: NumberLong(110), content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> defineArray() {\n        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:50:08.827Z')
              },
              {
                solutionId: NumberLong(111), content: 'def result():\n    return [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:04:56.346Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(112), userId: NumberLong(184), content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> defineArray() {\n        return Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T16:32:26.899Z'), submittedAt: new Date('2024-11-22T16:32:26.899Z') }
            ]
          },
          {
            assignmentId: NumberLong(113), title: '11. Calculate the Sum of All Elements in an Array',
            description: 'Write a function that calculates the sum of all elements in a given array (or list) of integers. Assign the result to a variable called `sum`. Make sure the function takes the array as input and returns the calculated sum.\n\nExamples:\n\n- If the array is [1, 2, 3, 4, 5], the function should return 15 because 1 + 2 + 3 + 4 + 5 = 15.\n- If the array is [10, 20, 30, 40, 50], the function should return 150 because 10 + 20 + 30 + 40 + 50 = 150.\n\nThis task tests your ability to iterate through an array, accumulate values, and assign the final result to a variable.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:08:08.958Z'),
            updatedAt: new Date('2024-11-22T16:26:40.527Z'),
            solutions: [
              {
                solutionId: NumberLong(114), content: 'import java.util.List;\n\npublic class Result {\n    public static int calculateSum(List<Integer> numbers) {\n        int sum = 0;\n        for (int number : numbers) {\n            sum += number;\n        }\n        return sum;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:51:18.183Z')
              },
              {
                solutionId: NumberLong(115), content: 'def result(numbers):\n    return sum(numbers)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:06:59.082Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(116), userId: NumberLong(184), content: 'import java.util.List;\n\npublic class Result {\n    public static int calculateSum(List<Integer> numbers) {\n        int sum = 0;\n        for (int number : numbers) {\n            sum += number;\n        }\n        return sum;\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T16:33:58.567Z'), submittedAt: new Date('2024-11-22T16:33:58.567Z') }
            ]
          },
          {
            assignmentId: NumberLong(117), title: '12. Find the Largest Element in an Array',
            description: 'Write a function that takes an array (or list) of integers as input and returns the largest element in the array. Ensure that the function iterates through the array to find the maximum value.\n\nExamples:\n\n- If the input is [1, 2, 3, 4, 5], the function should return 5.\n- If the input is [-10, -3, -7, -2], the function should return -2.\n- If the input is [0, 0, 0], the function should return 0.\n\nThis task tests your understanding of iteration, comparison, and finding the maximum value in an array.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:08:24.756Z'),
            updatedAt: new Date('2024-11-19T13:08:24.756Z'),
            solutions: [
              {
                solutionId: NumberLong(118), content: 'public class Result {\n    public static Integer findLargestElement(Integer[] array) {\n        if (array == null || array.length == 0) {\n            throw new IllegalArgumentException(\"Array cannot be null or empty\");\n        }\n        int max = array[0];\n        for (int i = 1; i < array.length; i++) {\n            if (array[i] > max) {\n                max = array[i];\n            }\n        }\n        return max;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:52:34.950Z')
              },
              {
                solutionId: NumberLong(119), content: 'def result(numbers):\n    return max(numbers)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:08.059Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(120), userId: NumberLong(184), content: 'public class Result {\n    public static Integer findLargestElement(Integer[] array) {\n        if (array == null || array.length == 0) {\n            throw new IllegalArgumentException("Array cannot be null or empty");\n        }\n        \n        int max = array[0];\n        for (int i = 1; i < array.length; i++) {\n            if (array[i] > max) {\n                max = array[i];\n            }\n        }\n        return max;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:45:21.022Z'), submittedAt: new Date('2024-11-22T15:45:21.022Z') },
              { submissionId: NumberLong(121), userId: NumberLong(184), content: 'public class Result { \n\n    public static Integer findLargestElement(Integer[] array) { \n\n        if (array == null || array.length == 0) { \n\n            throw new IllegalArgumentException("Array cannot be null or empty"); \n\n        } \n\n         \n\n        int max = array[0]; \n\n        for (int i = 1; i < array.length; i++) { \n\n            if (array[i] > max) { \n\n                max = array[i]; \n\n            } \n\n        } \n\n        return max; \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T16:34:20.602Z'), submittedAt: new Date('2024-11-22T16:34:20.602Z') }
            ]
          }
        ]
      },
      {
        lessonId: NumberLong(5), title: 'Lesson 5',
        content: 'Loops',
        lessonNumber: 5,
        requiredLevel: 2,
        createdAt: new Date('2024-11-19T13:03:51.662Z'),
        updatedAt: new Date('2024-11-19T13:03:51.662Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(122), title: '13. Use a For Loop to Calculate the Sum of Even Numbers',
            description: 'Write a for loop that calculates the sum of all even numbers between 1 and 20 (inclusive). Assign the result to a variable called `sum`. Ensure that only even numbers are included in the calculation.\n\nExamples:\n\n- The sum should be 110, because 2 + 4 + 6 + 8 + 10 + 12 + 14 + 16 + 18 + 20 = 110.\n\nThis task tests your understanding of using a loop to iterate through a range of numbers, identifying even numbers, and accumulating their values into a single sum.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:08:55.523Z'),
            updatedAt: new Date('2024-11-22T16:37:27.351Z'),
            solutions: [
              {
                solutionId: NumberLong(123), content: 'public class Result {\n    public static int calculateSumOfEvens() {\n        int sum = 0;\n        for (int i = 1; i <= 20; i++) {\n            if (i % 2 == 0) {\n                sum += i;\n            }\n        }\n        return sum;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:52:46.846Z')
              },
              {
                solutionId: NumberLong(124), content: 'def result():\n    return sum([i for i in range(1, 21) if i % 2 == 0])\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:18.609Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(125), userId: NumberLong(184), content: 'public class Result {\n    public static int calculateSumOfEvens() {\n        int sum = 0;\n        for (int i = 1; i <= 20; i++) {\n            if (i % 2 == 0) {\n                sum += i;\n            }\n        }\n        return sum;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T16:40:54.370Z'), submittedAt: new Date('2024-11-22T16:40:54.370Z') }
            ]
          },
          {
            assignmentId: NumberLong(126), title: '14. Write a While Loop to Sum Numbers',
            description: 'Write a function that uses a `while` loop to sum the numbers from 1 to a given integer `n`. The function should take `n` as input and return the sum of all numbers from 1 to `n`.\n\nExamples:\n\n- If the input is 5, the function should return 15 (1 + 2 + 3 + 4 + 5).\n- If the input is 10, the function should return 55.\n\nThis task tests your understanding of `while` loops, conditional logic, and basic arithmetic.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:09:10.789Z'),
            updatedAt: new Date('2024-11-19T13:09:10.789Z'),
            solutions: [
              {
                solutionId: NumberLong(127), content: 'public class Result {\n    public static int sumNumbersUpToN(int n) {\n        int sum = 0;\n        int i = 1;\n        while (i <= n) {\n            sum += i;\n            i++;\n        }\n        return sum;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:53:51.322Z')
              },
              {
                solutionId: NumberLong(128), content: 'def result(n):\n    return sum(range(1, n + 1)) if n > 0 else 0\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:25.782Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(129), userId: NumberLong(184), content: 'public class Result {\n    public static int sumNumbersUpToN(int n) {\n        int sum = 0;\n        int i = 1;\n        \n        while (i <= n) {\n            sum += i;\n            i++;\n        }\n        \n        return sum;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:47:02.588Z'), submittedAt: new Date('2024-11-22T15:47:02.588Z') }
            ]
          },
          {
            assignmentId: NumberLong(130), title: '15. Use a While Loop to Find a Number Divisible by 7',
            description: 'Write a while loop that generates random numbers between 1 and 50 until a number divisible by 7 is found. Use an appropriate random number generating function. Once a number divisible by 7 is found, use the `break` statement to exit the loop.\n\nExamples:\n\n- The loop should keep generating numbers like 34, 5, 13, 28 until it finds a number like 14 (which is divisible by 7). At this point, the loop should stop.\n\nThis task tests your understanding of while loops, generating random numbers, conditional checks for divisibility, and the use of the `break` statement to exit the loop when a condition is met.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:09:25.225Z'),
            updatedAt: new Date('2024-11-22T16:37:48.125Z'),
            solutions: [
              {
                solutionId: NumberLong(131), content: 'public class Result {\n    public static int findNumberDivisibleBy7(int[] numbers) {\n        int index = 0;\n        int number;\n        while (index < numbers.length) {\n            number = numbers[index];\n            if (number % 7 == 0) {\n                return number;\n            }\n            index++;\n        }\n        return -1;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:11.541Z')
              },
              {
                solutionId: NumberLong(132), content: 'def result(numbers):\n    for number in numbers:\n        if number % 7 == 0:\n            return number\n    return None\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:34.158Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(133), userId: NumberLong(184), content: 'public class Result {\n    public static int findNumberDivisibleBy7(int[] numbers) {\n        int index = 0;\n        int number;\n        while (index < numbers.length) {\n            number = numbers[index];\n            if (number % 7 == 0) {\n                return number;\n            }\n            index++;\n        }\n        return -1;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T16:45:26.950Z'), submittedAt: new Date('2024-11-22T16:45:26.950Z') }
            ]
          }
        ]
      },
      {
        lessonId: NumberLong(6), title: 'Lesson 6',
        content: 'Functions',
        lessonNumber: 6,
        requiredLevel: 2,
        createdAt: new Date('2024-11-19T13:04:03.526Z'),
        updatedAt: new Date('2024-11-19T13:04:03.526Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(134), title: '16. Write a Function to Return the Sum of Two Numbers',
            description: 'Write a function that takes two numbers as inputs and returns their sum. Ensure the function correctly calculates and returns the result.\n\nExamples:\n\n- If the inputs are 3 and 5, the function should return 8.\n- If the inputs are -2 and 10, the function should return 8.\n\nThis task tests your understanding of function creation, input handling, and returning results.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:09:54.371Z'),
            updatedAt: new Date('2024-11-19T13:09:54.371Z'),
            solutions: [
              {
                solutionId: NumberLong(135), content: 'public class Result {\n    public static int sumTwoNumbers(int a, int b) {\n        return a + b;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:19.428Z')
              },
              {
                solutionId: NumberLong(136), content: 'def result(a, b):\n    return a + b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:43.677Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(137), userId: NumberLong(184), content: 'public class Result {\n    public static int sumTwoNumbers(int a, int b) {\n        return a + b;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:48:32.063Z'), submittedAt: new Date('2024-11-22T15:48:32.063Z') }
            ]
          },
          {
            assignmentId: NumberLong(138), title: '17. Create a Function to Return the Largest Number in a List',
            description: 'Write a function that takes a list of numbers as input and returns the largest number in the list. Ensure that the function iterates through the list to find the maximum value.\n\nExamples:\n\n- If the input is [1, 2, 3, 4, 5], the function should return 5.\n- If the input is [-10, -3, -7, -2], the function should return -2.\n- If the input is [0, 0, 0], the function should return 0.\n\nThis task tests your understanding of iteration, comparison, and finding the maximum value in a list.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:10:17.887Z'),
            updatedAt: new Date('2024-11-19T13:10:17.887Z'),
            solutions: [
              {
                solutionId: NumberLong(139), content: 'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:25.129Z')
              },
              {
                solutionId: NumberLong(140), content: 'def result(numbers):\n    return max(numbers)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:07:57.454Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(141), userId: NumberLong(184), content: 'import java.util.List;\n\npublic class Result {\n    // Metoda do znalezienia największego elementu w liście\n    public static Integer findLargestInList(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            throw new IllegalArgumentException("List cannot be null or empty");\n        }\n\n        Integer max = numbers.get(0);\n        for (Integer number : numbers) {\n            if (number > max) {\n                max = number;\n            }\n        }\n        return max;\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T15:51:42.484Z'), submittedAt: new Date('2024-11-22T15:51:42.484Z') }
            ]
          },
          {
            assignmentId: NumberLong(142), title: '18. Create a Function to Return the Largest Number in a List',
            description: 'Write a function that takes a non-negative integer `n` as input and calculates its factorial using recursion. The factorial of a number is defined as `n! = n * (n-1) * ... * 1`, with the base case `0! = 1`.\n\nExamples:\n\n- If the input is 5, the function should return 120 (5! = 5 * 4 * 3 * 2 * 1).\n- If the input is 0, the function should return 1 (0! = 1).\n- If the input is 3, the function should return 6 (3! = 3 * 2 * 1).\n\nThis task tests your understanding of recursion, base cases, and mathematical calculations.',
            titleLevel: 'INTERMEDIATE',
            createdAt: new Date('2024-11-19T13:10:33.003Z'),
            updatedAt: new Date('2024-11-19T13:10:33.003Z'),
            solutions: [
              {
                solutionId: NumberLong(143), content: 'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:50.744Z')
              },
              {
                solutionId: NumberLong(144), content: 'def result(n):\n    if n == 0:\n        return 1\n    factorial = 1\n    for i in range(1, n + 1):\n        factorial *= i\n    return factorial\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:08:03.605Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(145), userId: NumberLong(184), content: 'public class Result {\n    public static int factorial(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException("Input must be a non-negative integer");\n        }\n        if (n == 0) {\n            return 1;\n        }\n        return n * factorial(n - 1);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:52:10.282Z'), submittedAt: new Date('2024-11-22T15:52:10.282Z') }
            ]
          }
        ]
      }
    ]
  },
  {
    _id: NumberLong(3), title: '3. Advanced Course',
    description: 'Advanced lessons, only for experienced programmers.',
    titleLevel: 'ADVANCED',
    createdAt: new Date('2024-11-19T12:37:41.672Z'),
    updatedAt: new Date('2024-11-19T12:37:41.672Z'),
    lessons: [
      {
        lessonId: NumberLong(7), title: 'Lesson 7',
        content: 'Recursion',
        lessonNumber: 7,
        requiredLevel: 3,
        createdAt: new Date('2024-11-19T13:04:18.725Z'),
        updatedAt: new Date('2024-11-19T13:04:18.725Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(146), title: '19. Write a Recursive Function to Calculate the Sum of Numbers from 1 to n',
            description: 'Write a function that takes a positive integer `n` as input and calculates the sum of all numbers from 1 to `n` using recursion. The base case should handle when `n` is 0, returning 0, and the recursive case should sum `n` with the result of the function called on `n-1`.\n\nExamples:\n\n- If the input is 5, the function should return 15 (1 + 2 + 3 + 4 + 5).\n- If the input is 10, the function should return 55 (1 + 2 + ... + 10).\n- If the input is 1, the function should return 1.\n\nThis task tests your understanding of recursion, base cases, and arithmetic progression.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:11:47.618Z'),
            updatedAt: new Date('2024-11-19T13:11:47.618Z'),
            solutions: [
              {
                solutionId: NumberLong(147), content: 'public class Result {\n    public static int sumToN(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        return n + sumToN(n - 1);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:54:56.849Z')
              },
              {
                solutionId: NumberLong(148), content: 'def result(n):\n    return sum(range(1, n + 1))\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:08:18.436Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(149), userId: NumberLong(184), content: 'public class Result {\n    public static int sumToN(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException("Input must be a non-negative integer");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        return n + sumToN(n - 1);\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T15:52:34.850Z'), submittedAt: new Date('2024-11-22T15:52:34.850Z') }
            ]
          },
          {
            assignmentId: NumberLong(150), title: '20. Create a Function to Find the nth Fibonacci Number',
            description: 'Write a function that takes a non-negative integer `n` as input and returns the nth Fibonacci number using recursion. The Fibonacci sequence is defined as:\n  - F(0) = 0\n  - F(1) = 1\n  - F(n) = F(n-1) + F(n-2) for n > 1\n\nExamples:\n\n- If the input is 0, the function should return 0.\n- If the input is 1, the function should return 1.\n- If the input is 5, the function should return 5 (Fibonacci sequence: 0, 1, 1, 2, 3, 5).\n- If the input is 7, the function should return 13.\n\nThis task tests your understanding of recursion, mathematical sequences, and efficient computation.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:12:02.985Z'),
            updatedAt: new Date('2024-11-19T13:12:02.985Z'),
            solutions: [
              {
                solutionId: NumberLong(151), content: 'public class Result {\n    public static int fibonacci(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException(\"Input must be a non-negative integer\");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        if (n == 1) {\n            return 1;\n        }\n        return fibonacci(n - 1) + fibonacci(n - 2);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:55:09.720Z')
              },
              {
                solutionId: NumberLong(152), content: 'def result(n):\n    if n == 0:\n        return 0\n    elif n == 1:\n        return 1\n    a, b = 0, 1\n    for _ in range(2, n + 1):\n        a, b = b, a + b\n    return b\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:08:24.092Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(153), userId: NumberLong(184), content: 'public class Result {\n    public static int fibonacci(int n) {\n        if (n < 0) {\n            throw new IllegalArgumentException("Input must be a non-negative integer");\n        }\n        if (n == 0) {\n            return 0;\n        }\n        if (n == 1) {\n            return 1;\n        }\n        return fibonacci(n - 1) + fibonacci(n - 2);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:53:02.798Z'), submittedAt: new Date('2024-11-22T15:53:02.798Z') }
            ]
          },
          {
            assignmentId: NumberLong(154), title: '21. Solve the Tower of Hanoi Problem Using Recursion',
            description: 'Write a function that solves the Tower of Hanoi problem for `n` disks. The function should take three inputs: the source rod, the destination rod, and the auxiliary rod. The goal is to move all disks from the source rod to the destination rod following these rules:\n\n1. Only one disk can be moved at a time.\n2. A disk can only be placed on top of a larger disk or on an empty rod.\n\nThe function should print each move in the format: \'Move disk from [source] to [destination]\'. Use recursion to break the problem into smaller subproblems.\n\nExamples:\n\n- For `n = 2`, the output should be:\n  Move disk from A to B\n  Move disk from A to C\n  Move disk from B to C\n\n- For `n = 3`, the output should be:\n  Move disk from A to C\n  Move disk from A to B\n  Move disk from C to B\n  Move disk from A to C\n  Move disk from B to A\n  Move disk from B to C\n  Move disk from A to C\n\nThis task tests your understanding of recursion, breaking down complex problems, and solving algorithmic challenges.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:12:14.568Z'),
            updatedAt: new Date('2024-11-19T13:12:14.568Z'),
            solutions: [
              {
                solutionId: NumberLong(155), content: 'import java.io.*;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static void towerOfHanoi(int n, char source, char destination, char auxiliary, Consumer<String> logger) {\n        if (n == 0) {\n            return;\n        }\n        towerOfHanoi(n - 1, source, auxiliary, destination, logger);\n        logger.accept(\"Move disk from \" + source + \" to \" + destination);\n        towerOfHanoi(n - 1, auxiliary, destination, source, logger);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:56:55.710Z')
              },
              {
                solutionId: NumberLong(156), content: 'def tower_of_hanoi(n, source, target, auxiliary, logger):\n    if n == 1:\n        logger(f\"Move disk from {source} to {target}\")\n        return\n    tower_of_hanoi(n - 1, source, auxiliary, target, logger)\n    logger(f\"Move disk from {source} to {target}\")\n    tower_of_hanoi(n - 1, auxiliary, target, source, logger)\n\ndef result(n):\n    output = []\n    logger = lambda x: output.append(x)\n    tower_of_hanoi(n, \'A\', \'C\', \'B\', logger)\n    return \'\'.join(output)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:09:28.958Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(157), userId: NumberLong(184), content: 'import java.io.*;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static void towerOfHanoi(int n, char source, char destination, char auxiliary, Consumer<String> logger) {\n        if (n == 0) {\n            return;\n        }\n        towerOfHanoi(n - 1, source, auxiliary, destination, logger);\n        logger.accept("Move disk from " + source + " to " + destination);\n        towerOfHanoi(n - 1, auxiliary, destination, source, logger);\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:53:37.263Z'), submittedAt: new Date('2024-11-22T15:53:37.263Z') }
            ]
          }
        ]
      },
      {
        lessonId: NumberLong(8), title: 'Lesson 8',
        content: 'Sorting Algorithms',
        lessonNumber: 8,
        requiredLevel: 3,
        createdAt: new Date('2024-11-19T13:04:28.093Z'),
        updatedAt: new Date('2024-11-19T13:04:28.093Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(158), title: '22. Implement Bubble Sort',
            description: 'Write a function that implements the Bubble Sort algorithm to sort a list of numbers in ascending order. The function should repeatedly compare adjacent elements in the list and swap them if they are in the wrong order. This process should continue until the list is fully sorted.\n\nExamples:\n\n- If the input is [5, 3, 8, 6, 2], the function should return [2, 3, 5, 6, 8].\n- If the input is [1, 4, 3, 2], the function should return [1, 2, 3, 4].\n- If the input is [10, 20, 5, 15], the function should return [5, 10, 15, 20].\n\nThis task tests your understanding of sorting algorithms and iterative problem-solving.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:13:01.662Z'),
            updatedAt: new Date('2024-11-19T13:13:01.662Z'),
            solutions: [
              {
                solutionId: NumberLong(159), content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\n\npublic class Result {\n    public static List<Integer> bubbleSort(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        int n = sortedNumbers.size();\n        boolean swapped;\n        do {\n            swapped = false;\n            for (int i = 0; i < n - 1; i++) {\n                if (sortedNumbers.get(i) > sortedNumbers.get(i + 1)) {\n                    int temp = sortedNumbers.get(i);\n                    sortedNumbers.set(i, sortedNumbers.get(i + 1));\n                    sortedNumbers.set(i + 1, temp);\n                    swapped = true;\n                }\n            }\n            n--;\n        } while (swapped);\n        return sortedNumbers;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:05.148Z')
              },
              {
                solutionId: NumberLong(160), content: 'def result(arr):\n    n = len(arr)\n    for i in range(n):\n        for j in range(0, n - i - 1):\n            if arr[j] > arr[j + 1]:\n                arr[j], arr[j + 1] = arr[j + 1], arr[j]\n    return arr\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:10:41.111Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(161), userId: NumberLong(184), content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\n\npublic class Result {\n    public static List<Integer> bubbleSort(List<Integer> numbers) {\n        if (numbers == null || numbers.isEmpty()) {\n            return numbers;\n        }\n        \n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        int n = sortedNumbers.size();\n        boolean swapped;\n        do {\n            swapped = false;\n            for (int i = 0; i < n - 1; i++) {\n                if (sortedNumbers.get(i) > sortedNumbers.get(i + 1)) {\n                    int temp = sortedNumbers.get(i);\n                    sortedNumbers.set(i, sortedNumbers.get(i + 1));\n                    sortedNumbers.set(i + 1, temp);\n                    swapped = true;\n                }\n            }\n            n--;\n        } while (swapped);\n        \n        return sortedNumbers;\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T15:54:05.804Z'), submittedAt: new Date('2024-11-22T15:54:05.804Z') }
            ]
          },
          {
            assignmentId: NumberLong(162), title: '23. Implement Quick Sort',
            description: 'Write a function that implements the Quick Sort algorithm to sort a list of numbers in ascending order. The function should use the divide-and-conquer strategy by selecting a pivot, partitioning the list into elements less than and greater than the pivot, and recursively sorting the partitions.\n\nExamples:\n\n- If the input is [5, 3, 8, 6, 2], the function should return [2, 3, 5, 6, 8].\n- If the input is [1, 4, 3, 2], the function should return [1, 2, 3, 4].\n- If the input is [10, 20, 5, 15], the function should return [5, 10, 15, 20].\n\nThis task tests your understanding of recursive algorithms and efficient sorting techniques.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:13:13.061Z'),
            updatedAt: new Date('2024-11-19T13:13:13.061Z'),
            solutions: [
              {
                solutionId: NumberLong(163), content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static List<Integer> quickSort(List<Integer> numbers) {\n        if (numbers == null || numbers.size() <= 1) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        quickSortHelper(sortedNumbers, 0, sortedNumbers.size() - 1);\n        return sortedNumbers;\n    }\n\n    private static void quickSortHelper(List<Integer> list, int low, int high) {\n        if (low < high) {\n            int pivotIndex = partition(list, low, high);\n            quickSortHelper(list, low, pivotIndex - 1);\n            quickSortHelper(list, pivotIndex + 1, high);\n        }\n    }\n\n    private static int partition(List<Integer> list, int low, int high) {\n        int pivot = list.get(high);\n        int i = low - 1;\n        for (int j = low; j < high; j++) {\n            if (list.get(j) < pivot) {\n                i++;\n                Collections.swap(list, i, j);\n            }\n        }\n        Collections.swap(list, i + 1, high);\n        return i + 1;\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:11.657Z')
              },
              {
                solutionId: NumberLong(164), content: 'def result(arr):\n    if len(arr) <= 1:\n        return arr\n    pivot = arr[len(arr) // 2]\n    left = [x for x in arr if x < pivot]\n    middle = [x for x in arr if x == pivot]\n    right = [x for x in arr if x > pivot]\n    return result(left) + middle + result(right)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:10:55.528Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(165), userId: NumberLong(184), content: 'import java.io.*;\nimport java.util.*;\nimport java.util.function.Function;\nimport java.util.function.Consumer;\n\npublic class Result {\n    public static List<Integer> quickSort(List<Integer> numbers) {\n        if (numbers == null || numbers.size() <= 1) {\n            return numbers;\n        }\n        List<Integer> sortedNumbers = new ArrayList<>(numbers);\n        quickSortHelper(sortedNumbers, 0, sortedNumbers.size() - 1);\n        return sortedNumbers;\n    }\n\n    private static void quickSortHelper(List<Integer> list, int low, int high) {\n        if (low < high) {\n            int pivotIndex = partition(list, low, high);\n            quickSortHelper(list, low, pivotIndex - 1);\n            quickSortHelper(list, pivotIndex + 1, high);\n        }\n    }\n\n    private static int partition(List<Integer> list, int low, int high) {\n        int pivot = list.get(high);\n        int i = low - 1;\n        for (int j = low; j < high; j++) {\n            if (list.get(j) < pivot) {\n                i++;\n                Collections.swap(list, i, j);\n            }\n        }\n        Collections.swap(list, i + 1, high);\n        return i + 1;\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:55:15.616Z'), submittedAt: new Date('2024-11-22T15:55:15.616Z') }
            ]
          },
          {
            assignmentId: NumberLong(166), title: '24. Selection Sort for an Array of Integers',
            description: 'Write a function to implement Selection Sort for an array of integers. This algorithm sorts an array by repeatedly finding the smallest element from the unsorted portion of the array and placing it in the correct position.\n\nExamples:\n\n- For an array [64, 25, 12, 22, 11], after sorting, the array should be [11, 12, 22, 25, 64].\n- In the first pass, the algorithm selects the smallest value (11) and swaps it with the first element (64), resulting in [11, 25, 12, 22, 64].\n- The sorting continues until all elements are sorted.\n\nThis task tests your ability to implement a classic sorting algorithm, use nested loops, and correctly swap values in an array.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:13:38.544Z'),
            updatedAt: new Date('2024-11-22T16:52:47.249Z'),
            solutions: [
              {
                solutionId: NumberLong(167), content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> selectionSort(List<Integer> numbers) {\n        Integer[] array = numbers.toArray(new Integer[0]);\n        int n = array.length;\n        for (int i = 0; i < n - 1; i++) {\n            int minIndex = i;\n            for (int j = i + 1; j < n; j++) {\n                if (array[j] < array[minIndex]) {\n                    minIndex = j;\n                }\n            }\n            int temp = array[minIndex];\n            array[minIndex] = array[i];\n            array[i] = temp;\n        }\n        return Arrays.asList(array);\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:19.110Z')
              },
              {
                solutionId: NumberLong(168), content: 'def result(arr):\n    n = len(arr)\n    for i in range(n):\n        min_idx = i\n        for j in range(i + 1, n):\n            if arr[j] < arr[min_idx]:\n                min_idx = j\n        arr[i], arr[min_idx] = arr[min_idx], arr[i]\n    return arr\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:11:04.718Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(169), userId: NumberLong(184), content: 'import java.util.List;\nimport java.util.Arrays;\n\npublic class Result {\n    public static List<Integer> selectionSort(List<Integer> numbers) {\n        Integer[] array = numbers.toArray(new Integer[0]);\n        int n = array.length;\n\n        for (int i = 0; i < n - 1; i++) {\n            int minIndex = i;\n            for (int j = i + 1; j < n; j++) {\n                if (array[j] < array[minIndex]) {\n                    minIndex = j;\n                }\n            }\n\n            int temp = array[minIndex];\n            array[minIndex] = array[i];\n            array[i] = temp;\n        }\n\n        return Arrays.asList(array);\n    }\n}\n', grade: 100, gradedAt: new Date('2024-11-22T16:54:12.141Z'), submittedAt: new Date('2024-11-22T16:54:12.141Z') }
            ]
          }
        ]
      },
      {
        lessonId: NumberLong(9), title: 'Lesson 9',
        content: 'Data Structures',
        lessonNumber: 9,
        requiredLevel: 3,
        createdAt: new Date('2024-11-19T13:04:48.507Z'),
        updatedAt: new Date('2024-11-19T13:04:48.507Z'),
        comments: [],
        assignments: [
          {
            assignmentId: NumberLong(170), title: '25. Create a Class Representing a Stack',
            description: 'Write a class that implements a stack data structure. The class should support the following operations:\n\n1. `push(value)` - Adds a value to the top of the stack.\n2. `pop()` - Removes and returns the value at the top of the stack. If the stack is empty, it should handle the error appropriately.\n3. `peek()` - Returns the value at the top of the stack without removing it.\n4. `is_empty()` - Returns `true` if the stack is empty, otherwise `false`.\n\nExamples:\n\n- If the operations are `push(5)`, `push(10)`, `pop()`, the result of `pop()` should be `10`, and the stack will contain [5].\n- If the stack is empty and `pop()` is called, it should handle the situation gracefully.\n\nThis task tests your understanding of classes, object-oriented programming, and stack operations.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:14:29.165Z'),
            updatedAt: new Date('2024-11-19T13:14:29.165Z'),
            solutions: [
              {
                solutionId: NumberLong(171), content: 'import java.io.*;\nimport java.util.*;\n\nclass Stack {\n    private List<Integer> stack;\n\n    public Stack() {\n        stack = new ArrayList<>();\n    }\n\n    public void push(int value) {\n        stack.add(value);\n    }\n\n    public int pop() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.remove(stack.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.get(stack.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return stack.isEmpty();\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:25.968Z')
              },
              {
                solutionId: NumberLong(172), content: 'class Stack:\n    def __init__(self):\n        self.items = []\n\n    def push(self, value):\n        self.items.append(value)\n\n    def pop(self):\n        return self.items.pop() if not self.is_empty() else \"N/A\"\n\n    def peek(self):\n        return self.items[-1] if not self.is_empty() else \"N/A\"\n\n    def is_empty(self):\n        return len(self.items) == 0\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:11:41.236Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(173), userId: NumberLong(184), content: 'import java.io.*;\nimport java.util.*;\n\nclass Stack {\n    private List<Integer> stack;\n\n    public Stack() {\n        stack = new ArrayList<>();\n    }\n\n\n    public void push(int value) {\n        stack.add(value);\n    }\n\n    public int pop() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.remove(stack.size() - 1);\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new EmptyStackException();\n        }\n        return stack.get(stack.size() - 1);\n    }\n\n    public boolean isEmpty() {\n        return stack.isEmpty();\n    }\n}', grade: 100, gradedAt: new Date('2024-11-22T15:56:09.261Z'), submittedAt: new Date('2024-11-22T15:56:09.261Z') }
            ]
          },
          {
            assignmentId: NumberLong(174), title: '26. Implement a Queue with Enqueue and Dequeue Operations',
            description: 'Write a class that implements a queue data structure. The class should support the following operations:\n\n1. `add(value)` - Adds a value to the end of the queue.\n2. `remove()` - Removes and returns the value at the front of the queue. If the queue is empty, it should handle the error appropriately.\n3. `peek()` - Returns the value at the front of the queue without removing it.\n4. `is_empty()` - Returns `true` if the queue is empty, otherwise `false`.\n\nExamples:\n\n- If the operations are `add(5)`, `add(10)`, `remove()`, the result of `remove()` should be `5`, and the queue will contain [10].\n- If the queue is empty and `remove()` is called, it should handle the situation gracefully.\n\nThis task tests your understanding of classes, object-oriented programming, and queue operations.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:14:42.699Z'),
            updatedAt: new Date('2024-11-22T15:17:03.013Z'),
            solutions: [
              {
                solutionId: NumberLong(175), content: 'import java.io.*;\nimport java.util.*;\n\nclass Queue {\n    private LinkedList<Integer> queue;\n\n    public Queue() {\n        queue = new LinkedList<>();\n    }\n\n    public void add(int value) {\n        queue.addLast(value);\n    }\n\n    public int remove() {\n        if (isEmpty()) {\n            throw new NoSuchElementException(\"Queue is empty\");\n        }\n        return queue.removeFirst();\n    }\n\n    public int peek() {\n        if (isEmpty()) {\n            throw new NoSuchElementException(\"Queue is empty\");\n        }\n        return queue.getFirst();\n    }\n\n    public boolean isEmpty() {\n        return queue.isEmpty();\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:31.351Z')
              },
              {
                solutionId: NumberLong(176), content: 'class Queue:\n    def __init__(self):\n        self.items = []\n\n    def enqueue(self, value):\n        self.items.append(value)\n\n    def dequeue(self):\n        return self.items.pop(0) if not self.is_empty() else \"N/A\"\n\n    def peek(self):\n        return self.items[0] if not self.is_empty() else \"N/A\"\n\n    def is_empty(self):\n        return len(self.items) == 0\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:12:16.763Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(177), userId: NumberLong(184), content: 'import java.io.*; \n\nimport java.util.*; \n\n  class Queue { \n\n    private LinkedList<Integer> queue; \n\n    public Queue() { \n\n        queue = new LinkedList<>(); \n\n    } \n\n    public void add(int value) { \n\n        queue.addLast(value); \n\n    } \n\n    public int remove() { \n\n        if (isEmpty()) { \n\n            throw new NoSuchElementException("Queue is empty"); \n\n        } \n\n        return queue.removeFirst(); \n\n    } \n\n    public int peek() { \n\n        if (isEmpty()) { \n\n            throw new NoSuchElementException("Queue is empty"); \n\n        } \n\n        return queue.getFirst(); \n\n    } \n\n    public boolean isEmpty() { \n\n        return queue.isEmpty(); \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T15:57:44.515Z'), submittedAt: new Date('2024-11-22T15:57:44.515Z') }
            ]
          },
          {
            assignmentId: NumberLong(178), title: '27. Write a Class for Working with a Binary Tree',
            description: 'Write a class that represents a binary tree. The class should include methods for common operations:\n\n1. `insert(value)` - Inserts a value into the binary tree following binary search tree rules.\n2. `find(value)` - Searches for a value in the tree and returns `true` if found, otherwise `false`.\n3. `in_order_traversal()` - Returns a list of values in the tree in in-order sequence.\n4. `pre_order_traversal()` - Returns a list of values in pre-order sequence.\n5. `post_order_traversal()` - Returns a list of values in post-order sequence.\n\nExamples:\n\n- If the tree contains [5, 3, 7] and `in_order_traversal()` is called, it should return [3, 5, 7].\n- If the value `5` is inserted into the tree and then `find(5)` is called, it should return `true`.\n- If the tree is empty and `find(1)` is called, it should return `false`.\n\nThis task tests your understanding of binary trees, traversal methods, and object-oriented design.',
            titleLevel: 'ADVANCED',
            createdAt: new Date('2024-11-19T13:14:57.725Z'),
            updatedAt: new Date('2024-11-19T13:14:57.725Z'),
            solutions: [
              {
                solutionId: NumberLong(179), content: 'import java.util.*;\n\nclass BinaryTree {\n    static class Node {\n        int value;\n        Node left, right;\n\n        Node(int value) {\n            this.value = value;\n            left = right = null;\n        }\n    }\n\n    private Node root;\n\n    public BinaryTree() {\n        root = null;\n    }\n\n    public void insert(int value) {\n        root = insertRec(root, value);\n    }\n\n    private Node insertRec(Node root, int value) {\n        if (root == null) {\n            root = new Node(value);\n            return root;\n        }\n        if (value < root.value) {\n            root.left = insertRec(root.left, value);\n        } else if (value > root.value) {\n            root.right = insertRec(root.right, value);\n        }\n        return root;\n    }\n\n    public boolean find(int value) {\n        return findRec(root, value);\n    }\n\n    private boolean findRec(Node root, int value) {\n        if (root == null) {\n            return false;\n        }\n        if (root.value == value) {\n            return true;\n        }\n        if (value < root.value) {\n            return findRec(root.left, value);\n        }\n        return findRec(root.right, value);\n    }\n\n    public List<Integer> inOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        inOrderRec(root, result);\n        return result;\n    }\n\n    private void inOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            inOrderRec(root.left, result);\n            result.add(root.value);\n            inOrderRec(root.right, result);\n        }\n    }\n\n    public List<Integer> preOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        preOrderRec(root, result);\n        return result;\n    }\n\n    private void preOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            result.add(root.value);\n            preOrderRec(root.left, result);\n            preOrderRec(root.right, result);\n        }\n    }\n\n    public List<Integer> postOrderTraversal() {\n        List<Integer> result = new ArrayList<>();\n        postOrderRec(root, result);\n        return result;\n    }\n\n    private void postOrderRec(Node root, List<Integer> result) {\n        if (root != null) {\n            postOrderRec(root.left, result);\n            postOrderRec(root.right, result);\n            result.add(root.value);\n        }\n    }\n}',
                language: 'java',
                createdAt: new Date('2024-11-22T21:57:39.293Z')
              },
              {
                solutionId: NumberLong(180), content: 'class BinaryTree:\n    class Node:\n        def __init__(self, value):\n            self.value = value\n            self.left = None\n            self.right = None\n\n    def __init__(self):\n        self.root = None\n\n    def insert(self, value):\n        if self.root is None:\n            self.root = self.Node(value)\n        else:\n            self._insert_recursive(self.root, value)\n\n    def _insert_recursive(self, node, value):\n        if value < node.value:\n            if node.left is None:\n                node.left = self.Node(value)\n            else:\n                self._insert_recursive(node.left, value)\n        else:\n            if node.right is None:\n                node.right = self.Node(value)\n            else:\n                self._insert_recursive(node.right, value)\n\n    def find(self, value):\n        return self._find_recursive(self.root, value)\n\n    def _find_recursive(self, node, value):\n        if node is None:\n            return False\n        if node.value == value:\n            return True\n        elif value < node.value:\n            return self._find_recursive(node.left, value)\n        else:\n            return self._find_recursive(node.right, value)\n\n    def in_order_traversal(self):\n        result = []\n        self._in_order_recursive(self.root, result)\n        return result\n\n    def _in_order_recursive(self, node, result):\n        if node is not None:\n            self._in_order_recursive(node.left, result)\n            result.append(node.value)\n            self._in_order_recursive(node.right, result)\n\n    def pre_order_traversal(self):\n        result = []\n        self._pre_order_recursive(self.root, result)\n        return result\n\n    def _pre_order_recursive(self, node, result):\n        if node is not None:\n            result.append(node.value)\n            self._pre_order_recursive(node.left, result)\n            self._pre_order_recursive(node.right, result)\n\n    def post_order_traversal(self):\n        result = []\n        self._post_order_recursive(self.root, result)\n        return result\n\n    def _post_order_recursive(self, node, result):\n        if node is not None:\n            self._post_order_recursive(node.left, result)\n            self._post_order_recursive(node.right, result)\n            result.append(node.value)\n',
                language: 'python',
                createdAt: new Date('2024-11-22T22:13:11.729Z')
              }
            ],
            submissions: [
              { submissionId: NumberLong(181), userId: NumberLong(184), content: 'import java.util.*; \n\nclass BinaryTree { \n\n    static class Node { \n\n        int value; \n\n        Node left, right; \n\n  \n\n        Node(int value) { \n\n            this.value = value; \n\n            left = right = null; \n\n        } \n\n    } \n\n  \n\n    private Node root; \n\n\n    public BinaryTree() { \n\n        root = null; \n\n    } \n\n\n\n    public void insert(int value) { \n\n        root = insertRec(root, value); \n\n    } \n\n  \n\n    private Node insertRec(Node root, int value) { \n\n        if (root == null) { \n\n            root = new Node(value); \n\n            return root; \n\n        } \n\n        if (value < root.value) { \n\n            root.left = insertRec(root.left, value); \n\n        } else if (value > root.value) { \n\n            root.right = insertRec(root.right, value); \n\n        } \n\n        return root; \n\n    } \n\n\n    public boolean find(int value) { \n\n        return findRec(root, value); \n\n    } \n\n  \n\n    private boolean findRec(Node root, int value) { \n\n        if (root == null) { \n\n            return false; \n\n        } \n\n        if (root.value == value) { \n\n            return true; \n\n        } \n\n        if (value < root.value) { \n\n            return findRec(root.left, value); \n\n        } \n\n        return findRec(root.right, value); \n\n    } \n\n\n    public List<Integer> inOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        inOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void inOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            inOrderRec(root.left, result); \n\n            result.add(root.value); \n\n            inOrderRec(root.right, result); \n\n        } \n\n    } \n\n\n    public List<Integer> preOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        preOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void preOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            result.add(root.value); \n\n            preOrderRec(root.left, result); \n\n            preOrderRec(root.right, result); \n\n        } \n\n    }  \n\n    public List<Integer> postOrderTraversal() { \n\n        List<Integer> result = new ArrayList<>(); \n\n        postOrderRec(root, result); \n\n        return result; \n\n    } \n\n  \n\n    private void postOrderRec(Node root, List<Integer> result) { \n\n        if (root != null) { \n\n            postOrderRec(root.left, result); \n\n            postOrderRec(root.right, result); \n\n            result.add(root.value); \n\n        } \n\n    } \n\n} ', grade: 100, gradedAt: new Date('2024-11-22T15:58:28.136Z'), submittedAt: new Date('2024-11-22T15:58:28.136Z') }
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
    _id: NumberLong(182), username: 'jan.kowalski',
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
    _id: NumberLong(183), username: 'radek',
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
      { courseId: NumberLong(1), lessonId: NumberLong(2), completedAt: new Date('2024-11-02T22:00:23.168Z') }
    ],
    quizResults: [
      { quizId: NumberLong(2), completedAt: new Date('2024-11-11T22:52:01.372Z'), points: NumberLong(20) },
      { quizId: NumberLong(4), completedAt: new Date('2024-11-14T19:42:34.085Z'), points: NumberLong(30) },
      { quizId: NumberLong(3), completedAt: new Date('2024-11-15T19:51:58.088Z'), points: NumberLong(50) },
      { quizId: NumberLong(5), completedAt: new Date('2024-11-19T10:06:45.443Z'), points: NumberLong(40) },
      { quizId: NumberLong(6), completedAt: new Date('2024-11-22T10:41:47.773Z'), points: NumberLong(40) }
    ],
    dailyTasks: [
      { taskId: NumberLong(4), assignmentDate: new Date('2024-11-19'), completedAt: null, points: null },
      { taskId: NumberLong(4), assignmentDate: new Date('2024-11-22'), completedAt: null, points: null },
      { taskId: NumberLong(4), assignmentDate: new Date('2024-12-03'), completedAt: null, points: null },
      { taskId: NumberLong(1), assignmentDate: new Date('2025-01-05'), completedAt: null, points: null },
      { taskId: NumberLong(1), assignmentDate: new Date('2025-01-06'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(184), username: 'maciek',
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
      { courseId: NumberLong(1), lessonId: NumberLong(1), completedAt: new Date('2024-11-22T15:32:24.377Z') },
      { courseId: NumberLong(1), lessonId: NumberLong(3), completedAt: new Date('2024-11-22T15:36:52.154Z') },
      { courseId: NumberLong(2), lessonId: NumberLong(6), completedAt: new Date('2024-11-22T15:52:10.305Z') },
      { courseId: NumberLong(3), lessonId: NumberLong(7), completedAt: new Date('2024-11-22T15:53:37.282Z') },
      { courseId: NumberLong(3), lessonId: NumberLong(9), completedAt: new Date('2024-11-22T15:58:28.168Z') },
      { courseId: NumberLong(3), lessonId: NumberLong(8), completedAt: new Date('2024-11-22T16:54:12.168Z') },
      { courseId: NumberLong(1), lessonId: NumberLong(2), completedAt: new Date('2024-11-22T17:58:11.195Z') }
    ],
    quizResults: [
      { quizId: NumberLong(4), completedAt: new Date('2024-11-19T13:37:25.421Z'), points: NumberLong(30) },
      { quizId: NumberLong(2), completedAt: new Date('2024-11-22T22:31:47.435Z'), points: NumberLong(30) },
      { quizId: NumberLong(6), completedAt: new Date('2024-11-26T19:08:33.865Z'), points: NumberLong(30) },
      { quizId: NumberLong(3), completedAt: new Date('2025-01-05T20:44:06.375Z'), points: NumberLong(20) },
      { quizId: NumberLong(5), completedAt: new Date('2025-01-11T15:17:15.451Z'), points: NumberLong(15) }
    ],
    dailyTasks: [
      { taskId: NumberLong(5), assignmentDate: new Date('2024-11-22'), completedAt: new Date('2024-11-22T20:16:47.055Z'), points: NumberLong(60) },
      { taskId: NumberLong(1), assignmentDate: new Date('2024-11-26'), completedAt: null, points: null },
      { taskId: NumberLong(4), assignmentDate: new Date('2024-11-27'), completedAt: null, points: null },
      { taskId: NumberLong(6), assignmentDate: new Date('2025-01-05'), completedAt: null, points: null },
      { taskId: NumberLong(2), assignmentDate: new Date('2025-01-11'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(185), username: 'marek',
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
      { taskId: NumberLong(2), assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(186), username: '123',
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
    _id: NumberLong(187), username: '123radek',
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
      { courseId: NumberLong(1), lessonId: NumberLong(2), completedAt: new Date('2024-11-11T14:59:32.817Z') }
    ],
    quizResults: [],
    dailyTasks: []
  },
  {
    _id: NumberLong(188), username: 'marcinek',
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
      { courseId: NumberLong(1), lessonId: NumberLong(2), completedAt: new Date('2024-11-11T15:02:05.021Z') }
    ],
    quizResults: [],
    dailyTasks: []
  },
  {
    _id: NumberLong(189), username: 'marcinek2',
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
      { courseId: NumberLong(1), lessonId: NumberLong(2), completedAt: new Date('2024-11-11T15:03:07.016Z') }
    ],
    quizResults: [
      { quizId: NumberLong(2), completedAt: new Date('2024-11-13T09:24:36.821Z'), points: NumberLong(20) },
      { quizId: NumberLong(5), completedAt: new Date('2024-11-19T10:57:22.535Z'), points: NumberLong(30) }
    ],
    dailyTasks: [
      { taskId: NumberLong(1), assignmentDate: new Date('2024-11-19'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(190), username: 'radek1',
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
      { quizId: NumberLong(2), completedAt: new Date('2024-11-19T13:53:34.886Z'), points: NumberLong(30) }
    ],
    dailyTasks: []
  },
  {
    _id: NumberLong(191), username: 'radek2',
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
      { quizId: NumberLong(2), completedAt: new Date('2024-11-19T13:54:00.600Z'), points: NumberLong(30) }
    ],
    dailyTasks: []
  },
  {
    _id: NumberLong(192), username: 'test1',
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
      { courseId: NumberLong(1), lessonId: NumberLong(1), completedAt: new Date('2024-11-22T16:18:07.150Z') },
      { courseId: NumberLong(1), lessonId: NumberLong(2), completedAt: new Date('2024-11-22T16:18:38.406Z') },
      { courseId: NumberLong(1), lessonId: NumberLong(3), completedAt: new Date('2024-11-22T16:19:09.464Z') },
      { courseId: NumberLong(2), lessonId: NumberLong(4), completedAt: new Date('2024-11-22T16:34:20.616Z') }
    ],
    quizResults: [],
    dailyTasks: [
      { taskId: NumberLong(5), assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(193), username: '123123123123123123123123123123',
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
      { taskId: NumberLong(5), assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(194), username: 'qwerty',
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
      { taskId: NumberLong(2), assignmentDate: new Date('2024-11-22'), completedAt: null, points: null }
    ]
  },
  {
    _id: NumberLong(195), username: '123123123231312123312312123',
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
    _id: NumberLong(196), fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: NumberLong(197), fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: NumberLong(198), fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: NumberLong(199), fileName: '123.mp4',
    filePath: 'C:\\Users\\hoffi\\Desktop\\studia (1)\\7 sem\\INZYNIERKA\\Backend\\StronaDoNaukiWybranegoJezykaProgramowania\\src\\videos/123.mp4',
    fileSize: NumberLong(17684012)
  },
  {
    _id: NumberLong(200), fileName: '123.mp4',
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
