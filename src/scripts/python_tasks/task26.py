test_inputs = [
    ["enqueue 5", "enqueue 10", "peek", "dequeue", "dequeue", "is_empty"]
]

expected_outputs = [
    ["N/A", "N/A", "5", "5", "10", "true"]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import Queue

    def result(operations):
        queue = Queue()
        results = []
        for operation in operations:
            if operation.startswith("enqueue"):
                _, value = operation.split()
                queue.enqueue(int(value))
                results.append("N/A")
            elif operation == "dequeue":
                results.append(str(queue.dequeue()))
            elif operation == "peek":
                results.append(str(queue.peek()))
            elif operation == "is_empty":
                results.append(str(queue.is_empty()).lower())
        return results

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)