test_inputs = [
    5,
    0,
    3,
    7,
    10
]

expected_outputs = [
    120,
    1,
    6,
    5040,
    3628800
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)