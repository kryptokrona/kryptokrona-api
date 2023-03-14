import os
import requests
import time
from prettytable import PrettyTable
from termcolor import colored

os.system('clear')

def check_site(url):
    start_time = time.time()
    response = requests.get(url)
    elapsed_time = (time.time() - start_time) * 1000
    elapsed_time = round(elapsed_time) # Round elapsed time to nearest whole number.
    if response.status_code == 200:
        status_color = 'green'
    else:
        status_color = 'red'
    if elapsed_time > 2000: # If elapsed time is over 2000ms, make it red. If it's over 1000ms, make it yellow. Otherwise, leave it white.
        elapsed_time_color = 'red'
    elif elapsed_time > 1000:
        elapsed_time_color = 'yellow'
    else:
        elapsed_time_color = 'green'
    return (colored(url, 'magenta'), colored('OK', status_color), colored(elapsed_time, elapsed_time_color))

def check_node(node):
    start_time = time.time()
    response = requests.get(node)
    elapsed_time = (time.time() - start_time) * 1000
    elapsed_time = round(elapsed_time) # Round elapsed time to nearest whole number.
    if response.status_code == 200:
        data = response.json()
        status = data['status']
        status_color = 'green'
    else:
        status = response.status_code
        status_color = 'red'
    if elapsed_time > 2000: # If elapsed time is over 2000ms, make it red. If it's over 1000ms, make it yellow. Otherwise, leave it white.
        elapsed_time_color = 'red'
    elif elapsed_time > 1000:
        elapsed_time_color = 'yellow'
    else:
        elapsed_time_color = 'green'
    node_name = node[7:-8]
    return (colored(node_name, 'blue'), colored(status, status_color), colored(elapsed_time, elapsed_time_color))

# Kryptokrona related sites
urls = ['https://www.kryptokrona.org', 'https://docs.kryptokrona.org', 'https://explorer.kryptokrona.org', 'https://explorer.kryptokrona.se', 'https://cli.hugin.chat']

# The nodes from the explorer
nodes = ['http://blocksum.org:11898/getinfo', 'http://swepool.org:11898/getinfo', 'http://Tifo.info:11898/getinfo',
    'http://gota.kryptokrona.se:11898/getinfo', 'http://wasa.kryptokrona.se:11898/getinfo',
    'http://spider-pig.hopto.org:11898/getinfo', 'http://privacymine.net:11898/getinfo', 'http://privacymine.net:21898/getinfo',
    'http://106.12.131.174:11898/getinfo', 'http://norpool.org:11898/getinfo', 'http://115.239.210.250:11898/getinfo', 'http://182.43.80.115:11898/getinfo']

def main():
    # Create a table with columns for URL, status, and elapsed time
    table = PrettyTable(['URL/Node', 'Status', 'Elapsed Time (ms)'])

    for url in urls:
        result = check_site(url)
        table.add_row(result)

    for node in nodes:
        result = check_node(node)
        table.add_row(result)

    print(table)

main()
