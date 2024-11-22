test_inputs = [
    [1, 2, 3, 4, 5],
    [-10, -3, -7, -2],
    [0, 0, 0],
    [42],
    [-5, -1, -20]
]

expected_outputs = [
    5,
    -2,
    0,
    42,
    -1
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)