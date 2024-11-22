test_inputs = [
    (3.0, 5.0),
    (2.5, 4.3),
    (-7.0, 2.0),
    (10.1, 0.9),
    (100.0, 200.0)
]

expected_outputs = [
    8.0,
    6.8,
    -5.0,
    11.0,
    300.0
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))