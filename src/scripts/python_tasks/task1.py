test_inputs = [42, -7, 100]
expected_outputs = [42, -7, 100]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)