import unittest
import sys
import io
from user_code import reverse_string

class TestReverseString(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.user_output = io.StringIO()
        original_stdout = sys.stdout
        sys.stdout = cls.user_output

        # Call the user's function once to capture the output
        reverse_string("hello")

        # Save the user's output to a file
        with open('user_output.txt', 'w') as f:
            f.write(cls.user_output.getvalue())

        # Restore stdout
        sys.stdout = original_stdout

    def test_reverse_string(self):
        # Redirect stdout to null during tests
        null_output = open('/dev/null', 'w')
        sys.stdout = null_output

        test_cases = [
            ("hello", "olleh"),
            ("world", "dlrow"),
            ("OpenAI", "IAnepO"),
            ("Python", "nohtyP"),
            ("", "")
        ]
        for input_str, expected in test_cases:
            result = reverse_string(input_str)
            self.assertEqual(result, expected, f"Failed for input: '{input_str}'")

        # Restore stdout
        sys.stdout = sys.__stdout__
        null_output.close()

if __name__ == '__main__':
    # Use a test runner that writes to stdout
    runner = unittest.TextTestRunner(stream=sys.stdout, verbosity=2)
    unittest.main(testRunner=runner, exit=False)
