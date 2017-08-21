import unittest
import logging
import json
import string
import argparse
import os
from httplib2 import Http

class TestConfigServer(unittest.TestCase):

    def call_config_service(self,serviceName,serviceEnv):
         targetUri = "http://localhost:8762/{}/{}".format(serviceName,serviceEnv)
         http_obj = Http(".cache")
         (resp, content) = http_obj.request(
         uri=targetUri,
         method='GET',
         headers={'Content-Type': 'application/json; charset=UTF-8', 'connection': 'close'})
         return resp,content


    def test_addressservice_default(self):
         http_obj = Http(".cache")
         (resp, content) = self.call_config_service("addressservice","default")
         results = json.loads(content.decode("utf-8"))
         self.assertEqual(resp.status, 200)
         self.assertEquals("classpath:config/addressservice/addressservice.yml",
                           results["propertySources"][0]["name"])

    def test_pupilservice_default(self):
         http_obj = Http(".cache")
         (resp, content) = self.call_config_service("pupilservice","default")
         results = json.loads(content.decode("utf-8"))
         self.assertEqual(resp.status, 200)
         self.assertEquals("classpath:config/pupilservice/pupilservice.yml",
                           results["propertySources"][0]["name"])


    def test_zuulservice_default(self):
         http_obj = Http(".cache")
         (resp, content) = self.call_config_service("zuulservice","default")
         results = json.loads(content.decode("utf-8"))
         self.assertEqual(resp.status, 200)
         self.assertEquals("classpath:config/zuulservice/zuulservice.yml",
                           results["propertySources"][0]["name"])


if __name__ == '__main__':
    print ("Running config service platform tests against localhost ip");
    unittest.main()
