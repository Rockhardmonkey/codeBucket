import random
import time

affirmative=("It is certain", "It is Decidedly so", "Without a doubt","Yes Definitely","You may rely on it","As I see it, yes","Most likely","Outlook Good","Yes","Signs point to yes")
nonCommittal=("Reply hazy, try again","Ask again later","Better not to tell you now","Cannot predict now","Concentrate and ask again")
negative=("Don’t count on it","My reply is no","My sources say no","Outlook not so good","Very doubtful")

Exit= bool(False)

while Exit == False:
    print("What would you like to know? I will do my best to answer your yes or no questions. type 'exit' to close")
    question= input()

    if question == ("exit"):
        Exit = bool(True)
    
    else: 
        varlist=random.randint(1,3)
        if varlist == 1:
            answer= random.choice(affirmative)
        elif varlist == 2:
            answer= random.choice(nonCommittal)
        else:
            answer= random.choice(negative)
        print("let me see")
        time.sleep(1)
        print(answer)
        time.sleep(1)

print("Goodbye")
time.sleep(1)