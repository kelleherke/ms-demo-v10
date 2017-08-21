import unittest
import logging
import json
import string
import argparse
import os
import urllib
from httplib2 import Http

class TestZuulService(unittest.TestCase):

    def call_zuul_service(self):
         targetUri = "http://localhost:5555/routes"
         http_obj = Http(".cache")
         (resp, content) = http_obj.request(
         uri=targetUri,
         method='GET',
         headers={'Content-Type': 'application/json; charset=UTF-8', 'connection': 'close', 'Accept': 'application/json'})
         return resp,content


    def test_zuul_service_routes(self):
        (resp, content) = self.call_zuul_service()
        results = json.loads(content.decode("utf-8"))
        self.assertEqual(resp.status, 200)
        self.assertEquals("addressservice", results["/api/address/**"])
        self.assertEquals("pupilservice", results[ "/api/pupil/**"])
        self.assertEquals(2, len(results))


if __name__ == '__main__':
    print ("Running zuul service platform tests against container locahhost ip")
    unittest.main()
