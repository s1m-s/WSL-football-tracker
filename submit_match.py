import requests 
import xml.etree.ElementTree as ET

SOAP_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/"
WSL_NAMESPACE = "http://wslfootballtracker.com"

ET.register_namespace("soapenv", SOAP_NAMESPACE)
ET.register_namespace("wsl", WSL_NAMESPACE)

home_team = input("Home Team:  ")
away_team = input("Away Team:  ")
home_score= input("Home score:  ")
away_score = input("Away Score:  ")

#creating SoAP envelope - previous error couldnt create envelope

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

#converting XML tree into uft-8 bytes
soap_request = ET.tostring(
    envelope, encoding="utf-8", xml_declaration=True
)

print("\nSOAP Request:\n")
print(soap_request.decode("utf-8"))

response = requests.post(
    "http://localhost:8080/ws/",data=soap_request,headers={
        "Content-Type": "text/xml; charset=utf-8", "SOAPAction": ""
    },
    timeout=10
)


print("\nResponse Status:")
print(response.status_code)

print("\nResponse Body:")
print(response.text if response.text else"(empty)")



