"""This Python script provides examples on using the E*TRADE API endpoints"""
from __future__ import print_function
import json
import sys
import requests
from rauth import OAuth1Session
from market.market import Market

with open('.etrade-oauth.json', 'r') as f:
    info_deserialized = json.load(f)

session = OAuth1Session(**info_deserialized)
base_url = 'https://api.etrade.com'
market = Market(session, base_url)

for param in sys.argv[1:]:
    
    quotes = market.quotes(param)
    
    for stock in quotes:
        symbol = stock['Product']['symbol']
        
        chainDates = market.chainDates(symbol)
        
        chains = [{'Expiration': chainDate, **market.chains(symbol, {
                'expiryYear': chainDate['year'],
                'expiryMonth': chainDate['month'],
                'expiryDay': chainDate['day'],
                'includeWeekly': 'true',
                'priceType': 'ALL'
            })} for chainDate in chainDates]
            
        data = { 'stock': stock, 'options': chains }
        
        with open("../src/test/resources/etrade/%s.json" % symbol, "w") as f:
            f.write(json.dumps(data, indent=4))
            print('Wrote: ' + symbol)
            