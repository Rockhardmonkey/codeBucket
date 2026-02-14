'''
Assignment Instructions:
Write a Python function that will work on three strings. The function will return to the user a concatenation of the string values in reverse order. The function is to be called from the main program.
In the main program, prompt the user for the three strings and pass these values to the function.
Assignment Deliverables:
Submit a text file containing your Python code. Name your file ITS320_CTA5.Option1.py.
'''

def rev_con_str(string1, string2, string3):
    return string3 + string2 + string1

print(rev_con_str(input('Enter string 1: '), input('Enter string 2: '), input('Enter string 3: ')))