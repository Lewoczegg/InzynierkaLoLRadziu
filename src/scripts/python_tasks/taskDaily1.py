test_inputs = [
    "Hello, World!",
    "",
    "OpenAI"
]

expected_outputs = [
    13,
    0,
    6
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)