
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

Animals.push('Turtle')
Animals.push('Dove')
Animals.push('Tiger')
print(Animals.peek())
Animals.push('Rhino')
print('\nAnimals Stack')
Animals.print_stack()

allAnimals.items = Animals.copy()

allAnimals.push('Unicorn')
print('\nAll Animals Stack')
allAnimals.print_stack()

print(Animals.pop())
Animals.print_stack()

while (not Animals.isEmpty()):
    print('Stack has ',Animals.size(),' remaining')
    print(Animals.pop())

if Animals.isEmpty():
        print('Stack is Empty')

print('\nThere we go')
print('\nAnimals Stack')
Animals.print_stack()
print('\nAll Animals Stack')
allAnimals.print_stack()