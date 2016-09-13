from itertools import permutations
n = list(map(str, input().strip().split(" ")))
[print("".join(i)) for i in sorted(list(permutations(list(n[0]),int(n[1]))))]
