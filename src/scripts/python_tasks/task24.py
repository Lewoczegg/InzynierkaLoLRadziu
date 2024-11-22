test_inputs = [
    [64, 25, 12, 22, 11],
    [5, 3, 8, 4, 2],
    [1, 2, 3, 4, 5],
    [10, 9, 8, 7, 6],
    [20, 15, 30, 5, 25],
    [100, 50, 25, 75, 10]
]

expected_outputs = [
    [11, 12, 22, 25, 64],
    [2, 3, 4, 5, 8],
    [1, 2, 3, 4, 5],
    [6, 7, 8, 9, 10],
    [5, 15, 20, 25, 30],
    [10, 25, 50, 75, 100]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)