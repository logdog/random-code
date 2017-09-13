import urllib.request
import urllib.parse
import re
import sys

class Team:

	def __init__(self, name, url, lang='en'):
		self.name = name
		self.url = url
		self.lang = lang

		self.wins = 0
		self.losses = 0
		self.ties = 0
		self.points = 3 * self.wins + self.ties

		self.goals_for = 0
		self.goals_against = 0
		self.goals_diff = self.goals_for - self.goals_against

		self.home_goals_for = 0
		self.home_goals_against = 0
		self.home_goals_diff = 0

		self.away_goals_for = 0
		self.away_goals_against = 0
		self.away_goals_diff = 0

		self.fouls = 0 # 1
		self.yellows = 0 # 3
		self.db_yellows = 0 # additional 3
		self.hard_reds = 0 # 7
		self.coach_ejections = 0 # 7
		self.sup_dis = 0 # 8
		self.disciplinary_points = 0

		self.games_played = 0

	def load(self, game):
		# game is a dict with the game's type, opponent, and result
		if not game['type'] == 'MLS' or not game['result']:
			return

		away_game = bool(game['opponent'][:2] == 'at' or game['opponent'][0] == '@')

		def getResult():
			result = game['result']
			if result == "DRAW" or result == "Match nul":
				return ("DRAW", 0, 0)

			if len(result.split(" ")) == 1: # forgot to say win draw loss
				result = "DRAW " + result
			elif len(result.split(" ")) == 3: # en francais
				result = "DRAW " + result.split(" ")[2]

			if result.split(" ")[0] == "Victoire":
				result = "WIN " + result.split(" ")[1]
			elif result.split(" ")[0] == "D\\xc3\\xa9faite":
				result = "LOSS " + result.split(" ")[1]
			
			first_points = int(result.split(" ")[1][0])
			second_points = int(result.split(" ")[1][-1]) # includes handler for shootout score WIN 1(5)-(3)1
			return (result.split(" ")[0], first_points, second_points)

		result, first_points, second_points = getResult()
		
		if result == 'WIN':
			self.wins += 1
			if not away_game:
				self.home_goals_for += first_points
				self.home_goals_against += second_points
			else:
				self.away_goals_for += first_points
				self.away_goals_against += second_points
		elif result == 'DRAW':
			self.ties += 1
			if not away_game:
				self.home_goals_for += first_points
				self.home_goals_against += second_points
			else:
				self.away_goals_for += first_points
				self.away_goals_against += second_points
		else:
			self.losses += 1
			if not away_game:
				self.home_goals_for += second_points
				self.home_goals_against += first_points
			else:
				self.away_goals_for += second_points
				self.away_goals_against += first_points

		self.games_played += 1
		self.tabulate()

	def tabulate(self):
		self.points = 3 * self.wins + self.ties
		self.goals_for = self.home_goals_for + self.away_goals_for
		self.goals_against = self.home_goals_against + self.away_goals_against
		self.home_goals_diff = self.home_goals_for - self.home_goals_against
		self.away_goals_diff = self.away_goals_for - self.away_goals_against
		self.goals_diff = self.goals_for - self.goals_against

	def __str__(self):
		s = self.name + "\n"

		s += "{:20} {:>5}\n".format("Points:", self.points)
		s += "{:20} {}-{}-{}\n".format("Record:", self.wins, self.ties, self.losses)
		s += "{:20} {:>5}\n".format("Goals Diff:", self.goals_diff)
		s += "{:20} {:>5}\n".format("Goals For:", self.goals_for)
		s += "{:20} {:>5}\n".format("Disciplinary Points:", self.disciplinary_points)
		s += "{:20} {:>5}\n".format("Away Diff:", self.away_goals_diff)
		s += "{:20} {:>5}\n".format("Away GF:", self.away_goals_for)
		s += "{:20} {:>5}\n".format("Home Diff:", self.home_goals_diff)
		s += "{:20} {:>5}\n".format("Home GF:", self.home_goals_for)

		return s


