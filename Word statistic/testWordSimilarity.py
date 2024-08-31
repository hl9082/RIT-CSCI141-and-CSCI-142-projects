"""
Project: TextStats
Task Four: Word Similarities

Test functions for the wordSimilarity module.

Author: Sean Strout (sps@cs.rit.edu)
Author: Aaron Deever atd@cs.rit.edu
Author: Eduardo Lima (lima@cs.rit.edu)
Author: Anton Selistskiy (amsvcs@rit.edu)
"""

from wordData import readWordFile

from wordSimilarity import topSimilar

def test(computed, expected):
    """
    test: (NatNum or Float)^5 (NatNum or Float)^5 -> Boolean
    effect: when computed is not equal to expected generate message
    """
    if computed == expected:
        print('OK')
        return True
    else:
        print('Got: ', computed, ', but expected', expected)
        return False

def testFileName(filename, in_word, expected):
    """
    testFileName: String NatNum (NatNum or Float)^5 -> Boolean
    """
    print(f'Testing {filename}: ', end="")
    words = readWordFile(filename)
    return test(topSimilar(words, in_word), expected)

def testAll():
    """
    testAll: Void -> NoneType
    """
    passed = True
    passed = testFileName('very_short.txt', 'airport', ['airport', 'wandered', 'request']) and passed
    passed = testFileName('a.txt', 'adj', ['adj', 'antitrust', 'adenosine', 'adenomas', 'ambulatory']) and passed
    passed = testFileName('all.txt', 'robot', ['robot', 'robots', 'robotics', 'neuroendocrine', 'programmable']) and passed
    print()

    result = 'Passed all tests.' if passed else 'Test(s) failed.'
    print(f'\n{result}')


if __name__ == '__main__':
    testAll()
