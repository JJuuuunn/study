import turtle
import random

screen = turtle.Screen()
screen.title("Interactive Art Project")
screen.bgcolor("black")
screen.setup(width=800, height=600)

colors = ["red", "blue", "green", "yellow", "orange", "purple", "white", "cyan", "magenta"]

def draw_circle(x, y):
    circle = turtle.Turtle()
    circle.penup()
    circle.speed(0)
    circle.goto(x, y)
    circle.shape("circle")

    color = random.choice(colors)
    circle.color(color)

def draw_circle_Upgrade(x, y):
    circle = turtle.Turtle()
    circle.penup()
    circle.speed(0)
    circle.shape("circle")

    color = random.choice(colors)
    circle.color(color)

    for size in range(1, 51):
        circle.goto(x, y)
        circle.shapesize(size / 10)

def draw_circle_Upgrade_2(x, y):
    circle = turtle.Turtle()
    circle.penup()
    circle.speed(0)
    circle.shape("circle")

    color = random.choice(colors)
    circle.color(color)

    max_size = random.randint(10, 50)

    for size in range(1, max_size):
        circle.goto(x, y)
        circle.shapesize(size / 10)

# screen.onclick(draw_circle)
# screen.onclick(draw_circle_Upgrade)
screen.onclick(draw_circle_Upgrade_2)