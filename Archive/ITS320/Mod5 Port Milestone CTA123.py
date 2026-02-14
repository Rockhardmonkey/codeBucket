

''' Critical Thinking 1   Option 1 
Create Mouse Art
'''
print("          (\-. ")
print("          / _'>")
print(" _)      / _)=")
print("(       / _/")
print(" '-.___(___)_")


'''   Critical Thinking 2   Option 1
#Write a Python program that performs the following tasks:
#Read from the console an arbitrary string S of length less than 50 characters.
#In the first output line, print True if S has any alphanumeric characters. Otherwise, print False. 
#In the second line, print True if S has any alphabetical characters. Otherwise, print False. 
#In the third line, print True if S has any digits. Otherwise, print False. 
#In the fourth line, print True if S has any lowercase characters. Otherwise, print False. 
#In the fifth line, print True if S has any uppercase characters. Otherwise, print False.
#Develop Python code that implements the program requirements.
'''

S=''
while S =='':
    S=input('Need input of less than 50 characters!')

    if len(S)<51:
        for char in S:
                alnum= char.isalnum()
                if alnum == True:
                    print('Alphanumerical? True')
                    break
        if(alnum !=1):
            print('Alphanumerical? False')
        for char in S:
                al= char.isalpha()
                if al == True:
                    print('Alphabetical? True')
                    break
        if(al !=1):
            print('Alphabetical? False')    
        for char in S:
            dig= char.isdigit()
            if dig == True:
                print('Digits? True')
                break
        if(dig !=1):
            print('Digits? False')
        for char in S:
            low= char.islower()
            if low == True:
                print('Lowercase? True')
                break
        if(low !=1):
            print('Lowercase? False')
        for char in S:
            up= char.isupper()
            if up == True:
                print('Uppercase? True')
                break
        if(up !=1):
            print('Uppercase? False')
        S=''
        continue   

    if len(S)>50:
        print('That is more than 50 characters, good day.')
        break

'''   Critical Thinking 3   Option 1
#Implement a program that reads in a year and outputs the approximate value of a Ferrari 250 GTO 
#in that year. Use the following table that describes the estimated value of a GTO at different 
# times since 1962.
'''
ferrari_250_GTO={
    range(1962,1964):18500,
    range(1965,1968):6000,
    range(1969,1971):12000,
    range(1972,1975):48000,
    range(1976,1980):200000,
    range(1981,1985):650000,
    range(1986,2012):35000000,
    range(2013,2014):52000000,
}
ferGtoYr=''

print('enter "exit" to exit program')

while ferGtoYr !='exit':
    ferGtoYr= (input('What year Ferrari 250 GTO are you looking for? '))
    if ferGtoYr =='exit':
        print('Goodbye')
        break
    else:
        ferGtoYr= int(ferGtoYr)
        if 1962 <= ferGtoYr <=1964:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1962,1964)], sep='')
        elif 1965 <= ferGtoYr <=1968:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1965,1968)], sep='')
        elif 1969 <= ferGtoYr <=1971:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1969,1971)], sep='')
        elif 1972 <= ferGtoYr <=1975:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1972,1975)], sep='')
        elif 1976 <= ferGtoYr <=1980:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1965,1968)], sep='')
        elif 1981 <= ferGtoYr <=1985:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1981,1985)], sep='')
        elif 1986 <= ferGtoYr <=2012:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1986,2012)], sep='')
        elif 2013 <= ferGtoYr <=2014:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(2013,2014)], sep='')
        else:
            print('We do not have value for that year')