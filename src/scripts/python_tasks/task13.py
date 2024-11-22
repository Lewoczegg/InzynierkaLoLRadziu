test_inputs = [
    [0]
]

expected_outputs = [
    110
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda _: result())