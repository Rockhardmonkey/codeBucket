
#       Option 1
#Write a Python program that performs the following tasks:
#Read from the console an arbitrary string S of length less than 50 characters.
#In the first output line, print True if S has any alphanumeric characters. Otherwise, print False. 
#In the second line, print True if S has any alphabetical characters. Otherwise, print False. 
#In the third line, print True if S has any digits. Otherwise, print False. 
#In the fourth line, print True if S has any lowercase characters. Otherwise, print False. 
#In the fifth line, print True if S has any uppercase characters. Otherwise, print False.
#Develop Python code that implements the program requirements.


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
