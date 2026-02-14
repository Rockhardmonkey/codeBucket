#Critical Thinking Mod 4 

Interest_value =[]
Original_values = []


for i in range(5):
    num=float(input('Please enter value: '))
    Original_values.append(num)
    Interest_value.append(num + num*.2)

total= sum(Original_values)
avg= total/5
Orig_max=max(Original_values)
Orig_min=min(Original_values)

print('\nFor original values:')
print('  Total is:',total,'\n  Average is:', avg, '\n  Maximum is:',Orig_max, '\n  Minimum is:',Orig_min,  )

in20_total=sum(Interest_value)
in20_avg=in20_total/5
in20_max=max(Interest_value)
in20_min=min(Interest_value)

print('\nFor values after 20% interest:')
print(' Original values with interest are:\n',Interest_value,'\n  Total is:',in20_total,'\n  Average is:', in20_avg, '\n  Maximum is:',in20_max, '\n  Minimum is:',in20_min,  )

#Critical Thinking Mod 5

def con_str_rev_3(string1, string2, string3):
    rev= string1 + string2 + string3
    return rev[::-1]

print(con_str_rev_3(input('Enter string 1: '), input('Enter string 2: '), input('Enter string 3: ')))

#Critical Thinking Mod 6

def main(A,B):
    AIn= 'Input Series "A" numbers with a single space between values: '
    BIn= 'Input Series "B" numbers with a single space between values: '
    A = [int(x) for x in input(AIn).split()]
    B = [int(x) for x in input(BIn).split()]
    AxB = [(a,b) for a in A for b in B]
    print('Cartesian Product(s): ',AxB)

if __name__ == '__main__':
    A = []
    B = []
    main(A,B)