test_inputs = [
    42,
    -7,
    0
]  # Dane wejściowe do testów

expected_outputs = [
    "42",
    "-7",
    "0"
]  # Oczekiwane wyniki

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    # Wywołaj metodę run_tests z TaskExecutor, przekazując funkcję result
    TaskExecutor.run_tests(test_inputs, expected_outputs, result)