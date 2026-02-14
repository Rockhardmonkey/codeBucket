
class Stack: 
    def __init__(self):
        self.items=[]
    def push(self, items):
        self.items.append(items)
    def pop(self):
        return self.items.pop() 
    def isEmpty(self):
        return self.size()==0
    def size(self):
        return len(self.items)
    def print_stack(self):
        print(self.items)
    def peek(self):
        return self.items[-1]
    def copy(self):
        return self.items.copy()
    
Animals =Stack()
allAnimals =Stack()

#Initiate Animal list with a 'Peek' Example 
Animals.push('Turtle')
Animals.push('Dove')
Animals.push('Tiger')
#'Peek' Method
print(Animals.peek())
Animals.push('Rhino')
#Shows Animal List as is before copy
print('\nAnimals Stack')
Animals.print_stack()

#Copy List method
allAnimals.items = Animals.copy()
#Adding item to new list idependent of prev list
allAnimals.push('Unicorn')
print('\nAll Animals Stack')
allAnimals.print_stack()

#Heading back to orginal list showing 'Pop' method
print(Animals.pop())
Animals.print_stack()

#Example dumping list, with countdown and pop funcionality
while (not Animals.isEmpty()):
    print('Stack has ',Animals.size(),' remaining')
    print(Animals.pop())

if Animals.isEmpty():
        print('Stack is Empty')

print('\nThere we go')

#Verification of both list being idependent and that first list was dumped
print('\nAnimals Stack')
Animals.print_stack()
print('\nAll Animals Stack')
allAnimals.print_stack()