import random

NUMBER_OF_COUPLES = 4


class Person:
    def __init__(self, _name):
        self.name = _name
        self.engaged = False
        self.proposals = []
        self.iterator = 0
        self.ranking = []

    def addRanking(self, _ranking):
        self.ranking = _ranking

    def addProposal(self, _female):
        self.proposals.append(_female)

    def clearProposals(self):
        self.proposals = []

def areAllNotEngaged(_women):
    for w in _women:
        if not w.engaged:
            return True
    return False

females = []
males = []

filePath = input("Enter file path or hit 'c' for console input: ")
auto = False
file = None
RANDOM = False
if filePath != 'c':
    if filePath == 'random':
        filePath = 'matches.txt'
        RANDOM = True
    file = open(filePath, 'r')
    auto = True

if auto:
    NUMBER_OF_COUPLES = int(file.readline())
    file.readline()
    file.readline()
    for _ in range(NUMBER_OF_COUPLES):
        s = file.readline()
        name = s.split(': ')[0].lstrip()
        males.append(Person(name))
    file.readline()
    file.readline()
    for _ in range(NUMBER_OF_COUPLES):
        s = file.readline()
        name = s.split(': ')[0].lstrip()
        females.append(Person(name))

    # Goes to males
    file.seek(0)
    file.readline()
    file.readline()
    file.readline()

    for man in males:
        str = (file.readline().rstrip('\n'))
        lsStr = str.replace(man.name + ': ', '').lstrip()
        ls = lsStr.split(', ')
        if RANDOM:
            random.shuffle(ls)
        for word in ls:
            for female in females:
                    if word == female.name:
                        man.ranking.append(female)
                        break


    file.readline()
    file.readline()

    for female in females:
        str = (file.readline().rstrip('\n'))
        lsStr = str.replace(female.name + ': ', '').lstrip()
        ls = lsStr.split(', ')
        if RANDOM:
            random.shuffle(ls)
        for word in ls:
            for man in males:
                if word == man.name:
                    female.ranking.append(man)
                    break

if not auto:
    NUMBER_OF_COUPLES = int(input("Number of Couples: "))

name = ''
for _ in range(NUMBER_OF_COUPLES):
    if not auto:
        name = input('Enter female name: ')
        females.append(Person(name))

for _ in range(NUMBER_OF_COUPLES):
    if not auto:
        name = input('Enter male name: ')
        males.append(Person(name))

if not auto:
    print('Enter suitors in order for...' )

for lady in females:
    num = 1
    for _ in range(int(NUMBER_OF_COUPLES)):
        if not auto:
            name = input("{}'s #{} choice: ".format(lady.name, num))
            if name == 'done':
                break
            man = None
            for dude in males:
                if dude.name == name:
                    man = dude
                    break
            lady.ranking.append(man)
        num += 1

if not auto:
    print('Enter suitors in order for...')

for man in males:
    num = 1
    for _ in range(int(NUMBER_OF_COUPLES)):
        if not auto:
            name = input("{}'s #{} choice: ".format(man.name, num))
        if name == 'done':
            break
            lady = None
            for girl in females:
                if girl.name == name:
                    lady = girl
                    break
            man.ranking.append(lady)
        num += 1

while areAllNotEngaged(females):

    # each female proposes to her top male, (male gets lady's proposal)
    for lady in females:
        if not lady.engaged:
            lady.ranking[lady.iterator].addProposal(lady)

    # male selects top proposal, adds iterator to unwanted suitors
    for man in males:
        lowestNumber = NUMBER_OF_COUPLES
        for lady in man.proposals:
            number = man.ranking.index(lady)
            if number < lowestNumber:
                lowestNumber = number
        if lowestNumber != NUMBER_OF_COUPLES:
            # print('MAN: \t', man.name, '\tSuitor: \t', man.ranking[lowestNumber].name)
            for lady in man.proposals:
                lady.iterator += 1
                lady.engaged = False
            man.ranking[lowestNumber].engaged = True
            man.ranking[lowestNumber].iterator -= 1
            man.clearProposals()
            man.addProposal(man.ranking[lowestNumber])

print
for man in males:
    print("{} {} {}".format(man.name, "should marry", man.proposals[0].name))