test_inputs = [
    0,
    1,
    5,
    7,
    10
]

expected_outputs = [
    0,
    1,
    5,
    13,
    55
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)