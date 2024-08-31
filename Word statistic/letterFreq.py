"""
Author: Huy Le
UID:770003751
File: letterFreq.py
Project:Calculating Text Statistics
Programming Language: Python
Task:1
This program also plots the distribution of the alphabetical characters'counts decreasingly.
Date:November 17th, 2023.
"""   
from wordData import readWordFile,totalOccurrences
import matplotlib.pyplot as plt
def getfreq(words):
    """
    This function returns a string of characters in the alphabet that are sorted
    decreasingly based on their occurrences in the words dictionary.
    :param words: A dictionary mapping words to dictionaries with years and counts.
    :return: a string of characters.
    """
    pairs=sorted(words.items(),key=lambda x:x[1]
                 ,reverse=True)
    res="".join(str(x[0]) for x in pairs)
    return res

def getoccurrencedict(words):
    """
    This function gets the occurrences of each character in the alphabet in each word in the words dictionary.
    :param words: A dictionary mapping words to dictionaries with years and counts.
    :return: a dictionary mapping characters to their occurrences.
    """
    worddict={chr(a):0 for a in range(ord('a'),ord('z')+1)}
    for word in words:
        c=totalOccurrences(word,words)
        for x in word:
            worddict[x]+=c
    return worddict

def letterFreq(words):
    """
    This function returns a string containing the 26 lowercase characters in the English alphabet, sorted in
    decreasing order of frequency of occurrence of each character.
    :param words:A dictionary mapping words to dictionaries with years and counts.
    :return: A string containing the 26 lowercase characters in the English alphabet, sorted in
    decreasing order of frequency of occurrence of each character.
    """
    return getfreq(getoccurrencedict(words))

def main():
    filename=input('Enter word file: ')
    words=readWordFile(filename)
    letters=getoccurrencedict(words)
    letter_count=getfreq(letters)
    print("Letters sorted by decreasing frequency:",
          letter_count)
    plt.bar(list(letters.keys()), list(letters.values()), color='skyblue')
    plt.show()
    
if __name__=='__main__':
    main()