from collections import deque

class myStack: 
    def __init__(self):
        self.container=[]
    def push(self, data):
        self.container.append(data)
    def pop(self):
        return self.container.pop()
    def peek(self):
        return self.container[-1]
    def is_empty(self):
        return len(self.container)==0
    def size(self):
        return len(self.container)

myStack= deque()

myStack.append('Mouse')
print(myStack)

myStack.append('Dog')
print(myStack)

myStack.pop()
print(myStack)

myStack.push('Turtle')
print(myStack)
