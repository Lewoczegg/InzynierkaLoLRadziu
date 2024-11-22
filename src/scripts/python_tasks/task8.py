test_inputs = [
    5,
    -3,
    0,
    12,
    -1
]

expected_outputs = [
    "5 is positive",
    "-3 is not positive",
    "0 is not positive",
    "12 is positive",
    "-1 is not positive"
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)