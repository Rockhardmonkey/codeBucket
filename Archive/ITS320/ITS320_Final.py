'''
Create a final program that meets the requirements outlined below.

Create an automobile class that will be used by a dealership as a vehicle inventory program. The following attributes should be present in your automobile class:

private string make
private string model
private string color
private int year
private int mileage
Your program should have appropriate methods such as:

constructor
add a new vehicle
remove a vehicle
update vehicle attributes
At the end of your program, it should allow the user to output all vehicle inventory to a text file.
'''

class Automobile:
    def __init__(self, make, model, color, year, mileage):
        self.inventory = []
        self.make = make
        self.model = model
        self.color = color
        self.year = year
        self.mileage = mileage

    def __str__(self):
        return f'{self.make} {self.model} {self.color} {self.year} {self.mileage}'
    
    def add_vehicle(self, make, model, color, year, mileage):
        vehicle = Automobile(make, model, color, year, mileage)
        self.inventory.append(vehicle)

    def remove_vehicle(self):
        print('Inventory:')
        for i, vehicle in enumerate(self.inventory):
            print(f'{i}: {vehicle}')
        index = int(input('Enter the index of the vehicle you would like to remove: '))
        self.inventory.pop(index)
    
    def update_vehicle(self, make, model, color, year, mileage):
        print('\nInventory:')
        for i, vehicle in enumerate(self.inventory):
            print(f'{i}: {vehicle}')
        index = int(input('Enter the index of the vehicle you would like to update: '))
        self.inventory[index].make = make
        self.inventory[index].model = model
        self.inventory[index].color = color
        self.inventory[index].year = year
        self.inventory[index].mileage = mileage
    
    def print_inventory(self):
        print('\nInventory:')
        for vehicle in self.inventory:
            print(vehicle)
        print('\n')
    
    def write_to_file(self):
        with open('inventory.txt', 'w') as file:
            for vehicle in self.inventory:
                file.write(f'{vehicle}\n')

def main():
    auto = Automobile('', '', '', 0, 0)
    user_input = ''
    print('Welcome to the dealership inventory program\n')
    while user_input != 'exit':
        user_input = input('What would you like to do? \n you can type "add", "remove", "update", "print", "export" or "exit": ')
        if user_input == 'add':
            auto.add_vehicle(input('Enter make: '), input('Enter model: '), input('Enter color: '), input('Enter year: '), input('Enter mileage: '))
            print('Vehicle added to inventory\n')
        elif user_input == 'remove':
            auto.remove_vehicle()
        elif user_input == 'update':
            print('\nCurrent Inventory:')
            for i, vehicle in enumerate(auto.inventory):
                print(f'{i}: {vehicle}')
            auto.update_vehicle(input('Enter new make: '), input('Enter new model: '), input('Enter new color: '), input('Enter new year: '), input('Enter new mileage: '))
        elif user_input == 'exit':
            print('Goodbye')
        elif user_input == 'print':
            auto.print_inventory()
        elif user_input == 'export':
            auto.write_to_file()
            print('Inventory exported to inventory.txt\n')
        else:
            print('Invalid input try again\n')
    
if __name__ == '__main__':
    main()
