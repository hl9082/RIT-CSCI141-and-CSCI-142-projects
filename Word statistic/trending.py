"""
Author: Huy Le
UID:770003751
File: trending.py
Project:Calculating Text Statistics
Programming Language: Python
Task:3
Date:November 17th, 2023.
"""
from wordData import readWordFile
def trending(words,startYr,endYr):
    """
    This function returns a list containing a tuple (Word, WordTrend) entry for each word for which qualifying
    data exists. The list is sorted in decreasing trend value.
    The trend value for each word should be calculated according to the following formula:
    - For each word in the words dictionary, a WordTrend object is created for that word only if that
    word has a yearly count of at least 1000 for both the starting year and the ending year.
    - Assuming that the word meets this requirement, the trend value for that word is calculated
    as the ratio of the number of times the word appeared in the ending year divided by the
    number of times the word appeared in the starting year.
    
    :param words: A dictionary mapping words to dictionaries with years and counts.
    
    :param startYr: An integer, the starting year for the computation of the trending words.
    
    :param endYr: An integer, the ending year for the computation of the trending words.
    
    :return: A list containing a tuple (Word, WordTrend) entry for each word for which qualifying
    data exists. The list is sorted in decreasing trend value.
    """
    res=[(word,words[word][endYr]/words[word][startYr])
         for word in words
         if min(words[word][startYr] if startYr in words[word] else 0,
                words[word][endYr] if endYr in words[word] else 0)>=1000]
    res.sort(key=lambda x:x[1],reverse=True)
    return res

def main():
    filename=input('Enter word file: ')
    words=readWordFile(filename)
    startYr=int(input('Enter start year: '))
    endYr=int(input('Enter end year: '))
    print('The top 10 trending words from ',startYr,' to ',endYr,
          ' :')
    lst=trending(words,startYr,endYr)
    n=10 if len(lst)>=10 else len(lst)
    for i in range(n):
        print(lst[i][0],end='\n')
    print('\nThe bottom 10 trending words from ',startYr,' to ',endYr,
          ' :')
    for i in range(-1,-11,-1):
        print(lst[i][0],end='\n')

if __name__=='__main__':
    main()