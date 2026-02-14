
#Implement a program that reads in a year and outputs the approximate value of a Ferrari 250 GTO 
#in that year. Use the following table that describes the estimated value of a GTO at different 
# times since 1962.

ferrari_250_GTO={
    range(1962,1964):18500,
    range(1965,1968):6000,
    range(1969,1971):12000,
    range(1972,1975):48000,
    range(1976,1980):200000,
    range(1981,1985):650000,
    range(1986,2012):35000000,
    range(2013,2014):52000000,
}
ferGtoYr=''

print('enter "exit" to exit program')

while ferGtoYr !='exit':
    ferGtoYr= (input('What year Ferrari 250 GTO are you looking for? '))
    if ferGtoYr =='exit':
        print('Goodbye')
        break
    else:
        ferGtoYr= int(ferGtoYr)
        if 1962 <= ferGtoYr <=1964:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1962,1964)], sep='')
        elif 1965 <= ferGtoYr <=1968:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1965,1968)], sep='')
        elif 1969 <= ferGtoYr <=1971:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1969,1971)], sep='')
        elif 1972 <= ferGtoYr <=1975:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1972,1975)], sep='')
        elif 1976 <= ferGtoYr <=1980:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1965,1968)], sep='')
        elif 1981 <= ferGtoYr <=1985:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1981,1985)], sep='')
        elif 1986 <= ferGtoYr <=2012:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(1986,2012)], sep='')
        elif 2013 <= ferGtoYr <=2014:
            print('That approximate value of a ',ferGtoYr,' Ferrari 250 GTO is $',ferrari_250_GTO[range(2013,2014)], sep='')
        else:
            print('We do not have value for that year')