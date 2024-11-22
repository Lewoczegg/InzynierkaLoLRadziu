test_inputs = [
    ("Hello", "World"),
    ("Open", "AI"),
    ("Java", ""),
    ("", "Test")
]

expected_outputs = [
    "HelloWorld",
    "OpenAI",
    "Java",
    "Test"
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import result

    TaskExecutor.run_tests(test_inputs, expected_outputs, lambda inputs: result(*inputs))