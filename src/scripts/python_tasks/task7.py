test_inputs = [
    4,
    7,
    0,
    -2,
    15
]

expected_outputs = [
    "4 is even",
    "7 is not even",
    "0 is even",
    "-2 is even",
    "15 is not even"
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)