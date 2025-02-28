import turtle as t

t.shape('turtle')
angle = 90
# t.speed(0) # 속도 빠르게


def repeat(n):
    t.forward(n)
    t.left(angle)
    repeat(n + 5)


repeat(5)
