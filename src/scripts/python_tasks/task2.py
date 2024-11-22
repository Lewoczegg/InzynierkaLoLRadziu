test_inputs = [
    (3, 5),
    (-2, 7),
    (0, 0),
    (10, -10),
    (100, 200)
]

expected_outputs = [
    8,
    5,
    0,
    0,
    300
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))