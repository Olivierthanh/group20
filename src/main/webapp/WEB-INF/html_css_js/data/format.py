import os

with open("map.txt", mode="r", encoding="utf8") as f:
    lines = [line.strip().split("\t") for line in f.readlines()]
    print(lines)
    mapping = [(line[0].split("/")[-1], line[1]) for line in lines]
    f.close()

for i, (src, label) in enumerate(mapping):
    os.rename(f"img/{src}.png", str(i + 1))
