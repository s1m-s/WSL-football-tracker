#import the request libraries sp that the client cna sent HTTP and SOAP requests to springboot
import requests 
#import xml tools used to construct the SOAP envelope 
import xml.etree.ElementTree as ET

#SOAP namespace required by SOAP envelopes 
SOAP_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/"
#namespace used by the football tracker SOAP service 
WSL_NAMESPACE = "http://wslfootballtracker.com"

ET.register_namespace("soapenv", SOAP_NAMESPACE)
ET.register_namespace("wsl", WSL_NAMESPACE)

#collect match information from the user
home_team = input("Home Team:  ")
away_team = input("Away Team:  ")
home_score= input("Home score:  ")
away_score = input("Away Score:  ")

#creating SoAP envelope that will be sent to the springboot SOAP endpoint - previous error couldnt create envelope

envelope = ET.Element(
    ET.QName(SOAP_NAMESPACE, "Envelope")
)

ET.SubElement(
    envelope, ET.QName(SOAP_NAMESPACE, "header")
)

body = ET.SubElement(
    envelope, ET.QName(SOAP_NAMESPACE, "Body")
)

match_request = ET.SubElement(
    body,ET.QName(WSL_NAMESPACE, "MatchRequest")
)

#create the matchrequest payload containing the matc hdetails entered by the user 
ET.SubElement(
    match_request, ET.QName(WSL_NAMESPACE,"homeTeam")
).text = home_team

ET.SubElement(
    match_request, ET.QName(WSL_NAMESPACE,"awayTeam")
).text = away_team

ET.SubElement(
    match_request, ET.QName(WSL_NAMESPACE,"homeScore")
).text = home_score

ET.SubElement(
    match_request, ET.QName(WSL_NAMESPACE,"awayScore")
).text = away_score

#converting XML tree into into a SOAP Request that can be sent over HTTP
soap_request = ET.tostring(
    envelope, encoding="utf-8", xml_declaration=True
)

#display the SOAP request for testing and debugging purposes
print("\nSOAP Request:\n")
print(soap_request.decode("utf-8"))

#send the SOAP request to the Springboot SOAP endpoint running locally 
response = requests.post(
    "http://localhost:8080/ws/",data=soap_request,headers={
        "Content-Type": "text/xml; charset=utf-8", "SOAPAction": ""
    },
    timeout=10
)

#displayes the http status code (helped sort errors throughtout coding)
print("\nResponse Status:")
print(response.status_code)

#displaye the SOAP response returned bby the springboot application 
print("\nResponse Body:")
print(response.text if response.text else"(empty)")



