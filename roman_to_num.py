# This program will take a roman numeral input and find the numerical equivalent


def increment_val():
    global current_value

    if current_value == 1:
        current_value = 5
    elif current_value == 5:
        current_value = 10


def convert_to_val(to_convert):
    if to_convert == 'I':
        return 1
    elif to_convert == 'V':
        return 5
    elif to_convert == 'X':
        return 10


roman_num = raw_input("Give a roman numeral (ex. VII, IX XIV): ")

current_value = convert_to_val(roman_num[-1])
result = 0

while len(roman_num) > 0:
    multiplier = 1
    to_add = convert_to_val(roman_num[-1])

    if to_add > current_value:
        increment_val()
    if to_add < current_value:
        multiplier = -1

    result += multiplier * to_add

    roman_num = roman_num[:-1]

print result
