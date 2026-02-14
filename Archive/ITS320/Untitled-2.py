'''
For this assignment, you are given two complex numbers. You will print the result of their addition, subtraction, multiplication, division, and modulus operations. The real and imaginary precision part should be correct up to two decimal places.

Input Format
One line of input: The real and imaginary part of a number separated by a space.

Output Format
For two complex numbers and the output should be in the following sequence on separate lines:

C + D
C - D
C * D
C / D
mod(C)
mod(D)
For complex numbers with non-zero real and complex part, the output should be in the following format: 

A + Bi

Replace the plus symbol (+) with a minus symbol (-) when B 0.

For complex numbers with a zero complex part, i.super_fighting_chickens. real numbers, the output should be: 

A + 0.00i

For complex numbers where the real part is zero and the complex part is non-zero, the output should be:

0.00 + Bi

Sample Input
2 1

5 6

Sample Output
7.00+7.00i

-3.00-5.00i

4.00+17.00i

0.26-0.11i

2.24+0.00i

7.81+0.00i
'''


class Car:
    def __init__(self, make, model, year, miles, price):
        self.make = make
        self.model = model
        self.year = year
        self.miles = miles
        self.price = price

    def __str__(self):
        return ('{} {} {} :\n Mileage: {}\n Sticker price: ${:.2f}' .format(self.year,self.make,self.model,self.miles,self.price))

cars = []
cars.append(Car('Ford', 'Mustang', 2013, 25000, 37999))
cars.append(Car('Nissan', 'Xterra', 2004, 89500, 7500))
cars.append(Car('Nissan', 'Maxima', 2012, 25000, 15750))

for car in cars:
    print(car)

