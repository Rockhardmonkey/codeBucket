def calculate_salary(hourly_rate, hours_worked):
    if hours_worked <= 40:
        salary = hourly_rate * hours_worked
    else:
        # Calculate regular pay for 40 hours
        regular_pay = hourly_rate * 40
        # Calculate overtime pay for extra hours (assuming 1.5 times hourly rate)
        overtime_pay = (hours_worked - 40) * (hourly_rate * 1.5)
        # Total salary
        salary = regular_pay + overtime_pay
    return salary

def main():
    hourly_rate = float(input("Enter hourly rate: "))
    hours_worked = float(input("Enter number of hours worked in week: "))

    salary = calculate_salary(hourly_rate, hours_worked)

    print("Weekly salary is: $", salary)

if __name__ == "__main__":
    main()
