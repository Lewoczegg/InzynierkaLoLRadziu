# taskexecutor.py
import sys
import io

def run_tests(test_inputs, expected_outputs, user_function):
    all_tests_passed = True

    # Przechwyć wyjście użytkownika podczas pierwszego wywołania
    user_output = io.StringIO()
    original_stdout = sys.stdout
    sys.stdout = user_output

    # Wywołaj funkcję użytkownika raz, aby przechwycić wyjście
    user_function(test_inputs[0])

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
            result = user_function(input_value)
        except Exception as e:
            sys.stdout = original_stdout
            print(f"Test failed for input: {input_value}. Exception occurred: {e}")
            all_tests_passed = False
            continue

        sys.stdout = original_stdout

        if result == expected:
            print(f"Test passed for input: '{input_value}'. Result: {result}")
        else:
            print(f"Test failed for input: '{input_value}'. Expected: {expected_outputs[i]}, but got: {result}")
            all_tests_passed = False

    if all_tests_passed:
        print("All tests passed!")
    else:
        print("Some tests failed.")
