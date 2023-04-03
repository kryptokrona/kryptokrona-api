import requests
import sys
from requests.exceptions import ConnectionError, Timeout, TooManyRedirects

try:
    domain = str(sys.argv[1])
    base_url = 'https://{}/{}'

    # trying posts endpoints
    print('GET ' + base_url.format(domain ,'api/v1/blocks'))
    posts = requests.get(base_url.format(domain, 'api/v1/blocks'))
    assert posts.status_code == requests.codes.ok

    # trying hashrate endpoints
    print('GET ' + base_url.format(domain, 'api/v1/hashrates'))
    posts = requests.get(base_url.format(domain, 'api/v1/hashrates'))
    assert posts.status_code == requests.codes.ok

    # trying output endpoints
    print('GET ' + base_url.format(domain, 'api/v1/outputs'))
    posts = requests.get(base_url.format(domain, 'api/v1/outputs'))
    assert posts.status_code == requests.codes.ok

    # trying transaction endpoints
    print('GET ' + base_url.format(domain, 'api/v1/transactions'))
    posts = requests.get(base_url.format(domain, 'api/v1/transactions'))
    assert posts.status_code == requests.codes.ok

    # trying prometheus metrics
    print('GET ' + base_url.format(domain, 'metrics'))
    prometheus_metrics = requests.get(base_url.format(domain, 'metrics'))
    assert prometheus_metrics.status_code == requests.codes.ok

    print('\nSMOKE TEST PASSED!')
except (AssertionError, ConnectionError, Timeout, TooManyRedirects):
    print('Smoke Test Failed!')
    sys.exit(1)