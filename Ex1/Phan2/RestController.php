<?php
require_once("RestHandler.php");

$ltA= "";
$lgA= "";
$ltB= "";
$lgB= "";
//location given with format lat,long Ex: 10.12334,199.2323
if(isset($_GET["a"])){
	$arr = explode(",",$_GET["a"]);
	if (sizeof($arr)==2){
		$ltA = $arr[0];
		$lgA = $arr[1];
	}
}
if(isset($_GET["b"])){
	$arr = explode(",",$_GET["b"]);
	if (sizeof($arr)==2){
		$ltB = $arr[0];
		$lgB = $arr[1];
	}
}

if(!empty($ltA)&!empty($ltB)){
	$restHandler = new RestHandler();
	$restHandler->getDistance($ltA,$lgA,$ltB,$lgB);
}
?>