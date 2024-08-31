
"""
Author: Huy Le
UID:770003751
File: wordData.py
Project:Calculating Text Statistics
Programming Language: Python
Task:0
Date:November 17th, 2023.
"""
def readWordFile(fileName):
    """
    This function reads a file, then returns a dictionary mapping words to dictionaries.
    The ”inner” dictionary associated with each word will use years as keys and counts as values.
    
    :param fileName: A string giving the name of a unigram data file. The readWordFile
     function assumes that the provided filename belongs to a file in the data folder. The
     function prepends the string ’data/’ to the given filename before attempting to
     open it.
     
    :return:A dictionary mapping words to dictionaries. The ”inner” dictionary associated
     with each word will use years as keys and counts as values.
    """
    res=dict()
    fileName="data/"+fileName
    with open(fileName) as f:
        lines=f.readlines()
        var=None
        for line in lines:
            if not line.isnumeric() and not "," in line:
                var=line.strip()
                res[var]=dict()
            elif "," in line:
                year,freq=line.strip().split(",")
                res[var][int(year)]=int(freq)
            elif line.isnumeric():
                res[var][int(line)]=0
    return res

def totalOccurrences(word,words):
    """
    This function returns the total number of times that a word has appeared in print.
    :param word: The word for which to calculate the count. Not guaranteed to exist in words.
    :param words: words: A dictionary mapping words to dictionaries with years and counts.
    :return: The total number of times that a word has appeared in print.
    """
    res=sum(x for x in words[word].values())
    return res

def main():
    filename=input('Enter word file: ')
    word=input('Enter word: ')
    words=readWordFile(filename)
    print('Total occurrences of ',word,': ',
          totalOccurrences(word,words))
    
if __name__=='__main__':
    main()
