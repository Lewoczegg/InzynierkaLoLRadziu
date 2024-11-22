test_inputs = [
    ["Alice", "Bob", "Charlie"],
    ["john", "doe"],
    ["upper"]
]

expected_outputs = [
    ["ALICE", "BOB", "CHARLIE"],
    ["JOHN", "DOE"],
    ["UPPER"]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)