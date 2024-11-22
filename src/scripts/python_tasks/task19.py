test_inputs = [
    5,
    10,
    1,
    0,
    7
]

expected_outputs = [
    15,
    55,
    1,
    0,
    28
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)