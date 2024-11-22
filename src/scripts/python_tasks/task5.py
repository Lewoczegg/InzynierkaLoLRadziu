test_inputs = [
    (4.0, 5.0),
    (2.5, 3.0),
    (-3.0, -2.0),
    (7.0, 0.0),
    (100.0, 0.5)
]

expected_outputs = [
    20.0,
    7.5,
    6.0,
    0.0,
    50.0
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))