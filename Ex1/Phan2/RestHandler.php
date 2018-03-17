<?php
require_once("SimpleRest.php");
ini_set("allow_url_fopen", 1);

class RestHandler extends SimpleRest {	
	public function encodeHtml($responseData) {
	
		$htmlResponse = "<table border='1'>";
		foreach($responseData as $key=>$value) {
    			$htmlResponse .= "<tr><td>". $key. "</td><td>". $value. "</td></tr>";
		}
		$htmlResponse .= "</table>";
		return $htmlResponse;		
	}
	
	public function encodeJson($responseData) {
		$jsonResponse = json_encode($responseData);
		return $jsonResponse;		
	}

	public function encodeXml($responseData) {
		// creating object of SimpleXMLElement
		$xml = new SimpleXMLElement('<?xml version="1.0"?><distance></distance>');
		foreach($responseData as $key=>$value) {
			$xml->addChild($key, $value);
		}
		return $xml->asXML();
	}

	public function getDistance($ltA,$lgA,$ltB,$lgB) {
		$json_url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins={$ltA},{$lgA}&destinations={$ltB},{$lgB}&key=AIzaSyAWlEIqkI-KanytyMIqdrr-7GFanQKlsmo"; 
		$crl = curl_init();
		curl_setopt($crl, CURLOPT_URL, $json_url);
		curl_setopt($crl, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($crl, CURLOPT_SSL_VERIFYPEER, FALSE); 
		$json = curl_exec($crl);
		curl_close($crl);
		$obj = json_decode($json, TRUE);
		$data = $obj["rows"][0]["elements"];

		if(!$data[0]["distance"]) {
			$statusCode = 404;
			$data = array('error' => 'Can\'t calculate distance!');		
		} else {
			$statusCode = 200;
			$data = array('distance' => $data[0]["distance"]["text"]);
		}

		$requestContentType = $_SERVER['HTTP_ACCEPT'];
		$this ->setHttpHeaders($requestContentType, $statusCode);
				
		if(strpos($requestContentType,'application/json') !== false){
			$response = $this->encodeJson($data);
			echo $response;
		} else if(strpos($requestContentType,'text/html') !== false){
			$response = $this->encodeHtml($data);
			echo $response;
		} else if(strpos($requestContentType,'application/xml') !== false){
			$response = $this->encodeXml($data);
			echo $response;
		}
	}
}
?>