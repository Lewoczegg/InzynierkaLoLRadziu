import sys
import io

def main():
    test_inputs = [2, 4, 5, 9, 13, 17, 20, 23, 29, 31]
    expected_outputs = [True, False, True, False, True, True, False, True, True, True]

    all_tests_passed = True

    # Przechwyć wyjście użytkownika podczas pierwszego wywołania
    user_output = io.StringIO()
    original_stdout = sys.stdout
    sys.stdout = user_output

    # Importuj kod użytkownika
    from user_code import is_prime

    # Wywołaj funkcję użytkownika raz, aby przechwycić wyjście
    is_prime(test_inputs[0])

    # Przywróć stdout
    sys.stdout = original_stdout

    # Zapisz wyjście użytkownika do pliku
    with open('user_output.txt', 'w') as f:
        f.write(user_output.getvalue())

    # Wykonaj testy, ignorując wyjście użytkownika
    for i, input_value in enumerate(test_inputs):
        expected = expected_outputs[i]

        # Ignoruj wyjście użytkownika podczas testów
        sys.stdout = open('/dev/null', 'w')

        try:
            result = is_prime(input_value)
        except Exception as e:
            sys.stdout = original_stdout
            print(f"Test failed for input: {input_value}. Exception occurred: {e}")
            all_tests_passed = False
            continue

        sys.stdout = original_stdout

        if result == expected:
            print(f"Test passed for input: {input_value}")
        else:
            print(f"Test failed for input: {input_value}. Expected: {expected}, but got: {result}")
            all_tests_passed = False

    if all_tests_passed:
        print("All tests passed!")
    else:
        print("Some tests failed.")

if __name__ == "__main__":
    main()
