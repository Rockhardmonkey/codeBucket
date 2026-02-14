
'''Assignment Instructions:
Write a program that utilizes a loop to read a set of five floating-point values from user input. Ask the user to enter the values, then print the following data:

Total
Average
Maximum
Minimum
Interest at 20% for each original value entered by the user.
Use the formula: Interest_Value = Original_value + Original_value*0.2

Assignment Deliverables:
Submit a text file containing your Python code. Name your file ITS320_CTA4_Option1.py.
'''

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


