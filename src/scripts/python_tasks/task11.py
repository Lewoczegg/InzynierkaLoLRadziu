test_inputs = [
    [1, 2, 3, 4, 5],
    [10, 20, 30, 40, 50],
    [-1, -2, -3, -4, -5],
    [0, 0, 0, 0, 0]
]

expected_outputs = [
    15,
    150,
    -15,
    0
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)