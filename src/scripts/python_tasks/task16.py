test_inputs = [
    (3, 5),
    (-2, 10),
    (0, 0),
    (100, 200),
    (-50, -70)
]

expected_outputs = [
    8,
    8,
    0,
    300,
    -120
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))