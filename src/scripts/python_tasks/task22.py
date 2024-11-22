test_inputs = [
    [5, 3, 8, 6, 2],
    [1, 4, 3, 2],
    [10, 20, 5, 15],
    [3, 3, 2, 1]
]

expected_outputs = [
    [2, 3, 5, 6, 8],
    [1, 2, 3, 4],
    [5, 10, 15, 20],
    [1, 2, 3, 3]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)