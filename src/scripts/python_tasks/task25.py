test_inputs = [
    ["push 5", "push 10", "peek", "pop", "pop", "is_empty"]
]

expected_outputs = [
    ["N/A", "N/A", "10", "10", "5", "true"]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import Stack

    def result(operations):
        stack = Stack()
        results = []
        for operation in operations:
            if operation.startswith("push"):
                _, value = operation.split()
                stack.push(int(value))
                results.append("N/A")
            elif operation == "pop":
                results.append(str(stack.pop()))
            elif operation == "peek":
                results.append(str(stack.peek()))
            elif operation == "is_empty":
                results.append(str(stack.is_empty()).lower())
        return results

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)