import unittest
import sys
import io
from user_code import sum

class TestSumFunction(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        # Przechwytywanie wyjścia użytkownika podczas pierwszego wywołania
        cls.user_output = io.StringIO()
        original_stdout = sys.stdout
        sys.stdout = cls.user_output

        # Wywołaj funkcję użytkownika raz, aby przechwycić wyjście
        sum(1, 2)

        # Zapisz wyjście użytkownika do pliku
        with open('user_output.txt', 'w') as f:
            f.write(cls.user_output.getvalue())

        # Przywróć stdout
        sys.stdout = original_stdout

    def test_sum(self):
        # Przekieruj stdout na null podczas testów
        null_output = open('/dev/null', 'w')
        sys.stdout = null_output

        test_cases = [
            ((1, 2), 3),
            ((3, 4), 7),
            ((10, 20), 30)
        ]
        for inputs, expected in test_cases:
            result = sum(*inputs)
            self.assertEqual(result, expected, f"Failed for inputs: {inputs}")

        # Przywróć stdout
        sys.stdout = sys.__stdout__
        null_output.close()

if __name__ == '__main__':
    # Użyj test runnera, który wypisuje wyniki na stdout
    runner = unittest.TextTestRunner(stream=sys.stdout, verbosity=2)
    unittest.main(testRunner=runner, exit=False)
