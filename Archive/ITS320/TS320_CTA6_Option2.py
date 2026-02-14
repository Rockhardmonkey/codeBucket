'''
Compute their Cartesian product, AxB of two lists. Each list has no more than 10 numbers.

For example, if the user supplies the two input lists:
A = [1,2]
B = [3,4]

then the Cartesian product output should be:
AxB = [(1,3),(1,4),(2,3),(2,4)]
'''

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