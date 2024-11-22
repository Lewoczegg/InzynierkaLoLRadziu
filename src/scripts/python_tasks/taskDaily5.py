test_inputs = [
    {"Alice": 85, "Bob": 90, "Charlie": 78},
    {"StudentA": 50, "StudentB": 60, "StudentC": 70},
    {"X": 100, "Y": 100, "Z": 100}
]

expected_outputs = [
    84.33,
    60.0,
    100.0
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)