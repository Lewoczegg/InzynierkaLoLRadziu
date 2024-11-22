test_inputs = [
    ({"Apple": "A fruit", "Dog": "An animal"}, ["Car", "A vehicle"]),
    ({"Book": "Something to read"}, ["Pen", "A tool for writing"]),
    ({}, ["Key", "Used for unlocking"])
]

expected_outputs = [
    {"Apple": "A fruit", "Dog": "An animal", "Car": "A vehicle"},
    {"Book": "Something to read", "Pen": "A tool for writing"},
    {"Key": "Used for unlocking"}
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))