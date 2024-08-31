"""
Author: Huy Le
UID:770003751
File: wordSimilarity.py
Project:Calculating Text Statistics
Programming Language: Python
Task:4
Date:November 17th, 2023.
"""
from wordData import readWordFile
from math import sqrt
def similar(words,word1,word2):
    """
    This function gives the "similarity" between 2 words in a words dictionary
    based on this algorithm :
    - First, it makes a vector of counts for word1 throught the years, then
    it do the same for word2.
    - Then, it normalizes these 2 vectors by dividing them by their respective
    lengths - which is calculated by the square roots of the sums of their respective squared elements.
    - Finally, it returns the dot product of these 2 vectors, which the 2 words' similarity.
    :param words: A dictionary mapping words to dictionaries with years and counts.
    :param word1: the first word.
    :param word2: the second word.
    :return: the dot product of 2 vectors representing 2 words.
    """
    yearset=set(words[word1].keys()).union(set(words[word2].keys()))
    freq1=list(words[word1][year] if year in words[word1] else 0 for year in yearset)
    freq2=list(words[word2][year] if year in words[word2] else 0 for year in yearset)
    freq1_norm,freq2_norm=sqrt(sum(a**2 for a in freq1)),sqrt(sum(b**2 for b in freq2))
    freq=list(zip(freq1,freq2))
    dot=sum((x/freq1_norm)*(y/freq2_norm) for x,y in freq)
    return dot

def topSimilar(words,word):
    """
    This function returns a list of the top five words including the input word in the descending order of
    similarity. If there are less than five words in the file, then return as much words as you
    have. If there is no such a word in the words dictionary, return just one word, itself.
    :param words: a dictionary mapping words to dictionaries with years and counts.
    :param word: a word, for which we are looking for similar words.
    :return: a list of top 5 words including the input word in the decreasing order of similarity.
    """
    wordlist=list(words.keys())
    wordlist.sort(key=lambda x:similar(words,word,x))
    return wordlist[-1:-6:-1] if len(wordlist)>=5 else wordlist[::-1]

def main():
    filename=input('Enter word file: ')
    words=readWordFile(filename)
    word=input('Enter word: ')
    print(topSimilar(words,word))
    
if __name__=='__main__':
    main()
