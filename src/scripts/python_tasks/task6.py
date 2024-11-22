test_inputs = [
    (10.0, 3.0),
    (7.0, 2.0),
    (-8.0, 3.0),
    (9.0, 4.0),
    (100.0, 6.0)
]

expected_outputs = [
    3,
    4,
    -3,
    2,
    17
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))