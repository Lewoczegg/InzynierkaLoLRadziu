def result(a, b):
    if a > b:
        return f"{a} is greater than {b}"
    else:
        return f"{a} is not greater than {b}"

test_inputs = [
    (10, 5),
    (3, 7),
    (4, 4),
    (-1, -5),
    (6, 6)
]

expected_outputs = [
    "10 is greater than 5",
    "3 is not greater than 7",
    "4 is not greater than 4",
    "-1 is greater than -5",
    "6 is not greater than 6"
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))