
# Here it the Weekly Payroll Dictionary
payrollWeekly ={
    'emp1' : 500,
    'emp2' : 300,
    'emp3' : 700,
    'emp4' : 400,
    'emp5' : 600,
    'emp6' : 550,
}

#This is out Sum function
payrollWeekTotal = sum(payrollWeekly.values())

print('Weekly payroll total is:',payrollWeekTotal,'\n')

#This is out Subtraction Function
print('Removing the 2 lowest paid employees would bring our payroll to:', payrollWeekTotal - payrollWeekly['emp2'] - payrollWeekly['emp4'],'\n')

#This is our Multiplication Function
print('Our yearly payroll expense would be', payrollWeekTotal * 50,'\n'),

#This is our Division Function
print('Our daily payroll expense would be:', payrollWeekTotal/5,'\n'),

#This is our Sort Function
sortedPayroll = sorted(payrollWeekly.items(), key=lambda x:x[1])
print('Here is our cost of payroll per employee from lowest to highest:',sortedPayroll,'\n'),

#Pseudocode for 5% increase

#OBTAIN payrollWeekly dictionary
#   CALCULATE new value with 5% increase from current value
#   REDEFINE payrollWeekly dictionary with new values

# Average Salary
print('The average employee weekly salary is:',payrollWeekTotal/len(payrollWeekly),'\n')
