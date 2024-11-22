test_inputs = [42, -7, 100]  # Dane wejściowe do testów
expected_outputs = [42, -7, 100]  # Oczekiwane wyniki

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    # Wywołaj metodę run_tests z TaskExecutor, przekazując funkcję define_integer
    TaskExecutor.run_tests(test_inputs, expected_outputs, result)