'''
Assignment Instructions:
Write a Python function that will work on three strings. The function will return to the user a concatenation of the string values in reverse order. The function is to be called from the main program.
In the main program, prompt the user for the three strings and pass these values to the function.
Assignment Deliverables:
Submit a text file containing your Python code. Name your file ITS320_CTA5.Option1.py.
'''

class B(Exception):
    pass

class C(B):
    pass

class D(C):
    pass

for cls in [B, C, D]:
    try:
        raise cls()
    except D:
        print("D")
    except C:
        print("C")
    except B:
        print("B")