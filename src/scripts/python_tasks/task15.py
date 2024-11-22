test_inputs = [
    [1, 2, 3, 14, 8, 9],
    [5, 9, 13, 21, 4, 7],
    [8, 10, 15, 28, 32, 45],
    [1, 6, 12, 13, 49],
    [3, 4, 10, 20, 35],
    [50, 44, 42, 7]
]

expected_outputs = [
    14,
    21,
    28,
    49,
    35,
    42
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)