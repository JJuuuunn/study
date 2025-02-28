import turtle as t

t.shape('turtle')
count = 10
dt = 10
angle = 90

for i in range(count):
    t.forward(dt)
    t.left(angle)
    dt = dt + 5

# 창이 꺼지지 않게 하기 위한 명령어 추가
t.exitonclick()