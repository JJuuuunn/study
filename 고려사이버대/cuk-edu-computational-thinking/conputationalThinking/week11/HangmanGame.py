from random import *

print(''' 행맨 게임을 시작합니다! 여러분에게는 11번의 기회가 주어집니다. \n 6자리 단어를 맞춰 보세요. 단어는 숨겨져 있습니다. 자 그럼 시작해 볼까요?''')

word_list = ['chance', 'action', 'beauty', 'castle']
word = word_list.pop(randrange(0, 4))

def hangman():
    fail = 0

    while (fail <= 11):
        guess = input('\n 어떤 알파벳이 포함되어 있을까요? 하나를 입력해보세요. \n =>')
        for char in word:
            if char == guess:
                print(char, end = ' ')
            else:
                print('_', end = ' ')


def hangman_Upgrade():

    fail = 0
    match = 0  # ‘_’가 몇 개 남아 있는지 개수 확인을 위한 변수 설정
    match_list = ' '

    while (fail <= 11):

        guess = input('\n 어떤 알파벳이 파함되어 있을까요? 하나를 입력해보세요. \n =>')
        match_list = match_list + guess

        for char in word:
            if char in match_list:  # match
                print(char, end=' ')
            else:
                print('_', end=' ')
                match = match + 1  # 실패 때마다 match 1 증가
        if guess not in word:
            fail = fail + 1
            print('기회는', 11 - fail, '번 남았습니다.\n')
        if match == 0:
            print('성공! 당신이 찾은 단어는 바로', word, '였습니다.')
            break  # 성공하면 멈추기
        elif fail == 11:
            print('실패했습니다!')
            break

        match = 0  # for문이 끝나면 match를 다시 초기화 하기

hangman()
# hangman_Upgrade()

