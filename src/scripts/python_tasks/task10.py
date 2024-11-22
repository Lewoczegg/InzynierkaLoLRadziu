test_inputs = [
    [1],
    [2]
]

expected_outputs = [
    [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],
    [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda _: result())