if __name__ == "__main__":

	seattle_sounders = Team("Seattle Sounders FC", 'soundersfc')
	portland_timbers = Team("Portland Timbers", 'timbers')
	fc_dallas = Team("FC Dallas", "fcdallas")

	western_teams = [Team("Seattle Sounders FC", 'soundersfc.com'), Team("Portland Timbers", 'timbers.com'), 
	Team("FC Dallas", "fcdallas.com"), Team("Houston Dynamo FC", 'houstondynamo.com'),
	Team("Vancouver Whitecaps FC", "whitecapsfc.com"), Team("Minnesota United FC", "mnufc.com"),
	Team("Real Salt Lake", "rsl.com"), Team("Colorado Rapids", "coloradorapids.com"),
	Team("LA Galaxy", "lagalaxy.com"), Team("San Jose Earthquakes", "sjearthquakes.com"), 
	Team("Sporting Kansas City", "sportingkc.com")]

	eastern_teams = [Team("Philidelphia Union", "philadelphiaunion.com"), Team("Atlanta United FC", "atlutd.com"), 
	Team("Chicago Fire", "chicago-fire.com"), Team("Columbus Crew SC", "columbuscrewsc.com"),
	Team("Montreal Impact", "impactmontreal.com", "fr"), Team("New England Revolution", "revolutionsoccer.net"),
	Team("New York City FC", "nycfc.com"), Team("New York Red Bulls", "newyorkredbulls.com"),
	Team("Orlando City", "orlandocitysc.com"), Team("DC United", "dcunited.com"),
	Team("Toronto FC", "torontofc.ca")]

	for team in set(western_teams) | set(eastern_teams):

		url = "https://www.{}/schedule?month=all&year=2017".format(team.url)
		# values = {'month': 'all', 'year': '2017'}
		# data = urllib.parse.urlencode(values).encode('utf-8')

		req = urllib.request.Request(url)

		with urllib.request.urlopen(req) as response:
			# https://www.soundersfc.com/schedule?month=all&year=2017
			res = str(response.read()).replace(r'\n', '')


		p = re.compile(r'<ul class="schedule_list list-reset">.*?/ul></div>')
		try:
			m = p.findall(res)[0]
		except IndexError:
			raise IndexError("Could not find schedule")

		p2 = re.compile(r'<li class="row row_no_padding">.*?/li>')
		m2 = p2.findall(m)

		gametype_p = re.compile(r'<span class="match_competition ">(.*?)<')
		result_p   = re.compile(r'<span class="match_result">(.*?)<')
		date_p     = re.compile(r'<div class="match_date">(.*?)<')
		opponent_p = re.compile(r'<div class="match_matchup">(.*?)<') # check for 'at' in the beginning of this string for location

		for match in m2:

			result_m = result_p.findall(match)
			date_m = date_p.findall(match)
			opponent_m = opponent_p.findall(match)
			gametype_m = gametype_p.findall(match)

			game = {
				'type': ''.join(gametype_m).strip(),
				'opponent': ''.join(opponent_m).strip(),
				'result': ''.join(result_m).strip()
			}
			sys.stdout.write(".")
			sys.stdout.flush()
			team.load(game)

	print("\nWESTERN CONFERENCE\n")
	western_teams.sort(key=lambda team: (team.points, team.wins, team.goals_diff, team.goals_for, (10000 - team.disciplinary_points), team.away_goals_diff, team.away_goals_for, team.home_goals_diff, team.home_goals_for), reverse=True)
	for i, team in enumerate(western_teams):
		print(team)
		if i == 5:
			print("-" * 100)

	print("\nEASTERN CONFERENCE\n")
	eastern_teams.sort(key=lambda team: (team.points, team.wins, team.goals_diff, team.goals_for, (10000 - team.disciplinary_points), team.away_goals_diff, team.away_goals_for, team.home_goals_diff, team.home_goals_for), reverse=True)
	for i, team in enumerate(eastern_teams):
		print(team)
		if i == 5:
			print("-" * 100)





