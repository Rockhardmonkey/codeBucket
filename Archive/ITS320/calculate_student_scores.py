

class TicTacToe:
    def __init__(self):
        self.board = [' ' for _ in range(9)]
        self.current_player = 'X'

    def display_board(self):
        for row in [self.board[i*3:(i+1)*3] for i in range(3)]:
            print('|'.join(row))
            print('-' * 5)

    def make_move(self, position):
        if self.board[position] == ' ':
            self.board[position] = self.current_player
            return True
        return False

    def check_win(self):
        win_conditions = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8],  # rows
            [0, 3, 6], [1, 4, 7], [2, 5, 8],  # columns
            [0, 4, 8], [2, 4, 6]              # diagonals
        ]
        for condition in win_conditions:
            if self.board[condition[0]] == self.board[condition[1]] == self.board[condition[2]] != ' ':
                return True
        return False

    def switch_player(self):
        self.current_player = 'O' if self.current_player == 'X' else 'X'

def main():
    students = [
        Student("Alice", [85, 90, 78]),
        Student("Bob", [92, 88, 84]),
        Student("Charlie", [70, 75, 80])
    ]

    for student in students:
        print(f"{student.name}'s average score is {student.average_score():.2f}")

    game = TicTacToe()
    while True:
        game.display_board()
        position = int(input(f"Player {game.current_player}, enter your move (0-8): "))
        if game.make_move(position):
            if game.check_win():
                game.display_board()
                print(f"Player {game.current_player} wins!")
                break
            game.switch_player()
        else:
            print("Invalid move. Try again.")
        if ' ' not in game.board:
            game.display_board()
            print("It's a tie!")
            break

if __name__ == "__main__":
    main()