"""
Author: Huy Le
UID:770003751
File: printedWords.py
Project:Calculating Text Statistics
Programming Language: Python
Task:2
This program also plots the number of printed words as a function of the year.
Date:November 17th, 2023.
"""
from wordData import *
import matplotlib.pyplot as plt
def printedWords(words):
    """
    This function returns a list containing tuples (year, count total words) for each year for which data
    exists. The list must be sorted in ascending order of year.
    :param words: A dictionary mapping words to dictionaries with years and counts.
    :return: A list containing tuples (year, count total words) for each year for which data
    exists. The list must be sorted in ascending order of year.
    """
    dct={year:0 for word in words for year in words[word]}
    for word in words:
        for year in words[word]:
            if year not in dct:
                dct[year]=0
            dct[year]+=words[word][year]
    return sorted(dct.items(),key=lambda x:x[0])

def wordsForYear(year,yearList):
    """
    This function returns an integer count representing the total number of printed words for that year.
    If there is no entry in yearList for the requested year, the function returns 0.
    :param year: an integer representing the year being queried.
    :param yearList:a list of tuples (year, count total words), as defined in the previous
    function. The list must be sorted in ascending order of year.
    :return: an integer count representing the total number of printed words for that year.
    """
    return sum(x for y,x in yearList if y==year)

def main():
    filename=input('Enter word file: ')
    words=readWordFile(filename)
    year=int(input('Enter year: '))
    tuplst=printedWords(words)
    yearList=[y for y,x in tuplst]
    print('Total printed words in ',year,' : ',wordsForYear(year,
                                                            tuplst))
    list_of_counts=[wordsForYear(year,tuplst) for year,c in
                    tuplst]
    plt.plot(yearList, list_of_counts)
    plt.show()
    
if __name__=='__main__':
    main()