import turtle

t = turtle.Turtle()

t.speed(0)

for i in range(50):
    t.forward(100)
    t.left(123)
    for j in range(4):
        t.forward(30)
        t.left(90)

turtle.exitonclick()