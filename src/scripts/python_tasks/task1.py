import sys
import io

def main():
    inputs = [
        (1, 2),
        (3, 4),
        (10, 20)
    ]
    expected_results = [
        3,
        7,
        30
    ]

    all_tests_passed = True

    # Przechwyć wyjście użytkownika podczas pierwszego wywołania
    user_output = io.StringIO()
    original_stdout = sys.stdout
    sys.stdout = user_output

    # Importuj kod użytkownika
    from user_code import sum

    # Wywołaj funkcję użytkownika raz, aby przechwycić wyjście
    sum(*inputs[0])

    # Przywróć stdout
    sys.stdout = original_stdout

    # Zapisz wyjście użytkownika do pliku
    with open('user_output.txt', 'w') as f:
        f.write(user_output.getvalue())

    # Wykonaj testy, ignorując wyjście użytkownika
    for i, input_pair in enumerate(inputs):
        a, b = input_pair
        expected = expected_results[i]

        # Ignoruj wyjście użytkownika podczas testów
        sys.stdout = open('/dev/null', 'w')

        try:
            result = sum(a, b)
        except Exception as e:
            sys.stdout = original_stdout
            print(f"Test failed for input: {a}, {b}. Exception occurred: {e}")
            all_tests_passed = False
            continue

        sys.stdout = original_stdout

        if result == expected:
            print(f"Test passed for input: {a} and {b}. Result: {result}")
        else:
            print(f"Test failed for input: {a} and {b}. Expected: {expected}, but got: {result}")
            all_tests_passed = False

    if all_tests_passed:
        print("All tests passed!")
    else:
        print("Some tests failed.")

if __name__ == "__main__":
    main()
