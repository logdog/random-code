import urllib.request
import urllib.parse
import pprint
import re

class Team:

	def __init__(self, name, url):
		self.name = name
		self.url = url

		self.wins = 0
		self.losses = 0
		self.ties = 0
		self.points = 3 * self.wins + self.ties

		self.goals_for = 0
		self.goals_against = 0
		self.goals_diff = self.goals_for - self.goals_against

	def load(game):
		pass

if __name__ == "__main__":

	url = "https://www.timbers.com/schedule"
	values = {'month': 'all', 'year': '2017'}
	data = urllib.parse.urlencode(values).encode('utf-8')

	req = urllib.request.Request(url, data)

	with urllib.request.urlopen(req) as response:
		# https://www.soundersfc.com/schedule?month=all&year=2017&club_options=11
		res = str(response.read()).replace(r'\n', '')


	p = re.compile(r'<ul class="schedule_list list-reset">.*?/ul>')
	m = p.findall(res)[0]

	p2 = re.compile(r'<li class="row row_no_padding">.*?/li>')
	m2 = p2.findall(m)

	gametype_p = re.compile(r'<span class="match_competition ">(.*?)<')
	result_p   = re.compile(r'<span class="match_result">(.*?)<')
	date_p     = re.compile(r'<div class="match_date">(.*?)<')
	opponent_p = re.compile(r'<div class="match_matchup">(.*?)<') # check for 'at' in the beginning of this string for location


	for match in m2:

		# print(match.replace('<', '\n<'), end="\n\n***\n\n")
		result_m = result_p.findall(match)
		date_m = date_p.findall(match)
		opponent_m = opponent_p.findall(match)
		gametype_m = gametype_p.findall(match)

		print("date: " + ''.join(date_m))
		print("type: " + ''.join(gametype_m))
		print("opponent: " + ''.join(opponent_m))
		print("result: " + ''.join(result_m))
		print("\n\n")






	# 	p = re.compile(r'<h4><span class="mw-headline" id="Matches">Matches</span>.*?/h4>(.*?)<h3>')
	# 	m = p.findall(res)

	# 	for t in m:



	# 		p2 = re.compile(r'div class="vevent">.*?</div>')
	# 		m2 = p2.findall(t)
	# 		for t2 in m2:

	# 			# pprint.pprint(t2)
	# 			# print("\n**********\n")

	# 			p3 = re.compile(r'<table.*?><tr>(.*?)/tr></table>')
	# 			m3 = p3.findall(t2)

	# 			for t3 in m3:

	# 				t3 = t3.replace(r'\n', '')

	# 				p4 = re.compile(r'<td.*?>.*?/td>')
	# 				m4 = p4.findall(t3)

	# 				d = {}

	# 				# print(t3, end='\n\n======\n\n')

	# 				for t4 in m4:

	# 					s += t4 + '\n'
						
	# 					print(t4, end='\n*******\n')

	# 				print("\n\n=======\n\n")
	# 				# pprint.pprint(t3)
	# 				# print("\n\n&&&&&&&&&&\n\n")

	# 		#pprint.pprint(t)

	# 		# print("\n\n\n\n\n==========\n\n\n\n\n")

	# with open("tables.html", "w") as f:
	# 	f.write(s)











