test_inputs = [
    "Hello world, welcome to the world of Java.",
    "Java is great.",
    "The world is big."
]

expected_outputs = [
    2,
    0,
    1
]

phrase = "world"

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda input: result(input, phrase))