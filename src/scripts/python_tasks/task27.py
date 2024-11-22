# task27.py

test_inputs = [
    ["insert 5", "insert 3", "insert 7", "insert 1", "insert 4", "in_order", "pre_order", "post_order", "find 5", "find 8"]
]

expected_outputs = [
    ["N/A", "N/A", "N/A", "N/A", "N/A", "[1, 3, 4, 5, 7]", "[5, 3, 1, 4, 7]", "[1, 4, 3, 7, 5]", "true", "false"]
]

if __name__ == "__main__":
    import TaskExecutor
    from user_code import BinaryTree

    def result(operations):
        tree = BinaryTree()
        results = []
        for operation in operations:
            parts = operation.split()
            if parts[0] == "insert":
                tree.insert(int(parts[1]))
                results.append("N/A")
            elif parts[0] == "find":
                results.append(str(tree.find(int(parts[1]))).lower())
            elif parts[0] == "in_order":
                results.append(str(tree.in_order_traversal()))
            elif parts[0] == "pre_order":
                results.append(str(tree.pre_order_traversal()))
            elif parts[0] == "post_order":
                results.append(str(tree.post_order_traversal()))
        return results

    TaskExecutor.run_tests(test_inputs, expected_outputs, result)
