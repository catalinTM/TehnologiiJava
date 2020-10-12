import grequests

URL = "http://localhost:8080/Lab1/generator"

PARAMS = {'letters': "ceva" }

#r = requests.get(url = URL, params = PARAMS)

#print(r.text)

async_requests = []
time = []

rs = [grequests.get(url = URL, params = PARAMS) for i in range(10)]

for request in grequests.map(rs):
    print(request.elapsed)
    #print(request.text)
