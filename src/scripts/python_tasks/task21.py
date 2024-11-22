test_inputs = [
    2,
    3
]

expected_outputs = [
    "Move disk from A to BMove disk from A to CMove disk from B to C",
    "Move disk from A to CMove disk from A to BMove disk from C to BMove disk from A to CMove disk from B to AMove disk from B to CMove disk from A to C"
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)